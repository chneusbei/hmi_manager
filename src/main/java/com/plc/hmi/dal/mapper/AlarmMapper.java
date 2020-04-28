package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.AlarmEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AlarmMapper {
    @Select("select * from alarm_info where is_deleted='0'")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "TRIGGER_DB",property ="triggerDb"),
            @Result(column = "TRIGGER_OFFSET",property ="triggerOffset"),
            @Result(column = "TRIGGER_BIT",property ="triggerBit"),
            @Result(column = "ALARM_TYPE",property ="alarmType"),
            @Result(column = "ALARM_GROUP",property ="alarmGroup"),
            @Result(column = "ACTIVE",property ="active"),
            @Result(column = "ALARM_INFO",property ="alarmInfo"),
            @Result(column = "ALARM_HELP",property ="alarmHelp"),
            @Result(column = "ALARM_STATUS",property ="alarmStatus"),
            @Result(column = "ALARM_TIME",property ="alarmTime"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<AlarmEntity> getAlarm();

    @Insert({"insert into alarm_info(id, TRIGGER_DB, TRIGGER_OFFSET, TRIGGER_BIT, ALARM_TYPE, ALARM_GROUP, ACTIVE, ALARM_INFO, ALARM_HELP, ALARM_STATUS, ALARM_TIME, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{triggerDb}, #{triggerOffset}, #{triggerBit}, #{alarmType}, #{alarmGroup}, #{active}, #{alarmInfo}, #{alarmHelp}, #{alarmStatus}, #{alarmTime}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(AlarmEntity entity);

    @Update({"update alarm_info set ALARM_STATUS=#{alarmStatus}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void updateAlarm(AlarmEntity entity);

    @Update({"update alarm_info set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(AlarmEntity entity);
}
