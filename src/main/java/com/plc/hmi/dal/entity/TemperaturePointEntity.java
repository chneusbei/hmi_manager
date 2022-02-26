package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class TemperaturePointEntity {
    //ID
    private Long id;
    //数据ID
    private Long temperatureId;
    //时间
    private Date temperatureTime;
    //温度名称
    private String temperatureName;
    //温度
    private BigDecimal temperatureValue;
    //PLC名
    private String plcName;
    //A线、B线
    private String lineType;
    //压装日期 YYYYMMDD
    private String handleDate;

    public String getTemperatureName() {
        return temperatureName;
    }

    public void setTemperatureName(String temperatureName) {
        this.temperatureName = temperatureName;
    }

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

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public Long getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(Long temperatureId) {
        this.temperatureId = temperatureId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
