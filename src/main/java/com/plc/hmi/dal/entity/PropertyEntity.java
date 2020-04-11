package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

public class PropertyEntity  extends AbstractBaseEntity {
    private String propName;
    private String propValue;
    private String propGroup;
    private String description;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public String getPropGroup() {
        return propGroup;
    }

    public void setPropGroup(String propGroup) {
        this.propGroup = propGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
