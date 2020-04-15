package com.plc.hmi.constants;

import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.types.PlcClientDatatype;

public class HmiConstants {

    //成功/是
    public static final String OK= "1";
    //失败/否
    public static final String NOK= "0";


    //PLC查询前缀
    public static final String PLC_QUERY_PREFIX ="%";
    //英文点
    public static final String POINT = ".";
    //反斜杠
    public static final String SEPARATE = "/";

    public enum PLC_TAG_GROUP {
        EQUIPMENT_OPERATION("equipment_operation", "设备信息_操作"),
        EQUIPMENT_STATUS("equipment_status", "设备信息_状态"),
        EQUIPMENT_ALARM("equipment_alarm", "设备信息_报警"),
        EQUIPMENT_IO_STATUS("equipment_io_status", "设备信息_IO状态"),
        EQUIPMENT_TRACE("equipment_trace", "设备信息_追溯系统"),
        SERVO_OPERATION("servo_operation", "伺服信息_操作"),
        SERVO_STATUS("servo_status", "伺服信息_状态"),
        SERVO_LIMIT_SET("servo_limit_set", "伺服信息_限定设置"),
        CURVE_DATA("curve_data", "曲线信息_数据"),
        CURVE_STATUS("curve_status", "曲线信息_状态"),
        CURVE_PROGRAM("curve_program", "曲线信息_程序"),
        CURVE_ERRAND("curve_errand", "曲线信息_公差窗口");

        //枚举对象的变量
        private String code;
        private String value;

        //重写枚举类的默认构造器
        PLC_TAG_GROUP(String code, String value) {
            this.code = code;
            this.value = value;
        }

        //获得code属性的值
        public String getCode() { return this.code; }

        //获得value属性的值
        public String getValue() { return this.value; }

    }

    public enum PLC_DATA_TYPE {
        BOOL("BOOL", PlcClientDatatype.BOOLEAN),
        BYTE("BYTE", PlcClientDatatype.BYTE),
        CHAR("CHAR", PlcClientDatatype.STRING),
        DATE("DATE", PlcClientDatatype.DATE),
        DINT("DINT", PlcClientDatatype.DOUBLE),
        DWORD("DWORD", PlcClientDatatype.DOUBLE),
        INT("INT", PlcClientDatatype.INTEGER),
        LINT("LINT", PlcClientDatatype.DOUBLE),
        LREAL("LREAL", PlcClientDatatype.STRING),
        LWORD("LWORD", PlcClientDatatype.DOUBLE),
        REAL("REAL", PlcClientDatatype.STRING),
        SINT("SINT", PlcClientDatatype.SHORT),
        TIME("TIME", PlcClientDatatype.TIME),
        UINT("UINT", PlcClientDatatype.INTEGER),
        ULINT("ULINT", PlcClientDatatype.DOUBLE),
        USINT("USINT", PlcClientDatatype.SHORT),
        WORD("WORD", PlcClientDatatype.DOUBLE);

        //枚举对象的变量
        private String code;
        private PlcClientDatatype value;

        //重写枚举类的默认构造器
        PLC_DATA_TYPE(String code, PlcClientDatatype value) {
            this.code = code;
            this.value = value;
        }

        //获得code属性的值
        public String getCode() { return this.code; }

        //获得value属性的值
        public PlcClientDatatype getValue() { return this.value; }

        public static PlcClientDatatype getTypeByCode(String code) {
            if (StringUtils.isNotBlank(code)) {
                for (PLC_DATA_TYPE ele : PLC_DATA_TYPE.values()) {/*获得枚举类中的所有枚举*/
                    if ((ele.getCode()).equals(code)) {
                        return ele.getValue();
                    }
                }
            }
            return null;
        }

    }
}
