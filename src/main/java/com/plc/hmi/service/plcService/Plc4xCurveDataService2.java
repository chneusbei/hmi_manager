package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
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
//@Service
//@Component
public class Plc4xCurveDataService2 extends Plc4xCurveDataService{
    public static String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_DATA_2.getCode();

    public void setTagGroup() {
        super.setTagGroup(tagGroup);
    }
}
