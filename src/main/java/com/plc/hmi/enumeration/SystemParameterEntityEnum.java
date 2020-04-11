package com.plc.hmi.enumeration;

public enum SystemParameterEntityEnum {
    MAX_DISTANCE("MAX_DISTANCE", "压机最大行程"),
    MAX_FORCE("MAX_FORCE", "压机最大压力"),
    MAX_SPEED("MAX_SPEED", "压机最大速度"),
    DEFAULT_BACK_SPEED("DEFAULT_BACK_SPEED", "默认回程速度");


    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    SystemParameterEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


}
