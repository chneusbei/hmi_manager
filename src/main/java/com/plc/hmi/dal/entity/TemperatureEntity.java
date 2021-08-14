package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.util.HmiUtils;

import java.math.BigDecimal;

public class TemperatureEntity extends AbstractBaseEntity {
    //PLC ID
    private String plcIp;
    //状态
    private String status;
    //压装日期 YYYYMMDD
    private String handleDate;
    //低速轴偏心铜套温度检测1
    private BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature1;
    //低速轴偏心铜套温度检测2
    private BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature2;
    //低速轴偏心铜套温度检测3
    private BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature3;
    //低速轴偏心铜套温度检测4
    private BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature4;
    //高速轴偏心铜套温度检测1
    private BigDecimal highSpeedAxisEccentricCopperSleeveTemperature1;
    //高速轴偏心铜套温度检测2
    private BigDecimal highSpeedAxisEccentricCopperSleeveTemperature2;
    //飞轮支撑大轴承温度检测1
    private BigDecimal flywheelSupportBigAxisTemperature1;
    //飞轮支撑大轴承温度检测2
    private BigDecimal flywheelSupportBigAxisTemperature2;
    //拉杆两端铜套温度检测1
    private BigDecimal drawbarBothEndsCopperSleeveTemperature1;
    //拉杆两端铜套温度检测2
    private BigDecimal drawbarBothEndsCopperSleeveTemperature2;
    //拉杆两端铜套温度检测3
    private BigDecimal drawbarBothEndsCopperSleeveTemperature3;
    //拉杆两端铜套温度检测4
    private BigDecimal drawbarBothEndsCopperSleeveTemperature4;
    //拉杆两端铜套温度检测5
    private BigDecimal drawbarBothEndsCopperSleeveTemperature5;
    //拉杆两端铜套温度检测6
    private BigDecimal drawbarBothEndsCopperSleeveTemperature6;
    //拉杆两端铜套温度检测7
    private BigDecimal drawbarBothEndsCopperSleeveTemperature7;
    //拉杆两端铜套温度检测8
    private BigDecimal drawbarBothEndsCopperSleeveTemperature8;
    //小皮带轮支撑轴承温度检测1
    private BigDecimal smallBeltWheelSupportAxisTemperature1;
    //小皮带轮支撑轴承温度检测2
    private BigDecimal smallBeltWheelSupportAxisTemperature2;
    //三脚架下端铜套温度检测1
    private BigDecimal tripodBottomCopperSleeveTemperature1;
    //三脚架下端铜套温度检测2
    private BigDecimal tripodBottomCopperSleeveTemperature2;
    //三脚架下端铜套温度检测3
    private BigDecimal tripodBottomCopperSleeveTemperature3;
    //三脚架下端铜套温度检测4
    private BigDecimal tripodBottomCopperSleeveTemperature4;
    //三脚架偏心大铜套温度检测1
    private BigDecimal tripodEccentricBigCopperSleeveTemperature1;
    //三脚架偏心大铜套温度检测2
    private BigDecimal tripodEccentricBigCopperSleeveTemperature2;
    //三脚架偏心大铜套温度检测3
    private BigDecimal tripodEccentricBigCopperSleeveTemperature3;
    //三脚架偏心大铜套温度检测4
    private BigDecimal tripodEccentricBigCopperSleeveTemperature4;
    //备用温度检测0
    private BigDecimal backupTemperature0;
    //备用温度检测1
    private BigDecimal backupTemperature1;
    //备用温度检测2
    private BigDecimal backupTemperature2;
    //备用温度检测3
    private BigDecimal backupTemperature3;
    //备用温度检测4
    private BigDecimal backupTemperature4;
    //备用温度检测5
    private BigDecimal backupTemperature5;
    //备用温度检测6
    private BigDecimal backupTemperature6;
    //备用温度检测7
    private BigDecimal backupTemperature7;
    //备用温度检测8
    private BigDecimal backupTemperature8;
    //备用温度检测9
    private BigDecimal backupTemperature9;
    //备用温度检测10
    private BigDecimal backupTemperature10;
    //备用温度检测11
    private BigDecimal backupTemperature11;
    //温度警戒值1
    private BigDecimal temperatureWarningValue1;
    //温度警戒值2
    private BigDecimal temperatureWarningValue2;

    public String getPlcIp() {
        return plcIp;
    }

    public void setPlcIp(String plcIp) {
        this.plcIp = plcIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public BigDecimal getLowSpeedAxisEccentricCopperSleeveTemperature1() {
        return lowSpeedAxisEccentricCopperSleeveTemperature1;
    }

    public void setLowSpeedAxisEccentricCopperSleeveTemperature1(BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature1) {
        this.lowSpeedAxisEccentricCopperSleeveTemperature1 = lowSpeedAxisEccentricCopperSleeveTemperature1;
    }

    public BigDecimal getLowSpeedAxisEccentricCopperSleeveTemperature2() {
        return lowSpeedAxisEccentricCopperSleeveTemperature2;
    }

    public void setLowSpeedAxisEccentricCopperSleeveTemperature2(BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature2) {
        this.lowSpeedAxisEccentricCopperSleeveTemperature2 = lowSpeedAxisEccentricCopperSleeveTemperature2;
    }

    public BigDecimal getLowSpeedAxisEccentricCopperSleeveTemperature3() {
        return lowSpeedAxisEccentricCopperSleeveTemperature3;
    }

    public void setLowSpeedAxisEccentricCopperSleeveTemperature3(BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature3) {
        this.lowSpeedAxisEccentricCopperSleeveTemperature3 = lowSpeedAxisEccentricCopperSleeveTemperature3;
    }

    public BigDecimal getLowSpeedAxisEccentricCopperSleeveTemperature4() {
        return lowSpeedAxisEccentricCopperSleeveTemperature4;
    }

    public void setLowSpeedAxisEccentricCopperSleeveTemperature4(BigDecimal lowSpeedAxisEccentricCopperSleeveTemperature4) {
        this.lowSpeedAxisEccentricCopperSleeveTemperature4 = lowSpeedAxisEccentricCopperSleeveTemperature4;
    }

    public BigDecimal getHighSpeedAxisEccentricCopperSleeveTemperature1() {
        return highSpeedAxisEccentricCopperSleeveTemperature1;
    }

    public void setHighSpeedAxisEccentricCopperSleeveTemperature1(BigDecimal highSpeedAxisEccentricCopperSleeveTemperature1) {
        this.highSpeedAxisEccentricCopperSleeveTemperature1 = highSpeedAxisEccentricCopperSleeveTemperature1;
    }

    public BigDecimal getHighSpeedAxisEccentricCopperSleeveTemperature2() {
        return highSpeedAxisEccentricCopperSleeveTemperature2;
    }

    public void setHighSpeedAxisEccentricCopperSleeveTemperature2(BigDecimal highSpeedAxisEccentricCopperSleeveTemperature2) {
        this.highSpeedAxisEccentricCopperSleeveTemperature2 = highSpeedAxisEccentricCopperSleeveTemperature2;
    }

    public BigDecimal getFlywheelSupportBigAxisTemperature1() {
        return flywheelSupportBigAxisTemperature1;
    }

    public void setFlywheelSupportBigAxisTemperature1(BigDecimal flywheelSupportBigAxisTemperature1) {
        this.flywheelSupportBigAxisTemperature1 = flywheelSupportBigAxisTemperature1;
    }

    public BigDecimal getFlywheelSupportBigAxisTemperature2() {
        return flywheelSupportBigAxisTemperature2;
    }

    public void setFlywheelSupportBigAxisTemperature2(BigDecimal flywheelSupportBigAxisTemperature2) {
        this.flywheelSupportBigAxisTemperature2 = flywheelSupportBigAxisTemperature2;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature1() {
        return drawbarBothEndsCopperSleeveTemperature1;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature1(BigDecimal drawbarBothEndsCopperSleeveTemperature1) {
        this.drawbarBothEndsCopperSleeveTemperature1 = drawbarBothEndsCopperSleeveTemperature1;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature2() {
        return drawbarBothEndsCopperSleeveTemperature2;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature2(BigDecimal drawbarBothEndsCopperSleeveTemperature2) {
        this.drawbarBothEndsCopperSleeveTemperature2 = drawbarBothEndsCopperSleeveTemperature2;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature3() {
        return drawbarBothEndsCopperSleeveTemperature3;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature3(BigDecimal drawbarBothEndsCopperSleeveTemperature3) {
        this.drawbarBothEndsCopperSleeveTemperature3 = drawbarBothEndsCopperSleeveTemperature3;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature4() {
        return drawbarBothEndsCopperSleeveTemperature4;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature4(BigDecimal drawbarBothEndsCopperSleeveTemperature4) {
        this.drawbarBothEndsCopperSleeveTemperature4 = drawbarBothEndsCopperSleeveTemperature4;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature5() {
        return drawbarBothEndsCopperSleeveTemperature5;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature5(BigDecimal drawbarBothEndsCopperSleeveTemperature5) {
        this.drawbarBothEndsCopperSleeveTemperature5 = drawbarBothEndsCopperSleeveTemperature5;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature6() {
        return drawbarBothEndsCopperSleeveTemperature6;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature6(BigDecimal drawbarBothEndsCopperSleeveTemperature6) {
        this.drawbarBothEndsCopperSleeveTemperature6 = drawbarBothEndsCopperSleeveTemperature6;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature7() {
        return drawbarBothEndsCopperSleeveTemperature7;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature7(BigDecimal drawbarBothEndsCopperSleeveTemperature7) {
        this.drawbarBothEndsCopperSleeveTemperature7 = drawbarBothEndsCopperSleeveTemperature7;
    }

    public BigDecimal getDrawbarBothEndsCopperSleeveTemperature8() {
        return drawbarBothEndsCopperSleeveTemperature8;
    }

    public void setDrawbarBothEndsCopperSleeveTemperature8(BigDecimal drawbarBothEndsCopperSleeveTemperature8) {
        this.drawbarBothEndsCopperSleeveTemperature8 = drawbarBothEndsCopperSleeveTemperature8;
    }

    public BigDecimal getSmallBeltWheelSupportAxisTemperature1() {
        return smallBeltWheelSupportAxisTemperature1;
    }

    public void setSmallBeltWheelSupportAxisTemperature1(BigDecimal smallBeltWheelSupportAxisTemperature1) {
        this.smallBeltWheelSupportAxisTemperature1 = smallBeltWheelSupportAxisTemperature1;
    }

    public BigDecimal getSmallBeltWheelSupportAxisTemperature2() {
        return smallBeltWheelSupportAxisTemperature2;
    }

    public void setSmallBeltWheelSupportAxisTemperature2(BigDecimal smallBeltWheelSupportAxisTemperature2) {
        this.smallBeltWheelSupportAxisTemperature2 = smallBeltWheelSupportAxisTemperature2;
    }

    public BigDecimal getTripodBottomCopperSleeveTemperature1() {
        return tripodBottomCopperSleeveTemperature1;
    }

    public void setTripodBottomCopperSleeveTemperature1(BigDecimal tripodBottomCopperSleeveTemperature1) {
        this.tripodBottomCopperSleeveTemperature1 = tripodBottomCopperSleeveTemperature1;
    }

    public BigDecimal getTripodBottomCopperSleeveTemperature2() {
        return tripodBottomCopperSleeveTemperature2;
    }

    public void setTripodBottomCopperSleeveTemperature2(BigDecimal tripodBottomCopperSleeveTemperature2) {
        this.tripodBottomCopperSleeveTemperature2 = tripodBottomCopperSleeveTemperature2;
    }

    public BigDecimal getTripodBottomCopperSleeveTemperature3() {
        return tripodBottomCopperSleeveTemperature3;
    }

    public void setTripodBottomCopperSleeveTemperature3(BigDecimal tripodBottomCopperSleeveTemperature3) {
        this.tripodBottomCopperSleeveTemperature3 = tripodBottomCopperSleeveTemperature3;
    }

    public BigDecimal getTripodBottomCopperSleeveTemperature4() {
        return tripodBottomCopperSleeveTemperature4;
    }

    public void setTripodBottomCopperSleeveTemperature4(BigDecimal tripodBottomCopperSleeveTemperature4) {
        this.tripodBottomCopperSleeveTemperature4 = tripodBottomCopperSleeveTemperature4;
    }

    public BigDecimal getTripodEccentricBigCopperSleeveTemperature1() {
        return tripodEccentricBigCopperSleeveTemperature1;
    }

    public void setTripodEccentricBigCopperSleeveTemperature1(BigDecimal tripodEccentricBigCopperSleeveTemperature1) {
        this.tripodEccentricBigCopperSleeveTemperature1 = tripodEccentricBigCopperSleeveTemperature1;
    }

    public BigDecimal getTripodEccentricBigCopperSleeveTemperature2() {
        return tripodEccentricBigCopperSleeveTemperature2;
    }

    public void setTripodEccentricBigCopperSleeveTemperature2(BigDecimal tripodEccentricBigCopperSleeveTemperature2) {
        this.tripodEccentricBigCopperSleeveTemperature2 = tripodEccentricBigCopperSleeveTemperature2;
    }

    public BigDecimal getTripodEccentricBigCopperSleeveTemperature3() {
        return tripodEccentricBigCopperSleeveTemperature3;
    }

    public void setTripodEccentricBigCopperSleeveTemperature3(BigDecimal tripodEccentricBigCopperSleeveTemperature3) {
        this.tripodEccentricBigCopperSleeveTemperature3 = tripodEccentricBigCopperSleeveTemperature3;
    }

    public BigDecimal getTripodEccentricBigCopperSleeveTemperature4() {
        return tripodEccentricBigCopperSleeveTemperature4;
    }

    public void setTripodEccentricBigCopperSleeveTemperature4(BigDecimal tripodEccentricBigCopperSleeveTemperature4) {
        this.tripodEccentricBigCopperSleeveTemperature4 = tripodEccentricBigCopperSleeveTemperature4;
    }

    public BigDecimal getBackupTemperature0() {
        return backupTemperature0;
    }

    public void setBackupTemperature0(BigDecimal backupTemperature0) {
        this.backupTemperature0 = backupTemperature0;
    }

    public BigDecimal getBackupTemperature1() {
        return backupTemperature1;
    }

    public void setBackupTemperature1(BigDecimal backupTemperature1) {
        this.backupTemperature1 = backupTemperature1;
    }

    public BigDecimal getBackupTemperature2() {
        return backupTemperature2;
    }

    public void setBackupTemperature2(BigDecimal backupTemperature2) {
        this.backupTemperature2 = backupTemperature2;
    }

    public BigDecimal getBackupTemperature3() {
        return backupTemperature3;
    }

    public void setBackupTemperature3(BigDecimal backupTemperature3) {
        this.backupTemperature3 = backupTemperature3;
    }

    public BigDecimal getBackupTemperature4() {
        return backupTemperature4;
    }

    public void setBackupTemperature4(BigDecimal backupTemperature4) {
        this.backupTemperature4 = backupTemperature4;
    }

    public BigDecimal getBackupTemperature5() {
        return backupTemperature5;
    }

    public void setBackupTemperature5(BigDecimal backupTemperature5) {
        this.backupTemperature5 = backupTemperature5;
    }

    public BigDecimal getBackupTemperature6() {
        return backupTemperature6;
    }

    public void setBackupTemperature6(BigDecimal backupTemperature6) {
        this.backupTemperature6 = backupTemperature6;
    }

    public BigDecimal getBackupTemperature7() {
        return backupTemperature7;
    }

    public void setBackupTemperature7(BigDecimal backupTemperature7) {
        this.backupTemperature7 = backupTemperature7;
    }

    public BigDecimal getBackupTemperature8() {
        return backupTemperature8;
    }

    public void setBackupTemperature8(BigDecimal backupTemperature8) {
        this.backupTemperature8 = backupTemperature8;
    }

    public BigDecimal getBackupTemperature9() {
        return backupTemperature9;
    }

    public void setBackupTemperature9(BigDecimal backupTemperature9) {
        this.backupTemperature9 = backupTemperature9;
    }

    public BigDecimal getBackupTemperature10() {
        return backupTemperature10;
    }

    public void setBackupTemperature10(BigDecimal backupTemperature10) {
        this.backupTemperature10 = backupTemperature10;
    }

    public BigDecimal getBackupTemperature11() {
        return backupTemperature11;
    }

    public void setBackupTemperature11(BigDecimal backupTemperature11) {
        this.backupTemperature11 = backupTemperature11;
    }

    public BigDecimal getTemperatureWarningValue1() {
        return temperatureWarningValue1;
    }

    public void setTemperatureWarningValue1(BigDecimal temperatureWarningValue1) {
        this.temperatureWarningValue1 = temperatureWarningValue1;
    }

    public BigDecimal getTemperatureWarningValue2() {
        return temperatureWarningValue2;
    }

    public void setTemperatureWarningValue2(BigDecimal temperatureWarningValue2) {
        this.temperatureWarningValue2 = temperatureWarningValue2;
    }
}
