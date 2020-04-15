package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import com.plc.hmi.service.StartRunService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SocketUtils;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 曲线信息-数据
 */
@Service
@Component
public class Plc4xCurveDataService extends Plc4xBaseService{
    private final Log logger = LogFactory.getLog(Plc4xCurveDataService.class);
    public static final String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode();
    public static Map<Long, List<PressureCurveEntity>> curveMap = new HashMap<Long, List<PressureCurveEntity>>();
    public static Long productNo;
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
        return curveMap.get(productNo);
    }

    /**
     * 获取实时曲线信息
     * @return
     */
    public void getCurveDatasFromPlc() {
        System.out.println("get data from plc >>>>>>>>>>>>>>>>>>>>>>>>");
//        List<PlcEntity> plcEntityList = this.getDatas();
//        PressureCurveEntity curve = plc2Curve(plcEntityList);
//        if(CollectionUtils.isEmpty(curveMap.get(curve.getPressDataId()))) {
//            //TODO
//            //需要将除当前当前零件外的其他信息全部入库并从map中去除。
//            curveMap.clear();
//            List<PressureCurveEntity>  curveEntityList = new ArrayList<PressureCurveEntity>();
//            curveEntityList.add(curve);
//            curveMap.put(curve.getPressDataId(),curveEntityList);
//        } else {
//            curveMap.get(curve.getPressDataId()).add(curve);
//        }
        try {
            //每20毫秒获取一次数据
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                }
            }

        }
        return curveEntity;
    }
}
