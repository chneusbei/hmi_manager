package com.plc.hmi.enumeration;

public enum HisAlarmEntityEnum {
    ALARM_ID("ALARM_ID", "警报信息ID"),
    ALARM_START_TIME("ALARM_START_TIME", "警报开始时间"),
    ALARM_STOP_TIME("ALARM_STOP_TIME", "警报结束时间"),
    ALARM_STATUS("ALARM_STATUS", "警报状态"),
    ALARM_CFM_STATUS("ALARM_CFM_STATUS", "警报确认状态");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    HisAlarmEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


}
