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
        List<String> dateList = HmiUtils.getFormatTcsDate(startDate, endDate);
        startDate = dateList.get(0);
        endDate = dateList.get(1);

        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, isWireless, lineType);
    }

    /**
     * 获取温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureWithParam(String startDate, String endDate, String plcName, String status, String lineType) {
        List<String> dateList = HmiUtils.getFormatTcsDate(startDate, endDate);
        startDate = dateList.get(0);
        endDate = dateList.get(1);
        if(StringUtils.isBlank(plcName)) {
            plcName = null;
        }

        return temperatureDao.getTemperatureWithParam(startDate, endDate, plcName, status, lineType);
    }


       /**
     * 获取一个点温度信息
     * @return
     */
    public List<TemperaturePointEntity> getTemperaturePointWithParam(String startDate, String endDate, String plcName, String temperatureName, String status,String lineType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TemperaturePointEntity> TemperaturePointEntityList = new ArrayList<TemperaturePointEntity>();
        // 参数解析

        if(StringUtils.isEmpty(plcName) || StringUtils.isBlank(plcName)) {
//            return TemperaturePointEntityList;
            if(lineType.equalsIgnoreCase(HmiConstants.LINE_TYPE.LINE_TYPE_A.getCode())) {
                plcName = HmiConstants.PLC_NAME_GROUP.PLC_A_1.getCode();
            } else {
                plcName = HmiConstants.PLC_NAME_GROUP.PLC_B_1.getCode();
            }
        }

        if(StringUtils.isEmpty(temperatureName)) {
//            return TemperaturePointEntityList;
            if(lineType.equalsIgnoreCase(HmiConstants.LINE_TYPE.LINE_TYPE_A.getCode())) {
                temperatureName = "getLeftFrontEccentricWheel";
            } else {
                temperatureName = "getLowSpeedAxisEccentricCopperSleeveTemperature1";
            }

        }

        List<String> dateList = HmiUtils.getFormatTcsDate(startDate, endDate);
        startDate = dateList.get(0);
        endDate = dateList.get(1);

        //获取历史数据
        TemperaturePointEntityList =  getTemperaturePointList(startDate, endDate,plcName, temperatureName, status, lineType);

        return TemperaturePointEntityList;
    }

    /**
     * 获取温度点列表信息
     * @return
     */
    public List<List<TemperaturePointEntity>> getTemperaturePointListWithParam(String startDate, String endDate, String plcName, String temperatureName, String status,String lineType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<List<TemperaturePointEntity>> TemperaturePointEntityTotalList = new ArrayList<>();
        // 参数解析
        List<String> dateList = HmiUtils.getFormatTcsDate(startDate, endDate);
        startDate = dateList.get(0);
        endDate = dateList.get(1);
        //PLC如果是空，根据lineType，获取所有的PLCName列表
        List<String> plcNameList = new ArrayList<String>();
        if(StringUtils.isNotEmpty(plcName)) {
            plcNameList.add(plcName);
        } else {
            plcNameList = HmiConstants.PLC_NAME_GROUP.getCodeListByLineType(lineType);
        }

        //如果温度点是空， 根据lineType 获取所有的温度点名称
        List<String> temperatureNameList = new ArrayList<String>();
        if(StringUtils.isNotEmpty(temperatureName)) {
            temperatureNameList.add(temperatureName);
        } else {
            if(lineType.equalsIgnoreCase(HmiConstants.LINE_TYPE.LINE_TYPE_A.getCode())) {
                temperatureNameList = HmiConstants.TEMPERATURE_POINT_LINE_A;
            } else{
                temperatureNameList = HmiConstants.TEMPERATURE_POINT_LINE_B;
            }
        }

        for(String plc : plcNameList) {
            for(String pointName : temperatureNameList) {
                List<TemperaturePointEntity> TemperaturePointEntityList =  getTemperaturePointList(startDate, endDate,plc, pointName, status, lineType);
                TemperaturePointEntityTotalList.add(TemperaturePointEntityList);
            }
        }

        return TemperaturePointEntityTotalList;
    }

    private List<TemperaturePointEntity> getTemperaturePointList(String startDate, String endDate, String plcName, String temperatureName, String status,String lineType)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TemperaturePointEntity> TemperaturePointEntityList = new ArrayList<TemperaturePointEntity>();
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
        logger.info(" getHisTemperature startDate={}, endDate={}, plcName={}, temperatureName={}" , startDate, endDate, plcName, temperatureName);
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        List<TemperaturePointEntity> temperaturePointList =  getTemperaturePointWithParam(startDate, endDate, plcName,temperatureName, null, lineType);
        return temperaturePointList;
    }


    /**
     * 获取历史温度点温度列表
     * @return
     */
    public List<List<TemperaturePointEntity>> getHisTemperatureList(String startDate,  String endDate,  String plcName,  String temperatureName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info(" getHisTemperatureList startDate={}, endDate={}, plcName={}, temperatureName={}" , startDate, endDate, plcName, temperatureName);
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        List<List<TemperaturePointEntity>> TemperaturePointEntityTotalList =  getTemperaturePointListWithParam(startDate, endDate, plcName,temperatureName, null, lineType);
//        String json = JSON.toJSONString(temperaturePointList);
//        temperaturePointList = new ArrayList<TemperaturePointEntity>();
        if(CollectionUtils.isEmpty(TemperaturePointEntityTotalList)) {
            List<TemperaturePointEntity> temperaturePointList = new ArrayList<TemperaturePointEntity>();
            TemperaturePointEntity entity = new TemperaturePointEntity();
            entity.setTemperatureValue(new BigDecimal(60));
            entity.setTemperatureTime(new Date());
            TemperaturePointEntity entity1 = new TemperaturePointEntity();
            entity1.setTemperatureValue(new BigDecimal(60));
            entity1.setTemperatureTime(new Date());
            temperaturePointList.add(entity);
            temperaturePointList.add(entity);
            TemperaturePointEntityTotalList.add(temperaturePointList);
        }
        return TemperaturePointEntityTotalList;
    }


    /**
     *获取最大ID
     * @return  long  id
     */
    public long getMaxId() {
        return temperatureDao.getMaxId();
    }

    /**temperatureConfig
     *根据最大ID获取大于这个ID的所有记录
     * @return List<TemperatureEntity>
     */
    public List<TemperatureEntity> getTemperatureWithMaxId(long maxId) {
        return temperatureDao.getTemperatureWithMaxId(maxId);
    }

}
