package com.plc.hmi.enumeration;

public enum PressureCurveEntityEnum {
    //数据库字段
    PRESS_DATA_ID("PRESS_DATA_ID", "位置/压力曲线ID"),
    RECORD_NO("RECORD_NO", "位置点序号"),
    POSITION("POSITION", "位置"),
    PRESS_FORCE("PRESS_FORCE", "压力"),
    HANDLE_DATE("HANDLE_DATE", "日期"),
    PRESS_DATE("PRESS_DATE", "压装时间点"),
    CUR_SPEED("CUR_SPEED", "速度");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    PressureCurveEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
