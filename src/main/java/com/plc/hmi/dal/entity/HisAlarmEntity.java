package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.util.Date;

public class HisAlarmEntity extends AbstractBaseEntity {
    //警报信息ID
    private Long alarmId;
    //警报开始时间
    private Date alarmStartTime;
    //警报结束时间
    private Date alarmStopTime;
    //警报状态  0历史 1当前
    private String alarmStatus;
    //警报确认状态  0已确认 1待确认
    private String alarmCfmStatus;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Date getAlarmStartTime() {
        return alarmStartTime;
    }

    public void setAlarmStartTime(Date alarmStartTime) {
        this.alarmStartTime = alarmStartTime;
    }

    public Date getAlarmStopTime() {
        return alarmStopTime;
    }

    public void setAlarmStopTime(Date alarmStopTime) {
        this.alarmStopTime = alarmStopTime;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getAlarmCfmStatus() {
        return alarmCfmStatus;
    }

    public void setAlarmCfmStatus(String alarmCfmStatus) {
        this.alarmCfmStatus = alarmCfmStatus;
    }
}
