package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class TemperaturePointEntity {

    //时间
    private Date temperatureTime;
    //温度
    private BigDecimal temperatureValue;

    public Date getTemperatureTime() {
        return temperatureTime;
    }

    public void setTemperatureTime(Date temperatureTime) {
        this.temperatureTime = temperatureTime;
    }

    public BigDecimal getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(BigDecimal temperatureValue) {
        this.temperatureValue = temperatureValue;
    }
}
