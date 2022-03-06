package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.dal.mapper.PropertyMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PropertyDao {
    @Resource
    PropertyMapper propertyMapper;

    public void insert(PropertyEntity entity) {
        propertyMapper.insert(entity);
    }

    public int update(PropertyEntity entity) {
       return  propertyMapper.update(entity);
    }

    public void updateById(PropertyEntity entity) {
        propertyMapper.updateById(entity);
    }

    public void delete(PropertyEntity entity) {
        propertyMapper.delete(entity);
    }

    public List<PropertyEntity> getProperties() {
        List<PropertyEntity>  propertyEntityList = propertyMapper.getPropertyConfig();
        return propertyEntityList;
    }

    public List<PropertyEntity> getPropertiesWithGroup(String group) {
        return propertyMapper.getPropertyWithGroup(group);
    }
}
