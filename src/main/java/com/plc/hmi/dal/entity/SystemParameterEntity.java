package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;

public class SystemParameterEntity extends AbstractBaseEntity {
    //压机最大行程
    private BigDecimal maxDistance;
    //压机最大压力
    private BigDecimal maxForce;
    //压机最大速度
    private BigDecimal maxSpeed;
    //默认回程速度
    private BigDecimal defaultBackSpeed;

    public BigDecimal getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(BigDecimal maxDistance) {
        this.maxDistance = maxDistance;
    }

    public BigDecimal getMaxForce() {
        return maxForce;
    }

    public void setMaxForce(BigDecimal maxForce) {
        this.maxForce = maxForce;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getDefaultBackSpeed() {
        return defaultBackSpeed;
    }

    public void setDefaultBackSpeed(BigDecimal defaultBackSpeed) {
        this.defaultBackSpeed = defaultBackSpeed;
    }

    @Override
    public String toString() {
        return "SystemParameterEntity{" +
                "maxDistance=" + maxDistance +
                ", maxForce=" + maxForce +
                ", maxSpeed=" + maxSpeed +
                ", defaultBackSpeed=" + defaultBackSpeed +
                '}';
    }
}
