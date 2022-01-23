package com.plc.hmi.service;

import com.plc.hmi.dal.dao.TemperatureDao;
import com.plc.hmi.dal.entity.TemperatureEntity;
import com.plc.hmi.dal.entity.TemperaturePointEntity;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 温度服务
 */
@Service
@Component
public class TemperatureService extends AbstractBaseService{
    @Resource
    TemperatureDao temperatureDao;

    private final Log logger = LogFactory.getLog(TemperatureService.class);

    /**
     * 获取温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, boolean isWireless) {
        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, isWireless);
    }

    /**
     * 获取温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status) {
        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status);
    }


    /**
     * 获取温度一个点信息
     * @return
     */
    public List<TemperaturePointEntity> getTemperaturePointWithParam(String startDate, String endDate, String plcName, String temperatureName,String status) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TemperaturePointEntity> TemperaturePointEntityList = new ArrayList<TemperaturePointEntity>();
       // 参数解析

        if(StringUtils.isEmpty(plcName)) {
            return TemperaturePointEntityList;
        }

        if(StringUtils.isEmpty(temperatureName)) {
            return TemperaturePointEntityList;
        }

               if(!StringUtils.isEmpty(startDate)) {
            startDate=startDate.replace("-","");
        }

        if(!StringUtils.isEmpty(endDate)) {
            endDate=endDate.replace("-","");
        }

        if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            startDate = HmiUtils.getYYYYMMDDString(new Date());
            endDate =startDate;
        } else if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            if(StringUtils.isEmpty(startDate)) {
                startDate = endDate;
            } else {
                endDate = startDate;
            }
        }

        //获取历史数据
        List<TemperatureEntity> TemperatureEntityList = temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status);

        //数据组装
        for(TemperatureEntity temperatureEntity: TemperatureEntityList) {
            Method method = temperatureEntity.getClass().getMethod(temperatureName);
            BigDecimal temperatureValue = (BigDecimal) method.invoke(temperatureEntity);
            Method method2 = temperatureEntity.getClass().getMethod("getCreateTime");
            Date temperatureTime = (Date) method2.invoke(temperatureEntity);
            TemperaturePointEntity temperaturePointEntity = new TemperaturePointEntity();
            temperaturePointEntity.setTemperatureTime(temperatureTime);
            temperaturePointEntity.setTemperatureValue(temperatureValue);
            TemperaturePointEntityList.add(temperaturePointEntity);
        }

        return TemperaturePointEntityList;
    }

}
