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
    //CSV 正压反压分包地址 -正压
    public static final String CSV_OUTPUT_PATH_POSITIVE ="/正压";
    //CSV 正压反压分包地址 -反压
    public static final String CSV_OUTPUT_PATH_NEGATIVE ="/反压";
    //每个压头对应csv文件夹前缀
    public static final String CSV_PRESSURE_HEAD_PATH_PREFIX ="/pressureHead";
    //CSV 正压反压分包地址中的压装成功包
    public static final String CSV_OUTPUT_PATH_OK ="/OK";
    //CSV 正压反压分包地址中的压装成功包
    public static final String CSV_OUTPUT_PATH_NOK ="/NOK";

    //温度监控信息状态 - 正常
    public static final String TEMPERATURE_STATUS_NORMAL ="OK";
    //温度监控信息状态 - 报警
    public static final String TEMPERATURE_STATUS_WARNING ="WARNING";
    //温度csv文件夹二级目录
    public static final String TEMPERATURE_CSV_OUTPUT_PATH_ ="/temperature";
    //温度csv文件头
    public static final String[] TEMPERATURE_CURVE_TITLES = new String[]{"序号", "上位机IP", "数据所属日期", "状态", "低速轴偏心铜套温度检测1", "低速轴偏心铜套温度检测2",
            "低速轴偏心铜套温度检测3", "低速轴偏心铜套温度检测4", "高速轴偏心铜套温度检测1", "高速轴偏心铜套温度检测2", "飞轮支撑大轴承温度检测1", "飞轮支撑大轴承温度检测2",
            "拉杆两端铜套温度检测1", "拉杆两端铜套温度检测2", "拉杆两端铜套温度检测3", "拉杆两端铜套温度检测4", "拉杆两端铜套温度检测5", "拉杆两端铜套温度检测6",
            "拉杆两端铜套温度检测7", "拉杆两端铜套温度检测8", "小皮带轮支撑轴承温度检测1", "小皮带轮支撑轴承温度检测2", "三脚架下端铜套温度检测1", "三脚架下端铜套温度检测2",
            "三脚架下端铜套温度检测3", "三脚架下端铜套温度检测4", "三脚架偏心大铜套温度检测1", "三脚架偏心大铜套温度检测2", "三脚架偏心大铜套温度检测3", "三脚架偏心大铜套温度检测4",
            "备用温度检测0", "备用温度检测1", "备用温度检测2", "备用温度检测3", "备用温度检测4", "备用温度检测5", "备用温度检测6", "备用温度检测7", "备用温度检测8",
            "备用温度检测9", "备用温度检测10", "备用温度检测11", "温度警戒值1", "温度警戒值2"};


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
        CURVE_ERRAND("curve_errand", "曲线信息_公差窗口"),
        START_PLC("start_plc", "启动PLC测试"),
        CURVE_DATA_NEW("curve_data_new", "新曲线信息状态_数据"),
        CURVE_DATA_UPDATE("curve_data_update", "新曲线信息状态更新"),
        TEMPERATURE_DATA("temperature", "温度数据查询"),
        TEMPERATURE_SETTING("temperature_setting", "温度预警阈值设置");
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
