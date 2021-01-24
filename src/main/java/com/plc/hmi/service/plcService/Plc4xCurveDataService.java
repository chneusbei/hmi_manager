package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.PropertyService;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/***
 * 曲线信息-数据
 */
@Service
@Component
public class Plc4xCurveDataService extends Plc4xBaseService{
    @Autowired
    private PressureCurveService pressureCurveService;
    @Autowired
    private PropertyService propertyService;

    private final Log logger = LogFactory.getLog(Plc4xCurveDataService.class);
    public static String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode();
    public static Map<Long, List<PressureCurveEntity>> curveMap = new HashMap<Long, List<PressureCurveEntity>>();
    public static PressureCurveEntity currentCurve = new PressureCurveEntity();
    public static Map<Long, List<PressureCurveEntity>> curveMap2 = new HashMap<Long, List<PressureCurveEntity>>();
    public static PressureCurveEntity currentCurve2 = new PressureCurveEntity();
    public static Long productNo=0L;
    public static Long productNo2=0L;
//    private static Long startTime= 0L;
//    private static Long endTime= 0L;
//    private static Long peerStartTime= 0L;

    /**
     * 获取实时单条曲线信息
     * @return
     */
    public PressureCurveEntity getCurrentCurve() {
        return currentCurve;
    }

    /**
     *  获取设备状态
     *  频率是每秒钟一次
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public synchronized List<PlcEntity> getDatas() {
        super.initQuereyList(tagGroup);
        return super.getDataByBuilder();
    }


    /**
     * 获取实时曲线信息
     * @return
     */
    public List<PressureCurveEntity> getCurveDatas() {
//        System.out.println("page  request received>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        List<PressureCurveEntity> list = curveMap.get(productNo);
//        if(!CollectionUtils.isEmpty(list)) {
//            System.out.println("size = "+list.size()+", totalTime="+(endTime-startTime) );
//        } else {
//            System.out.println("curvel data is null" );
//        }
        return list;
    }
    /**
     * 获取实时曲线2信息
     * @return
     */
    public List<PressureCurveEntity> getCurveDatas2() {
//        System.out.println("page  request received>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        List<PressureCurveEntity> list = curveMap2.get(productNo2);
//        List<PressureCurveEntity> list = curveMap.get(productNo);
//        if(!CollectionUtils.isEmpty(list)) {
//            System.out.println("size = "+list.size()+", totalTime="+(endTime-startTime) );
//        } else {
//            System.out.println("curvel data is null" );
//        }
        return list;
    }

    /**
     *
     */
    public synchronized void doHeartBeat() {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("systemFlagTag", "true");
        setDatas(paraMap);
    }

    /**
     * 获取实时曲线信息
     * @return
     */
    public synchronized void getCurveDatasFromPlc() {
//        System.out.println(">>>>>>>>>>>>>>>tagGroup = "+tagGroup);
//        peerStartTime = System.currentTimeMillis();
//        System.out.println("get data from plc >>>>>>>>>>>>>>>>>>>>>>>>");
        List<PlcEntity> plcEntityList = this.getDatas();
        List<PressureCurveEntity> curveEntityList = plc2Curve(plcEntityList);
        if(CollectionUtils.isEmpty(curveEntityList)) {
            return;
        } else {
//System.out.println("get curve from plc success....................................");
        }

        PressureCurveEntity curve =  curveEntityList.get(0);
        currentCurve = curve;
        if (curve.getCurveRecording()) {
//                startTime = this.startTime > 0 ? startTime : System.currentTimeMillis();
//            System.out.println("******productNo="+curve.getPressDataId()+" , position="+curve.getPosition());
            if (CollectionUtils.isEmpty(curveMap)) {
                //如果容器是空的， 说明是新曲线， 曲线id 递增。
                productNo ++;
            }
                curve.setRecordNo(productNo);
            if (CollectionUtils.isEmpty(curveMap.get(productNo))) {
                List<PressureCurveEntity> curveEntityList2 = new ArrayList<PressureCurveEntity>();
                curveEntityList2.add(curve);
                curveMap.put(productNo, curveEntityList2);
            } else {
                curveMap.get(productNo).add(curve);
            }
        }

        if(curveEntityList.size() ==2) {
            //双压头时
            PressureCurveEntity curve2 =  curveEntityList.get(1);
            currentCurve2 = curve2;
            if (curve2.getCurveRecording()) {
                if (CollectionUtils.isEmpty(curveMap2)) {
                    //如果容器是空的， 说明是新曲线， 曲线id 递增。
                    productNo2 ++;
                }
                curve2.setRecordNo(productNo2);
                if (CollectionUtils.isEmpty(curveMap2.get(productNo2))) {
                    List<PressureCurveEntity> curveEntityList2 = new ArrayList<PressureCurveEntity>();
                    curveEntityList2.add(curve2);
                    curveMap2.put(productNo2, curveEntityList2);
                } else {
                    curveMap2.get(productNo2).add(curve2);
                }
            }
        }



        /*
        if(curve.getCurveRecording()) {
//             System.out.println("getCurveRecording = true ******** ");
        } else {
//            System.out.println("getCurveRecording = false ");
        }
        */


        //数据入库
         if(!curveEntityList.get(0).getCurveRecording()){
            //需要将除当前当前零件外的其他信息全部入库并从map中去除。
             curve2DB(curveMap);
//                if (endTime == 0) {
//                    endTime = this.startTime > 0 ? System.currentTimeMillis() : endTime;
//                }
        }
        if(curveEntityList.size() ==2 && !curveEntityList.get(1).getCurveRecording()){
            //需要将除当前当前零件外的其他信息全部入库并从map中去除。
            //双曲线入库
            curve2DB(curveMap2);
        }
//        System.out.println("every time spend time = "+(System.currentTimeMillis() - peerStartTime));

//        try {
//            //每20毫秒获取一次数据
//            Thread.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    private void curve2DB(Map<Long, List<PressureCurveEntity>> map) {
        if (!map.isEmpty()) {
//                System.out.println("set curve data to batch insert thread*************************");
//                pressureCurveService.curve2queue(curveMap.get(productNo));
//                Iterator<Long> it=curveMap.keySet().iterator();
            ArrayList<Boolean> isOkList = null;
            List<PressureCurveEntity> entityList = null;
            for(Long recordId : map.keySet()){
                entityList = map.get(recordId);
                if(!CollectionUtils.isEmpty(entityList)) {
                   isOkList = pressureCurveService.batchInsert(entityList);
                }

            }
            // 数据入库后，修改PLC的OK/NOK/压装完成  三个变量
            setFlagAfterPressure(isOkList);
            map.clear();
        }
    }


    /**
     * 将PLC信息对象转换为曲线对象
     * @param plcEntityList
     * @return
     */
    private List<PressureCurveEntity> plc2Curve(List<PlcEntity> plcEntityList) {
        List<PressureCurveEntity> curveEntityList = new ArrayList<PressureCurveEntity>();
        PressureCurveEntity curveEntity = new PressureCurveEntity();
        PressureCurveEntity curveEntity2 = new PressureCurveEntity();
        curveEntityList.add(curveEntity);
        if(propertyService.isDubblePress()) {
            curveEntityList.add(curveEntity2);
        }

        if(!CollectionUtils.isEmpty(plcEntityList)) {
            for(PlcEntity plcEntity : plcEntityList) {
                if(null == plcEntity) {
                    continue;
                }
                /*支持双压头
                 */
                //第一条曲线
                if(PlcEntityEnum.curve_data_curPosition.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //当前位置
                    curveEntity.setPosition(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_curForce.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    // 当前压力	Real
                    curveEntity.setPressForce(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_curSpeed.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //当前速度	Real
                    curveEntity.setSpeed(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                    curveEntity.setCurSpeed(curveEntity.getSpeed());
                } else if(PlcEntityEnum.curve_data_reserve0.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_0
                    curveEntity.setReserve0(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_reserve1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_1
                    curveEntity.setReserve1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_reserve2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_2
                    curveEntity.setReserve2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.equipment_operation_productNo.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //零件号
                    curveEntity.setProductId(HmiUtils.getLongValue(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_status_curve_recording.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //启动标识
                    curveEntity.setCurveRecording(HmiUtils.getBooleanValue(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_status_press_flag.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //正压反压
                    curveEntity.setPressFlag(HmiUtils.getBooleanValue(plcEntity.getValueOjb()) ? 1 : 0);
                } else if(PlcEntityEnum.curve_data_pressure_out_range.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //压力是否超限
                    curveEntity.setPressureOutRange(HmiUtils.getBooleanValue(plcEntity.getValueOjb()) ? 1 : 0);
                } else if(PlcEntityEnum.curve_data_trace_code0.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode0(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode1(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode2(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode3(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode4(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code5.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode5(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code6.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode6(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code7.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode7(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code8.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode8(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code9.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode9(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code10.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode10(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code11.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode11(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code12.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode12(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code13.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode13(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code14.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode14(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code15.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode15(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code16.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode16(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code17.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode17(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code18.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode18(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code19.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode19(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code20.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode20(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code21.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode21(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code22.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode22(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code23.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode23(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                }else if(PlcEntityEnum.curve_data_trace_code24.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode24(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code25.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode25(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code26.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode26(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code27.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode27(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code28.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode28(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                }  else if(PlcEntityEnum.curve_data_trace_code29.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode29(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code30.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode30(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_trace_code31.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCode31(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
                } else if (PlcEntityEnum.curve_data_trace_code_flag.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //追溯码
                    curveEntity.setTraceCodeFlag(HmiUtils.getBooleanValue(plcEntity.getValueOjb()));
                }


                //第二个压头
                if(PlcEntityEnum.curve_data_curPosition2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //当前位置
                    curveEntity2.setPosition(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_curForce2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    // 当前压力	Real
                    curveEntity2.setPressForce(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_curSpeed2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //当前速度	Real
                    curveEntity2.setSpeed(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                    curveEntity2.setCurSpeed(curveEntity2.getSpeed());
                } else if(PlcEntityEnum.curve_data_reserve0_2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_0
                    curveEntity2.setReserve0(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_reserve1_2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_1
                    curveEntity2.setReserve1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_data_reserve2_2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_2
                    curveEntity2.setReserve2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.equipment_operation_productNo2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //零件号
                    curveEntity2.setProductId(HmiUtils.getLongValue(plcEntity.getValueOjb()));
                    curveEntity2.setRecordNo(HmiUtils.getLongValue(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_status_curve_recording2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //启动标识
                    curveEntity2.setCurveRecording(HmiUtils.getBooleanValue(plcEntity.getValueOjb()));
                } else if(PlcEntityEnum.curve_status_press_flag2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //正压反压
                    curveEntity2.setPressFlag(HmiUtils.getBooleanValue(plcEntity.getValueOjb()) ? 1 : 0);
                } else if(PlcEntityEnum.curve_data_pressure_out_range2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //压力是否超限
                    curveEntity2.setPressureOutRange(HmiUtils.getBooleanValue(plcEntity.getValueOjb())  ? 1 : 0);
                }
            }

        }
        //第一个压头
        curveEntity.setIsDeleted("0");
        curveEntity.setCreateBy("SYS");
        curveEntity.setUpdateBy("SYS");
        curveEntity.setCreateTime(new Date());
        curveEntity.setUpdateTime(new Date());
        curveEntity.setHandleDate(HmiUtils.getYYYYMMDDString(curveEntity.getCreateTime()));
        curveEntity.setSolidLine(true);
        curveEntity.setErrant(false);
        curveEntity.setPressureHeadNo(1);
        if(null == curveEntity.getCurveRecording()) {
            curveEntity.setCurveRecording(false);
        }
        curveEntity.setTraceCodeForce();
        //第二个压头
        curveEntity2.setIsDeleted("0");
        curveEntity2.setCreateBy("SYS");
        curveEntity2.setUpdateBy("SYS");
        curveEntity2.setCreateTime(new Date());
        curveEntity2.setUpdateTime(new Date());
        curveEntity2.setHandleDate(HmiUtils.getYYYYMMDDString(curveEntity2.getCreateTime()));
        curveEntity2.setSolidLine(true);
        curveEntity2.setErrant(false);
        curveEntity2.setPressureHeadNo(2);
        if(null == curveEntity2.getCurveRecording()) {
            curveEntity2.setCurveRecording(false);
        }
        curveEntity2.setTraceCode(curveEntity.getTraceCode());
//        logger.info("CurveRecording = "+curveEntity.getCurveRecording() + ", gPressFlag = "+curveEntity.getPressFlag() );
        return curveEntityList;
    }


    /**
     * 获取实时曲线信息+公差窗口
     * @return
     */
    public List<List<PressureCurveEntity>> getCurveDatasAndErrand() {
//        System.out.println("page  request received>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        List<PressureCurveEntity> list = curveMap.get(productNo);
        if(!CollectionUtils.isEmpty(list)) {
//            System.out.println("size = "+list.size()+", totalTime="+(endTime-startTime) );
        } else {

        }
//        return curveMap.get(productNo);
        return null;
    }

    /**
     * 设置曲线开始开关
     */
    public synchronized void setDatas(Map<String, String> paraMap) {
        super.initWriteList(tagGroup, paraMap);
        super.setPlcData();
    }

    /**
     * 压装结束后，设置曲线状态，OK /NOK/压装完成
     */
    public synchronized void setFlagAfterPressure(ArrayList<Boolean> isOkList) {
        Map<String, String> paraMap = new HashMap<>();
        if(CollectionUtils.isEmpty(isOkList)) {
            return;
        }
        boolean  isOk = isOkList.get(0);

        if(isOk) {
            paraMap.put("ok", "true");
            paraMap.put("nok", "false");
        } else {
            paraMap.put("ok", "false");
            paraMap.put("nok", "true");
        }

        paraMap.put("finish", "true");

        if(isOkList.size() == 2) {
            boolean isOK2 = isOkList.get(1);
            if(isOK2) {
                paraMap.put("ok2", "true");
                paraMap.put("nok2", "false");
            } else {
                paraMap.put("ok2", "false");
                paraMap.put("nok2", "true");
            }
            paraMap.put("finish2", "true");
        }

        super.initWriteList(HmiConstants.PLC_TAG_GROUP.CURVE_STATUS.getCode(), paraMap);
        super.setPlcData();


//        super.initQuereyList(HmiConstants.PLC_TAG_GROUP.CURVE_STATUS.getCode());
//        List<PlcEntity>  status = super.getDataByBuilder();
    }


    public  void setTagGroup(String tagGroup) {
        this.tagGroup = tagGroup;
    }

    public  String getTagGroup() {
        return tagGroup;
    }
}
