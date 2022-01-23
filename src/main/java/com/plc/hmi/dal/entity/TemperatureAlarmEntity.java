package com.plc.hmi.dal.entity;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.util.HmiUtils;

import java.math.BigDecimal;
import java.util.Date;

public class TemperatureAlarmEntity extends AbstractBaseEntity {
    //批次号
    private Long temperatureBatchId;
    //压装日期 YYYYMMDD
    private String handleDate;
    //对象名称
    private String temperatureName;
    //温度值
    private BigDecimal temperatureValue;
    //警戒值
    private BigDecimal temperatureWarningValue;

    public TemperatureAlarmEntity() {

    }

    public TemperatureAlarmEntity(Long temperatureBatchId, String handleDate, String temperatureName, BigDecimal temperatureValue, BigDecimal temperatureWarningValue) {
        this.setTemperatureBatchId(temperatureBatchId);
        this.setHandleDate(handleDate);
        this.setTemperatureName(temperatureName);
        this.setTemperatureValue(temperatureValue);
        this.setTemperatureWarningValue(temperatureWarningValue);
    }

    public Long getTemperatureBatchId() {
        return temperatureBatchId;
    }

    public void setTemperatureBatchId(Long temperatureBatchId) {
        this.temperatureBatchId = temperatureBatchId;
    }

    public String getTemperatureName() {
        return temperatureName;
    }

    public void setTemperatureName(String temperatureName) {
        this.temperatureName = temperatureName;
    }

    public BigDecimal getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(BigDecimal temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public BigDecimal getTemperatureWarningValue() {
        return temperatureWarningValue;
    }

    public void setTemperatureWarningValue(BigDecimal temperatureWarningValue) {
        this.temperatureWarningValue = temperatureWarningValue;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }
}
