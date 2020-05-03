package com.plc.hmi.dal.entity;


import java.math.BigDecimal;

public class ErrandResultEntity{
    //曲线IO
    private String recordId;
    //最大位移满足
    private boolean maxPositonSucess = true;
    //最小位移满足
    private boolean minPositonSucess = true;
    //最大压力满足
    private boolean maxPressSucess = true;
    //最小压力满足
    private boolean minPressSucess = true;
    //存在小于最小位移的点
    private boolean hasPointBeforeMinPosition = true;
    //存在大于最大位移的点
    private boolean hasPointAfterManPosition = true;

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

    public boolean isMaxPositonSucess() {
        return maxPositonSucess;
    }

    public void setMaxPositonSucess(boolean maxPositonSucess) {
        this.maxPositonSucess = maxPositonSucess;
    }

    public boolean isMinPositonSucess() {
        return minPositonSucess;
    }

    public void setMinPositonSucess(boolean minPositonSucess) {
        this.minPositonSucess = minPositonSucess;
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

    public boolean isHasPointBeforeMinPosition() {
        return hasPointBeforeMinPosition;
    }

    public void setHasPointBeforeMinPosition(boolean hasPointBeforeMinPosition) {
        this.hasPointBeforeMinPosition = hasPointBeforeMinPosition;
    }

    public boolean isHasPointAfterManPosition() {
        return hasPointAfterManPosition;
    }

    public void setHasPointAfterManPosition(boolean hasPointAfterManPosition) {
        this.hasPointAfterManPosition = hasPointAfterManPosition;
    }
}
