package com.plc.hmi.enumeration;

public enum AlarmEntityEnum {
    TRIGGER_DB("TRIGGER_DB", "触发DB"),
    TRIGGER_OFFSET("TRIGGER_OFFSET", "触发DB偏移"),
    TRIGGER_BIT("TRIGGER_BIT", "触发位"),
    ALARM_TYPE("ALARM_TYPE", "警报类型"),
    ALARM_GROUP("ALARM_GROUP", "报警组"),
    ACTIVE("ACTIVE", "激活"),
    ALARM_INFO("ALARM_INFO", "警报内容"),
    ALARM_HELP("ALARM_HELP", "警报提示"),
    ALARM_STATUS("ALARM_STATUS", "警报状态"),
    ALARM_TIME("ALARM_TIME", "警报时间点");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    AlarmEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
