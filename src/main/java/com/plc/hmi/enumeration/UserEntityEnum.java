package com.plc.hmi.enumeration;

public enum UserEntityEnum {
    USER_NAME("USER_NAME", "用户名"),
    USER_PASSOWORD("USER_PASSWORD", "密码"),
    ROLE_ID("ROLE_ID", "用户角色");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    UserEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


}
