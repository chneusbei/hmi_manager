package com.plc.hmi.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.dao.PropertyDao;
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
        List<PropertyEntity> propList = propertyDao.getProperties();
        if(!CollectionUtils.isEmpty(propList)) {
            for(PropertyEntity property : propList) {
                staticPropertyMap.put(property.getPropName(),property.getPropValue());
            }
        }
        return propList;
    }

    public List<PropertyEntity> getPropertyWithGroup(String group) {
        List<PropertyEntity> propList = propertyDao.getPropertiesWithGroup(group);
        if(!CollectionUtils.isEmpty(propList)) {
            for(PropertyEntity property : propList) {
                staticPropertyMap.put(property.getPropName(),property.getPropValue());
            }
        }
        return propList;
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
        if(!staticPropertyMap.containsKey(ConfigConstants.PRESS_COUNT)) {
            getPropertyWithGroup(ConfigConstants.GROUP_PRESS_COUNT);
        }
        pressCount =HmiUtils.getIntValue(staticPropertyMap.get(ConfigConstants.PRESS_COUNT));
        if(pressCount > 1) {
            isDubblePress = true;
        } else {
            isDubblePress = false;
        }
        return isDubblePress;
    }

    public String getProperty(String key) {
        if(null == staticPropertyMap.get(key)) {
            this.getProperties();
        }
        return staticPropertyMap.get(key);
    }

}
