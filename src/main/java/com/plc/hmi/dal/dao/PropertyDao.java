package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.dal.mapper.PropertyMapper;
import com.plc.hmi.enumeration.PropertyConfigEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class PropertyDao extends AbstractHmiBaseDao {
    @Resource
    PropertyMapper propertyMapper;

    public void insert(PropertyEntity entity) {
        propertyMapper.insert(entity);
    }

    public void update(PropertyEntity entity) {
        propertyMapper.update(entity);
    }

    public void delete(PropertyEntity entity) {
        propertyMapper.delete(entity);
    }

    public List<PropertyEntity> getProperties() {
        return this.getEntityList(propertyMapper.getPropertyConfig());
    }

    public List<PropertyEntity> getPropertiesWithGroup(String group) {
        return this.getEntityList(propertyMapper.getPropertyWithGroup(group));
    }


    @Override
    protected PropertyEntity getEntity(HashMap map) {
        PropertyEntity entity = new PropertyEntity();
        super.setEntityBase(entity, map);
        entity.setPropName(HmiUtils.getString(map.get(PropertyConfigEntityEnum.PROP_NAME.getCode())));
        entity.setPropValue(HmiUtils.getString(map.get(PropertyConfigEntityEnum.PROP_VALUE.getCode())));
        entity.setPropGroup(HmiUtils.getString(map.get(PropertyConfigEntityEnum.PROP_GROUP.getCode())));
        entity.setDescription(HmiUtils.getString(map.get(PropertyConfigEntityEnum.DESCRIPTION.getCode())));
        return entity;
    }

}
