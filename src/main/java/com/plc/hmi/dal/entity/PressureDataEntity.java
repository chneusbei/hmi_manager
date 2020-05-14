package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class PressureDataEntity extends AbstractBaseEntity {
    //压头序号
    private int pressureHeadNo;
    //产品ID
    private Long productId;
    //产品二维码
    private String productNo;
    //压装结果 0失败 1成功
    private String pressResult;
    //位置/压力曲线ID
    private String recordId;
    //数据开始时间
    private BigDecimal startDate;
    //数据结束时间
    private BigDecimal endDate;
    //最大压力值
    private BigDecimal maxPress;
    //最大压力时候位移
    private BigDecimal positionOfMaxPress;

    public BigDecimal getEndDate() {
        return endDate;
    }

    public void setEndDate(BigDecimal endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMaxPress() {
        return maxPress;
    }

    public void setMaxPress(BigDecimal maxPress) {
        this.maxPress = maxPress;
    }

    public BigDecimal getPositionOfMaxPress() {
        return positionOfMaxPress;
    }

    public void setPositionOfMaxPress(BigDecimal positionOfMaxPress) {
        this.positionOfMaxPress = positionOfMaxPress;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getPressResult() {
        return pressResult;
    }

    public void setPressResult(String pressResult) {
        this.pressResult = pressResult;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public BigDecimal getStartDate() {
        return startDate;
    }

    public void setStartDate(BigDecimal startDate) {
        this.startDate = startDate;
    }

    public int getPressureHeadNo() {
        return pressureHeadNo;
    }

    public void setPressureHeadNo(int pressureHeadNo) {
        this.pressureHeadNo = pressureHeadNo;
    }
}
