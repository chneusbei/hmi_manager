package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PlcConfigEntity;
import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.dal.mapper.PlcConfigMapper;
import com.plc.hmi.dal.mapper.PropertyMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class PlcConfigDao {
    @Resource
    PlcConfigMapper plcConfigMapper;

    public List<PlcConfigEntity> getPlcList() {
        return plcConfigMapper.getPlcConfigList();
    }

    public void insert(PlcConfigEntity entity) {
         plcConfigMapper.insert(entity);
    }

    public void delete(PlcConfigEntity entity) {
        plcConfigMapper.delete(entity);
    }

    public void update(PlcConfigEntity entity) {
        plcConfigMapper.update(entity);
    }


}
