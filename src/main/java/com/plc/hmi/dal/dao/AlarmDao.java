package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.dal.mapper.AlarmMapper;
import com.plc.hmi.enumeration.AlarmEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class AlarmDao {
    @Resource
    AlarmMapper alarmMapper;

    public List<AlarmEntity> getAlarm() {
        return alarmMapper.getAlarm();
    }

    public void insert(AlarmEntity entity) {
        alarmMapper.insert(entity);
    }

    public void updateAlarm(AlarmEntity entity) {
        alarmMapper.updateAlarm(entity);
    }

    public void delete(AlarmEntity entity) {
        alarmMapper.delete(entity);
    }


}
