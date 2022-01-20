package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TemperatureEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemperatureMapper {

    @Select({"<script>",
            "select * from temperature where IS_DELETED='0' and HANDLE_DATE between  #{startDate} and #{endDate} ",
            "<if test='plcName!=null' >",
            "and PLC_NAME = #{plcName}",
            "</if>",
            "<if test='status!=null' >",
            "and STATUS = #{status}",
            "</if>",
            "<if test='isWireless!=null' >",
            "and IS_WIRELESS = #{isWireless}",
            "</if>",
            "order by id asc",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "BATCH_ID",property ="batchId"),
            @Result(column = "PLC_IP",property ="plcIp"),
            @Result(column = "PLC_NAME",property ="plcName"),
            @Result(column = "IS_WIRELESS",property ="isWireless"),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "STATUS",property ="status"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1",property ="lowSpeedAxisEccentricCopperSleeveTemperature1"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2",property ="lowSpeedAxisEccentricCopperSleeveTemperature2"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_3",property ="lowSpeedAxisEccentricCopperSleeveTemperature3"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_4",property ="lowSpeedAxisEccentricCopperSleeveTemperature4"),
            @Result(column = "HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1",property ="highSpeedAxisEccentricCopperSleeveTemperature1"),
            @Result(column = "HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2",property ="highSpeedAxisEccentricCopperSleeveTemperature2"),
            @Result(column = "FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_1",property ="flywheelSupportBigAxisTemperature1"),
            @Result(column = "FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_2",property ="flywheelSupportBigAxisTemperature2"),
            @Result(column = "HIGH_SPEED_AXIS_ROLLING_BEARING_TEMPERATURE_1",property ="highSpeedAxisRollingBearingTemperature1"),
            @Result(column = "HIGH_SPEED_AXIS_ROLLING_BEARING_TEMPERATURE_2",property ="highSpeedAxisRollingBearingTemperature2"),
            @Result(column = "LEFT_FRONT_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE",property ="leftFrontTripodEccentricBigCopperSleeve"),
            @Result(column = "LEFT_REAR_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE",property ="leftRearTripodEccentricBigCopperSleeve"),
            @Result(column = "RIGHT_FRONT_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE",property ="rightFrontTripodEccentricBigCopperSleeve"),
            @Result(column = "RIGHT_REAR_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE",property ="rightRearTripodEccentricBigCopperSleeve"),
            @Result(column = "LEFT_FRONT_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE",property ="leftFrontTripodBottomCopperSleeve"),
            @Result(column = "LEFT_REAR_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE",property ="leftRearTripodBottomCopperSleeve"),
            @Result(column = "RIGHT_FRONT_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE",property ="rightFrontTripodBottomCopperSleeve"),
            @Result(column = "RIGHT_REAR_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE",property ="rightRearTripodBottomCopperSleeve"),
            @Result(column = "LEFT_FRONT_CONNECTING_ROD_BIG_COPPER_SLEEVE",property ="leftFrontConnectingRodBigCopperSleeve"),
            @Result(column = "LEFT_REAR_CONNECTING_ROD_BIG_COPPER_SLEEVE",property ="leftRearConnectingRodBigCopperSleeve"),
            @Result(column = "RIGHT_FRONT_CONNECTING_ROD_BIG_COPPER_SLEEVE",property ="rightFrontConnectingRodBigCopperSleeve"),
            @Result(column = "RIGHT_REAR_CONNECTING_ROD_BIG_COPPER_SLEEVE",property ="rightRearConnectingRodBigCopperSleeve"),
            @Result(column = "LEFT_FRONT_LOW_SPEED_AXIS_COPPER_SLEEVE",property ="leftFrontLowSpeedAxisCopperSleeve"),
            @Result(column = "LEFT_REAR_LOW_SPEED_AXIS_COPPER_SLEEVE",property ="leftRearLowSpeedAxisCopperSleeve"),
            @Result(column = "RIGHT_FRONT_LOW_SPEED_AXIS_COPPER_SLEEVE",property ="rightFrontLowSpeedAxisCopperSleeve"),
            @Result(column = "RIGHT_REAR_LOW_SPEED_AXIS_COPPER_SLEEVE",property ="rightRearLowSpeedAxisCopperSleeve"),
            @Result(column = "BACKUP_TEMPERATURE_0",property ="backupTemperature0"),
            @Result(column = "BACKUP_TEMPERATURE_1",property ="backupTemperature1"),
            @Result(column = "BACKUP_TEMPERATURE_2",property ="backupTemperature2"),
            @Result(column = "BACKUP_TEMPERATURE_3",property ="backupTemperature3"),
            @Result(column = "BACKUP_TEMPERATURE_4",property ="backupTemperature4"),
            @Result(column = "BACKUP_TEMPERATURE_5",property ="backupTemperature5"),
            @Result(column = "BACKUP_TEMPERATURE_6",property ="backupTemperature6"),
            @Result(column = "BACKUP_TEMPERATURE_7",property ="backupTemperature7"),
            @Result(column = "BACKUP_TEMPERATURE_8",property ="backupTemperature8"),
            @Result(column = "BACKUP_TEMPERATURE_9",property ="backupTemperature9"),
            @Result(column = "BACKUP_TEMPERATURE_10",property ="backupTemperature10"),
            @Result(column = "BACKUP_TEMPERATURE_11",property ="backupTemperature11"),
            @Result(column = "TEMPERATURE_WARNING_VALUE_1",property ="temperatureWarningValue1"),
            @Result(column = "TEMPERATURE_WARNING_VALUE_2",property ="temperatureWarningValue2"),
            @Result(column = "CREATE_TIME",property ="createTime"),
    })
    List<TemperatureEntity> getTemperatureWithParam(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("plcName") String plcName, @Param("status") String status, @Param("isWireless") String isWireless);


    @Insert({"INSERT INTO temperature(ID, BATCH_ID, PLC_IP, PLC_NAME, IS_WIRELESS, HANDLE_DATE, STATUS, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_3, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_4, HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1, HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2, FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_1, FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_2, HIGH_SPEED_AXIS_ROLLING_BEARING_TEMPERATURE_1, HIGH_SPEED_AXIS_ROLLING_BEARING_TEMPERATURE_2, LEFT_FRONT_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE, LEFT_REAR_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE, RIGHT_FRONT_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE, RIGHT_REAR_TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE, LEFT_FRONT_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE, LEFT_REAR_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE, RIGHT_FRONT_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE, RIGHT_REAR_TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE, LEFT_FRONT_CONNECTING_ROD_BIG_COPPER_SLEEVE, LEFT_REAR_CONNECTING_ROD_BIG_COPPER_SLEEVE, RIGHT_FRONT_CONNECTING_ROD_BIG_COPPER_SLEEVE, RIGHT_REAR_CONNECTING_ROD_BIG_COPPER_SLEEVE, LEFT_FRONT_LOW_SPEED_AXIS_COPPER_SLEEVE, LEFT_REAR_LOW_SPEED_AXIS_COPPER_SLEEVE, RIGHT_FRONT_LOW_SPEED_AXIS_COPPER_SLEEVE, RIGHT_REAR_LOW_SPEED_AXIS_COPPER_SLEEVE, BACKUP_TEMPERATURE_0, BACKUP_TEMPERATURE_1, BACKUP_TEMPERATURE_2, BACKUP_TEMPERATURE_3, BACKUP_TEMPERATURE_4, BACKUP_TEMPERATURE_5, BACKUP_TEMPERATURE_6, BACKUP_TEMPERATURE_7, BACKUP_TEMPERATURE_8, BACKUP_TEMPERATURE_9, BACKUP_TEMPERATURE_10, BACKUP_TEMPERATURE_11, TEMPERATURE_WARNING_VALUE_1, TEMPERATURE_WARNING_VALUE_2, CREATE_BY, UPDATE_BY, CREATE_TIME, UPDATE_TIME, IS_DELETED) values(null,#{entity.batchId},#{entity.plcIp},#{entity.plcName},#{entity.isWireless}, #{entity.handleDate}, #{entity.status}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature1}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature2}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature3}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature4}, #{entity.highSpeedAxisEccentricCopperSleeveTemperature1}, #{entity.highSpeedAxisEccentricCopperSleeveTemperature2}, #{entity.flywheelSupportBigAxisTemperature1}, #{entity.flywheelSupportBigAxisTemperature2}, #{entity.highSpeedAxisRollingBearingTemperature1},#{entity.highSpeedAxisRollingBearingTemperature2}, #{entity.leftFrontTripodEccentricBigCopperSleeve}, #{entity.leftRearTripodEccentricBigCopperSleeve}, #{entity.rightFrontTripodEccentricBigCopperSleeve}, #{entity.rightRearTripodEccentricBigCopperSleeve},#{entity.leftFrontTripodBottomCopperSleeve}, #{entity.leftRearTripodBottomCopperSleeve}, #{entity.rightFrontTripodBottomCopperSleeve}, #{entity.rightRearTripodBottomCopperSleeve}, #{entity.leftFrontConnectingRodBigCopperSleeve},#{entity.leftRearConnectingRodBigCopperSleeve}, #{entity.rightFrontConnectingRodBigCopperSleeve}, #{entity.rightRearConnectingRodBigCopperSleeve}, #{entity.leftFrontLowSpeedAxisCopperSleeve}, #{entity.leftRearLowSpeedAxisCopperSleeve},#{entity.rightFrontLowSpeedAxisCopperSleeve}, #{entity.rightRearLowSpeedAxisCopperSleeve}, #{entity.backupTemperature0}, #{entity.backupTemperature1}, #{entity.backupTemperature2},#{entity.backupTemperature3}, #{entity.backupTemperature4}, #{entity.backupTemperature5}, #{entity.backupTemperature6}, #{entity.backupTemperature7},#{entity.backupTemperature8}, #{entity.backupTemperature9}, #{entity.backupTemperature10}, #{entity.backupTemperature11}, #{entity.temperatureWarningValue1},#{entity.temperatureWarningValue2},  #{entity.createBy}, #{entity.updateBy}, now(), now(), '0')"})
    void insert(@Param("entity") TemperatureEntity entity);

    /*
    @Select({"<script>",
            "select * from temperature where IS_DELETED='0' and HANDLE_DATE between  #{startDate} and #{endDate} ",
            "<if test='plcName!=null' >",
            "and PLC_NAME = #{plcName}",
            "</if>",
            "<if test='status!=null' >",
            "and STATUS = #{status}",
            "</if>",
            "order by id asc",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PLC_NAME",property ="plcName"),
            @Result(column = "PLC_IP",property ="plcIp"),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "STATUS",property ="status"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1",property ="lowSpeedAxisEccentricCopperSleeveTemperature1"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2",property ="lowSpeedAxisEccentricCopperSleeveTemperature2"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_3",property ="lowSpeedAxisEccentricCopperSleeveTemperature3"),
            @Result(column = "LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_4",property ="lowSpeedAxisEccentricCopperSleeveTemperature4"),
            @Result(column = "HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1",property ="highSpeedAxisEccentricCopperSleeveTemperature1"),
            @Result(column = "HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2",property ="highSpeedAxisEccentricCopperSleeveTemperature2"),
            @Result(column = "FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_1",property ="flywheelSupportBigAxisTemperature1"),
            @Result(column = "FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_2",property ="flywheelSupportBigAxisTemperature2"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_1",property ="drawbarBothEndsCopperSleeveTemperature1"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_2",property ="drawbarBothEndsCopperSleeveTemperature2"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_3",property ="drawbarBothEndsCopperSleeveTemperature3"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_4",property ="drawbarBothEndsCopperSleeveTemperature4"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_5",property ="drawbarBothEndsCopperSleeveTemperature5"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_6",property ="drawbarBothEndsCopperSleeveTemperature6"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_7",property ="drawbarBothEndsCopperSleeveTemperature7"),
            @Result(column = "DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_8",property ="drawbarBothEndsCopperSleeveTemperature8"),
            @Result(column = "SMALL_BELT_WHEEL_SUPPORT_AXIS_TEMPERATURE_1",property ="smallBeltWheelSupportAxisTemperature1"),
            @Result(column = "SMALL_BELT_WHEEL_SUPPORT_AXIS_TEMPERATURE_2",property ="smallBeltWheelSupportAxisTemperature2"),
            @Result(column = "TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_1",property ="tripodBottomCopperSleeveTemperature1"),
            @Result(column = "TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_2",property ="tripodBottomCopperSleeveTemperature2"),
            @Result(column = "TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_3",property ="tripodBottomCopperSleeveTemperature3"),
            @Result(column = "TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_4",property ="tripodBottomCopperSleeveTemperature4"),
            @Result(column = "TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_1",property ="tripodEccentricBigCopperSleeveTemperature1"),
            @Result(column = "TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_2",property ="tripodEccentricBigCopperSleeveTemperature2"),
            @Result(column = "TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_3",property ="tripodEccentricBigCopperSleeveTemperature3"),
            @Result(column = "TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_4",property ="tripodEccentricBigCopperSleeveTemperature4"),
            @Result(column = "BACKUP_TEMPERATURE_0",property ="backupTemperature0"),
            @Result(column = "BACKUP_TEMPERATURE_1",property ="backupTemperature1"),
            @Result(column = "BACKUP_TEMPERATURE_2",property ="backupTemperature2"),
            @Result(column = "BACKUP_TEMPERATURE_3",property ="backupTemperature3"),
            @Result(column = "BACKUP_TEMPERATURE_4",property ="backupTemperature4"),
            @Result(column = "BACKUP_TEMPERATURE_5",property ="backupTemperature5"),
            @Result(column = "BACKUP_TEMPERATURE_6",property ="backupTemperature6"),
            @Result(column = "BACKUP_TEMPERATURE_7",property ="backupTemperature7"),
            @Result(column = "BACKUP_TEMPERATURE_8",property ="backupTemperature8"),
            @Result(column = "BACKUP_TEMPERATURE_9",property ="backupTemperature9"),
            @Result(column = "BACKUP_TEMPERATURE_10",property ="backupTemperature10"),
            @Result(column = "BACKUP_TEMPERATURE_11",property ="backupTemperature11"),
            @Result(column = "TEMPERATURE_WARNING_VALUE_1",property ="temperatureWarningValue1"),
            @Result(column = "TEMPERATURE_WARNING_VALUE_2",property ="temperatureWarningValue2"),
            @Result(column = "CREATE_TIME",property ="createTime"),
    })
    List<TemperatureEntity> getTemperatureWithParam(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("plcName") String plcName, @Param("status") String status);


    @Insert({"INSERT INTO temperature(ID, PLC_IP, PLC_NAME, HANDLE_DATE, STATUS, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_3, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_4, HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1, HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2, FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_1, FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_2, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_1, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_2, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_3, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_4, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_5, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_6, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_7, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_8, SMALL_BELT_WHEEL_SUPPORT_AXIS_TEMPERATURE_1, SMALL_BELT_WHEEL_SUPPORT_AXIS_TEMPERATURE_2, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_1, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_2, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_3, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_4, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_1, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_2, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_3, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_4, BACKUP_TEMPERATURE_0, BACKUP_TEMPERATURE_1, BACKUP_TEMPERATURE_2, BACKUP_TEMPERATURE_3, BACKUP_TEMPERATURE_4, BACKUP_TEMPERATURE_5, BACKUP_TEMPERATURE_6, BACKUP_TEMPERATURE_7, BACKUP_TEMPERATURE_8, BACKUP_TEMPERATURE_9, BACKUP_TEMPERATURE_10, BACKUP_TEMPERATURE_11, TEMPERATURE_WARNING_VALUE_1, TEMPERATURE_WARNING_VALUE_2, CREATE_BY, UPDATE_BY, CREATE_TIME, UPDATE_TIME, IS_DELETED) values(null, #{entity.plcIp},#{entity.plcName}, #{entity.handleDate}, #{entity.status}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature1}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature2}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature3}, #{entity.lowSpeedAxisEccentricCopperSleeveTemperature4}, #{entity.highSpeedAxisEccentricCopperSleeveTemperature1}, #{entity.highSpeedAxisEccentricCopperSleeveTemperature2}, #{entity.flywheelSupportBigAxisTemperature1}, #{entity.flywheelSupportBigAxisTemperature2}, #{entity.drawbarBothEndsCopperSleeveTemperature1},#{entity.drawbarBothEndsCopperSleeveTemperature2}, #{entity.drawbarBothEndsCopperSleeveTemperature3}, #{entity.drawbarBothEndsCopperSleeveTemperature4}, #{entity.drawbarBothEndsCopperSleeveTemperature5}, #{entity.drawbarBothEndsCopperSleeveTemperature6},#{entity.drawbarBothEndsCopperSleeveTemperature7}, #{entity.drawbarBothEndsCopperSleeveTemperature8}, #{entity.smallBeltWheelSupportAxisTemperature1}, #{entity.smallBeltWheelSupportAxisTemperature2}, #{entity.tripodBottomCopperSleeveTemperature1},#{entity.tripodBottomCopperSleeveTemperature2}, #{entity.tripodBottomCopperSleeveTemperature3}, #{entity.tripodBottomCopperSleeveTemperature4}, #{entity.tripodEccentricBigCopperSleeveTemperature1}, #{entity.tripodEccentricBigCopperSleeveTemperature2},#{entity.tripodEccentricBigCopperSleeveTemperature3}, #{entity.tripodEccentricBigCopperSleeveTemperature4}, #{entity.backupTemperature0}, #{entity.backupTemperature1}, #{entity.backupTemperature2},#{entity.backupTemperature3}, #{entity.backupTemperature4}, #{entity.backupTemperature5}, #{entity.backupTemperature6}, #{entity.backupTemperature7},#{entity.backupTemperature8}, #{entity.backupTemperature9}, #{entity.backupTemperature10}, #{entity.backupTemperature11}, #{entity.temperatureWarningValue1},#{entity.temperatureWarningValue2},  #{entity.createBy}, #{entity.updateBy}, now(), now(), '0')"})
    void insert(@Param("entity") TemperatureEntity entity);
*/
}
