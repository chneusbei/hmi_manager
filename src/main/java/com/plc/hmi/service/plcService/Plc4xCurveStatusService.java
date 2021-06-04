package com.plc.hmi.service.plcService;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.CurveStatusEntity;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/***
 * 曲线信息-数据
 */
@Service
@Component
public class Plc4xCurveStatusService extends Plc4xBaseService {
    public static final String tagGroup = HmiConstants.PLC_TAG_GROUP.CURVE_STATUS.getCode();

    /**
     * 获取设备状态
     * 频率是每秒钟一次
     * 高频查询，需要先获得 PlcReadRequest.Builder
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

    /***
     * 获取PCL上曲线状态和基本信息
     * @param entityList
     * @return
     */
    public CurveStatusEntity getCurveStatus(List<PlcEntity> entityList) {
        CurveStatusEntity curveStatusEntity = new CurveStatusEntity();

        if (!CollectionUtils.isEmpty(entityList)) {
            return curveStatusEntity;
        }
        for (PlcEntity plcEntity : entityList) {
            if (null == plcEntity) {
                continue;
            }

            if (PlcEntityEnum.curve_status_motion_state1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //PLC曲线状态1
                curveStatusEntity.setMotionState1(HmiUtils.getIntValue(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_status_motion_state2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //PLC曲线状态2
                curveStatusEntity.setMotionState2(HmiUtils.getIntValue(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_status_data_length1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //曲线数量1
                curveStatusEntity.setDataLength1(HmiUtils.getIntValue(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_status_data_length2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //曲线数量2
                curveStatusEntity.setDataLength2(HmiUtils.getIntValue(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.equipment_operation_productNo.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //零件号
                curveStatusEntity.setProductId(HmiUtils.getLongValue(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_status_press_flag.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //正压反压
                curveStatusEntity.setPressFlag(HmiUtils.getBooleanValue(plcEntity.getValueOjb()) ? 1 : 0);
            } else if (PlcEntityEnum.curve_data_pressure_out_range.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压力是否超限
                curveStatusEntity.setPressureOutRange(HmiUtils.getBooleanValue(plcEntity.getValueOjb()) ? 1 : 0);
            } else if (PlcEntityEnum.curve_data_trace_code0.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode0(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode1(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode2(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode3(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode4(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code5.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode5(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code6.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode6(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code7.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode7(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code8.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode8(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code9.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode9(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code10.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode10(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code11.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode11(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code12.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode12(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code13.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode13(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code14.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode14(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code15.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode15(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code16.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode16(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code17.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode17(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code18.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode18(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code19.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode19(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code20.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode20(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code21.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode21(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code22.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode22(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code23.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode23(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code24.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode24(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code25.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode25(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code26.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode26(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code27.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode27(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code28.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode28(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code29.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode29(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code30.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode30(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code31.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCode31(HmiUtils.getStringNoNull(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.curve_data_trace_code_flag.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //追溯码
                curveStatusEntity.setTraceCodeFlag(HmiUtils.getBooleanValue(plcEntity.getValueOjb()));
            }

            curveStatusEntity.setHandleDate(HmiUtils.getYYYYMMDDString(curveStatusEntity.getCreateTime()));
            curveStatusEntity.setTraceCodeForce();
//        logger.info("CurveRecording = "+curveStatusEntity.getCurveRecording() + ", gPressFlag = "+curveStatusEntity.getPressFlag() );
        }
        return curveStatusEntity;
    }
}