package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureCurveDao;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.util.HmiUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static java.lang.Boolean.TRUE;

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
    public void batchInsert(List<PressureCurveEntity> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            return;
        }
        //需要对应插入一条数据到pressure_data, 同时建立pressure_data和pressure_curve的关联关系
        /**
         *  1 插入pressure_data
         */
        InsertPressureDate(entityList);
        /**
         * 1 插入pressure_curve
         */
        pressureCurveDao.batchInsert(entityList);
    }

    private void InsertPressureDate(List<PressureCurveEntity> entityList) {
        //生成 RECORD_ID， 一个产品一次压装对应一个RECORD_ID,一个产品可以进行多次压装
        //RECORD_ID生成规则，产品ID+当前时间戳
        Long productId = entityList.get(0).getProductId();
        StringBuffer recordIdBuffer = new StringBuffer();
        recordIdBuffer.append(productId);
        recordIdBuffer.append(HmiUtils.getFormatDateString());
        String recordId = recordIdBuffer.toString();
        //获取最大压力及最大压力时位移。
        PressureCurveEntity MaxPresscurve = entityList.get(0);

        //获取公差窗口信息
        PressureProgramEntity pressureProgramEntity = programService.getErrandData(productId);
        //公差窗口计算结果
        Map<Integer, List<Boolean>> errandResltMap = new HashMap<Integer, List<Boolean>>();
        errandResltMap.put(1,new ArrayList<Boolean>());
        errandResltMap.put(2,new ArrayList<Boolean>());
        errandResltMap.put(3,new ArrayList<Boolean>());
        errandResltMap.put(4,new ArrayList<Boolean>());
        errandResltMap.put(5,new ArrayList<Boolean>());

        for(PressureCurveEntity curveEntity : entityList) {
            curveEntity.setRecordId(recordId);
            MaxPresscurve=MaxPresscurve.getPressForce().compareTo(curveEntity.getPressForce()) < 0 ? curveEntity : MaxPresscurve;
        }
        PressureDataEntity pressureDataEntity = new PressureDataEntity();
        pressureDataEntity.setProductId(productId);
        pressureDataEntity.setRecordId(recordId);
        pressureDataEntity.setPressResult("1");//需要从从公差窗口计算
        pressureDataEntity.setMaxPress(MaxPresscurve.getPressForce());
        pressureDataEntity.setPositionOfMaxPress(MaxPresscurve.getPosition());
        pressureDataEntity.setStartDate(new BigDecimal(HmiUtils.getMillFormatDateString(entityList.get(0).getCreateTime())));
        pressureDataEntity.setEndDate(new BigDecimal(HmiUtils.getMillFormatDateString(entityList.get(entityList.size()-1).getCreateTime())));
        pressureDataEntity.setCreateBy("SYS");
        pressureDataEntity.setUpdateBy("SYS");
        pressureDataEntity.setCreateTime(new Date());
        pressureDataEntity.setUpdateTime(new Date());
        pressureDataService.insert(pressureDataEntity);
    }

    /***
     * 根据公差窗口确定曲线是否压装是成功的
     */
    private void setCurveResultStatus( Map<Integer, List<Boolean>> errandResltMap, PressureCurveEntity curveEntity, PressureProgramEntity pressureProgramEntity) {
        if(pressureProgramEntity.getErrandType1()>=0) {
            setCurveResultStatus(errandResltMap,0, curveEntity, pressureProgramEntity.getErrandType1(),
                    pressureProgramEntity.getPositionMin1(), pressureProgramEntity.getPositionMax1(),
                    pressureProgramEntity.getPressMin1(), pressureProgramEntity.getPressMax1());
        } else  if(pressureProgramEntity.getErrandType2()>=0) {
            setCurveResultStatus(errandResltMap,1,curveEntity, pressureProgramEntity.getErrandType2(),
                    pressureProgramEntity.getPositionMin2(), pressureProgramEntity.getPositionMax2(),
                    pressureProgramEntity.getPressMin2(), pressureProgramEntity.getPressMax2());
        } else  if(pressureProgramEntity.getErrandType3()>=0) {
            setCurveResultStatus(errandResltMap,2,curveEntity, pressureProgramEntity.getErrandType3(),
                    pressureProgramEntity.getPositionMin3(), pressureProgramEntity.getPositionMax3(),
                    pressureProgramEntity.getPressMin3(), pressureProgramEntity.getPressMax3());
        } else  if(pressureProgramEntity.getErrandType4()>=0) {
            setCurveResultStatus(errandResltMap,3,curveEntity, pressureProgramEntity.getErrandType4(),
                    pressureProgramEntity.getPositionMin4(), pressureProgramEntity.getPositionMax4(),
                    pressureProgramEntity.getPressMin4(), pressureProgramEntity.getPressMax4());
        } else  if(pressureProgramEntity.getErrandType5()>=0) {
            setCurveResultStatus(errandResltMap,4,curveEntity, pressureProgramEntity.getErrandType5(),
                    pressureProgramEntity.getPositionMin5(), pressureProgramEntity.getPositionMax5(),
                    pressureProgramEntity.getPressMin5(), pressureProgramEntity.getPressMax5());
        }
    }

    private void setCurveResultStatus(Map<Integer, List<Boolean>> errandResltMap, int index, PressureCurveEntity curveEntity, int errandType,
                                      BigDecimal positionMin, BigDecimal positionMax,
                                      BigDecimal pressMin, BigDecimal pressMax) {
        switch(errandType) {
            case 0 :
                //最大位移窗口
                //成功条件：1.最大位移大于positionMin  2.最大位移小于positionMax
                if(errandResltMap.get(index).size() ==0) {
                    errandResltMap.get(index).add(false);
                    errandResltMap.get(index).add(true);
                }
                if(!errandResltMap.get(index).get(0)) {
                    if(curveEntity.getPosition().compareTo(positionMin) >=0) {
                       boolean b= errandResltMap.get(index).get(0) ;
                       b =true;
                    }
                }

                if(errandResltMap.get(index).get(1)) {
                    if(curveEntity.getPosition().compareTo(positionMax) >0) {
                        boolean b= errandResltMap.get(index).get(0) ;
//                        b =falsedddd;
                    }
                }

                break;
            case 1 :
                //最大压力窗口

                break;
            case 2 :
                //配合窗口

                break;
            case 3 :
                //右-下限制窗口

                break;
            case 4 :
                //穿越窗口

                break;
            case 5 :
                //峰值窗口

                break;
            case 6 :
                //左-上限制窗口

                break;
            case 7 :
                //右-上限制窗口

                break;
            case 8 :
                //顶部结束窗口

                break;
            case 9 :
                //右侧结束窗口

                break;
            case 10 :
                //平均值窗口

                break;
            case 11 :
                //拐点窗口

                break;
        }

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
