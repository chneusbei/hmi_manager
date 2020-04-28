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
    //警报内容
    private String alarmInfo;
    //触发DB 报警是由哪个地址触发的
    private int triggerDb;
    //触发DB偏移 触发DB报警是由哪个地址触发的
    private int triggerOffset;
    //触发位 触发DB报警是由哪个地址触发的
    private int triggerBit;
    //警报类型
    private String alarmType;
    //报警组
    private int alarmGroup;

    public int getTriggerDb() {
        return triggerDb;
    }

    public void setTriggerDb(int triggerDb) {
        this.triggerDb = triggerDb;
    }

    public int getTriggerOffset() {
        return triggerOffset;
    }

    public void setTriggerOffset(int triggerOffset) {
        this.triggerOffset = triggerOffset;
    }

    public int getTriggerBit() {
        return triggerBit;
    }

    public void setTriggerBit(int triggerBit) {
        this.triggerBit = triggerBit;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public int getAlarmGroup() {
        return alarmGroup;
    }

    public void setAlarmGroup(int alarmGroup) {
        this.alarmGroup = alarmGroup;
    }

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

    public String getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }
}
