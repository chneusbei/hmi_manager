package com.plc.hmi.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.dao.TemperatureDao;
import com.plc.hmi.dal.entity.TemperatureEntity;
import com.plc.hmi.dal.entity.TemperaturePointEntity;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

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

    private final Log logger = LogFactory.getLog(TemperatureService.class);

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

        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, isWireless, lineType, 0);
    }

    /**
     * 获取温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, String lineType, int limit) {
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

        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, lineType, limit);
    }


    /**
     * 获取温度一个点信息
     * @return
     */
    public List<TemperaturePointEntity> getTemperaturePointWithParam(String startDate, String endDate, String plcName, String temperatureName, String status,String lineType, int limit) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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
        List<TemperatureEntity> TemperatureEntityList = temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, lineType, limit);

        //数据组装
        for(TemperatureEntity temperatureEntity: TemperatureEntityList) {
            Method method = temperatureEntity.getClass().getMethod(temperatureName);
            BigDecimal temperatureValue = (BigDecimal) method.invoke(temperatureEntity);
            method = temperatureEntity.getClass().getMethod("getCreateTime");
            Date temperatureTime = (Date) method.invoke(temperatureEntity);
            String plcNameStr= plcName;
            if(null == plcName) {
                method = temperatureEntity.getClass().getMethod("plcName");
                plcNameStr = (String) method.invoke(temperatureEntity);
            }
            method = temperatureEntity.getClass().getMethod("handleDate");
            String handleDate = (String) method.invoke(temperatureEntity);
            method = temperatureEntity.getClass().getMethod("id");
            Long temperatureId = (Long) method.invoke(temperatureEntity);

            TemperaturePointEntity temperaturePointEntity = new TemperaturePointEntity();
            temperaturePointEntity.setLineType(lineType);
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
        String clearDateString = propertyService.getProperty(ConfigConstants.TEMPERATURE_DATA_CLEAR);
        BigDecimal months = new BigDecimal(clearDateString);
        temperatureDao.deleteHistoryData(months.intValue());
        temperatureAlarmService.deleteHistoryData(months.intValue());
    }


    /**
     * 获取历史温度点温度
     * @return
     */
    public List<TemperaturePointEntity> getHisTemperature(String startDate,  String endDate,  String plcName,  String temperatureName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //        System.out.println(" getHisTemperature startDate" + startDate + ", endDate"+endDate + ", plcName = "+ plcName);
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        List<TemperaturePointEntity> temperaturePointList =  getTemperaturePointWithParam(startDate, endDate, plcName,temperatureName, null, lineType, 20);
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
