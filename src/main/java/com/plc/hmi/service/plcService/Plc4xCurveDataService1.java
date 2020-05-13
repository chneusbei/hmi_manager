package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

/***
 * 曲线信息-数据
 */
//@Service
//@Component
public class Plc4xCurveDataService1 extends Plc4xCurveDataService{
    public static String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode();

    public void setTagGroup() {
        super.setTagGroup(tagGroup);
    }
}
