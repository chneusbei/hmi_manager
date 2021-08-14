package com.plc.hmi.enumeration;

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

    //温度信息
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature1("lowSpeedAxisEccentricCopperSleeveTemperature1", "低速轴偏心铜套温度检测1"),
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature2("lowSpeedAxisEccentricCopperSleeveTemperature2", "低速轴偏心铜套温度检测2"),
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature3("lowSpeedAxisEccentricCopperSleeveTemperature3", "低速轴偏心铜套温度检测3"),
    temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature4("lowSpeedAxisEccentricCopperSleeveTemperature4", "速轴偏心铜套温度检测4"),
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
