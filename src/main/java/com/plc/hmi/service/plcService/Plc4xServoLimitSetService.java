package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import java.util.List;

/***
 * 设备信息--IO状态
 */
@Service
@Component
public class Plc4xServoLimitSetService extends Plc4xBaseService{
    public static final String tagGroup = HmiConstants.PLC_TAG_GROUP.SERVO_LIMIT_SET.getCode();
    /**
     *  获取设备状态
     *  频率是每秒钟一次
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public List<PlcEntity> getDatas() {
       super.initQuereyList(tagGroup);
       return super.getDataByBuilder();
    }

    public void setDatas(List<PlcEntity> queryList) {

    }

}
