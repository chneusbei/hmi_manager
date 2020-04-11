package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.AlarmEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AlarmMapper {
    @Select("select * from alarm_info where is_deleted='0'")
    List<HashMap> getAlarm();

    @Insert({"insert into alarm_info(id, TRIGGER_DB, TRIGGER_OFFSET, TRIGGER_BIT, ALARM_TYPE, ALARM_GROUP, ACTIVE, ALARM_INFO, ALARM_HELP, ALARM_STATUS, ALARM_TIME, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{triggerDb}, #{triggerOffset}, #{triggerBit}, #{alarmType}, #{alarmGroup}, #{active}, #{alarmInfo}, #{alarmHelp}, #{alarmStatus}, #{alarmTime}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(AlarmEntity entity);

    @Update({"update alarm_info set ALARM_STATUS=#{alarmStatus}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void updateAlarm(AlarmEntity entity);

    @Update({"update alarm_info set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(AlarmEntity entity);
}
