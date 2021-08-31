package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class PressureStatisticalDataEntity extends AbstractBaseEntity {
    //压装总数量
    private BigDecimal totalAmount = new BigDecimal(0);
    //成功数量
    private BigDecimal successAmount = new BigDecimal(0);
    //失败数量
    private BigDecimal failAmount = new BigDecimal(0);

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(BigDecimal successAmount) {
        this.successAmount = successAmount;
    }

    public BigDecimal getFailAmount() {
        return failAmount;
    }

    public void setFailAmount(BigDecimal failAmount) {
        this.failAmount = failAmount;
    }
}
