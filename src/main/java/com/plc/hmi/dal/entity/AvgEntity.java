package com.plc.hmi.dal.entity;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class AvgEntity extends AbstractBaseEntity {

    public AvgEntity() {
        this.setCreateBy("ADMIN");
        this.setUpdateBy("ADMIN");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
        this.setStatus(HmiConstants.AVG_STATUS_ACTIVE);
    }

    //状态
    private String status;
    //AVG名
    private String avgName;
    //AVG车长
    private BigDecimal avgLong;
    //AVG车宽
    private BigDecimal avgWide;
    //导航控制IP端口
    private String naviIp;
    //变量控制IP端口
    private String varIp;
    //线速度
    private BigDecimal lineVelocity;
    //角速度
    private BigDecimal angleVelocity;
    //启用状态
    private String isActivated;

    //地图名称
    private String mapName;
    //车间名称
    private String workshopName;
    //工厂名称
    private String factoryName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvgName() {
        return avgName;
    }

    public void setAvgName(String avgName) {
        this.avgName = avgName;
    }

    public BigDecimal getAvgLong() {
        return avgLong;
    }

    public void setAvgLong(BigDecimal avgLong) {
        this.avgLong = avgLong;
    }

    public BigDecimal getAvgWide() {
        return avgWide;
    }

    public void setAvgWide(BigDecimal avgWide) {
        this.avgWide = avgWide;
    }

    public String getNaviIp() {
        return naviIp;
    }

    public void setNaviIp(String naviIp) {
        this.naviIp = naviIp;
    }

    public String getVarIp() {
        return varIp;
    }

    public void setVarIp(String varIp) {
        this.varIp = varIp;
    }

    public BigDecimal getLineVelocity() {
        return lineVelocity;
    }

    public void setLineVelocity(BigDecimal lineVelocity) {
        this.lineVelocity = lineVelocity;
    }

    public BigDecimal getAngleVelocity() {
        return angleVelocity;
    }

    public void setAngleVelocity(BigDecimal angleVelocity) {
        this.angleVelocity = angleVelocity;
    }

    public String getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
