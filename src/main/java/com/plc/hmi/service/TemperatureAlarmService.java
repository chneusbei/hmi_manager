package com.plc.hmi.service;

import com.plc.hmi.dal.dao.TemperatureAlarmDao;
import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * 温度报警服务
 */
@Service
@Component
public class TemperatureAlarmService extends AbstractBaseService{
    @Resource
    TemperatureAlarmDao temperatureAlarmEntity;

    private final Log logger = LogFactory.getLog(TemperatureAlarmService.class);

    /**
     * 历史报警信息查询
     */
    public List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(@RequestParam(value = "startDate",required = true) String startDate,
                                                              @RequestParam(value = "endDate",required = true) String endDate){
        List<TemperatureAlarmEntity> temperatureAlarmList = temperatureAlarmEntity.getTemperatureAlarmWithParam(startDate, endDate);
        return temperatureAlarmList;

    }
}
