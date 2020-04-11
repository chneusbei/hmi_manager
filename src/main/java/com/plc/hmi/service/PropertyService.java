package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PropertyDao;
import com.plc.hmi.dal.entity.PropertyEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * 系统配置信息服务， 非业务表， 用于技术配置
 */
@Service
@Component
public class PropertyService extends AbstractBaseService{
    @Resource
    PropertyDao propertyDao;

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
}
