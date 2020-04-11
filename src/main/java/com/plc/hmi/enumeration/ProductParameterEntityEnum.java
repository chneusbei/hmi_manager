package com.plc.hmi.enumeration;

public enum ProductParameterEntityEnum {
    PRODUCT_ID("PRODUCT_ID", "产品表ID");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    ProductParameterEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
