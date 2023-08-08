package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class FactoryInfoEntity extends AbstractBaseEntity {

    public FactoryInfoEntity() {
        this.setCreateBy("ADMIN");
        this.setUpdateBy("ADMIN");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
    }

    //工厂名称
    private String factoryName;
    //车间名称
    private String workshopName;
    //地图数量
    private BigDecimal mapAccount;
    //地图名称
    private String mapName;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public BigDecimal getMapAccount() {
        return mapAccount;
    }

    public void setMapAccount(BigDecimal mapAccount) {
        this.mapAccount = mapAccount;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
