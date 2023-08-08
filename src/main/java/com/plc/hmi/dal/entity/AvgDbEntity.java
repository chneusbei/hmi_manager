package com.plc.hmi.dal.entity;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class AvgDbEntity extends AbstractBaseEntity {

    public AvgDbEntity() {
        this.setCreateBy("ADMIN");
        this.setUpdateBy("ADMIN");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
    }

    //数据库类型
    private String dbType;
    //数据库名
    private String dbName;
    //数据库IP
    private String ip;
    //数据库端口
    private String port;
    //用户名
    private String userName;
    //密码
    private String password;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
