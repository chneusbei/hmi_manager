package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;

public class TemperatureAlarmEntity extends AbstractBaseEntity {
    //PLC名
    private String plcName;
    //是否无线温度
    private boolean isWireless;
    //温度ID
    private Long temperatureId;
    //压装日期 YYYYMMDD
    private String handleDate;
    //A线B线
    private String lineType;
    //对象名称
    private String temperatureName;
    //温度值
    private BigDecimal temperatureValue;
    //警戒值
    private BigDecimal temperatureWarningValue;

    public TemperatureAlarmEntity() {

    }

    public TemperatureAlarmEntity(String plcName, boolean isWireless, Long temperatureId, String handleDate, String lineType, String temperatureName, BigDecimal temperatureValue, BigDecimal temperatureWarningValue) {
        this.setPlcName(plcName);
        this.setWireless(isWireless);
        this.setTemperatureId(temperatureId);
        this.setHandleDate(handleDate);
        this.setLineType(lineType);
        this.setTemperatureName(temperatureName);
        this.setTemperatureValue(temperatureValue);
        this.setTemperatureWarningValue(temperatureWarningValue);
    }

    public Long getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(Long temperatureId) {
        this.temperatureId = temperatureId;
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

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }

    public boolean isWireless() {
        return isWireless;
    }

    public void setWireless(boolean wireless) {
        isWireless = wireless;
    }
}
