package com.plc.hmi.dal.entity.plc;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class ConnectionInfoEntity extends AbstractBaseEntity {

    public ConnectionInfoEntity() {
        this.setCreateBy("ADMIN");
        this.setUpdateBy("ADMIN");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
    }

    //连接名称
    private String connectionName;
    //IP地址
    private String connectionIp;
    //端口号
    private String connectionPort;
    //连接用户名
    private String userName;
    //连接密码
    private String password;

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionIp() {
        return connectionIp;
    }

    public void setConnectionIp(String connectionIp) {
        this.connectionIp = connectionIp;
    }

    public String getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(String connectionPort) {
        this.connectionPort = connectionPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
