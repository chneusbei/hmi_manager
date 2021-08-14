package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

public class PlcConfigEntity extends AbstractBaseEntity {
    private String plcName;
    private String plcServerIp;
    private String plcServerRock;
    private String plcServerSlot;

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }

    public String getPlcServerIp() {
        return plcServerIp;
    }

    public void setPlcServerIp(String plcServerIp) {
        this.plcServerIp = plcServerIp;
    }

    public String getPlcServerRock() {
        return plcServerRock;
    }

    public void setPlcServerRock(String plcServerRock) {
        this.plcServerRock = plcServerRock;
    }

    public String getPlcServerSlot() {
        return plcServerSlot;
    }

    public void setPlcServerSlot(String plcServerSlot) {
        this.plcServerSlot = plcServerSlot;
    }
}
