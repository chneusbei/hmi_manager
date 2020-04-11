package com.plc.hmi.dal.entity.plc;
/***
 * plc监控信息查询请求实体
 */
public class PlcInfoResponseEntity  {

    private String defaultInfo;

    //自动模式
    private boolean autoModel;
    //手动模式
    private boolean humanModel;
    //系统报警
    private boolean systemAlarm;
    //紧急停止
    private boolean emergencyStrop;
    //回原中
    private boolean backOrigin;
    //压机原位
    private boolean PressureMachineOrigin;
    //记录曲线
    private boolean recordData;
    //在线
    private boolean onLine;

    public boolean isHumanModel() {
        return humanModel;
    }

    public void setHumanModel(boolean humanModel) {
        this.humanModel = humanModel;
    }

    public boolean isSystemAlarm() {
        return systemAlarm;
    }

    public void setSystemAlarm(boolean systemAlarm) {
        this.systemAlarm = systemAlarm;
    }

    public boolean isEmergencyStrop() {
        return emergencyStrop;
    }

    public void setEmergencyStrop(boolean emergencyStrop) {
        this.emergencyStrop = emergencyStrop;
    }

    public boolean isBackOrigin() {
        return backOrigin;
    }

    public void setBackOrigin(boolean backOrigin) {
        this.backOrigin = backOrigin;
    }

    public boolean isPressureMachineOrigin() {
        return PressureMachineOrigin;
    }

    public void setPressureMachineOrigin(boolean pressureMachineOrigin) {
        PressureMachineOrigin = pressureMachineOrigin;
    }

    public boolean isRecordData() {
        return recordData;
    }

    public void setRecordData(boolean recordData) {
        this.recordData = recordData;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    public boolean isAutoModel() {
        return autoModel;
    }

    public void setAutoModel(boolean autoModel) {
        this.autoModel = autoModel;
    }

    public String getDefaultInfo() {
        return defaultInfo;
    }

    public void setDefaultInfo(String defaultInfo) {
        this.defaultInfo = defaultInfo;
    }





}
