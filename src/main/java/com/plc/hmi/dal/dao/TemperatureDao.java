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

    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, boolean isWireless, String lineType) {
        return temperatureMapper.getTemperatureWithParam(startDate, endDate, plcName, status, isWireless ? "1" : "0", lineType);
    }

    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, String lineType) {
        List<TemperatureEntity> temperatureEntityList =  temperatureMapper.getTemperatureWithParam(startDate, endDate, plcName, status, null, lineType);
        return temperatureEntityList;
    }

    public void insert(TemperatureEntity temperatureEntity) {
        temperatureMapper.insert(temperatureEntity);
    }


    public void deleteHistoryData(int months) {
        temperatureMapper.deleteHistoryData(months);
    }
}
