package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 曲线信息-数据
 */
@Service
@Component
public class Plc4xCurveStatusService extends Plc4xBaseService{
    public static final String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_STATUS.getCode();
    /**
     *  获取设备状态
     *  频率是每秒钟一次
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public List<PlcEntity> getDatas() {
       super.initQuereyList(tagGroup);
       return super.getDataByBuilder();
    }

    public void setDatas() {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put(PlcEntityEnum.curve_status_curve_recording.getCode(), "true");
        super.initWriteList(tagGroup, paraMap);
        super.setPlcData();
    }
}
