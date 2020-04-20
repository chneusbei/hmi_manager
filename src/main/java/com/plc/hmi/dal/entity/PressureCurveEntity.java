package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class PressureCurveEntity extends AbstractBaseEntity {
    //位置/压力曲线ID
    private String recordId;
    //产品ID
    private Long productId;
    //曲线记录开始状态
    private Boolean curveRecording;
    //位置点序号
    private int recordNo;
    //位置
    private BigDecimal position;
    //压力
    private BigDecimal pressForce;
    //当前速度
    private BigDecimal curSpeed;
    //压装日期 YYYYMMDD
    private String handleDate;
    //压装时间点
    private BigDecimal pressDate;
    //压装时间点
    private BigDecimal shortPressDate;


    /**
     * PLC上有的字段
     */
    //当前速度
    private BigDecimal speed;
    //预留_0
    private BigDecimal reserve0;
    //reserve1
    private BigDecimal reserve1;
    //reserve2
    private BigDecimal reserve2;


    /**
     * 为界面画公差窗口添加的属性
     */
    //是否是公差曲线， 为true时用黑色线paint，为false时，用蓝色线paint
    private boolean isErrant;
    //是否是实线， 如果是false， 需要画为虚线。 只有是公差曲线即isErrant=true时，才可能是false
    private boolean isSolidLine;

    public boolean isErrant() {
        return isErrant;
    }

    public void setErrant(boolean errant) {
        isErrant = errant;
    }

    public boolean isSolidLine() {
        return isSolidLine;
    }

    public void setSolidLine(boolean solidLine) {
        isSolidLine = solidLine;
    }

    public BigDecimal getReserve0() {
        return reserve0;
    }

    public void setReserve0(BigDecimal reserve0) {
        this.reserve0 = reserve0;
    }

    public BigDecimal getReserve1() {
        return reserve1;
    }

    public void setReserve1(BigDecimal reserve1) {
        this.reserve1 = reserve1;
    }

    public BigDecimal getReserve2() {
        return reserve2;
    }

    public void setReserve2(BigDecimal reserve2) {
        this.reserve2 = reserve2;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getPressDate() {
        return pressDate;
    }

    public void setPressDate(BigDecimal pressDate) {
        this.pressDate = pressDate;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public int getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
    }

    public BigDecimal getPosition() {
        return position;
    }

    public void setPosition(BigDecimal position) {
        this.position = position;
    }

    public BigDecimal getPressForce() {
        return pressForce;
    }

    public void setPressForce(BigDecimal pressForce) {
        this.pressForce = pressForce;
    }

    public BigDecimal getCurSpeed() {
        return curSpeed;
    }

    public void setCurSpeed(BigDecimal curSpeed) {
        this.curSpeed = curSpeed;
    }

    public BigDecimal getShortPressDate() {
        return shortPressDate;
    }

    public void setShortPressDate(BigDecimal shortPressDate) {
        this.shortPressDate = shortPressDate;
    }

    public Boolean getCurveRecording() {
        return curveRecording;
    }

    public void setCurveRecording(Boolean curveRecording) {
        this.curveRecording = curveRecording;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
