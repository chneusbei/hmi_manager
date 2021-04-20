package com.plc.hmi.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.dao.PressureCurveDao;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * 压力曲线服务
 */
@Service
@Component
public class PressureCurveService extends AbstractBaseService{
    @Resource
    PressureCurveDao pressureCurveDao;
    @Resource
    PressureDataService pressureDataService;
    @Resource
    PressureProgramService programService;
    @Resource
    private PropertyService propertyService;

    private final Log logger = LogFactory.getLog(PressureCurveService.class);
    //用户存储实时PLC曲线信息。线程安全的链表容器
    private static java.util.concurrent.ConcurrentLinkedDeque<PressureCurveEntity>
            synCurveDeque =  new java.util.concurrent.ConcurrentLinkedDeque<PressureCurveEntity>();;

    /**
     * 获取产品压力曲线信息
     * @param recordId
     * @return
     */
    public List<PressureCurveEntity> getHisDateByCode(Long  recordId) {
        return pressureCurveDao.getCurveData(recordId);
    }

    /**
     * 获取产品压力曲线信息
     * @param
     * @return
     */
    public List<PressureCurveEntity> getHisDate(String recordId, int pressureHeadNo) {
        return pressureCurveDao.getPressureCurveWithDate(recordId, pressureHeadNo);
    }

//    public List<PressureCurveEntity> getPressureCurveWithDate(String handleDate) {
//        return pressureCurveDao.getPressureCurveWithDate(handleDate);
//    }
//
//    public List<PressureCurveEntity> getPressureCurveWithDateStart(String handleDate) {
//        return pressureCurveDao.getPressureCurveWithDateStart(handleDate);
//    }

    public void insertList(List<PressureCurveEntity> entityList) {
        if(entityList != null) {
            for (PressureCurveEntity entity : entityList) {
                this.insert(entity);
            }
        }
    }

    public void insert(PressureCurveEntity entity) {
        pressureCurveDao.insert(entity);
    }

    /**
     * 批量入库
     * @param entityList
     */
    public ArrayList<Boolean> batchInsert(List<PressureCurveEntity> entityList) {
        //需要对应插入一条数据到pressure_data, 同时建立pressure_data和pressure_curve的关联关系
        /**
         *  1 插入pressure_data
         *  如果是双压头， 需要将数据拆分到两个list中
         */
        ArrayList<Boolean> isOkList = new ArrayList<Boolean>();
        if(propertyService.isDubblePress()) {
            List<PressureCurveEntity> pressureHeadList1 = new ArrayList();
            List<PressureCurveEntity> pressureHeadList2 = new ArrayList();
            for(PressureCurveEntity entity : entityList) {
                if(entity.getPressureHeadNo() == 1) {
                    pressureHeadList1.add(entity);
                } else if(entity.getPressureHeadNo() == 2){
                    pressureHeadList2.add(entity);
                }

            }
            boolean isOk1 = true;
            if(!CollectionUtils.isEmpty(pressureHeadList1)) {
                isOk1 = InsertPressureDate(pressureHeadList1, "1");
                System.out.println(">>>>>>>>>>>>>>>>第一个压头的压装结果"+isOk1);
            }

            boolean isOk2 = true;
            if(!CollectionUtils.isEmpty(pressureHeadList2)) {
                isOk2 = InsertPressureDate(pressureHeadList2, "2");
                System.out.println(">>>>>>>>>>>>>>>>第二个压头的压装结果"+isOk2);
            }
            isOkList.add(isOk1);
            isOkList.add(isOk2);
        } else {
            boolean isOk = InsertPressureDate(entityList, "1");
            isOkList.add(isOk);
        }
        /**
         * 1 插入pressure_curve
         */
        pressureCurveDao.batchInsert(entityList);
        return isOkList;
    }

    private boolean InsertPressureDate(List<PressureCurveEntity> entityList, String pressHeadNo) {
        boolean isOk = true;
        //生成 RECORD_ID， 一个产品一次压装对应一个RECORD_ID,一个产品可以进行多次压装
        //RECORD_ID生成规则，产品ID+当前时间戳
        Long productId = entityList.get(0).getProductId();
        StringBuffer recordIdBuffer = new StringBuffer();
        recordIdBuffer.append(productId);
        recordIdBuffer.append(HmiUtils.getFormatDateString());
        String recordId = recordIdBuffer.toString();
        //获取最大压力及最大压力时位移。
        PressureCurveEntity MaxPressure = entityList.get(0);
        //公差窗口计算结果List
        List<ErrandResultEntity> errandResultList = new ArrayList<ErrandResultEntity>();
        //判断是否压装时压力超限
        PressureCurveEntity lastCurveEntity = entityList.get(entityList.size()-1);
//        boolean isPressureOutRange = false;
        boolean showErrand = HmiUtils.getBooleanValue(propertyService.getProperty(ConfigConstants.ERRAND_USE_FLAG));
        boolean hasErrand = true;
        if(lastCurveEntity.getPressureOutRange() != 0) {
            //压力超限， 直接判断压装失败
            logger.info("压力超限， 压装失败, id="+lastCurveEntity.getRecordId()
                    +", productCode=" + lastCurveEntity.getProductCode()
                    + " productId= "+lastCurveEntity.getProductId()
                    + " RecordNo= "+lastCurveEntity.getRecordNo()
            );
//            isPressureOutRange = true;
        } else if(showErrand && hasErrand) {
            //压力未超限， 判断公差窗口
            //获取公差窗口信息
            String programCode = HmiUtils.getProgrameCode(pressHeadNo, HmiUtils.getString(entityList.get(0).getProductId()));
            PressureProgramEntity pressureProgramEntity = programService.getErrandData(programCode);
            hasErrand = setErrandResultList(pressureProgramEntity, errandResultList);

            int i = 0;
            for (PressureCurveEntity curveEntity : entityList) {
                i++;
                curveEntity.setRecordId(recordId);
                MaxPressure = MaxPressure.getPressForce().compareTo(curveEntity.getPressForce()) < 0 ? curveEntity : MaxPressure;
                //判断公差窗口

                setCurveResultStatus(errandResultList, curveEntity, i == entityList.size() ? true : false);
            }
        }
        PressureDataEntity pressureDataEntity = new PressureDataEntity();
        pressureDataEntity.setProductId(productId);
        pressureDataEntity.setRecordId(recordId);
        if(lastCurveEntity.getPressureOutRange() != 0) {
            //压力超限， 直接判断压装失败
            isOk= false;
            pressureDataEntity.setPressResult("0");//压力超限 1成功 0 失败
        } else {
            if(showErrand && hasErrand) {
                isOk = isCurveSuccess(errandResultList);
            } else {
                isOk=true;
            }
            pressureDataEntity.setPressResult(isOk ? "1" : "0");//需要从从公差窗口计算
        }
        pressureDataEntity.setMaxPress(MaxPressure.getPressForce());
        pressureDataEntity.setPositionOfMaxPress(MaxPressure.getPosition());
        pressureDataEntity.setStartDate(new BigDecimal(HmiUtils.getMillFormatDateString(entityList.get(0).getCreateTime())));
        pressureDataEntity.setEndDate(new BigDecimal(HmiUtils.getMillFormatDateString(entityList.get(entityList.size()-1).getCreateTime())));
        pressureDataEntity.setCreateBy("SYS");
        pressureDataEntity.setUpdateBy("SYS");
        pressureDataEntity.setCreateTime(new Date());
        pressureDataEntity.setUpdateTime(new Date());
        pressureDataEntity.setPressureHeadNo(entityList.get(0).getPressureHeadNo());
        pressureDataService.insert(pressureDataEntity);
        return isOk;
    }



    /***
     * 根据公差窗口确定曲线是否压装是成功的
     */
    private void setCurveResultStatus(List<ErrandResultEntity> errandResultList, PressureCurveEntity curveEntity, boolean isLastOne) {
        if(errandResultList.size()==0) {
            return;
        }
       for(ErrandResultEntity errandResultEntity : errandResultList) {
           setCurveResultStatus(errandResultEntity, curveEntity, isLastOne);
       }
    }

    private void setCurveResultStatus(ErrandResultEntity errandResultEntity, PressureCurveEntity curveEntity, boolean isLastOne) {
        switch(errandResultEntity.getErrandType()) {
            case 0 :
                //最大位移窗口
                //成功条件：1.最大位移大于positionMin  2.最大位移小于positionMax
                //判断是否达到最小位移点
//                if(!errandResultEntity.isMinPositonSucess()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >= 0) {
                        errandResultEntity.setMinPositionFlag(true);
                    }
//                }

                //判断是否超过最大位移点
//                if(errandResultEntity.isMaxPositonSucess()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) > 0) {
                        errandResultEntity.setMaxPositionSuccess(false);
                        break;
                    }
//                }
                break;
            case 1 :
                //最大压力窗口
                //成功条件：1.最大压力大于等于pressMin  2.最大压力小于等于positionMax

                //判断是否达到最小压力点
//                if(!errandResultEntity.isMinPressSucess()) {
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) >=0) {
                        errandResultEntity.setMinPressFlag(true);
                    }
//                }
                //判断是否超过最大压力点
//                if(errandResultEntity.isMaxPressSucess()) {
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >0) {
                        errandResultEntity.setMaxPressSuccess(false);
                        break;
                    }
//                }
                break;
            case 2 :
                //配合窗口
                /*
                  测量曲线必须完全通过力窗口，不能碰到窗口上下边
                  1.未达到对应的位置失败
                  2.未穿越最大位移失败
                  3.穿越期间如果碰到上下压力位置，失败
                 */
                //判断是否达到最小位移点
//                if(!errandResultEntity.isMinPositonSucess()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0) {
                        errandResultEntity.setMinPositionFlag(true);
                    }
//                }

                //判断是否达到最大位移点
//                if(!errandResultEntity.isMaxPositonSucess()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) > 0) {
                        errandResultEntity.setMaxPositionFlag(true);
                    }
//                }

                //位置在最小位移和最大位移中间时才做判断
                if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0
                    && curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) <=0
                    //&& errandResultEntity.isMinPressSucess() && errandResultEntity.isMaxPressSucess()
                ) {
                    //判断是否大于最小压力
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) < 0) {
                        errandResultEntity.setMinPressSuccess(false);
                        break;
                    }

                    //判断是否小于最大压力
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) > 0) {
                        errandResultEntity.setMaxPressSuccess(false);
                        break;
                    }
                }
                break;
            case 3 :
                //右-下限制窗口
                 //穿过右边实线场景，存在BUG，会判定成功
                /*
                  曲线必须在窗口下限以上通过窗口，不能碰到窗口右边界。在窗口中至少找到一个测量点
                  1.未达到最小位移 失败
                  2.大于最小位移， 小于最大位移时，必须大于最小压力
                  3.在窗口期间必须有大于最大压力的值
                  4.必须有小于最小位移的点
                  5.如果达到最大位移点， 压力要大于最大压力
                 */
                //判断是否有小于最小位移点
//                if(!errandResultEntity.isHasPointBeforeMinPosition()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) < 0) {
                        errandResultEntity.setMinPositionFlag(true);
                    }
//                }


                //位置在最小位移和最大位移中间时才做判断
                if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0
                        && curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) <=0
                       // && errandResultEntity.isMinPressSucess()
                ) {
                    //判断是否大于最大压力
                    /*
                    if(!errandResultEntity.isMaxPressSucess()) {
                        if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >= 0) {
                            errandResultEntity.setMaxPressSucess(true);
                        }
                    }
                    */
                    //判断是否大于最小压力
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) <= 0) {
                        errandResultEntity.setMinPressSuccess(false);
                        break;
                    }

                }

                break;
            case 4 :
                //穿越窗口
                //不支持， 默认全是成功
                break;
            case 5 :
                //峰值窗口
                // 只测试了NOK场景
                /**
                 * 测量曲线必须在窗口上边界以下通过，不能碰到窗口左边界和右边界。曲线必须在
                 *  窗口外部开始和结束。在窗口里至少找到一个测量点。
                 *  1.必须存在最小位移之前的点
                 *  2.必须存在最大位位移之后的点
                 *  3.最大最小位移之间的点， 必须存在大于最小压力的点
                 *  4.最大最小位移之间的点， 不能存在大于最大压力的点
                 */
                /*
                //判断是否有小于最小位移点
                if(!errandResultEntity.isHasPointBeforeMinPosition()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) < 0) {
                        errandResultEntity.setHasPointBeforeMinPosition(true);
                    }
                }

                //判断是否有大于最大位移点
                if(!errandResultEntity.isHasPointAfterMaxPosition()) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) > 0) {
                        errandResultEntity.setHasPointAfterMaxPosition(true);
                    }
                }
                */
                //位置在最小位移和最大位移中间时才做判断
                if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0
                        && curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) <=0
                       // && errandResultEntity.isMaxPressSucess()
                ) {
                    if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) == 0
                    || curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) ==0) {
                        //最小位移点时 //最大位移点时
                        if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) > 0){
                            errandResultEntity.setMaxPressSuccess(false);
                            break;
                        }
                    }

                    //判断是否大于最大压力
                   // if(errandResultEntity.isMaxPressSucess()) {
                        if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >= 0) {
                            errandResultEntity.setMaxPressSuccess(false);
                            break;
                        }
                   // }

                    //判断是否大于最小压力
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) > 0) {
                        errandResultEntity.setMinPressFlag(true);
                    }

                }

                break;
            case 6 :
                //左-上限制窗口
                //穿过左侧实现场景，存在BUG
                /*
                 曲线必须在窗口上边界下面通过，不能碰到窗口左边界。曲线可在窗口内开始或结
                束。在窗口中必须至少找到一个测量点
                1.必须到达最小位置和最大位置之间
                2.不能超过最大压力点
                 */
                //位置在最小位移和最大位移中间时才做判断
                if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0
                        && curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) <=0
//                        && errandResultEntity.isMaxPressSucess()
                ) {
                    //设置进入箱体成功
//                    if(!errandResultEntity.isMinPositonSucess()) {
                        if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) > 0) {
                            errandResultEntity.setMinPressFlag(true);
                        }

//                    }
                    //判断是否大于最大压力
//                    if(errandResultEntity.isMaxPressSucess()) {
                        if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >= 0) {
                            errandResultEntity.setMaxPressSuccess(false);
                            break;
                        }
//                    }
                }
                break;
            case 7 :
                //右-上限制窗口
                //不支持， 默认全是成功
                break;
            case 8 :
                //顶部结束窗口
                /*
                曲线必须在窗口内结束，曲线必须在窗口上边界下通过，并且不能碰到窗口左右边
                界。曲线必须在窗口外部开始，至少一个测量点必须在窗口内找到
                1.需要有在箱体之外的点， 位置小于最小位置， 或者位置在最小最大位移之间，但是小于等于最小压力
                2.最后一个点一定要在箱体里。只判断最有一个点
                 */
                //1 判断是否有箱体外面的点, 条件：大于等于最小位移，小于最大位移，且小于小压力
                if(curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0
                        && curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) < 0
                       //&& curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) < 0
                ) {
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) < 0) {
                        errandResultEntity.setMinPositionFlag(true);
                    }
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) > 0) {
                        errandResultEntity.setMaxPressSuccess(false);
                        break;
                    }
                }
                //2 判断最后一个点是否符合要求, 条件 ：大于等于最小位移、小于等于最大位移、大于等于最小压力，小于等于最大压力
                // 最后一个点不是最高点，是0点， 所有最后一个点的判断都不对
                if(isLastOne) {
                    if (curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) <= 0
                            || curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) >= 0
                            || curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) <= 0
                            || curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >= 0
                    ) {
                        errandResultEntity.setMaxPressSuccess(false);
                        break;
                    }
                }
                break;
            case 9 :
                //右侧结束窗口
                // 最后一个点不是最高点，是0点， 所有最后一个点的判断都不对
                /*
                曲线必须在窗口上下边界通过，且不碰到窗口右边界。在窗口里必须找到一个测量点，曲线必须在窗口内结束
                1.不能触碰上下压力边界
                2.在箱体内部结束
                 */
                //1 在最大最小位移点中间时， 必须小于最大压力， 大于最小压力
                if(//errandResultEntity.isMinPressSucess()  &&
                        curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) >=0
                        && curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) < 0
                ) {
                    if(!errandResultEntity.isMinPositionFlag()) {
                        errandResultEntity.setMinPositionFlag(true);
                    }
                    if(curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) <= 0
                            || curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >= 0) {
                        errandResultEntity.setMinPressSuccess(false);
//                        System.out.println("左进右不出判断失败 position="+curveEntity.getPosition()
//                                +"， PositionMin=" + errandResultEntity.getPositionMin()
//                                +"， PositionMax=" + errandResultEntity.getPositionMax()
//                                +"， PressForce=" + curveEntity.getPressForce()
//                                +"， PressMin=" + errandResultEntity.getPressMin()
//                                +"， PressMax=" + errandResultEntity.getPressMax()
//                        );
                        break;
                    }
                }


                //2 判断最后一个点是否符合要求, 条件 ：大于等于最小位移、小于等于最大位移、大于等于最小压力，小于等于最大压力
                /*
                if(isLastOne) {
                    if (curveEntity.getPosition().compareTo(errandResultEntity.getPositionMin()) <= 0
                            || curveEntity.getPosition().compareTo(errandResultEntity.getPositionMax()) >= 0
                           || curveEntity.getPressForce().compareTo(errandResultEntity.getPressMin()) <= 0
                                || curveEntity.getPressForce().compareTo(errandResultEntity.getPressMax()) >= 0) {
                            errandResultEntity.setMaxPressSucess(false);
                            break;
                    }
                }
                 */
                break;
            case 10 :
                //平均值窗口
                //不支持， 默认全是成功

                break;
            case 11 :
                //拐点窗口
                //不支持， 默认全是成功

                break;
        }

    }

    /**
     * 初始化公差窗口判定结果对象
     * @param pressureProgramEntity
     * @param errandResltList
     */
    private boolean setErrandResultList(PressureProgramEntity pressureProgramEntity, List<ErrandResultEntity> errandResltList){
        boolean  hasErrand = false;
        if (pressureProgramEntity == null) {
            return false;
        }
        if(pressureProgramEntity.getErrandType1()>=0) {
            hasErrand =true;
            ErrandResultEntity errandResultEntity1 = new ErrandResultEntity();
            errandResultEntity1.setErrandType(pressureProgramEntity.getErrandType1());
            errandResultEntity1.setPositionMax(pressureProgramEntity.getPositionMax1());
            errandResultEntity1.setPositionMin(pressureProgramEntity.getPositionMin1());
            errandResultEntity1.setPressMax(pressureProgramEntity.getPressMax1());
            errandResultEntity1.setPressMin(pressureProgramEntity.getPressMin1());
            errandResltList.add(errandResultEntity1);
        } else  if(pressureProgramEntity.getErrandType2()>=0) {
            hasErrand =true;
            ErrandResultEntity errandResultEntity2 = new ErrandResultEntity();
            errandResultEntity2.setErrandType(pressureProgramEntity.getErrandType2());
            errandResultEntity2.setPositionMax(pressureProgramEntity.getPositionMax2());
            errandResultEntity2.setPositionMin(pressureProgramEntity.getPositionMin2());
            errandResultEntity2.setPressMax(pressureProgramEntity.getPressMax2());
            errandResultEntity2.setPressMin(pressureProgramEntity.getPressMin2());
            errandResltList.add(errandResultEntity2);
        } else  if(pressureProgramEntity.getErrandType3()>=0) {
            hasErrand =true;
            ErrandResultEntity errandResultEntity3 = new ErrandResultEntity();
            errandResultEntity3.setErrandType(pressureProgramEntity.getErrandType3());
            errandResultEntity3.setPositionMax(pressureProgramEntity.getPositionMax3());
            errandResultEntity3.setPositionMin(pressureProgramEntity.getPositionMin3());
            errandResultEntity3.setPressMax(pressureProgramEntity.getPressMax3());
            errandResultEntity3.setPressMin(pressureProgramEntity.getPressMin3());
            errandResltList.add(errandResultEntity3);
        } else  if(pressureProgramEntity.getErrandType4()>=0) {
            hasErrand =true;
            ErrandResultEntity errandResultEntity4 = new ErrandResultEntity();
            errandResultEntity4.setErrandType(pressureProgramEntity.getErrandType4());
            errandResultEntity4.setPositionMax(pressureProgramEntity.getPositionMax4());
            errandResultEntity4.setPositionMin(pressureProgramEntity.getPositionMin4());
            errandResultEntity4.setPressMax(pressureProgramEntity.getPressMax4());
            errandResultEntity4.setPressMin(pressureProgramEntity.getPressMin4());
            errandResltList.add(errandResultEntity4);
        } else  if(pressureProgramEntity.getErrandType5()>=0) {
            hasErrand =true;
            ErrandResultEntity errandResultEntity5 = new ErrandResultEntity();
            errandResultEntity5.setErrandType(pressureProgramEntity.getErrandType5());
            errandResultEntity5.setPositionMax(pressureProgramEntity.getPositionMax5());
            errandResultEntity5.setPositionMin(pressureProgramEntity.getPositionMin5());
            errandResultEntity5.setPressMax(pressureProgramEntity.getPressMax5());
            errandResultEntity5.setPressMin(pressureProgramEntity.getPressMin5());
            errandResltList.add(errandResultEntity5);
        }

        //设置公差窗口默认值
        for(ErrandResultEntity entity : errandResltList) {
            if(0==entity.getErrandType()) {
                //最大位移窗口
                entity.setMinPositionFlag(false);
            } else if(1==entity.getErrandType()) {
                //最大压力窗口
                entity.setMinPressFlag(false);
            } else if(2==entity.getErrandType()) {
                //配合窗口
                entity.setMinPositionFlag(false);
                entity.setMaxPositionFlag(false);
            } else if(3==entity.getErrandType()) {
                //右-下限制窗口
                entity.setMinPositionFlag(false);
//                entity.setMaxPressSucess(false);
            } else if(5==entity.getErrandType()) {
                //峰值窗口
//                entity.setHasPointBeforeMinPosition(false);
//                entity.setHasPointAfterMaxPosition(false);
                entity.setMinPressFlag(false);
            } else if(6==entity.getErrandType()) {
                //左-上限制窗口
                entity.setMinPressFlag(false);
            } else if(8==entity.getErrandType()) {
                //顶部结束窗口
                entity.setMinPositionFlag(false);
            } else if(9==entity.getErrandType()) {
                //右侧结束窗口
                entity.setMinPositionFlag(false);
            }
        }
        return hasErrand;
    }

    /**
     * 判断曲线是否压装成功
     * @param errandResltList
     * @return
     */
    private boolean isCurveSuccess(List<ErrandResultEntity> errandResltList) {
        boolean result = true;
        boolean minPositionFlag = false;
        boolean maxPositionFlag = false;
        boolean minPressFlag = false;
        boolean maxPressFlag = false;
        for(ErrandResultEntity entity : errandResltList) {
            /*
            if(!entity.isMinPositonSucess() || !entity.isMaxPositonSucess()
                    ||!entity.isMinPressSucess() || !entity.isMaxPressSucess()) {
                result = false;
                break;
            }
            if(!hasPointAfterMaxPosition && entity.isHasPointAfterMaxPosition()) {
                hasPointAfterMaxPosition = true;
            }
            if(!hasPointBeforeMinPosition && entity.isHasPointBeforeMinPosition() ) {
                hasPointBeforeMinPosition = true;
            }
*/
            if (0==entity.getErrandType()) {
                //最大位移窗口
                if(!entity.isMaxPositionSuccess()) {
                    return false;
                }

                if (!minPositionFlag && entity.isMinPositionFlag()) {
                    minPositionFlag = true;
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }
//                entity.setMinPositonSucess(false);
            } else if (1==entity.getErrandType()) {
                //最大压力窗口
                if(!entity.isMaxPressSuccess()) {
                    return false;
                }

                if (!minPressFlag && entity.isMinPressFlag()) {
                    minPositionFlag = true;
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }
//                entity.setMinPressSucess(false);
            } else if (2==entity.getErrandType()) {
                //配合窗口
                if(! entity.isMinPressSuccess() || !entity.isMaxPressSuccess()) {
                    return false;
                }

                if (!minPositionFlag && entity.isMinPositionFlag()) {
                    minPositionFlag = true;
                }
                if (!maxPositionFlag && entity.isMaxPositionFlag()) {
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }
//                entity.setMinPositonSucess(false);
//                entity.setMaxPositonSucess(false);
            } else if (3==entity.getErrandType()) {
                //右-下限制窗口
//                entity.setMinPositonSucess(false);
//                entity.setHasPointBeforeMinPosition(false);
//                entity.setMaxPressSucess(false);
                if(!entity.isMinPressSuccess()) {
                    return false;
                }

                if (!minPositionFlag && entity.isMinPositionFlag()) {
                    minPositionFlag = true;
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }

            } else if (5==entity.getErrandType()) {
                //峰值窗口
                if(!entity.isMaxPressSuccess()) {
                    return false;
                }

                if(!minPressFlag && entity.isMinPressFlag() ) {
                    minPositionFlag = true;
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }
//                entity.setMinPressSucess(false);
            } else if (6==entity.getErrandType()) {
                //左-上限制窗口
                if(!entity.isMaxPressSuccess()) {
                    return false;
                }

                if(!minPressFlag && entity.isMinPressFlag() ) {
                    minPositionFlag = true;
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }
//                entity.setMinPressSucess(false);
            } else if (8==entity.getErrandType()) {
                //顶部结束窗口
                if(!entity.isMaxPressSuccess()) {
                    return false;
                }

                if(!minPositionFlag && entity.isMinPositionFlag() ) {
                    minPositionFlag = true;
                    maxPositionFlag = true;
                    minPressFlag = true;
                    maxPressFlag = true;
                }
//                entity.setHasPointBeforeMinPosition(false);
            } else if (9==entity.getErrandType()) {
                //右侧结束窗口
                if(!entity.isMinPressSuccess()) {
                    return false;
                }
                minPositionFlag = true;
                maxPositionFlag = true;
                minPressFlag = true;
                maxPressFlag = true;
            }
        }

        if(!result) {
            return false;
        }
        if(!minPositionFlag || !maxPositionFlag
                || !minPressFlag || !maxPressFlag) {
            result = false;
        }

        return result;
    }

    /**
     * 实时数据加入入库队列
     * @param entityList
     */
    public void curve2queue(List<PressureCurveEntity> entityList) {
        synCurveDeque.addAll(entityList);
        System.out.println("add data to queue  size :" + entityList.size());
    }


    /**
     * 自动批量入库
     */
    public void autoBatchInsert() {
        System.out.println("批量插入操作。。。。。。。。。。。。。。。。。。。。。 sie = " + synCurveDeque.size());
        if(!synCurveDeque.isEmpty()) {
            List<PressureCurveEntity> entityList = new ArrayList<PressureCurveEntity>();
            while(!synCurveDeque.isEmpty()) {
                entityList.add(synCurveDeque.removeFirst());
                if(entityList.size() ==50 ||synCurveDeque.size() ==0) {
                    this.batchInsert(entityList);
                    entityList.clear();
                }
            }
        }
    }


}
