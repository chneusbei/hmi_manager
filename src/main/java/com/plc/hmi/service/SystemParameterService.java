package com.plc.hmi.service;

import com.plc.hmi.dal.dao.SystemParameterDao;
import com.plc.hmi.dal.entity.SystemParameterEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * 系统配置服务
 */
@Service
@Component
public class SystemParameterService extends AbstractBaseService{
    @Resource
    SystemParameterDao dao;

    public SystemParameterEntity getSystemParameters() {
        return dao.getSystemParameters();
    }

    public void insert(SystemParameterEntity entity) {
        dao.insert(entity);
    }

    public void update(SystemParameterEntity entity) {
        dao.update(entity);
    }

    public void delete(SystemParameterEntity entity) {
        dao.delete(entity);
    }
}
