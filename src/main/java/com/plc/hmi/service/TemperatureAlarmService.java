package com.plc.hmi.service;

import com.plc.hmi.dal.dao.TemperatureAlarmDao;
import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private final Log logger = LogFactory.getLog(TemperatureAlarmService.class);

    /**
     * 历史报警信息查询
     */
    public List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(String startDate, String endDate, String lineType, int limit){
        List<TemperatureAlarmEntity> temperatureAlarmList = temperatureAlarmDao.getTemperatureAlarmWithParam(startDate, endDate, lineType, limit);
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
