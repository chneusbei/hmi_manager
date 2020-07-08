package com.plc.hmi.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.dao.PropertyDao;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.util.HmiUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置信息服务， 非业务表， 用于技术配置
 */
@Service
@Component
public class PropertyService extends AbstractBaseService{
    @Resource
    PropertyDao propertyDao;
    public static Map<String, String> staticPropertyMap = new HashMap<String, String>();

    public List<PropertyEntity> getProperties() {
        return propertyDao.getProperties();
    }

    public List<PropertyEntity> getPropertyWithGroup(String group) {
        return propertyDao.getPropertiesWithGroup(group);
    }

    public void insertList(List<PropertyEntity> entityList) {
        if(entityList != null) {
            for (PropertyEntity entity : entityList) {
                this.insert(entity);
            }
        }
    }

    public void insert(PropertyEntity entity) {
        propertyDao.insert(entity);
    }

    public void update(PropertyEntity entity) {
        propertyDao.update(entity);
    }
    public void delete(PropertyEntity entity) {
        propertyDao.delete(entity);
    }

    public boolean isDubblePress() {
        boolean isDubblePress = false;
        int pressCount = 1;
        if(null != staticPropertyMap.get(ConfigConstants.PRESS_COUNT)) {
            pressCount =HmiUtils.getIntValue(staticPropertyMap.get(ConfigConstants.PRESS_COUNT));
        } else {
            List<PropertyEntity>  pressCountList = getPropertyWithGroup(ConfigConstants.GROUP_PRESS_COUNT);
            if(!CollectionUtils.isEmpty(pressCountList)) {
                PropertyEntity pressCountConfig = pressCountList.get(0);
                if(null != pressCountConfig) {
                   pressCount =HmiUtils.getIntValue(pressCountConfig.getPropValue());
                   staticPropertyMap.put(ConfigConstants.PRESS_COUNT,pressCountConfig.getPropValue());
                }
            }
        }
        if(pressCount > 1) {
            isDubblePress = true;
        } else {
            isDubblePress = false;
        }
//        System.out.println("pressCount = "+pressCount);
        return isDubblePress;
    }

    public int getcurveFetchFrequency() {
        int curveFetchFrequency = 0;
        if(null != staticPropertyMap.get(ConfigConstants.CURVE_FETCH_FREQUENCY)) {
            curveFetchFrequency = HmiUtils.getIntValue(staticPropertyMap.get(ConfigConstants.CURVE_FETCH_FREQUENCY));
        } else {
            List<PropertyEntity>  curveFetchFrequencyList = getPropertyWithGroup(ConfigConstants.GROUP_CURVE_FETCH_FREQUENCY);
            if(!CollectionUtils.isEmpty(curveFetchFrequencyList)) {
                PropertyEntity curveFetchFrequencyConfig = curveFetchFrequencyList.get(0);
                if(null != curveFetchFrequencyConfig) {
                    curveFetchFrequency =HmiUtils.getIntValue(curveFetchFrequencyConfig.getPropValue());
                    staticPropertyMap.put(ConfigConstants.CURVE_FETCH_FREQUENCY,curveFetchFrequencyConfig.getPropValue());
                }
            }
        }
//        System.out.println("curveFetchFrequency = "+curveFetchFrequency);
        return curveFetchFrequency;
    }

    public int getHeartbeatFrequency() {
        int heartbeatFrequency = 0;
        if(null != staticPropertyMap.get(ConfigConstants.HEARTBEAT_FREQUENCY)) {
            heartbeatFrequency = HmiUtils.getIntValue(staticPropertyMap.get(ConfigConstants.HEARTBEAT_FREQUENCY));
        } else {
            List<PropertyEntity>  heartbeatFrequencyList = getPropertyWithGroup(ConfigConstants.GROUP_HEARTBEAT_FREQUENCY);
            if(!CollectionUtils.isEmpty(heartbeatFrequencyList)) {
                PropertyEntity heartbeatFrequencyConfig = heartbeatFrequencyList.get(0);
                if(null != heartbeatFrequencyConfig) {
                    heartbeatFrequency =HmiUtils.getIntValue(heartbeatFrequencyConfig.getPropValue());
                    staticPropertyMap.put(ConfigConstants.HEARTBEAT_FREQUENCY,heartbeatFrequencyConfig.getPropValue());
                }
            }
        }
//        System.out.println("heartbeatFrequency = "+heartbeatFrequency);
        return heartbeatFrequency;
    }

}
