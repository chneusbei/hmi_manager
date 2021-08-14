package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
public class Plc4xTestService extends Plc4xBaseService{
    public static final String tagGroup ="test";

    /**
     *  获取设备状态
     *  频率是每秒钟一次
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public List<PlcEntity> getDatas() {
        super.initQuereyList(tagGroup);
        return super.getDataByBuilder(null);
    }
}
