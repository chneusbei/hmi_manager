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
public class SystemParameterDao {
    @Resource
    SystemParameterMapper mapper;

    public void insert(SystemParameterEntity entity) {
        mapper.insert(entity);
    }

    public void update(SystemParameterEntity entity) {
        mapper.update(entity);
    }

    public void delete(SystemParameterEntity entity) {
        mapper.delete(entity);
    }

    public  SystemParameterEntity getSystemParameters() {
        return mapper.getSystemParameters();
    }


}
