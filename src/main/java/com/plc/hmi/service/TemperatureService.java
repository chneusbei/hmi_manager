package com.plc.hmi.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.dao.TemperatureDao;
import com.plc.hmi.dal.entity.TemperatureEntity;
import com.plc.hmi.dal.entity.TemperaturePointEntity;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
    @Resource
    PropertyService propertyService;
    @Resource
    TemperatureAlarmService temperatureAlarmService;

    private static Logger logger = LoggerFactory.getLogger(TemperatureService.class);

    /**
     * 获取温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, boolean isWireless, String lineType) {
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

        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, isWireless, lineType);
    }

    /**
     * 获取温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, String lineType) {
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
        if(StringUtils.isBlank(plcName)) {
            plcName = null;
        }

        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, lineType);
    }


    /**
     * 获取温度一个点信息
     * @return
     */
    public List<TemperaturePointEntity> getTemperaturePointWithParam(String startDate, String endDate, String plcName, String temperatureName, String status,String lineType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TemperaturePointEntity> TemperaturePointEntityList = new ArrayList<TemperaturePointEntity>();
       // 参数解析

        if(StringUtils.isEmpty(plcName) || StringUtils.isBlank(plcName)) {
//            return TemperaturePointEntityList;
            plcName = "B-1";
        }

        if(StringUtils.isEmpty(temperatureName)) {
//            return TemperaturePointEntityList;
            temperatureName = "getLowSpeedAxisEccentricCopperSleeveTemperature1";
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
        List<TemperatureEntity> TemperatureEntityList = temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, lineType);

        //数据组装
        for(TemperatureEntity temperatureEntity: TemperatureEntityList) {
            Method method = temperatureEntity.getClass().getMethod(temperatureName);
            BigDecimal temperatureValue = (BigDecimal) method.invoke(temperatureEntity);
            method = temperatureEntity.getClass().getMethod("getCreateTime");
            Date temperatureTime = (Date) method.invoke(temperatureEntity);
            String plcNameStr= plcName;
            if(null == plcName) {
                method = temperatureEntity.getClass().getMethod("getPlcName");
                plcNameStr = (String) method.invoke(temperatureEntity);
            }
            method = temperatureEntity.getClass().getMethod("getHandleDate");
            String handleDate = (String) method.invoke(temperatureEntity);
            method = temperatureEntity.getClass().getMethod("getId");
            Long temperatureId = (Long) method.invoke(temperatureEntity);

            TemperaturePointEntity temperaturePointEntity = new TemperaturePointEntity();
            temperaturePointEntity.setLineType(lineType);
            temperaturePointEntity.setPlcName(plcNameStr);
            temperaturePointEntity.setHandleDate(handleDate);
            temperaturePointEntity.setTemperatureTime(temperatureTime);
            temperaturePointEntity.setTemperatureName(HmiConstants.TEMPERATURE_POINT.valueOf(temperatureName).getValue());
            temperaturePointEntity.setTemperatureValue(temperatureValue);
            temperaturePointEntity.setTemperatureId(temperatureId);
            TemperaturePointEntityList.add(temperaturePointEntity);
        }

        return TemperaturePointEntityList;
    }


    /**
     * 清理历史数据
     * 包括历史温度信息和历史报警信息。
     * @return
     */
    public void deleteHistoryData() {
       logger.info("开始清理历史数据:"+ HmiUtils.getFormatDateString(new Date()));
        String clearDateString = propertyService.getProperty(ConfigConstants.TEMPERATURE_DATA_CLEAR);
        BigDecimal months = new BigDecimal(clearDateString);
        temperatureDao.deleteHistoryData(months.intValue());
        temperatureAlarmService.deleteHistoryData(months.intValue());
        logger.info("清理历史数据完成:"+ HmiUtils.getFormatDateString(new Date()));
    }


    /**
     * 获取历史温度点温度
     * @return
     */
    public List<TemperaturePointEntity> getHisTemperature(String startDate,  String endDate,  String plcName,  String temperatureName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info(" getHisTemperature startDate={}, endDate={}, plcName={}" , startDate, endDate, plcName);
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        List<TemperaturePointEntity> temperaturePointList =  getTemperaturePointWithParam(startDate, endDate, plcName,temperatureName, null, lineType);
//        String json = JSON.toJSONString(temperaturePointList);
//        temperaturePointList = new ArrayList<TemperaturePointEntity>();
        if(CollectionUtils.isEmpty(temperaturePointList)) {
            TemperaturePointEntity entity = new TemperaturePointEntity();
            entity.setTemperatureValue(new BigDecimal(60));
            entity.setTemperatureTime(new Date());
            TemperaturePointEntity entity1 = new TemperaturePointEntity();
            entity1.setTemperatureValue(new BigDecimal(60));
            entity1.setTemperatureTime(new Date());
            temperaturePointList.add(entity);
            temperaturePointList.add(entity);
        }
        return temperaturePointList;
    }

}
