package com.plc.hmi.enumeration;

public enum PressureDataEntityEnum {
    PRODUCT_ID("PRODUCT_ID", "产品ID"),
    PRODUCT_NO("PRODUCT_NO", "产品二维码"),
    PRESS_RESULT("PRESS_RESULT", "压装结果"),
    RECORD_ID("RECORD_ID", "位置/压力曲线ID"),
    START_DATE("START_DATE", "压装开始时间"),
    END_DATE("END_DATE", "压装结束时间"),
    MAX_PRESS("MAX_PRESS", "最大压力值"),
    POSITION_OF_MAX_PRESS("POSITION_OF_MAX_PRESS", "最大压力时候位移"),
    COUNT("COUNT", "统计汇总值");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    PressureDataEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
