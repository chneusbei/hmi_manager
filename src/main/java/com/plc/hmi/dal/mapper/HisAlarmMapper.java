package com.plc.hmi.dal.mapper;


import com.plc.hmi.dal.entity.HisAlarmEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface HisAlarmMapper {
    @Select("select * from his_alarm_info where is_deleted='0' ")
    List<HashMap> getHisAlarm();

    @Insert({"insert into his_alarm_info(id, ALARM_ID, ALARM_START_TIME, ALARM_STOP_TIME,ALARM_STATUS,ALARM_CFM_STATUS, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{alarmId}, #{alarmStartTime}, #{alarmStopTime}, #{alarmStatus}, #{alarmCfmStatus}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(HisAlarmEntity  entity);

    @Update({"update his_alarm_info set ALARM_STATUS=#{alarmStatus}, ALARM_CFM_STATUS=#{alarmCfmStatus}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void updateHisAlarm(HisAlarmEntity entity);


    @Update({"update his_alarm_info set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(HisAlarmEntity entity);
}
