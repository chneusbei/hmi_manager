package com.plc.hmi.enumeration;

import java.math.BigDecimal;

public enum PlcEntityEnum {
    //设备信息-操作
    equipment_operation_productNo("productNo", "零件号"),
    //曲线信息-状态
    curve_status_curve_recording("curveRecording", "曲线记录开始"),
    //曲线信息-正压反压
    curve_status_press_flag("pressFlag", "正压反压"),

    //曲线信息-数据 curve_data
    curve_data_curPosition("curPosition", "当前位置"),
    curve_data_curForce("curForce", "当前压力"),
    curve_data_curSpeed("curSpeed", "当前速度"),
    curve_data_reserve0("reserve0", "预留_0"),
    curve_data_reserve1("reserve1", "预留_1"),
    curve_data_reserve2("reserve2", "预留_2"),
    curve_data_pressure_out_range("pressureOutRange", "压力是否超限"),
    curve_data_trace_code_flag("traceCodeFlag", "扫码枪启用"),
    curve_data_trace_code0("traceCode0", "追溯码"),
    curve_data_trace_code1("traceCode1", "追溯码"),
    curve_data_trace_code2("traceCode2", "追溯码"),
    curve_data_trace_code3("traceCode3", "追溯码"),
    curve_data_trace_code4("traceCode4", "追溯码"),
    curve_data_trace_code5("traceCode5", "追溯码"),
    curve_data_trace_code6("traceCode6", "追溯码"),
    curve_data_trace_code7("traceCode7", "追溯码"),
    curve_data_trace_code8("traceCode8", "追溯码"),
    curve_data_trace_code9("traceCode9", "追溯码"),
    curve_data_trace_code10("traceCode10", "追溯码"),
    curve_data_trace_code11("traceCode11", "追溯码"),
    curve_data_trace_code12("traceCode12", "追溯码"),
    curve_data_trace_code13("traceCode13", "追溯码"),
    curve_data_trace_code14("traceCode14", "追溯码"),
    curve_data_trace_code15("traceCode15", "追溯码"),
    curve_data_trace_code16("traceCode16", "追溯码"),
    curve_data_trace_code17("traceCode17", "追溯码"),
    curve_data_trace_code18("traceCode18", "追溯码"),
    curve_data_trace_code19("traceCode19", "追溯码"),
    curve_data_trace_code20("traceCode20", "追溯码"),
    curve_data_trace_code21("traceCode21", "追溯码"),
    curve_data_trace_code22("traceCode22", "追溯码"),
    curve_data_trace_code23("traceCode23", "追溯码"),
    curve_data_trace_code24("traceCode24", "追溯码"),
    curve_data_trace_code25("traceCode25", "追溯码"),
    curve_data_trace_code26("traceCode26", "追溯码"),
    curve_data_trace_code27("traceCode27", "追溯码"),
    curve_data_trace_code28("traceCode28", "追溯码"),
    curve_data_trace_code29("traceCode29", "追溯码"),
    curve_data_trace_code30("traceCode30", "追溯码"),
    curve_data_trace_code31("traceCode31", "追溯码"),

    //PLC曲线状态1
    curve_status_motion_state1("motionState1", "PLC曲线状态1"),
    //PLC曲线状态2
    curve_status_motion_state2("motionState2", "PLC曲线状态2"),
    //曲线数量1 dataLength1
    curve_status_data_length1("dataLength1", "曲线数量1"),
    //曲线数量2 dataLength2
    curve_status_data_length2("dataLength2", "曲线数量2"),
    /*
    第二条曲线
     */
    //设备信息-操作
    equipment_operation_productNo2("productNo2", "零件号"),
    //曲线信息-状态
    curve_status_curve_recording2("curveRecording2", "曲线记录开始"),
    //曲线信息-正压反压
    curve_status_press_flag2("pressFlag", "正压反压"),


    //曲线信息-数据 curve_data
    curve_data_curPosition2("curPosition2", "当前位置"),
    curve_data_curForce2("curForce2", "当前压力"),
    curve_data_curSpeed2("curSpeed2", "当前速度"),
    curve_data_reserve0_2("reserve0_2", "预留_0"),
    curve_data_reserve1_2("reserve1_2", "预留_1"),
    curve_data_reserve2_2("reserve2_2", "预留_2"),
    curve_data_pressure_out_range2("pressureOutRange", "压力是否超限"),

    //新温度信息
    temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature1("p1LowSpeedAxisEccentricCopperSleeveTemperature1", "压机1低速轴偏心铜套温度检测1"),
    temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature2("p1LowSpeedAxisEccentricCopperSleeveTemperature2", "压机1低速轴偏心铜套温度检测2"),
    temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature3("p1LowSpeedAxisEccentricCopperSleeveTemperature3", "压机1低速轴偏心铜套温度检测3"),
    temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature4("p1LowSpeedAxisEccentricCopperSleeveTemperature4", "压机1低速轴偏心铜套温度检测4"),
    temperature_data_p1HighSpeedAxisEccentricCopperSleeveTemperature1("p1HighSpeedAxisEccentricCopperSleeveTemperature1", "高速轴偏心铜套温度检测1"),
    temperature_data_p1HighSpeedAxisEccentricCopperSleeveTemperature2("p1HighSpeedAxisEccentricCopperSleeveTemperature2", "高速轴偏心铜套温度检测2"),
    temperature_data_p1FlywheelSupportBigAxisTemperature1("p1FlywheelSupportBigAxisTemperature1", "压机1飞轮支撑大轴承温度检测1"),
    temperature_data_p1FlywheelSupportBigAxisTemperature2("p1FlywheelSupportBigAxisTemperature2", "压机1飞轮支撑大轴承温度检测2"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature1("drawbarBothEndsCopperSleeveTemperature1", "压机1拉杆两端铜套温度检测1"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature2("drawbarBothEndsCopperSleeveTemperature2", "压机1拉杆两端铜套温度检测2"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature3("drawbarBothEndsCopperSleeveTemperature3", "压机1拉杆两端铜套温度检测3"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature4("drawbarBothEndsCopperSleeveTemperature4", "压机1拉杆两端铜套温度检测4"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature5("drawbarBothEndsCopperSleeveTemperature5", "压机1拉杆两端铜套温度检测5"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature6("drawbarBothEndsCopperSleeveTemperature6", "压机1拉杆两端铜套温度检测6"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature7("drawbarBothEndsCopperSleeveTemperature7", "压机1拉杆两端铜套温度检测7"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature8("drawbarBothEndsCopperSleeveTemperature8", "压机1拉杆两端铜套温度检测8"),
    temperature_data_smallBeltWheelSupportAxisTemperature1("smallBeltWheelSupportAxisTemperature1", "压机1小皮带轮支持轴承温度检测1"),
    temperature_data_smallBeltWheelSupportAxisTemperature2("smallBeltWheelSupportAxisTemperature2", "压机1小皮带轮支持轴承温度检测2"),
    temperature_data_p2HighSpeedAxisRollingBearingTemperature1("p2HighSpeedAxisRollingBearingTemperature1", "压机2高速轴滚动轴承温度检测1"),
    temperature_data_p2HighSpeedAxisRollingBearingTemperature2("p2HighSpeedAxisRollingBearingTemperature2", "压机2高速轴滚动轴承温度检测2"),
    temperature_data_p2FlywheelSupportBigAxisTemperature1("p2FlywheelSupportBigAxisTemperature1", "压机2飞轮支撑大轴承温度检测1"),
    temperature_data_p2FlywheelSupportBigAxisTemperature2("p2FlywheelSupportBigAxisTemperature2", "压机2飞轮支撑大轴承温度检测2"),
    temperature_data_p3HighSpeedAxisRollingBearingTemperature1("p3HighSpeedAxisRollingBearingTemperature1", "压机3高速轴滚动轴承温度检测1"),
    temperature_data_p3HighSpeedAxisRollingBearingTemperature2("p3HighSpeedAxisRollingBearingTemperature2", "压机3高速轴滚动轴承温度检测2"),
    temperature_data_p3FlywheelSupportBigAxisTemperature1("p3FlywheelSupportBigAxisTemperature1", "压机3飞轮支撑大轴承温度检测1"),
    temperature_data_p3FlywheelSupportBigAxisTemperature2("p3FlywheelSupportBigAxisTemperature2", "压机3飞轮支撑大轴承温度检测2"),
    temperature_data_p4HighSpeedAxisRollingBearingTemperature1("p4HighSpeedAxisRollingBearingTemperature1", "压机4高速轴滚动轴承温度检测1"),
    temperature_data_p4HighSpeedAxisRollingBearingTemperature2("p4HighSpeedAxisRollingBearingTemperature2", "压机4高速轴滚动轴承温度检测2"),
    temperature_data_p4FlywheelSupportBigAxisTemperature1("p4FlywheelSupportBigAxisTemperature1", "压机4飞轮支撑大轴承温度检测1"),
    temperature_data_p4FlywheelSupportBigAxisTemperature2("p4FlywheelSupportBigAxisTemperature2", "压机4飞轮支撑大轴承温度检测2"),
    temperature_data_p5HighSpeedAxisRollingBearingTemperature1("p5HighSpeedAxisRollingBearingTemperature1", "压机5高速轴滚动轴承温度检测1"),
    temperature_data_p5HighSpeedAxisRollingBearingTemperature2("p5HighSpeedAxisRollingBearingTemperature2", "压机5高速轴滚动轴承温度检测2"),
    temperature_data_p5FlywheelSupportBigAxisTemperature1("p5FlywheelSupportBigAxisTemperature1", "压机5飞轮支撑大轴承温度检测1"),
    temperature_data_p5FlywheelSupportBigAxisTemperature2("p5FlywheelSupportBigAxisTemperature2", "压机5飞轮支撑大轴承温度检测2"),
    temperature_data_backupTemperature0("backupTemperature0", "备用温度检测0"),
    temperature_data_backupTemperature1("backupTemperature1", "备用温度检测1"),
    temperature_data_backupTemperature2("backupTemperature2", "备用温度检测2"),
    temperature_data_backupTemperature3("backupTemperature3", "备用温度检测3"),
    temperature_data_backupTemperature4("backupTemperature4", "备用温度检测4"),
    temperature_data_backupTemperature5("backupTemperature5", "备用温度检测5"),
    temperature_data_backupTemperature6("backupTemperature6", "备用温度检测6"),
    temperature_data_backupTemperature7("backupTemperature7", "备用温度检测7"),
    temperature_data_backupTemperature8("backupTemperature8", "备用温度检测8"),
    temperature_data_backupTemperature9("backupTemperature9", "备用温度检测9"),
    temperature_data_backupTemperature10("backupTemperature10", "备用温度检测10"),
    temperature_data_backupTemperature11("backupTemperature1", "备用温度检测11"),
    temperature_data_temperatureWarningValue1("temperatureWarningValue1", "温度警戒值1"),
    temperature_data_temperatureWarningValue2("temperatureWarningValue2", "温度警戒值2"),
    temperature_data_p1WirelessLeftFrontTripodEccentricBigCopperSleeve("p1WirelessLeftFrontTripodEccentricBigCopperSleeve", "压机1无线温度:左前三脚架偏心大铜套"),
    temperature_data_p1WirelessLeftRearTripodEccentricBigCopperSleeve("p1WirelessLeftRearTripodEccentricBigCopperSleeve", "压机1无线温度:左后三脚架偏心大铜套"),
    temperature_data_p1WirelessRightFrontTripodEccentricBigCopperSleeve("p1WirelessRightFrontTripodEccentricBigCopperSleeve", "压机1无线温度:右前三脚架偏心大铜套"),
    temperature_data_p1WirelessRightRearTripodEccentricBigCopperSleeve("p1WirelessRightRearTripodEccentricBigCopperSleeve", "压机1无线温度:右后三脚架偏心大铜套"),
    temperature_data_p1WirelessLeftFrontTripodBottomCopperSleeve("p1WirelessLeftFrontTripodBottomCopperSleeve", "压机1无线温度:左前三脚架下端铜套"),
    temperature_data_p1WirelessLeftRearTripodBottomCopperSleeve("p1WirelessLeftRearTripodBottomCopperSleeve", "压机1无线温度:左后三脚架下端铜套"),
    temperature_data_p1WirelessRightFrontTripodBottomCopperSleeve("p1WirelessRightFrontTripodBottomCopperSleeve", "压机1无线温度:右前三脚架下端铜套"),
    temperature_data_p1WirelessRightRearTripodBottomCopperSleeve("p1WirelessRightRearTripodBottomCopperSleeve", "压机1无线温度:右后三脚架下端铜套"),
    temperature_data_p2WirelessLeftFrontConnectingRodBigCopperSleeve("p2WirelessLeftFrontConnectingRodBigCopperSleeve", "压机2无线温度:左前连杆大铜套"),
    temperature_data_p2WirelessLeftRearConnectingRodBigCopperSleeve("p2WirelessLeftRearConnectingRodBigCopperSleeve", "压机2无线温度:左后连杆大铜套"),
    temperature_data_p2WirelessRightFrontConnectingRodBigCopperSleeve("p2WirelessRightFrontConnectingRodBigCopperSleeve", "压机2无线温度:右前连杆大铜套"),
    temperature_data_p2WirelessRightRearConnectingRodBigCopperSleeve("p2WirelessRightRearConnectingRodBigCopperSleeve", "压机2无线温度:右后连杆大铜套"),
    temperature_data_p2WirelessLeftFrontLowSpeedAxisCopperSleeve("p2WirelessLeftFrontLowSpeedAxisCopperSleeve", "压机2无线温度:左前低速轴铜套"),
    temperature_data_p2WirelessLeftRearLowSpeedAxisCopperSleeve("p2WirelessLeftRearLowSpeedAxisCopperSleeve", "压机2无线温度:左后低速轴铜套"),
    temperature_data_p2WirelessRightFrontLowSpeedAxisCopperSleeve("p2WirelessRightFrontLowSpeedAxisCopperSleeve", "压机2无线温度:右前低速轴铜套"),
    temperature_data_p2WirelessRightRearLowSpeedAxisCopperSleeve("p2WirelessRightRearLowSpeedAxisCopperSleeve", "压机2无线温度:右后低速轴铜套"),
    temperature_data_p3WirelessLeftFrontConnectingRodBigCopperSleeve("p3WirelessLeftFrontConnectingRodBigCopperSleeve", "压机3无线温度:左前连杆大铜套"),
    temperature_data_p3WirelessLeftRearConnectingRodBigCopperSleeve("p3WirelessLeftRearConnectingRodBigCopperSleeve", "压机3无线温度:左后连杆大铜套"),
    temperature_data_p3WirelessRightFrontConnectingRodBigCopperSleeve("p3WirelessRightFrontConnectingRodBigCopperSleeve", "压机3无线温度:右前连杆大铜套"),
    temperature_data_p3WirelessRightRearConnectingRodBigCopperSleeve("p3WirelessRightRearConnectingRodBigCopperSleeve", "压机3无线温度:右后连杆大铜套"),
    temperature_data_p3WirelessLeftFrontLowSpeedAxisCopperSleeve("p3WirelessLeftFrontLowSpeedAxisCopperSleeve", "压机3无线温度:左前低速轴铜套"),
    temperature_data_p3WirelessLeftRearLowSpeedAxisCopperSleeve("p3WirelessLeftRearLowSpeedAxisCopperSleeve", "压机3无线温度:左后低速轴铜套"),
    temperature_data_p3WirelessRightFrontLowSpeedAxisCopperSleeve("p3WirelessRightFrontLowSpeedAxisCopperSleeve", "压机3无线温度:右前低速轴铜套"),
    temperature_data_p3WirelessRightRearLowSpeedAxisCopperSleeve("p3WirelessRightRearLowSpeedAxisCopperSleeve", "压机3无线温度:右后低速轴铜套"),
    temperature_data_p4WirelessLeftFrontConnectingRodBigCopperSleeve("p4WirelessLeftFrontConnectingRodBigCopperSleeve", "压机4无线温度:左前连杆大铜套"),
    temperature_data_p4WirelessLeftRearConnectingRodBigCopperSleeve("p4WirelessLeftRearConnectingRodBigCopperSleeve", "压机4无线温度:左后连杆大铜套"),
    temperature_data_p4WirelessRightFrontConnectingRodBigCopperSleeve("p4WirelessRightFrontConnectingRodBigCopperSleeve", "压机4无线温度:右前连杆大铜套"),
    temperature_data_p4WirelessRightRearConnectingRodBigCopperSleeve("p4WirelessRightRearConnectingRodBigCopperSleeve", "压机4无线温度:右后连杆大铜套"),
    temperature_data_p4WirelessLeftFrontLowSpeedAxisCopperSleeve("p4WirelessLeftFrontLowSpeedAxisCopperSleeve", "压机4无线温度:左前低速轴铜套"),
    temperature_data_p4WirelessLeftRearLowSpeedAxisCopperSleeve("p4WirelessLeftRearLowSpeedAxisCopperSleeve", "压机4无线温度:左后低速轴铜套"),
    temperature_data_p4WirelessRightFrontLowSpeedAxisCopperSleeve("p4WirelessRightFrontLowSpeedAxisCopperSleeve", "压机4无线温度:右前低速轴铜套"),
    temperature_data_p4WirelessRightRearLowSpeedAxisCopperSleeve("p4WirelessRightRearLowSpeedAxisCopperSleeve", "压机4无线温度:右后低速轴铜套"),
    temperature_data_p5WirelessLeftFrontConnectingRodBigCopperSleeve("p5WirelessLeftFrontConnectingRodBigCopperSleeve", "压机5无线温度:左前连杆大铜套"),
    temperature_data_p5WirelessLeftRearConnectingRodBigCopperSleeve("p5WirelessLeftRearConnectingRodBigCopperSleeve", "压机5无线温度:左后连杆大铜套"),
    temperature_data_p5WirelessRightFrontConnectingRodBigCopperSleeve("p5WirelessRightFrontConnectingRodBigCopperSleeve", "压机5无线温度:右前连杆大铜套"),
    temperature_data_p5WirelessRightRearConnectingRodBigCopperSleeve("p5WirelessRightRearConnectingRodBigCopperSleeve", "压机5无线温度:右后连杆大铜套"),
    temperature_data_p5WirelessLeftFrontLowSpeedAxisCopperSleeve("p5WirelessLeftFrontLowSpeedAxisCopperSleeve", "压机5无线温度:左前低速轴铜套"),
    temperature_data_p5WirelessLeftRearLowSpeedAxisCopperSleeve("p5WirelessLeftRearLowSpeedAxisCopperSleeve", "压机5无线温度:左后低速轴铜套"),
    temperature_data_p5WirelessRightFrontLowSpeedAxisCopperSleeve("p5WirelessRightFrontLowSpeedAxisCopperSleeve", "压机5无线温度:右前低速轴铜套"),
    temperature_data_p5WirelessRightRearLowSpeedAxisCopperSleeve("p5WirelessRightRearLowSpeedAxisCopperSleeve", "压机5无线温度:右后低速轴铜套");

    //温度信息
    /*
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature1("lowSpeedAxisEccentricCopperSleeveTemperature1", "低速轴偏心铜套温度检测1"),
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature2("lowSpeedAxisEccentricCopperSleeveTemperature2", "低速轴偏心铜套温度检测2"),
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature3("lowSpeedAxisEccentricCopperSleeveTemperature3", "低速轴偏心铜套温度检测3"),
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature4("lowSpeedAxisEccentricCopperSleeveTemperature4", "低速轴偏心铜套温度检测4"),
    temperature_data_highSpeedAxisEccentricCopperSleeveTemperature1("highSpeedAxisEccentricCopperSleeveTemperature1", "高速轴偏心铜套温度检测1"),
    temperature_data_highSpeedAxisEccentricCopperSleeveTemperature2("highSpeedAxisEccentricCopperSleeveTemperature2", "高速轴偏心铜套温度检测2"),
    temperature_data_flywheelSupportBigAxisTemperature1("flywheelSupportBigAxisTemperature1", "飞轮支撑大轴承温度检测1"),
    temperature_data_flywheelSupportBigAxisTemperature2("flywheelSupportBigAxisTemperature2", "飞轮支撑大轴承温度检测2"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature1("drawbarBothEndsCopperSleeveTemperature1", "拉杆两端铜套温度检测1"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature2("drawbarBothEndsCopperSleeveTemperature2", "拉杆两端铜套温度检测2"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature3("drawbarBothEndsCopperSleeveTemperature3", "拉杆两端铜套温度检测3"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature4("drawbarBothEndsCopperSleeveTemperature4", "拉杆两端铜套温度检测4"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature5("drawbarBothEndsCopperSleeveTemperature5", "拉杆两端铜套温度检测5"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature6("drawbarBothEndsCopperSleeveTemperature6", "拉杆两端铜套温度检测6"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature7("drawbarBothEndsCopperSleeveTemperature7", "拉杆两端铜套温度检测7"),
    temperature_data_drawbarBothEndsCopperSleeveTemperature8("drawbarBothEndsCopperSleeveTemperature8", "拉杆两端铜套温度检测8"),
    temperature_data_smallBeltWheelSupportAxisTemperature1("smallBeltWheelSupportAxisTemperature1", "小皮带轮支撑轴承温度检测1"),
    temperature_data_smallBeltWheelSupportAxisTemperature2("smallBeltWheelSupportAxisTemperature2", "小皮带轮支撑轴承温度检测2"),
    temperature_data_tripodBottomCopperSleeveTemperature1("tripodBottomCopperSleeveTemperature1", "三脚架下端铜套温度检测1"),
    temperature_data_tripodBottomCopperSleeveTemperature2("tripodBottomCopperSleeveTemperature2", "三脚架下端铜套温度检测2"),
    temperature_data_tripodBottomCopperSleeveTemperature3("tripodBottomCopperSleeveTemperature3", "三脚架下端铜套温度检测3"),
    temperature_data_tripodBottomCopperSleeveTemperature4("tripodBottomCopperSleeveTemperature4", "三脚架下端铜套温度检测4"),
    temperature_data_tripodEccentricBigCopperSleeveTemperature1("tripodEccentricBigCopperSleeveTemperature1", "三脚架偏心大铜套温度检测1"),
    temperature_data_tripodEccentricBigCopperSleeveTemperature2("tripodEccentricBigCopperSleeveTemperature2", "三脚架偏心大铜套温度检测2"),
    temperature_data_tripodEccentricBigCopperSleeveTemperature3("tripodEccentricBigCopperSleeveTemperature3", "三脚架偏心大铜套温度检测3"),
    temperature_data_tripodEccentricBigCopperSleeveTemperature4("tripodEccentricBigCopperSleeveTemperature4", "三脚架偏心大铜套温度检测4"),
    temperature_data_backupTemperature0("backupTemperature0", "备用温度检测0"),
    temperature_data_backupTemperature1("backupTemperature1", "备用温度检测1"),
    temperature_data_backupTemperature2("backupTemperature2", "备用温度检测2"),
    temperature_data_backupTemperature3("backupTemperature3", "备用温度检测3"),
    temperature_data_backupTemperature4("backupTemperature4", "备用温度检测4"),
    temperature_data_backupTemperature5("backupTemperature5", "备用温度检测5"),
    temperature_data_backupTemperature6("backupTemperature6", "备用温度检测6"),
    temperature_data_backupTemperature7("backupTemperature7", "备用温度检测7"),
    temperature_data_backupTemperature8("backupTemperature8", "备用温度检测8"),
    temperature_data_backupTemperature9("backupTemperature9", "备用温度检测9"),
    temperature_data_backupTemperature10("backupTemperature10", "备用温度检测10"),
    temperature_data_backupTemperature11("backupTemperature11", "备用温度检测11"),
    temperature_data_temperatureWarningValue1("temperatureWarningValue1", "温度警戒值1"),
    temperature_data_temperatureWarningValue2("temperatureWarningValue2", "温度警戒值2");
*/


    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    PlcEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
