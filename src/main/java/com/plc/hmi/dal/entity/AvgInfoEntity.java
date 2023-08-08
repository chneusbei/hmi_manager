package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.util.Date;

public class AvgInfoEntity extends AbstractBaseEntity {

    public AvgInfoEntity() {
        this.setCreateBy("ADMIN");
        this.setUpdateBy("ADMIN");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
    }

    //AVG名
    private String avgName;
    //动力电
    private String electricity;
    //充电吸合
    private String chargeSwitch;
    //充电状态
    private String chargeStatus;
    //电池
    private String cell;
    //导航模式
    private String navigationMode;
    //激光任务状态
    private String laserTaskStatus;
    //激光当前点ID
    private String laserCurrentPointId;
    //激光目标点ID
    private String laserTargetId;
    //激光坐标
    private String laserCoordinate;
    //二维码ID
    private String qrCodeId;
    //二维码状态
    private String qrCodeStatus;
    //二维码坐标
    private String qrCodeCoordinate;
    //液压动作
    private String hydraulicPressureAction;
    //液压位置
    private String hydraulicPressurePosition;
    //液压故障
    private String hydraulicPressureError;

    public String getAvgName() {
        return avgName;
    }

    public void setAvgName(String avgName) {
        this.avgName = avgName;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getChargeSwitch() {
        return chargeSwitch;
    }

    public void setChargeSwitch(String chargeSwitch) {
        this.chargeSwitch = chargeSwitch;
    }

    public String getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(String chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNavigationMode() {
        return navigationMode;
    }

    public void setNavigationMode(String navigationMode) {
        this.navigationMode = navigationMode;
    }

    public String getLaserTaskStatus() {
        return laserTaskStatus;
    }

    public void setLaserTaskStatus(String laserTaskStatus) {
        this.laserTaskStatus = laserTaskStatus;
    }

    public String getLaserCurrentPointId() {
        return laserCurrentPointId;
    }

    public void setLaserCurrentPointId(String laserCurrentPointId) {
        this.laserCurrentPointId = laserCurrentPointId;
    }

    public String getLaserTargetId() {
        return laserTargetId;
    }

    public void setLaserTargetId(String laserTargetId) {
        this.laserTargetId = laserTargetId;
    }

    public String getLaserCoordinate() {
        return laserCoordinate;
    }

    public void setLaserCoordinate(String laserCoordinate) {
        this.laserCoordinate = laserCoordinate;
    }

    public String getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(String qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getQrCodeStatus() {
        return qrCodeStatus;
    }

    public void setQrCodeStatus(String qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    public String getQrCodeCoordinate() {
        return qrCodeCoordinate;
    }

    public void setQrCodeCoordinate(String qrCodeCoordinate) {
        this.qrCodeCoordinate = qrCodeCoordinate;
    }

    public String getHydraulicPressureAction() {
        return hydraulicPressureAction;
    }

    public void setHydraulicPressureAction(String hydraulicPressureAction) {
        this.hydraulicPressureAction = hydraulicPressureAction;
    }

    public String getHydraulicPressurePosition() {
        return hydraulicPressurePosition;
    }

    public void setHydraulicPressurePosition(String hydraulicPressurePosition) {
        this.hydraulicPressurePosition = hydraulicPressurePosition;
    }

    public String getHydraulicPressureError() {
        return hydraulicPressureError;
    }

    public void setHydraulicPressureError(String hydraulicPressureError) {
        this.hydraulicPressureError = hydraulicPressureError;
    }
}
