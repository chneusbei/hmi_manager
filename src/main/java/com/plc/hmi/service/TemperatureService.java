package com.plc.hmi.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.dao.PressureCurveDao;
import com.plc.hmi.dal.dao.TemperatureDao;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 压力曲线服务
 */
@Service
@Component
public class TemperatureService extends AbstractBaseService{
    @Resource
    TemperatureDao temperatureDao;

    private final Log logger = LogFactory.getLog(TemperatureService.class);

    /**
     * 获取产品压力曲线信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcIp, String status) {
        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcIp, status);
    }

}
