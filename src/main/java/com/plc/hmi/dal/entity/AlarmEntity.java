package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.util.Date;

public class AlarmEntity extends AbstractBaseEntity {
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
    //激活 0未激活 1激活
    private String active;
    //警报内容
    private String alarmInfo;
    //警报提示
    private String alarmHelp;
    //警报状态 0待确认 1已确认
    private String alarmStatus;
    //警报时间点
    private Date alarmTime;

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }



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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(String alarmInfo) {
        this.alarmInfo = alarmInfo;
    }

    public String getAlarmHelp() {
        return alarmHelp;
    }

    public void setAlarmHelp(String alarmHelp) {
        this.alarmHelp = alarmHelp;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }
}
