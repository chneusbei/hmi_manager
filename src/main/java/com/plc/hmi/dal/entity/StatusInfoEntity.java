package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

public class StatusInfoEntity extends AbstractBaseEntity {
    //是否在记录曲线--PLC连接状态
    private Boolean isPlcConnected;

    //压装结果--上一条曲线的压装结果
    private Boolean isCurveOk;

    //压装结果--上面第二条条曲线的压装结果
    private Boolean isCurveOk2;

    //系统状态
    private Boolean systemConnection=true;

    public Boolean getCurveOk2() {
        return isCurveOk2;
    }

    public void setCurveOk2(Boolean curveOk2) {
        isCurveOk2 = curveOk2;
    }

    public Boolean getSystemConnection() {
        return systemConnection;
    }

    public void setSystemConnection(Boolean systemConnection) {
        this.systemConnection = systemConnection;
    }

    public Boolean getCurveOk() {
        return isCurveOk;
    }

    public void setCurveOk(Boolean curveOk) {
        isCurveOk = curveOk;
    }

    public Boolean getPlcConnected() {
        return isPlcConnected;
    }

    public void setPlcConnected(Boolean plcConnected) {
        isPlcConnected = plcConnected;
    }
}
