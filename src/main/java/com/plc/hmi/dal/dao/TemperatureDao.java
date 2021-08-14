package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.TemperatureEntity;
import com.plc.hmi.dal.mapper.TemperatureMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TemperatureDao {
    @Resource
    TemperatureMapper temperatureMapper;

    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcIp, String status) {
        return temperatureMapper.getTemperatureWithParam(startDate, endDate, plcIp, status);
    }

    public void insert(TemperatureEntity temperatureEntity) {
        temperatureMapper.insert(temperatureEntity);
    }


}
