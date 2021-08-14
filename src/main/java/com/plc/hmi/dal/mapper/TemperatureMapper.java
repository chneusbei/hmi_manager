package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TemperatureEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemperatureMapper {

    @Select({"<script>",
            "select * from temperature where IS_DELETED='0' and HANDLE_DATE between  #{startDate} and #{endDate} ",
            "<if test='plcIp!=null' >",
            "and PLC_IP = #{plcIp}",
            "</if>",
            "<if test='STATUS!=null' >",
            "and STATUS = #{status}",
            "</if>",
            "order by id",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
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
    })
    List<TemperatureEntity> getTemperatureWithParam(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("plcIp") String plcIp, @Param("status") String status);


    @Insert({"INSERT INTO temperature(PLC_IP, HANDLE_DATE, STATUS, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_3, LOW_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_4, HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_1, HIGH_SPEED_AXIS_ECCENTRIC_COPPER_SLEEVE_TEMPERATURE_2, FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_1, FLYWHEEL_SUPPORT_BIG_AXIS_TEMPERATURE_2, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_1, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_2, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_3, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_4, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_5, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_6, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_7, DRAWBAR_BOTH_ENDS_COPPER_SLEEVE_TEMPERATURE_8, SMALL_BELT_WHEEL_SUPPORT_AXIS_TEMPERATURE_1, SMALL_BELT_WHEEL_SUPPORT_AXIS_TEMPERATURE_2, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_1, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_2, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_3, TRIPOD_BOTTOM_COPPER_SLEEVE_TEMPERATURE_4, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_1, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_2, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_3, TRIPOD_ECCENTRIC_BIG_COPPER_SLEEVE_TEMPERATURE_4, BACKUP_TEMPERATURE_0, BACKUP_TEMPERATURE_1, BACKUP_TEMPERATURE_2, BACKUP_TEMPERATURE_3, BACKUP_TEMPERATURE_4, BACKUP_TEMPERATURE_5, BACKUP_TEMPERATURE_6, BACKUP_TEMPERATURE_7, BACKUP_TEMPERATURE_8, BACKUP_TEMPERATURE_9, BACKUP_TEMPERATURE_10, BACKUP_TEMPERATURE_11, TEMPERATURE_WARNING_VALUE_1, TEMPERATURE_WARNING_VALUE_2, CREATE_BY, UPDATE_BY, CREATE_TIME, UPDATE_TIME, IS_DELETED) values(null, #{plcIp}, #{handleDate}, #{status}, #{lowSpeedAxisEccentricCopperSleeveTemperature1}, #{lowSpeedAxisEccentricCopperSleeveTemperature2}, #{lowSpeedAxisEccentricCopperSleeveTemperature3}, #{lowSpeedAxisEccentricCopperSleeveTemperature4}, #{highSpeedAxisEccentricCopperSleeveTemperature1}, #{highSpeedAxisEccentricCopperSleeveTemperature2}, #{flywheelSupportBigAxisTemperature1}, #{flywheelSupportBigAxisTemperature2}, #{drawbarBothEndsCopperSleeveTemperature1},#{drawbarBothEndsCopperSleeveTemperature2}, #{drawbarBothEndsCopperSleeveTemperature3}, #{drawbarBothEndsCopperSleeveTemperature4}, #{drawbarBothEndsCopperSleeveTemperature5}, #{drawbarBothEndsCopperSleeveTemperature6},#{drawbarBothEndsCopperSleeveTemperature7}, #{drawbarBothEndsCopperSleeveTemperature8}, #{smallBeltWheelSupportAxisTemperature1}, #{smallBeltWheelSupportAxisTemperature2}, #{tripodBottomCopperSleeveTemperature1},#{tripodBottomCopperSleeveTemperature2}, #{tripodBottomCopperSleeveTemperature3}, #{tripodBottomCopperSleeveTemperature4}, #{tripodEccentricBigCopperSleeveTemperature1}, #{tripodEccentricBigCopperSleeveTemperature2},#{tripodEccentricBigCopperSleeveTemperature3}, #{tripodEccentricBigCopperSleeveTemperature4}, #{backupTemperature0}, #{backupTemperature1}, #{backupTemperature2},#{backupTemperature3}, #{backupTemperature4}, #{backupTemperature5}, #{backupTemperature6}, #{backupTemperature7},#{backupTemperature8}, #{backupTemperature9}, #{backupTemperature10}, #{backupTemperature11}, #{temperatureWarningValue1},#{temperatureWarningValue2}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(@Param("entity") TemperatureEntity entity);

}
