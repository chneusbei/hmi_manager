package com.plc.hmi.dal.entity;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.util.HmiUtils;

import java.math.BigDecimal;
import java.util.Date;

public class TemperatureEntity extends AbstractBaseEntity {

    public TemperatureEntity() {
        this.setCreateBy("SYS");
        this.setUpdateBy("SYS");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
        this.setHandleDate(HmiUtils.getYYYYMMDDString(this.getCreateTime()));
        this.setStatus(HmiConstants.TEMPERATURE_STATUS_NORMAL);
        this.setLineType(HmiConstants.LINE_TYPE.LINE_TYPE_B.getCode());
    }

    public TemperatureEntity(String lineType) {
        this.setCreateBy("SYS");
        this.setUpdateBy("SYS");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
        this.setHandleDate(HmiUtils.getYYYYMMDDString(this.getCreateTime()));
        this.setStatus(HmiConstants.TEMPERATURE_STATUS_NORMAL);
        this.setLineType(lineType);
    }

    public TemperatureEntity(String plcName, Long batchId, boolean isWireLess, String lineType) {
        this.setCreateBy("SYS");
        this.setUpdateBy("SYS");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
        this.setHandleDate(HmiUtils.getYYYYMMDDString(this.getCreateTime()));
        this.setBatchId(batchId);
        this.setPlcName(plcName);
        this.setWireless(isWireLess);
        this.setStatus(HmiConstants.TEMPERATURE_STATUS_NORMAL);
        this.setLineType(lineType);
    }
    //批次号
    private Long batchId;
    //PLC ID
    private String plcIp;
    //PLC名
    private String plcName;
    //是否无线温度
    private boolean isWireless;
    //A线、B线
    private String lineType;
    //压装日期 YYYYMMDD
    private String handleDate;
    //状态
    private String status;
    /**
     * B线温度
     */
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
    //高速轴滚动轴承温度检测1
    private BigDecimal highSpeedAxisRollingBearingTemperature1;
    //高速轴滚动轴承温度检测2
    private BigDecimal highSpeedAxisRollingBearingTemperature2;
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
    //小皮带轮支持轴承温度检测1
    private BigDecimal smallBeltWheelSupportAxisTemperature1;
    //小皮带轮支持轴承温度检测2
    private BigDecimal smallBeltWheelSupportAxisTemperature2;
    //左前三脚架偏心大铜套温度检测
    private BigDecimal leftFrontTripodEccentricBigCopperSleeve;
    //左后三脚架偏心大铜套温度检测
    private BigDecimal leftRearTripodEccentricBigCopperSleeve;
    //右前三脚架偏心大铜套温度检测
    private BigDecimal rightFrontTripodEccentricBigCopperSleeve;
    //右后三脚架偏心大铜套温度检测
    private BigDecimal rightRearTripodEccentricBigCopperSleeve;
    //左前三脚架下端铜套温度检测
    private BigDecimal leftFrontTripodBottomCopperSleeve;
    //左后三脚架下端铜套温度检测
    private BigDecimal leftRearTripodBottomCopperSleeve;
    //右前三脚架下端铜套温度检测
    private BigDecimal rightFrontTripodBottomCopperSleeve;
    //右后三脚架下端铜套温度检测
    private BigDecimal rightRearTripodBottomCopperSleeve;
    //左前连杆大铜套
    private BigDecimal leftFrontConnectingRodBigCopperSleeve;
    //左后连杆大铜套
    private BigDecimal leftRearConnectingRodBigCopperSleeve;
    //右前连杆大铜套
    private BigDecimal rightFrontConnectingRodBigCopperSleeve;
    //右后连杆大铜套
    private BigDecimal rightRearConnectingRodBigCopperSleeve;
    //左前低速轴铜套
    private BigDecimal leftFrontLowSpeedAxisCopperSleeve;
    //左后低速轴铜套
    private BigDecimal leftRearLowSpeedAxisCopperSleeve;
    //右前低速轴铜套
    private BigDecimal rightFrontLowSpeedAxisCopperSleeve;
    //右后低速轴铜套
    private BigDecimal rightRearLowSpeedAxisCopperSleeve;
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

    /**
     * A线温度
     */
    //A线温度警戒值
    private BigDecimal temperatureWarningValueA;
    //偏心轮左前
    private BigDecimal leftFrontEccentricWheel;
    //偏心轮左后
    private BigDecimal leftRearEccentricWheel;
    //偏心轮右前
    private BigDecimal rightFrontEccentricWheel;
    //偏心轮右后
    private BigDecimal rightRearEccentricWheel;
    //连杆左前
    private BigDecimal leftFrontConnectingRod;
    //连杆左后
    private BigDecimal leftRearConnectingRod;
    //连杆右前
    private BigDecimal rightFrontConnectingRod;
    //连杆右后
    private BigDecimal rightRearConnectingRod;
    //右高速齿轮轴承(前)温度
    private BigDecimal rightFrontHighSpeedGearBearingTemperature;
    //右高速齿轮轴承(后)温度
    private BigDecimal rightRearHighSpeedGearBearingTemperature;
    //左高速齿轮轴承(前)温度
    private BigDecimal leftFrontHighSpeedGearBearingTemperature;
    //左高速齿轮轴承(后)温度
    private BigDecimal leftRearHighSpeedGearBearingTemperature;
    //右中间齿轮轴承(前)温度
    private BigDecimal rightFrontMiddleGearBearingTemperature;
    //右中间齿轮轴承(后)温度
    private BigDecimal rightRearMiddleGearBearingTemperature;
    //左中间齿轮轴承(前)温度
    private BigDecimal leftFrontMiddleGearBearingTemperature;
    //左中间齿轮轴承(后)温度
    private BigDecimal leftRearMiddleGearBearingTemperature;
    //主轴齿轮轴承1温度
    private BigDecimal mainAxisGearBearingTemperature1;
    //主轴齿轮轴承2温度
    private BigDecimal mainAxisGearBearingTemperature2;
    //主轴齿轮轴承3温度
    private BigDecimal mainAxisGearBearingTemperature3;
    //主轴齿轮轴承4温度
    private BigDecimal mainAxisGearBearingTemperature4;
    //主轴齿轮轴承5温度
    private BigDecimal mainAxisGearBearingTemperature5;
    //主轴齿轮轴承6温度
    private BigDecimal mainAxisGearBearingTemperature6;
    //主轴齿轮轴承7温度
    private BigDecimal mainAxisGearBearingTemperature7;
    //主轴齿轮轴承8温度
    private BigDecimal mainAxisGearBearingTemperature8;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getPlcIp() {
        return plcIp;
    }

    public void setPlcIp(String plcIp) {
        this.plcIp = plcIp;
    }

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }

    public boolean isWireless() {
        return isWireless;
    }

    public void setWireless(boolean wireless) {
        isWireless = wireless;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getHighSpeedAxisRollingBearingTemperature1() {
        return highSpeedAxisRollingBearingTemperature1;
    }

    public void setHighSpeedAxisRollingBearingTemperature1(BigDecimal highSpeedAxisRollingBearingTemperature1) {
        this.highSpeedAxisRollingBearingTemperature1 = highSpeedAxisRollingBearingTemperature1;
    }

    public BigDecimal getHighSpeedAxisRollingBearingTemperature2() {
        return highSpeedAxisRollingBearingTemperature2;
    }

    public void setHighSpeedAxisRollingBearingTemperature2(BigDecimal highSpeedAxisRollingBearingTemperature2) {
        this.highSpeedAxisRollingBearingTemperature2 = highSpeedAxisRollingBearingTemperature2;
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

    public BigDecimal getLeftFrontTripodEccentricBigCopperSleeve() {
        return leftFrontTripodEccentricBigCopperSleeve;
    }

    public void setLeftFrontTripodEccentricBigCopperSleeve(BigDecimal leftFrontTripodEccentricBigCopperSleeve) {
        this.leftFrontTripodEccentricBigCopperSleeve = leftFrontTripodEccentricBigCopperSleeve;
    }

    public BigDecimal getLeftRearTripodEccentricBigCopperSleeve() {
        return leftRearTripodEccentricBigCopperSleeve;
    }

    public void setLeftRearTripodEccentricBigCopperSleeve(BigDecimal leftRearTripodEccentricBigCopperSleeve) {
        this.leftRearTripodEccentricBigCopperSleeve = leftRearTripodEccentricBigCopperSleeve;
    }

    public BigDecimal getRightFrontTripodEccentricBigCopperSleeve() {
        return rightFrontTripodEccentricBigCopperSleeve;
    }

    public void setRightFrontTripodEccentricBigCopperSleeve(BigDecimal rightFrontTripodEccentricBigCopperSleeve) {
        this.rightFrontTripodEccentricBigCopperSleeve = rightFrontTripodEccentricBigCopperSleeve;
    }

    public BigDecimal getRightRearTripodEccentricBigCopperSleeve() {
        return rightRearTripodEccentricBigCopperSleeve;
    }

    public void setRightRearTripodEccentricBigCopperSleeve(BigDecimal rightRearTripodEccentricBigCopperSleeve) {
        this.rightRearTripodEccentricBigCopperSleeve = rightRearTripodEccentricBigCopperSleeve;
    }

    public BigDecimal getLeftFrontTripodBottomCopperSleeve() {
        return leftFrontTripodBottomCopperSleeve;
    }

    public void setLeftFrontTripodBottomCopperSleeve(BigDecimal leftFrontTripodBottomCopperSleeve) {
        this.leftFrontTripodBottomCopperSleeve = leftFrontTripodBottomCopperSleeve;
    }

    public BigDecimal getLeftRearTripodBottomCopperSleeve() {
        return leftRearTripodBottomCopperSleeve;
    }

    public void setLeftRearTripodBottomCopperSleeve(BigDecimal leftRearTripodBottomCopperSleeve) {
        this.leftRearTripodBottomCopperSleeve = leftRearTripodBottomCopperSleeve;
    }

    public BigDecimal getRightFrontTripodBottomCopperSleeve() {
        return rightFrontTripodBottomCopperSleeve;
    }

    public void setRightFrontTripodBottomCopperSleeve(BigDecimal rightFrontTripodBottomCopperSleeve) {
        this.rightFrontTripodBottomCopperSleeve = rightFrontTripodBottomCopperSleeve;
    }

    public BigDecimal getRightRearTripodBottomCopperSleeve() {
        return rightRearTripodBottomCopperSleeve;
    }

    public void setRightRearTripodBottomCopperSleeve(BigDecimal rightRearTripodBottomCopperSleeve) {
        this.rightRearTripodBottomCopperSleeve = rightRearTripodBottomCopperSleeve;
    }

    public BigDecimal getLeftFrontConnectingRodBigCopperSleeve() {
        return leftFrontConnectingRodBigCopperSleeve;
    }

    public void setLeftFrontConnectingRodBigCopperSleeve(BigDecimal leftFrontConnectingRodBigCopperSleeve) {
        this.leftFrontConnectingRodBigCopperSleeve = leftFrontConnectingRodBigCopperSleeve;
    }

    public BigDecimal getLeftRearConnectingRodBigCopperSleeve() {
        return leftRearConnectingRodBigCopperSleeve;
    }

    public void setLeftRearConnectingRodBigCopperSleeve(BigDecimal leftRearConnectingRodBigCopperSleeve) {
        this.leftRearConnectingRodBigCopperSleeve = leftRearConnectingRodBigCopperSleeve;
    }

    public BigDecimal getRightFrontConnectingRodBigCopperSleeve() {
        return rightFrontConnectingRodBigCopperSleeve;
    }

    public void setRightFrontConnectingRodBigCopperSleeve(BigDecimal rightFrontConnectingRodBigCopperSleeve) {
        this.rightFrontConnectingRodBigCopperSleeve = rightFrontConnectingRodBigCopperSleeve;
    }

    public BigDecimal getRightRearConnectingRodBigCopperSleeve() {
        return rightRearConnectingRodBigCopperSleeve;
    }

    public void setRightRearConnectingRodBigCopperSleeve(BigDecimal rightRearConnectingRodBigCopperSleeve) {
        this.rightRearConnectingRodBigCopperSleeve = rightRearConnectingRodBigCopperSleeve;
    }

    public BigDecimal getLeftFrontLowSpeedAxisCopperSleeve() {
        return leftFrontLowSpeedAxisCopperSleeve;
    }

    public void setLeftFrontLowSpeedAxisCopperSleeve(BigDecimal leftFrontLowSpeedAxisCopperSleeve) {
        this.leftFrontLowSpeedAxisCopperSleeve = leftFrontLowSpeedAxisCopperSleeve;
    }

    public BigDecimal getLeftRearLowSpeedAxisCopperSleeve() {
        return leftRearLowSpeedAxisCopperSleeve;
    }

    public void setLeftRearLowSpeedAxisCopperSleeve(BigDecimal leftRearLowSpeedAxisCopperSleeve) {
        this.leftRearLowSpeedAxisCopperSleeve = leftRearLowSpeedAxisCopperSleeve;
    }

    public BigDecimal getRightFrontLowSpeedAxisCopperSleeve() {
        return rightFrontLowSpeedAxisCopperSleeve;
    }

    public void setRightFrontLowSpeedAxisCopperSleeve(BigDecimal rightFrontLowSpeedAxisCopperSleeve) {
        this.rightFrontLowSpeedAxisCopperSleeve = rightFrontLowSpeedAxisCopperSleeve;
    }

    public BigDecimal getRightRearLowSpeedAxisCopperSleeve() {
        return rightRearLowSpeedAxisCopperSleeve;
    }

    public void setRightRearLowSpeedAxisCopperSleeve(BigDecimal rightRearLowSpeedAxisCopperSleeve) {
        this.rightRearLowSpeedAxisCopperSleeve = rightRearLowSpeedAxisCopperSleeve;
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

    public BigDecimal getTemperatureWarningValueA() {
        return temperatureWarningValueA;
    }

    public void setTemperatureWarningValueA(BigDecimal temperatureWarningValueA) {
        this.temperatureWarningValueA = temperatureWarningValueA;
    }

    public BigDecimal getLeftFrontEccentricWheel() {
        return leftFrontEccentricWheel;
    }

    public void setLeftFrontEccentricWheel(BigDecimal leftFrontEccentricWheel) {
        this.leftFrontEccentricWheel = leftFrontEccentricWheel;
    }

    public BigDecimal getLeftRearEccentricWheel() {
        return leftRearEccentricWheel;
    }

    public void setLeftRearEccentricWheel(BigDecimal leftRearEccentricWheel) {
        this.leftRearEccentricWheel = leftRearEccentricWheel;
    }

    public BigDecimal getRightFrontEccentricWheel() {
        return rightFrontEccentricWheel;
    }

    public void setRightFrontEccentricWheel(BigDecimal rightFrontEccentricWheel) {
        this.rightFrontEccentricWheel = rightFrontEccentricWheel;
    }

    public BigDecimal getRightRearEccentricWheel() {
        return rightRearEccentricWheel;
    }

    public void setRightRearEccentricWheel(BigDecimal rightRearEccentricWheel) {
        this.rightRearEccentricWheel = rightRearEccentricWheel;
    }

    public BigDecimal getLeftFrontConnectingRod() {
        return leftFrontConnectingRod;
    }

    public void setLeftFrontConnectingRod(BigDecimal leftFrontConnectingRod) {
        this.leftFrontConnectingRod = leftFrontConnectingRod;
    }

    public BigDecimal getLeftRearConnectingRod() {
        return leftRearConnectingRod;
    }

    public void setLeftRearConnectingRod(BigDecimal leftRearConnectingRod) {
        this.leftRearConnectingRod = leftRearConnectingRod;
    }

    public BigDecimal getRightFrontConnectingRod() {
        return rightFrontConnectingRod;
    }

    public void setRightFrontConnectingRod(BigDecimal rightFrontConnectingRod) {
        this.rightFrontConnectingRod = rightFrontConnectingRod;
    }

    public BigDecimal getRightRearConnectingRod() {
        return rightRearConnectingRod;
    }

    public void setRightRearConnectingRod(BigDecimal rightRearConnectingRod) {
        this.rightRearConnectingRod = rightRearConnectingRod;
    }

    public BigDecimal getRightFrontHighSpeedGearBearingTemperature() {
        return rightFrontHighSpeedGearBearingTemperature;
    }

    public void setRightFrontHighSpeedGearBearingTemperature(BigDecimal rightFrontHighSpeedGearBearingTemperature) {
        this.rightFrontHighSpeedGearBearingTemperature = rightFrontHighSpeedGearBearingTemperature;
    }

    public BigDecimal getRightRearHighSpeedGearBearingTemperature() {
        return rightRearHighSpeedGearBearingTemperature;
    }

    public void setRightRearHighSpeedGearBearingTemperature(BigDecimal rightRearHighSpeedGearBearingTemperature) {
        this.rightRearHighSpeedGearBearingTemperature = rightRearHighSpeedGearBearingTemperature;
    }

    public BigDecimal getLeftFrontHighSpeedGearBearingTemperature() {
        return leftFrontHighSpeedGearBearingTemperature;
    }

    public void setLeftFrontHighSpeedGearBearingTemperature(BigDecimal leftFrontHighSpeedGearBearingTemperature) {
        this.leftFrontHighSpeedGearBearingTemperature = leftFrontHighSpeedGearBearingTemperature;
    }

    public BigDecimal getLeftRearHighSpeedGearBearingTemperature() {
        return leftRearHighSpeedGearBearingTemperature;
    }

    public void setLeftRearHighSpeedGearBearingTemperature(BigDecimal leftRearHighSpeedGearBearingTemperature) {
        this.leftRearHighSpeedGearBearingTemperature = leftRearHighSpeedGearBearingTemperature;
    }

    public BigDecimal getRightFrontMiddleGearBearingTemperature() {
        return rightFrontMiddleGearBearingTemperature;
    }

    public void setRightFrontMiddleGearBearingTemperature(BigDecimal rightFrontMiddleGearBearingTemperature) {
        this.rightFrontMiddleGearBearingTemperature = rightFrontMiddleGearBearingTemperature;
    }

    public BigDecimal getRightRearMiddleGearBearingTemperature() {
        return rightRearMiddleGearBearingTemperature;
    }

    public void setRightRearMiddleGearBearingTemperature(BigDecimal rightRearMiddleGearBearingTemperature) {
        this.rightRearMiddleGearBearingTemperature = rightRearMiddleGearBearingTemperature;
    }

    public BigDecimal getLeftFrontMiddleGearBearingTemperature() {
        return leftFrontMiddleGearBearingTemperature;
    }

    public void setLeftFrontMiddleGearBearingTemperature(BigDecimal leftFrontMiddleGearBearingTemperature) {
        this.leftFrontMiddleGearBearingTemperature = leftFrontMiddleGearBearingTemperature;
    }

    public BigDecimal getLeftRearMiddleGearBearingTemperature() {
        return leftRearMiddleGearBearingTemperature;
    }

    public void setLeftRearMiddleGearBearingTemperature(BigDecimal leftRearMiddleGearBearingTemperature) {
        this.leftRearMiddleGearBearingTemperature = leftRearMiddleGearBearingTemperature;
    }

    public BigDecimal getMainAxisGearBearingTemperature1() {
        return mainAxisGearBearingTemperature1;
    }

    public void setMainAxisGearBearingTemperature1(BigDecimal mainAxisGearBearingTemperature1) {
        this.mainAxisGearBearingTemperature1 = mainAxisGearBearingTemperature1;
    }

    public BigDecimal getMainAxisGearBearingTemperature2() {
        return mainAxisGearBearingTemperature2;
    }

    public void setMainAxisGearBearingTemperature2(BigDecimal mainAxisGearBearingTemperature2) {
        this.mainAxisGearBearingTemperature2 = mainAxisGearBearingTemperature2;
    }

    public BigDecimal getMainAxisGearBearingTemperature3() {
        return mainAxisGearBearingTemperature3;
    }

    public void setMainAxisGearBearingTemperature3(BigDecimal mainAxisGearBearingTemperature3) {
        this.mainAxisGearBearingTemperature3 = mainAxisGearBearingTemperature3;
    }

    public BigDecimal getMainAxisGearBearingTemperature4() {
        return mainAxisGearBearingTemperature4;
    }

    public void setMainAxisGearBearingTemperature4(BigDecimal mainAxisGearBearingTemperature4) {
        this.mainAxisGearBearingTemperature4 = mainAxisGearBearingTemperature4;
    }

    public BigDecimal getMainAxisGearBearingTemperature5() {
        return mainAxisGearBearingTemperature5;
    }

    public void setMainAxisGearBearingTemperature5(BigDecimal mainAxisGearBearingTemperature5) {
        this.mainAxisGearBearingTemperature5 = mainAxisGearBearingTemperature5;
    }

    public BigDecimal getMainAxisGearBearingTemperature6() {
        return mainAxisGearBearingTemperature6;
    }

    public void setMainAxisGearBearingTemperature6(BigDecimal mainAxisGearBearingTemperature6) {
        this.mainAxisGearBearingTemperature6 = mainAxisGearBearingTemperature6;
    }

    public BigDecimal getMainAxisGearBearingTemperature7() {
        return mainAxisGearBearingTemperature7;
    }

    public void setMainAxisGearBearingTemperature7(BigDecimal mainAxisGearBearingTemperature7) {
        this.mainAxisGearBearingTemperature7 = mainAxisGearBearingTemperature7;
    }

    public BigDecimal getMainAxisGearBearingTemperature8() {
        return mainAxisGearBearingTemperature8;
    }

    public void setMainAxisGearBearingTemperature8(BigDecimal mainAxisGearBearingTemperature8) {
        this.mainAxisGearBearingTemperature8 = mainAxisGearBearingTemperature8;
    }
}
