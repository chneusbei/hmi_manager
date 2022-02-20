package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import com.plc.hmi.dal.mapper.TemperatureAlarmMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TemperatureAlarmDao {
    @Resource
    TemperatureAlarmMapper temperatureAlarmMapper;

    public List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(String startDate, String endDate, String lineType) {
        return temperatureAlarmMapper.getTemperatureAlarmWithParam(startDate, endDate, lineType);
    }


    public void insert(TemperatureAlarmEntity temperatureAlarmEntity) {
        temperatureAlarmMapper.insert(temperatureAlarmEntity);
    }


}
