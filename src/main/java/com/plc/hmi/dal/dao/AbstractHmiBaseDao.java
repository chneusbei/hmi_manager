package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.enumeration.BaseEntityEnum;
import com.plc.hmi.util.HmiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractHmiBaseDao <T extends AbstractBaseEntity>{

    protected abstract T getEntity(HashMap map);

    protected List<AbstractBaseEntity> getEntityList(List<HashMap> valueList) {
        List<AbstractBaseEntity> entityList = new ArrayList<AbstractBaseEntity>();
        if (valueList != null) {
            for (HashMap map : valueList) {
                entityList.add(getEntity(map));
            }
        }
        return entityList;
    }
    protected void setEntityBase(T entity, HashMap order) {
        entity.setId(Long.valueOf(String.valueOf(order.get(BaseEntityEnum.ID.getCode()))).longValue());
        entity.setIsDeleted(String.valueOf(order.get(BaseEntityEnum.IS_DELETED.getCode())));
        entity.setCreateBy(String.valueOf(order.get(BaseEntityEnum.CREATE_BY.getCode())));
        entity.setUpdateBy(String.valueOf(order.get(BaseEntityEnum.UPDATE_BY.getCode())));
        entity.setUpdateTime(HmiUtils.getDate(order.get(BaseEntityEnum.UPDATE_TIME.getCode())));
        entity.setCreateTime(HmiUtils.getDate(order.get(BaseEntityEnum.CREATE_TIME.getCode())));
    }
}

