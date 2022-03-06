package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;

/***
 * 曲线信息-数据
 */
//@Service
//@Component
public class Plc4xCurveDataService2 extends Plc4xCurveDataService{
    public static String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA_2.getCode();

    public void setTagGroup() {
        super.setTagGroup(tagGroup);
    }
}
