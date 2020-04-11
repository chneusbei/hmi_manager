package com.plc.hmi.enumeration;

public enum BaseEntityEnum {
    //通用字段
    ID("ID", "主键"),
    IS_DELETED("IS_DELETED", "删除标志"),//0否1是
    CREATE_BY("CREATE_BY", "创建人"),
    UPDATE_BY("UPDATE_BY", "修改人"),
    UPDATE_TIME("UPDATE_TIME", "修改时间"),
    CREATE_TIME("CREATE_TIME", "创建时间");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    BaseEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
