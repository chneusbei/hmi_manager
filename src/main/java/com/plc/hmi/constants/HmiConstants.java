package com.plc.hmi.constants;

import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.types.PlcClientDatatype;

import java.util.ArrayList;
import java.util.List;

public class HmiConstants {


    //AVG启用状态 - 启用
    public static final String AVG_STATUS_ACTIVE ="1";
    //AVG启用状态 - 停用
    public static final String AVG_STATUS_STOP ="0";















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
    public static final String[] TEMPERATURE_CURVE_TITLES_NEW = new String[]{"ID","批次号", "上位机名","上位机IP", "状态","数据所属日期", "低速轴偏心铜套温度检测1", "低速轴偏心铜套温度检测2",
            "低速轴偏心铜套温度检测3", "低速轴偏心铜套温度检测4", "高速轴偏心铜套温度检测1", "高速轴偏心铜套温度检测2", "飞轮支撑大轴承温度检测1", "飞轮支撑大轴承温度检测2","高速轴滚动轴承温度检测1","高速轴滚动轴承温度检测2",
            "拉杆两端铜套温度检测1","拉杆两端铜套温度检测2","拉杆两端铜套温度检测3","拉杆两端铜套温度检测4","拉杆两端铜套温度检测5",
            "拉杆两端铜套温度检测6","拉杆两端铜套温度检测7","拉杆两端铜套温度检测8","小皮带轮支持轴承温度检测1","小皮带轮支持轴承温度检测2",
            "左前三脚架偏心大铜套温度检测", "左后三脚架偏心大铜套温度检测", "右前三脚架偏心大铜套温度检测", "右后三脚架偏心大铜套温度检测",
            "左前三脚架下端铜套温度检测", "左后三脚架下端铜套温度检测", "右前三脚架下端铜套温度检测", "右后三脚架下端铜套温度检测", "左前连杆大铜套", "左后连杆大铜套",
            "右前连杆大铜套", "右后连杆大铜套", "左前低速轴铜套", "左后低速轴铜套", "右前低速轴铜套", "右后低速轴铜套",
            "备用温度检测0", "备用温度检测1", "备用温度检测2", "备用温度检测3", "备用温度检测4", "备用温度检测5", "备用温度检测6", "备用温度检测7", "备用温度检测8",
            "备用温度检测9", "备用温度检测10", "备用温度检测11",
            "温度警戒值1", "温度警戒值2",
            "A线温度警戒值", "偏心轮左前", "偏心轮左后", "偏心轮右前", "偏心轮右后", "连杆左前","连杆左后","连杆右前","连杆右后",
            "右高速齿轮轴承(前)温度","右高速齿轮轴承(后)温度","右中间齿轮轴承(前)温度","右中间齿轮轴承(后)温度",
            "左高速齿轮轴承(前)温度","左高速齿轮轴承(后)温度","左中间齿轮轴承(前)温度" ,"左中间齿轮轴承(后)温度",
            "主轴齿轮轴承1温度","主轴齿轮轴承2温度","主轴齿轮轴承3温度","主轴齿轮轴承4温度","主轴齿轮轴承5温度","主轴齿轮轴承6温度",
            "主轴齿轮轴承7温度","主轴齿轮轴承8温度"};

    //温度csv文件头
    public static final String[] TEMPERATURE_CURVE_TITLES_POINT = new String[]{"ID","上位机名", "A线B线", "温度点名","温度值","时间"};


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
        TEMPERATURE_A("temperatureA", "温度数据查询-A线"),
        TEMPERATURE_B("temperatureB", "温度数据查询-B线"),
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

    public enum PLC_NAME_GROUP {
        PLC_B_1("B-1", "压机一"),
        PLC_B_2("B-2", "压机二"),
        PLC_B_3("B-3", "压机三"),
        PLC_B_4("B-4", "压机四"),
        PLC_B_5("B-5", "压机五"),
        PLC_B_WIRELESS_1("B-W1", "压机一无线"),
        PLC_B_WIRELESS_2("B-W2", "压机二无线"),
        PLC_B_WIRELESS_3("B-W3", "压机三无线"),
        PLC_B_WIRELESS_4("B-W4", "压机四无线"),
        PLC_B_WIRELESS_5("B-W5", "压机五无线"),
        PLC_A_1("A-1", "压机一"),
        PLC_A_2("A-2", "压机二"),
        PLC_A_3("A-3", "压机三"),
        PLC_A_4("A-4", "压机四"),
        PLC_A_WIRELESS_1("A-W1", "压机一无线"),
        PLC_A_WIRELESS_2("A-W2", "压机二无线"),
        PLC_A_WIRELESS_3("A-W3", "压机三无线"),
        PLC_A_WIRELESS_4("A-W4", "压机四无线"),
        PLC_A_WIRELESS_5("A-W5", "压机五无线"),
        ;
        //枚举对象的变量
        private String code;
        private String value;

        //重写枚举类的默认构造器
        PLC_NAME_GROUP(String code, String value) {
            this.code = code;
            this.value = value;
        }

        //获得code属性的值
        public String getCode() { return this.code; }

        //获得value属性的值
        public String getValue() { return this.value; }

        //获得code值列表
        public static List<String> getCodeListByLineType(String LineTye) {
            List<String> codeList = new ArrayList<String>();
            for (PLC_NAME_GROUP plc : PLC_NAME_GROUP.values()) {
                if(plc.getCode().startsWith(LineTye.toUpperCase())) {
                    codeList.add(plc.getCode());
                }
            }
            return codeList;
        }

    }

    public enum LINE_TYPE{
        LINE_TYPE_A("A", "A线"),
        LINE_TYPE_B("B", "B线");
        //枚举对象的变量
        private String code;
        private String value;

        //重写枚举类的默认构造器
        LINE_TYPE(String code, String value) {
            this.code = code;
            this.value = value;
        }

        //获得code属性的值
        public String getCode() { return this.code; }

        //获得value属性的值
        public String getValue() { return this.value; }

    }

    public enum TEMPERATURE_POINT{
        getLowSpeedAxisEccentricCopperSleeveTemperature1("getLowSpeedAxisEccentricCopperSleeveTemperature1","低速轴偏心铜套温度1"),
        getLowSpeedAxisEccentricCopperSleeveTemperature2("getLowSpeedAxisEccentricCopperSleeveTemperature2","低速轴偏心铜套温度2"),
        getLowSpeedAxisEccentricCopperSleeveTemperature3("getLowSpeedAxisEccentricCopperSleeveTemperature3","低速轴偏心铜套温度3"),
        getLowSpeedAxisEccentricCopperSleeveTemperature4("getLowSpeedAxisEccentricCopperSleeveTemperature4","低速轴偏心铜套温度4"),
        getHighSpeedAxisEccentricCopperSleeveTemperature1("getHighSpeedAxisEccentricCopperSleeveTemperature1","高速轴偏心铜套温度1"),
        getHighSpeedAxisEccentricCopperSleeveTemperature2("getHighSpeedAxisEccentricCopperSleeveTemperature2","高速轴偏心铜套温度2"),
        getHighSpeedAxisRollingBearingTemperature1("getHighSpeedAxisRollingBearingTemperature1","高速轴滚动轴承温度1"),
        getHighSpeedAxisRollingBearingTemperature2("getHighSpeedAxisRollingBearingTemperature2","高速轴滚动轴承温度2"),
        getFlywheelSupportBigAxisTemperature1("getFlywheelSupportBigAxisTemperature1","飞轮支撑大轴承温度1"),
        getFlywheelSupportBigAxisTemperature2("getFlywheelSupportBigAxisTemperature2","飞轮支撑大轴承温度2"),
        getDrawbarBothEndsCopperSleeveTemperature1("getDrawbarBothEndsCopperSleeveTemperature1","拉杆两端铜套温度检测1"),
        getDrawbarBothEndsCopperSleeveTemperature2("getDrawbarBothEndsCopperSleeveTemperature2","拉杆两端铜套温度检测2"),
        getDrawbarBothEndsCopperSleeveTemperature3("getDrawbarBothEndsCopperSleeveTemperature3","拉杆两端铜套温度检测3"),
        getDrawbarBothEndsCopperSleeveTemperature4("getDrawbarBothEndsCopperSleeveTemperature4","拉杆两端铜套温度检测4"),
        getDrawbarBothEndsCopperSleeveTemperature5("getDrawbarBothEndsCopperSleeveTemperature5","拉杆两端铜套温度检测5"),
        getDrawbarBothEndsCopperSleeveTemperature6("getDrawbarBothEndsCopperSleeveTemperature6","拉杆两端铜套温度检测6"),
        getDrawbarBothEndsCopperSleeveTemperature7("getDrawbarBothEndsCopperSleeveTemperature7","拉杆两端铜套温度检测7"),
        getDrawbarBothEndsCopperSleeveTemperature8("getDrawbarBothEndsCopperSleeveTemperature8","拉杆两端铜套温度检测8"),
        getSmallBeltWheelSupportAxisTemperature1("getSmallBeltWheelSupportAxisTemperature1","小皮带轮支撑轴承温度检测1"),
        getSmallBeltWheelSupportAxisTemperature2("getSmallBeltWheelSupportAxisTemperature2","小皮带轮支撑轴承温度检测2"),
        getLeftFrontTripodEccentricBigCopperSleeve("getLeftFrontTripodEccentricBigCopperSleeve","左前三脚架偏心大铜套"),
        getLeftRearTripodEccentricBigCopperSleeve("getLeftRearTripodEccentricBigCopperSleeve","左后三脚架偏心大铜套"),
        getRightFrontTripodEccentricBigCopperSleeve("getRightFrontTripodEccentricBigCopperSleeve","右前三脚架偏心大铜套"),
        getRightRearTripodEccentricBigCopperSleeve("getRightRearTripodEccentricBigCopperSleeve","右后三脚架偏心大铜套"),
        getLeftFrontTripodBottomCopperSleeve("getLeftFrontTripodBottomCopperSleeve","左前三脚架下端铜套"),
        getLeftRearTripodBottomCopperSleeve("getLeftRearTripodBottomCopperSleeve","左后三脚架下端铜套"),
        getRightFrontTripodBottomCopperSleeve("getRightFrontTripodBottomCopperSleeve","右前三脚架下端铜套"),
        getRightRearTripodBottomCopperSleeve("getRightRearTripodBottomCopperSleeve","右后三脚架下端铜套"),
        getLeftFrontConnectingRodBigCopperSleeve("getLeftFrontConnectingRodBigCopperSleeve","左前连杆大铜套"),
        getLeftRearConnectingRodBigCopperSleeve("getLeftRearConnectingRodBigCopperSleeve","左后连杆大铜套"),
        getRightFrontConnectingRodBigCopperSleeve("getRightFrontConnectingRodBigCopperSleeve","右前连杆大铜套"),
        getRightRearConnectingRodBigCopperSleeve("getRightRearConnectingRodBigCopperSleeve","右后连杆大铜套"),
        getLeftFrontLowSpeedAxisCopperSleeve("getLeftFrontLowSpeedAxisCopperSleeve","左前低速轴铜套"),
        getLeftRearLowSpeedAxisCopperSleeve("getLeftRearLowSpeedAxisCopperSleeve","左后低速轴铜套"),
        getRightFrontLowSpeedAxisCopperSleeve("getRightFrontLowSpeedAxisCopperSleeve","右前低速轴铜套"),
        getRightRearLowSpeedAxisCopperSleeve("getRightRearLowSpeedAxisCopperSleeve","右后低速轴铜套"),
        getLeftFrontEccentricWheel("getLeftFrontEccentricWheel","偏心轮左前"),
        getLeftRearEccentricWheel("getLeftRearEccentricWheel","偏心轮左后"),
        getRightFrontEccentricWheel("getRightFrontEccentricWheel","偏心轮右前"),
        getRightRearEccentricWheel("getRightRearEccentricWheel","偏心轮右后"),
        getLeftFrontConnectingRod("getLeftFrontConnectingRod","连杆左前"),
        getLeftRearConnectingRod("getLeftRearConnectingRod","连杆左后"),
        getRightFrontConnectingRod("getRightFrontConnectingRod","连杆右前"),
        getRightRearConnectingRod("getRightRearConnectingRod","连杆右后"),
        getRightFrontHighSpeedGearBearingTemperature("getRightFrontHighSpeedGearBearingTemperature","前右高速齿轮轴承"),
        getRightRearHighSpeedGearBearingTemperature("getRightRearHighSpeedGearBearingTemperature","右后高速齿轮轴承"),
        getLeftFrontHighSpeedGearBearingTemperature("getLeftFrontHighSpeedGearBearingTemperature","左前高速齿轮轴承"),
        getLeftRearHighSpeedGearBearingTemperature("getLeftRearHighSpeedGearBearingTemperature","左后高速齿轮轴承"),
        getRightFrontMiddleGearBearingTemperature("getRightFrontMiddleGearBearingTemperature","右前中间齿轮轴承"),
        getRightRearMiddleGearBearingTemperature("getRightRearMiddleGearBearingTemperature","右后中间齿轮轴承"),
        getLeftFrontMiddleGearBearingTemperature("getLeftFrontMiddleGearBearingTemperature","左前中间齿轮轴承"),
        getLeftRearMiddleGearBearingTemperature("getLeftRearMiddleGearBearingTemperature","左后中间齿轮轴承"),
        getMainAxisGearBearingTemperature1("getMainAxisGearBearingTemperature1","主轴齿轮轴承1"),
        getMainAxisGearBearingTemperature2("getMainAxisGearBearingTemperature2","主轴齿轮轴承2"),
        getMainAxisGearBearingTemperature3("getMainAxisGearBearingTemperature3","主轴齿轮轴承3"),
        getMainAxisGearBearingTemperature4("getMainAxisGearBearingTemperature4","主轴齿轮轴承4"),
        getMainAxisGearBearingTemperature5("getMainAxisGearBearingTemperature5","主轴齿轮轴承5"),
        getMainAxisGearBearingTemperature6("getMainAxisGearBearingTemperature6","主轴齿轮轴承6"),
        getMainAxisGearBearingTemperature7("getMainAxisGearBearingTemperature7","主轴齿轮轴承7"),
        getMainAxisGearBearingTemperature8 ("getMainAxisGearBearingTemperature8","主轴齿轮轴承8");
        //枚举对象的变量
        private String code;
        private String value;

        //重写枚举类的默认构造器
        TEMPERATURE_POINT(String code, String value) {
            this.code = code;
            this.value = value;
        }

        //获得code属性的值
        public String getCode() { return this.code; }

        //获得value属性的值
        public String getValue() { return this.value; }

        public String getValueByCode(String code ) {
            return TEMPERATURE_POINT.valueOf(code).getValue();
        }
    }

    //A线温度点信息
    public static final List<String> TEMPERATURE_POINT_LINE_A = new ArrayList<String>();
    static{
        TEMPERATURE_POINT_LINE_A.add("getLeftFrontEccentricWheel"); //偏心轮左前
        TEMPERATURE_POINT_LINE_A.add("getLeftRearEccentricWheel"); //偏心轮左后
        TEMPERATURE_POINT_LINE_A.add("getRightFrontEccentricWheel"); //偏心轮右前
        TEMPERATURE_POINT_LINE_A.add("getRightRearEccentricWheel"); //偏心轮右后
        TEMPERATURE_POINT_LINE_A.add("getLeftFrontConnectingRod"); //连杆左前
        TEMPERATURE_POINT_LINE_A.add("getLeftRearConnectingRod"); //连杆左后
        TEMPERATURE_POINT_LINE_A.add("getRightFrontConnectingRod"); //连杆右前
        TEMPERATURE_POINT_LINE_A.add("getRightRearConnectingRod"); //连杆右后
        TEMPERATURE_POINT_LINE_A.add("getRightFrontHighSpeedGearBearingTemperature"); //前右高速齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getRightRearHighSpeedGearBearingTemperature"); //右后高速齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getLeftFrontHighSpeedGearBearingTemperature"); //左前高速齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getLeftRearHighSpeedGearBearingTemperature"); //左后高速齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getRightFrontMiddleGearBearingTemperature");  //右前中间齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getRightRearMiddleGearBearingTemperature"); //右后中间齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getLeftFrontMiddleGearBearingTemperature"); //左前中间齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getLeftRearMiddleGearBearingTemperature"); //左后中间齿轮轴承
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature1"); //主轴齿轮轴承1
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature2"); //主轴齿轮轴承2
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature3"); //主轴齿轮轴承3
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature4"); //主轴齿轮轴承4
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature5"); //主轴齿轮轴承5
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature6"); //主轴齿轮轴承6
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature7"); //主轴齿轮轴承7
        TEMPERATURE_POINT_LINE_A.add("getMainAxisGearBearingTemperature8"); //主轴齿轮轴承8
    }

    //B线温度点信息
    public static final List<String> TEMPERATURE_POINT_LINE_B = new ArrayList<String>();
    static{
        TEMPERATURE_POINT_LINE_B.add("getLowSpeedAxisEccentricCopperSleeveTemperature1"); //低速轴偏心铜套温度1
        TEMPERATURE_POINT_LINE_B.add("getLowSpeedAxisEccentricCopperSleeveTemperature2"); //低速轴偏心铜套温度2
        TEMPERATURE_POINT_LINE_B.add("getLowSpeedAxisEccentricCopperSleeveTemperature3"); //低速轴偏心铜套温度3
        TEMPERATURE_POINT_LINE_B.add("getLowSpeedAxisEccentricCopperSleeveTemperature4"); //低速轴偏心铜套温度4
        TEMPERATURE_POINT_LINE_B.add("getHighSpeedAxisEccentricCopperSleeveTemperature1"); //高速轴偏心铜套温度1
        TEMPERATURE_POINT_LINE_B.add("getHighSpeedAxisEccentricCopperSleeveTemperature2"); //高速轴偏心铜套温度2
        TEMPERATURE_POINT_LINE_B.add("getHighSpeedAxisRollingBearingTemperature1"); //高速轴滚动轴承温度1
        TEMPERATURE_POINT_LINE_B.add("getHighSpeedAxisRollingBearingTemperature2"); //高速轴滚动轴承温度2
        TEMPERATURE_POINT_LINE_B.add("getFlywheelSupportBigAxisTemperature1"); //飞轮支撑大轴承温度1
        TEMPERATURE_POINT_LINE_B.add("getFlywheelSupportBigAxisTemperature2"); //飞轮支撑大轴承温度2
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature1"); //拉杆两端铜套温度检测1
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature2"); //拉杆两端铜套温度检测2
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature3"); //拉杆两端铜套温度检测3
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature4"); //拉杆两端铜套温度检测4
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature5"); //拉杆两端铜套温度检测5
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature6"); //拉杆两端铜套温度检测6
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature7"); //拉杆两端铜套温度检测7
        TEMPERATURE_POINT_LINE_B.add("getDrawbarBothEndsCopperSleeveTemperature8"); //拉杆两端铜套温度检测8
        TEMPERATURE_POINT_LINE_B.add("getSmallBeltWheelSupportAxisTemperature1"); //小皮带轮支撑轴承温度检测1
        TEMPERATURE_POINT_LINE_B.add("getSmallBeltWheelSupportAxisTemperature2"); //小皮带轮支撑轴承温度检测2
        TEMPERATURE_POINT_LINE_B.add("getLeftFrontTripodEccentricBigCopperSleeve"); //左前三脚架偏心大铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftRearTripodEccentricBigCopperSleeve"); //左后三脚架偏心大铜套
        TEMPERATURE_POINT_LINE_B.add("getRightFrontTripodEccentricBigCopperSleeve"); //右前三脚架偏心大铜套
        TEMPERATURE_POINT_LINE_B.add("getRightRearTripodEccentricBigCopperSleeve"); //右后三脚架偏心大铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftFrontTripodBottomCopperSleeve"); //左前三脚架下端铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftRearTripodBottomCopperSleeve"); //左后三脚架下端铜套
        TEMPERATURE_POINT_LINE_B.add("getRightFrontTripodBottomCopperSleeve"); //右前三脚架下端铜套
        TEMPERATURE_POINT_LINE_B.add("getRightRearTripodBottomCopperSleeve"); //右后三脚架下端铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftFrontConnectingRodBigCopperSleeve"); //左前连杆大铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftRearConnectingRodBigCopperSleeve"); //左后连杆大铜套
        TEMPERATURE_POINT_LINE_B.add("getRightFrontConnectingRodBigCopperSleeve"); //右前连杆大铜套
        TEMPERATURE_POINT_LINE_B.add("getRightRearConnectingRodBigCopperSleeve"); //右后连杆大铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftFrontLowSpeedAxisCopperSleeve"); //左前低速轴铜套
        TEMPERATURE_POINT_LINE_B.add("getLeftRearLowSpeedAxisCopperSleeve"); //左后低速轴铜套
        TEMPERATURE_POINT_LINE_B.add("getRightFrontLowSpeedAxisCopperSleeve"); //右前低速轴铜套
        TEMPERATURE_POINT_LINE_B.add("getRightRearLowSpeedAxisCopperSleeve"); //右后低速轴铜套
    }


}
