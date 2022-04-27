package com.plc.hmi.service;

import com.plc.hmi.dal.dao.TemperatureAlarmDao;
import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * 温度报警服务
 */
@Service
@Component
public class TemperatureAlarmService extends AbstractBaseService{
    @Resource
    TemperatureAlarmDao temperatureAlarmDao;

    private static Logger logger = LoggerFactory.getLogger(TemperatureAlarmService.class);

    /**
     * 历史报警信息查询
     */
    public List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(String startDate, String endDate, String lineType){
        List<TemperatureAlarmEntity> temperatureAlarmList = temperatureAlarmDao.getTemperatureAlarmWithParam(startDate, endDate, lineType);
        return temperatureAlarmList;

    }

    /**
     * 清理 month月内的报警数据
     * @param months
     */
    public void deleteHistoryData(int months) {
        temperatureAlarmDao.deleteHistoryData(months);
    }
}
