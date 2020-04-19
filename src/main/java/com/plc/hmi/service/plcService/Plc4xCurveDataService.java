package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import com.plc.hmi.service.PressureCurveService;
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
    public static final String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode();
    public static Map<Long, List<PressureCurveEntity>> curveMap = new HashMap<Long, List<PressureCurveEntity>>();
    public static Long productNo=0L;
    private static Long startTime= 0L;
    private static Long endTime= 0L;
    private static Long peerStartTime= 0L;

    /**
     *  获取设备状态
     *  频率是每秒钟一次
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public List<PlcEntity> getDatas() {
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
    public void getCurveDatasFromPlc() {
        peerStartTime = System.currentTimeMillis();
//        System.out.println("get data from plc >>>>>>>>>>>>>>>>>>>>>>>>");
        List<PlcEntity> plcEntityList = this.getDatas();
        PressureCurveEntity curve = plc2Curve(plcEntityList);
        if(curve.getCurveRecording()) {
//             System.out.println("getCurveRecording = true ******** ");
        } else {
//            System.out.println("getCurveRecording = false ");
        }
        if(curve.getCurveRecording()) {
            startTime =  this.startTime > 0 ? startTime : System.currentTimeMillis();
//            System.out.println("******productNo="+curve.getPressDataId()+" , position="+curve.getPosition());
            productNo = curve.getPressDataId();
           if(CollectionUtils.isEmpty(curveMap.get(curve.getPressDataId()))) {
               List<PressureCurveEntity>  curveEntityList = new ArrayList<PressureCurveEntity>();
               curveEntityList.add(curve);
               curveMap.put(curve.getPressDataId(),curveEntityList);
            }else {
               curveMap.get(curve.getPressDataId()).add(curve);
           }

        } else {
            //需要将除当前当前零件外的其他信息全部入库并从map中去除。
            if(!curveMap.isEmpty()) {
//                System.out.println("set curve data to batch insert thread*************************");
//                pressureCurveService.curve2queue(curveMap.get(productNo));
                pressureCurveService.batchInsert(curveMap.get(productNo));
                curveMap.clear();
            }
            if(endTime ==0) {
                endTime = this.startTime > 0 ? System.currentTimeMillis() : endTime;
            }
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
    private PressureCurveEntity plc2Curve(List<PlcEntity> plcEntityList) {
        PressureCurveEntity curveEntity = new PressureCurveEntity();
        if(!CollectionUtils.isEmpty(plcEntityList)) {
            for(PlcEntity plcEntity : plcEntityList) {
                if(PlcEntityEnum.curve_data_curPosition.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //当前位置
                    curveEntity.setPosition(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if(PlcEntityEnum.curve_data_curForce.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    // 当前压力	Real
                    curveEntity.setPressForce(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if(PlcEntityEnum.curve_data_curSpeed.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //当前速度	Real
                    curveEntity.setSpeed(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if(PlcEntityEnum.curve_data_reserve0.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_0
                    curveEntity.setReserve0(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if(PlcEntityEnum.curve_data_reserve1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_1
                    curveEntity.setReserve1(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if(PlcEntityEnum.curve_data_reserve2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //预留_2
                    curveEntity.setReserve2(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if(PlcEntityEnum.equipment_operation_productNo.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //零件号
                    curveEntity.setPressDataId(new Long(plcEntity.getValueOjb().toString()));
                }else if(PlcEntityEnum.curve_status_curve_recording.getCode().equalsIgnoreCase(plcEntity.getName())) {
                    //零件号
                    curveEntity.setCurveRecording(new Boolean(plcEntity.getValueOjb().toString()));
                }

            }

        }
        curveEntity.setIsDeleted("0");
        curveEntity.setCreateBy("SYS");
        curveEntity.setUpdateBy("SYS");
        curveEntity.setCreateTime(new Date());
        curveEntity.setUpdateTime(new Date());
        return curveEntity;
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
    public void setDatas() {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("choice", "true");
        super.initWriteList(tagGroup, paraMap);
        super.setPlcData();
    }

}
