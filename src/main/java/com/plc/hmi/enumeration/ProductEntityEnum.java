package com.plc.hmi.enumeration;

public enum ProductEntityEnum {
    PRODUCT_CODE("PRODUCT_CODE", "产品代码"),
    PRODUCT_TYPE("PRODUCT_TYPE", "产品类型");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    ProductEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


}
