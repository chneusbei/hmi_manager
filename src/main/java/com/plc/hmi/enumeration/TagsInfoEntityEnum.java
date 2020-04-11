package com.plc.hmi.enumeration;

public enum TagsInfoEntityEnum {
    TAG_NAME("TAG_NAME", "变量名"),
    TAG_EN_NAME("TAG_EN_NAME", "变量英文名"),
    TAG_TYPE_ID("TAG_TYPE_ID", "变量类型ID"),
    TAG_TYPE_DES("TAG_TYPE_DES", "变量类型描述"),
    TAG_AREA("TAG_AREA", "数据区"),
    TAG_AREA_NAME("TAG_AREA_NAME", "数据区名"),
    DB_NO("DB_NO", "数据块地址"),
    ADDRESS("ADDRESS", "地址"),
    TAG_BIT("TAG_BIT", "位"),
    TAG_GROUP("TAG_GROUP", "分组");

    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    TagsInfoEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


}
