package com.plc.hmi.dal.dao;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.dal.mapper.PropertyMapper;
import com.plc.hmi.enumeration.PropertyConfigEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class PropertyDao {
    @Resource
    PropertyMapper propertyMapper;

    public void insert(PropertyEntity entity) {
        propertyMapper.insert(entity);
    }

    public void update(PropertyEntity entity) {
        propertyMapper.update(entity);
    }

    public void updateById(PropertyEntity entity) {
        propertyMapper.updateById(entity);
    }

    public void delete(PropertyEntity entity) {
        propertyMapper.delete(entity);
    }

    public List<PropertyEntity> getProperties() {
        List<PropertyEntity>  propertyEntityList = propertyMapper.getPropertyConfig();
//        System.out.println(System.currentTimeMillis() +" select  value:"+ propertyEntityList.get(0).getPropValue());
        return propertyEntityList;
    }

    public List<PropertyEntity> getPropertiesWithGroup(String group) {
        return propertyMapper.getPropertyWithGroup(group);
    }
}
