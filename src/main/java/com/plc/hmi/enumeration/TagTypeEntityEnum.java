package com.plc.hmi.enumeration;

public enum TagTypeEntityEnum {
    DATA_TYPE("DATA_TYPE", "数据类型"),
    DATA_LENGTH("DATA_LENGTH", "数据长度");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    TagTypeEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


}
