package com.plc.hmi.constants;
public class ConfigConstants {
    /**
     * PLC SERVER 属性数据库配置KEY
     */

    public static final String GROUP_PLC_SERVER = "plc.server";
    //HOST prefix
    public static final String PLC_SERVER_HOST_PREFIX = "s7://";
    //IP
    public static final String  PLC_SERVER_IP = "plc.server.ip";
    //机架号
    public static final String  PLC_SERVER_ROCK = "plc.server.rock";
    //插槽号
    public static final String  PLC_SERVER_SLOT = "plc.server.slot";

    /**
     * press.count 压头数量数据库配置
     */
    public static final String GROUP_PRESS_COUNT = "press.count";
    //压头数量
    public static final String PRESS_COUNT = "press.count";

    /**
     * xaxis xy轴起始结束值数据库配置
     */
    public static final String XMIN="axis.xMin";
    public static final String XMAX="axis.xMax";
    public static final String YMIN="axis.yMin";
    public static final String YMAX="axis.yMax";
    public static final String XMIN2="axis.xMin2";
    public static final String XMAX2="axis.xMax2";
    public static final String YMIN2="axis.yMin2";
    public static final String YMAX2="axis.yMax2";
    public static final String AXIS_GROUP="axis.value";

    /**
     * 系统与PLC心跳频率， 毫秒数
     */
    public static final String GROUP_HEARTBEAT_FREQUENCY = "heartbeat.frequency";
    public static final String HEARTBEAT_FREQUENCY = "heartbeat.frequency";

    /**
     * 系统取曲线频率，毫秒数
     */
    public static final String GROUP_CURVE_FETCH_FREQUENCY = "curve.fetch.frequency";
    public static final String CURVE_FETCH_FREQUENCY = "curve.fetch.frequency";

}
