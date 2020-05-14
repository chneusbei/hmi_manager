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
    //英文逗号
    public static final String COMMA = ",";
    //反斜杠
    public static final String SEPARATE = "/";

    //文件行分隔符
    public static final String CSV_ROW_SEPARATOR = "\r\n";

    //CSV导出文件头
//    public static final String CURVE_TITLES = "ID, 压力曲线ID, 产品ID, 位置点序号, 位置, 压力, 速度, 压装时间点";
    public static final String CURVE_TITLES = "Index, BLANK, Position, Force, Analog, Time, Speed";
    public static final String DATA_TITLES = "ID, 产品ID, 压力曲线ID, 压装结果, 最大压力值, 最大压力时候位移,压装开始时间,压装结束时间";
    //CSV 文件写入地址
    public static final String CSV_OUTPUT_PATH="D:/csv";
    //每个压头对应csv文件夹前缀
    public static final String CSV_PRESSURE_HEAD_PATH_PREFIX ="/pressureHead";


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
        CURVE_DATA_2("curve_data_2", "曲线信息_数据"),//双压头时第二个压头
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
