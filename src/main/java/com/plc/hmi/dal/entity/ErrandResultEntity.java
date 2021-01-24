package com.plc.hmi.dal.entity;


import java.math.BigDecimal;

public class ErrandResultEntity{
    //曲线IO
    private String recordId;
    //最大位移满足
    private boolean maxPositionSucess = true;
    //最小位移满足
    private boolean minPositionSucess = true;
    //最大压力满足
    private boolean maxPressSucess = true;
    //最小压力满足
    private boolean minPressSucess = true;
    //最小位移要求满足标志
    private boolean minPositionFlag = true;
    //最大位移要求满足标志
    private boolean maxPositionFlag = true;
    //最小压力要求满足标志
    private boolean minPressFlag = true;
    //最大压力要求满足标志
    private boolean maxPressFlag = true;

    //公差窗口类习惯
    private int errandType;
    //最小位移
    private BigDecimal positionMin;
    //最大位移
    private BigDecimal positionMax;
    //最小压力
    private BigDecimal pressMin;
    //最大压力
    private BigDecimal pressMax;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public boolean isMaxPositionSucess() {
        return maxPositionSucess;
    }

    public void setMaxPositionSucess(boolean maxPositionSucess) {
        this.maxPositionSucess = maxPositionSucess;
    }

    public boolean isMinPositionSucess() {
        return minPositionSucess;
    }

    public void setMinPositionSucess(boolean minPositionSucess) {
        this.minPositionSucess = minPositionSucess;
    }

    public boolean isMaxPressSucess() {
        return maxPressSucess;
    }

    public void setMaxPressSucess(boolean maxPressSucess) {
        this.maxPressSucess = maxPressSucess;
    }

    public boolean isMinPressSucess() {
        return minPressSucess;
    }

    public void setMinPressSucess(boolean minPressSucess) {
        this.minPressSucess = minPressSucess;
    }

    public int getErrandType() {
        return errandType;
    }

    public void setErrandType(int errandType) {
        this.errandType = errandType;
    }

    public BigDecimal getPositionMin() {
        return positionMin;
    }

    public void setPositionMin(BigDecimal positionMin) {
        this.positionMin = positionMin;
    }

    public BigDecimal getPositionMax() {
        return positionMax;
    }

    public void setPositionMax(BigDecimal positionMax) {
        this.positionMax = positionMax;
    }

    public BigDecimal getPressMin() {
        return pressMin;
    }

    public void setPressMin(BigDecimal pressMin) {
        this.pressMin = pressMin;
    }

    public BigDecimal getPressMax() {
        return pressMax;
    }

    public void setPressMax(BigDecimal pressMax) {
        this.pressMax = pressMax;
    }

    public boolean isMinPositionFlag() {
        return minPositionFlag;
    }

    public void setMinPositionFlag(boolean minPositionFlag) {
        this.minPositionFlag = minPositionFlag;
    }

    public boolean isMaxPositionFlag() {
        return maxPositionFlag;
    }

    public void setMaxPositionFlag(boolean maxPositionFlag) {
        this.maxPositionFlag = maxPositionFlag;
    }

    public boolean isMinPressFlag() {
        return minPressFlag;
    }

    public void setMinPressFlag(boolean minPressFlag) {
        this.minPressFlag = minPressFlag;
    }

    public boolean isMaxPressFlag() {
        return maxPressFlag;
    }

    public void setMaxPressFlag(boolean maxPressFlag) {
        this.maxPressFlag = maxPressFlag;
    }
}
