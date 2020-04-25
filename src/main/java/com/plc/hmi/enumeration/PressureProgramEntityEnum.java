package com.plc.hmi.enumeration;

import com.plc.hmi.util.HmiUtils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PressureProgramEntityEnum {
    PRODUCT_ID("PRODUCT_ID", "产品表ID"),
    PRODUCT_CODE("PRODUCT_CODE", "产品代码"),
    STEP_1("STEP_1", "步骤1"),
    STEP_2("STEP_2", "步骤2"),
    STEP_3("STEP_3", "步骤3"),
    STEP_4("STEP_4", "步骤4"),
    STEP_5("STEP_5", "步骤5"),
    STEP_6("STEP_6", "步骤6"),
    STEP_7("STEP_7", "步骤7"),
    STEP_8("STEP_8", "步骤8"),
    PROGRAM_TYPE_1("PROGRAM_TYPE_1", "程序类型1"),
    PROGRAM_TYPE_2("PROGRAM_TYPE_2", "程序类型2"),
    PROGRAM_TYPE_3("PROGRAM_TYPE_3", "程序类型3"),
    PROGRAM_TYPE_4("PROGRAM_TYPE_4", "程序类型4"),
    PROGRAM_TYPE_5("PROGRAM_TYPE_5", "程序类型5"),
    PROGRAM_TYPE_6("PROGRAM_TYPE_6", "程序类型6"),
    PROGRAM_TYPE_7("PROGRAM_TYPE_7", "程序类型7"),
    PROGRAM_TYPE_8("PROGRAM_TYPE_8", "程序类型8"),
    PROGRAM_VALUE_1("PROGRAM_VALUE_1", "程序参数值1"),
    PROGRAM_VALUE_2("PROGRAM_VALUE_2", "程序参数值2"),
    PROGRAM_VALUE_3("PROGRAM_VALUE_3", "程序参数值3"),
    PROGRAM_VALUE_4("PROGRAM_VALUE_4", "程序参数值4"),
    PROGRAM_VALUE_5("PROGRAM_VALUE_5", "程序参数值5"),
    PROGRAM_VALUE_6("PROGRAM_VALUE_6", "程序参数值6"),
    PROGRAM_VALUE_7("PROGRAM_VALUE_7", "程序参数值7"),
    PROGRAM_VALUE_8("PROGRAM_VALUE_8", "程序参数值8"),
    SPEED_1("SPEED_1", "速度1"),
    ALARM_DEAL_TYPE_1("ALARM_DEAL_TYPE_1", "报警处理方式1"),
    POSITION_1("POSITION_1", "位置1"),
    PROTECT_PRESS_1("PROTECT_PRESS_1", "保护压力值1"),
    PRESS_1("PRESS_1", "压力1"),
    PROTECT_POSITION_1("PROTECT_POSITION_1", "保护位置值1"),
    PROTECT_TIME_1("PROTECT_TIME_1", "保压时间值1"),
    SPEED_2("SPEED_2", "速度2"),
    ALARM_DEAL_TYPE_2("ALARM_DEAL_TYPE_2", "报警处理方式2"),
    POSITION_2("POSITION_2", "位置2"),
    PROTECT_PRESS_2("PROTECT_PRESS_2", "保护压力值2"),
    PRESS_2("PRESS_2", "压力2"),
    PROTECT_POSITION_2("PROTECT_POSITION_2", "保护位置值2"),
    PROTECT_TIME_2("PROTECT_TIME_2", "保压时间值2"),
    SPEED_3("SPEED_3", "速度3"),
    ALARM_DEAL_TYPE_3("ALARM_DEAL_TYPE_3", "报警处理方式3"),
    POSITION_3("POSITION_3", "位置3"),
    PROTECT_PRESS_3("PROTECT_PRESS_3", "保护压力值3"),
    PRESS_3("PRESS_3", "压力3"),
    PROTECT_POSITION_3("PROTECT_POSITION_3", "保护位置值3"),
    PROTECT_TIME_3("PROTECT_TIME_3", "保压时间值3"),
    SPEED_4("SPEED_4", "速度4"),
    ALARM_DEAL_TYPE_4("ALARM_DEAL_TYPE_4", "报警处理方式4"),
    POSITION_4("POSITION_4", "位置4"),
    PROTECT_PRESS_4("PROTECT_PRESS_4", "保护压力值4"),
    PRESS_4("PRESS_4", "压力4"),
    PROTECT_POSITION_4("PROTECT_POSITION_4", "保护位置值4"),
    PROTECT_TIME_4("PROTECT_TIME_4", "保压时间值4"),
    SPEED_5("SPEED_5", "速度5"),
    ALARM_DEAL_TYPE_5("ALARM_DEAL_TYPE_5", "报警处理方式5"),
    POSITION_5("POSITION_5", "位置5"),
    PROTECT_PRESS_5("PROTECT_PRESS_5", "保护压力值5"),
    PRESS_5("PRESS_5", "压力5"),
    PROTECT_POSITION_5("PROTECT_POSITION_5", "保护位置值5"),
    PROTECT_TIME_5("PROTECT_TIME_5", "保压时间值5"),
    SPEED_6("SPEED_6", "速度6"),
    ALARM_DEAL_TYPE_6("ALARM_DEAL_TYPE_6", "报警处理方式6"),
    POSITION_6("POSITION_6", "位置6"),
    PROTECT_PRESS_6("PROTECT_PRESS_6", "保护压力值6"),
    PRESS_6("PRESS_6", "压力6"),
    PROTECT_POSITION_6("PROTECT_POSITION_6", "保护位置值6"),
    PROTECT_TIME_6("PROTECT_TIME_6", "保压时间值6"),
    SPEED_7("SPEED_7", "速度7"),
    ALARM_DEAL_TYPE_7("ALARM_DEAL_TYPE_7", "报警处理方式7"),
    POSITION_7("POSITION_7", "位置7"),
    PROTECT_PRESS_7("PROTECT_PRESS_7", "保护压力值7"),
    PRESS_7("PRESS_7", "压力7"),
    PROTECT_POSITION_7("PROTECT_POSITION_7", "保护位置值7"),
    PROTECT_TIME_7("PROTECT_TIME_7", "保压时间值7"),
    SPEED_8("SPEED_8", "速度8"),
    ALARM_DEAL_TYPE_8("ALARM_DEAL_TYPE_8", "报警处理方式8"),
    POSITION_8("POSITION_8", "位置8"),
    PROTECT_PRESS_8("PROTECT_PRESS_8", "保护压力值8"),
    PRESS_8("PRESS_8", "压力8"),
    PROTECT_POSITION_8("PROTECT_POSITION_8", "保护位置值8"),
    PROTECT_TIME_8("PROTECT_TIME_8", "保压时间值8"),
    ERRAND_TYPE_1("ERRAND_TYPE_1", "窗口类型1"),
    POSITION_MIN_1("POSITION_MIN_1", "位置下限1"),
    POSITION_MAX_1("POSITION_MAX_1", "位置上限1"),
    PRESS_MIN_1("PRESS_MIN_1", "压力下限1"),
    PRESS_MAX_1("PRESS_MAX_1", "压力上限1"),
    ERRAND_TYPE_2("ERRAND_TYPE_2", "窗口类型2"),
    POSITION_MIN_2("POSITION_MIN_2", "位置下限2"),
    POSITION_MAX_2("POSITION_MAX_2", "位置上限2"),
    PRESS_MIN_2("PRESS_MIN_2", "压力下限2"),
    PRESS_MAX_2("PRESS_MAX_2", "压力上限2"),
    ERRAND_TYPE_3("ERRAND_TYPE_3", "窗口类型3"),
    POSITION_MIN_3("POSITION_MIN_3", "位置下限3"),
    POSITION_MAX_3("POSITION_MAX_3", "位置上限3"),
    PRESS_MIN_3("PRESS_MIN_3", "压力下限3"),
    PRESS_MAX_3("PRESS_MAX_3", "压力上限3"),
    ERRAND_TYPE_4("ERRAND_TYPE_4", "窗口类型4"),
    POSITION_MIN_4("POSITION_MIN_4", "位置下限4"),
    POSITION_MAX_4("POSITION_MAX_4", "位置上限4"),
    PRESS_MIN_4("PRESS_MIN_4", "压力下限4"),
    PRESS_MAX_4("PRESS_MAX_4", "压力上限4"),
    ERRAND_TYPE_5("ERRAND_TYPE_5", "窗口类型5"),
    POSITION_MIN_5("POSITION_MIN_5", "位置下限5"),
    POSITION_MAX_5("POSITION_MAX_5", "位置上限5"),
    PRESS_MIN_5("PRESS_MIN_5", "压力下限5"),
    PRESS_MAX_5("PRESS_MAX_5", "压力上限5");
    //枚举对象的变量
    private String code;
    private String value;

    //重写枚举类的默认构造器
    PressureProgramEntityEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, String> getLookup() {
        return lookup;
    }

    //获得code属性的值
    public String getCode() { return this.code; }

    //获得value属性的值
    public String getValue() { return this.value; }


    private static final Map<String, String> lookup = new HashMap<String, String>();
    static {
        for (PressureProgramEntityEnum s : EnumSet.allOf(PressureProgramEntityEnum.class)) {
            lookup.put(s.getValue(),HmiUtils.lineToHump(s.getCode()));
        }
    }
}
