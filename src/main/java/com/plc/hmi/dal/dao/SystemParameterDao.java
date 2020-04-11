package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.SystemParameterEntity;
import com.plc.hmi.dal.mapper.SystemParameterMapper;
import com.plc.hmi.enumeration.SystemParameterEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class SystemParameterDao extends AbstractHmiBaseDao {
    @Resource
    SystemParameterMapper mapper;

    @Override
    protected SystemParameterEntity getEntity(HashMap map) {
        SystemParameterEntity entity = new SystemParameterEntity();
        super.setEntityBase(entity, map);
        entity.setMaxDistance(HmiUtils.getBigDicimal(map.get(SystemParameterEntityEnum.MAX_DISTANCE.getCode())));
        entity.setMaxForce(HmiUtils.getBigDicimal(map.get(SystemParameterEntityEnum.MAX_FORCE.getCode())));
        entity.setMaxSpeed(HmiUtils.getBigDicimal(map.get(SystemParameterEntityEnum.MAX_SPEED.getCode())));
        entity.setDefaultBackSpeed(HmiUtils.getBigDicimal(map.get(SystemParameterEntityEnum.DEFAULT_BACK_SPEED.getCode())));
        return entity;
    }

    public void insert(SystemParameterEntity entity) {
        mapper.insert(entity);
    }

    public void update(SystemParameterEntity entity) {
        mapper.update(entity);
    }

    public void delete(SystemParameterEntity entity) {
        mapper.delete(entity);
    }

    public  List<SystemParameterEntity> getSystemParameters() {
        return this.getEntityList( mapper.getSystemParameters());
    }


}
