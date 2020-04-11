package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.base.dao.AbstractBaseDao;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import javax.annotation.Resource;

public abstract  class AbstractDimDao<T extends AbstractBaseEntity>  extends AbstractBaseDao<T> {
    @Resource
    protected SqlMapClientTemplate sqlMapClientTemplate;

    @Override
    public SqlMapClientTemplate getSqlMapClientTemplate() {
        return sqlMapClientTemplate;
    }
}
