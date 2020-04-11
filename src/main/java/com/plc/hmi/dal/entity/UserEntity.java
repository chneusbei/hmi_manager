package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

public class UserEntity extends AbstractBaseEntity {
    private String userName;
    private String userPassword;
    private int roleId;
    private UserRoleEntity userRoleEntity;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserRoleEntity getUserRoleEntity() {
        return userRoleEntity;
    }

    public void setUserRoleEntity(UserRoleEntity userRoleEntity) {
        this.userRoleEntity = userRoleEntity;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", roleId=" + roleId +
                ", userRoleEntity=" + userRoleEntity +
                '}';
    }
}
