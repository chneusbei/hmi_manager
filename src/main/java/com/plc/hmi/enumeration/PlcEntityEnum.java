package com.plc.hmi.enumeration;

public enum PlcEntityEnum {
    //设备信息-操作
    equipment_operation_productNo("productNo", "零件号"),
    //曲线信息-状态
    curve_status_curve_recording("curveRecording", "曲线记录开始"),


    //曲线信息-数据 curve_data
    curve_data_curPosition("curPosition", "当前位置"),
    curve_data_curForce("curForce", "当前压力"),
    curve_data_curSpeed("curSpeed", "当前速度"),
    curve_data_reserve0("reserve0", "预留_0"),
    curve_data_reserve1("reserve1", "预留_1"),
    curve_data_reserve2("reserve2", "预留_2"),

    /*
    第二条曲线
     */
    //设备信息-操作
    equipment_operation_productNo2("productNo2", "零件号"),
    //曲线信息-状态
    curve_status_curve_recording2("curveRecording2", "曲线记录开始"),


    //曲线信息-数据 curve_data
    curve_data_curPosition2("curPosition2", "当前位置"),
    curve_data_curForce2("curForce2", "当前压力"),
    curve_data_curSpeed2("curSpeed2", "当前速度"),
    curve_data_reserve0_2("reserve0_2", "预留_0"),
    curve_data_reserve1_2("reserve1_2", "预留_1"),
    curve_data_reserve2_2("reserve2_2", "预留_2");



    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    PlcEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }

}
