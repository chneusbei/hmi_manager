package com.plc.hmi.enumeration;

public enum PropertyConfigEntityEnum {
    PROP_NAME("PROP_NAME", "属性名"),
    PROP_VALUE("PROP_VALUE", "属性值"),
    PROP_GROUP("PROP_GROUP", "分组"),
    DESCRIPTION("DESCRIPTION", "描述");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    PropertyConfigEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
