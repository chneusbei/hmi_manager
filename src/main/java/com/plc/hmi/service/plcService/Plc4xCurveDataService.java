package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.util.HmiUtils;
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

    private final Log logger = LogFactory.getLog(Plc4xCurveDataService.class);
    public static String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode();
    public static Map<Long, List<PressureCurveEntity>> curveMap = new HashMap<Long, List<PressureCurveEntity>>();
    public static PressureCurveEntity currentCurve = new PressureCurveEntity();
    public static Long productNo=0L;
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
        if(!CollectionUtils.isEmpty(list)) {
//            System.out.println("size = "+list.size()+", totalTime="+(endTime-startTime) );
        } else {
//            System.out.println("curvel data is null" );
        }
        return curveMap.get(productNo);
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
        currentCurve = curveEntityList.get(0);
        if(CollectionUtils.isEmpty(curveEntityList)) {
            return;
        }
        /*
        if(curve.getCurveRecording()) {
//             System.out.println("getCurveRecording = true ******** ");
        } else {
//            System.out.println("getCurveRecording = false ");
        }
        */
        for(PressureCurveEntity curve : curveEntityList) {
            if (curve.getCurveRecording()) {
//                startTime = this.startTime > 0 ? startTime : System.currentTimeMillis();
//            System.out.println("******productNo="+curve.getPressDataId()+" , position="+curve.getPosition());
                productNo = curve.getProductId();
                if (CollectionUtils.isEmpty(curveMap.get(curve.getProductId()))) {
                    List<PressureCurveEntity> curveEntityList2 = new ArrayList<PressureCurveEntity>();
                    curveEntityList2.add(curve);
                    curveMap.put(curve.getProductId(), curveEntityList2);
                } else {
                    curveMap.get(curve.getProductId()).add(curve);
                }

            }
        }

        //数据入库
         if(!curveEntityList.get(0).getCurveRecording() && !curveEntityList.get(1).getCurveRecording()){
            //需要将除当前当前零件外的其他信息全部入库并从map中去除。
            if (!curveMap.isEmpty()) {
//                System.out.println("set curve data to batch insert thread*************************");
//                pressureCurveService.curve2queue(curveMap.get(productNo));
//                Iterator<Long> it=curveMap.keySet().iterator();
                for(Long recordId : curveMap.keySet()){
                    pressureCurveService.batchInsert(curveMap.get(recordId));
                }
                curveMap.clear();
            }
//                if (endTime == 0) {
//                    endTime = this.startTime > 0 ? System.currentTimeMillis() : endTime;
//                }
        }
//        System.out.println("every time spend time = "+(System.currentTimeMillis() - peerStartTime));

//        try {
//            //每20毫秒获取一次数据
//            Thread.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
        curveEntityList.add(curveEntity2);
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
                }else if(PlcEntityEnum.curve_status_curve_recording.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //启动标识
                    curveEntity.setCurveRecording(HmiUtils.getBooleanValue(plcEntity.getValueOjb()));
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
                }else if(PlcEntityEnum.curve_status_curve_recording2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //启动标识
                    curveEntity2.setCurveRecording(HmiUtils.getBooleanValue(plcEntity.getValueOjb()));
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
    public void setDatas(Map<String, String> paraMap) {
        super.initWriteList(tagGroup, paraMap);
        super.setPlcData();
    }


    public  void setTagGroup(String tagGroup) {
        this.tagGroup = tagGroup;
    }

    public  String getTagGroup() {
        return tagGroup;
    }
}
