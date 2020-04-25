package com.plc.hmi.dal.mapper;


import com.plc.hmi.dal.entity.HisAlarmEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface HisAlarmMapper {
    @Select("select * from his_alarm_info where is_deleted='0' ")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "ALARM_ID",property ="alarmId"),
            @Result(column = "ALARM_INFO",property ="alarmInfo"),
            @Result(column = "TRIGGER_DB",property ="triggerDb"),
            @Result(column = "TRIGGER_OFFSET",property ="triggerOffset"),
            @Result(column = "TRIGGER_BIT",property ="triggerBit"),
            @Result(column = "ALARM_TYPE",property ="alarmType"),
            @Result(column = "ALARM_GROUP",property ="alarmGroup"),
            @Result(column = "ALARM_START_TIME",property ="alarmStartTime"),
            @Result(column = "ALARM_STOP_TIME",property ="alarmStopTime"),
            @Result(column = "ALARM_STATUS",property ="alarmStatus"),
            @Result(column = "ALARM_CFM_STATUS",property ="alarmCfmStatus"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<HisAlarmEntity> getHisAlarm();

    @Insert({"insert into his_alarm_info(id, ALARM_ID, ALARM_INFO,TRIGGER_DB,TRIGGER_OFFSET,TRIGGER_BIT,ALARM_TYPE, ALARM_GROUP,ALARM_START_TIME, ALARM_STOP_TIME,ALARM_STATUS,ALARM_CFM_STATUS, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{alarmId}, #{alarmInfo}, #{triggerDb}, #{triggerOffset}, #{triggerBit}, #{alarmType}, #{alarmGroup},#{alarmStartTime}, #{alarmStopTime}, #{alarmStatus}, #{alarmCfmStatus}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(HisAlarmEntity  entity);

    @Update({"update his_alarm_info set ALARM_STATUS=#{alarmStatus}, ALARM_CFM_STATUS=#{alarmCfmStatus}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void updateHisAlarm(HisAlarmEntity entity);


    @Update({"update his_alarm_info set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(HisAlarmEntity entity);
}
