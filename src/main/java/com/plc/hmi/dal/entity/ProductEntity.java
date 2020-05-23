package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

public class ProductEntity extends AbstractBaseEntity {
    //零件号
    private String productId;
    //产品代码
    private String productCode;
    //产品类型
    private String productType;
    //产品名称
    private String productName;
    //产品追溯码
    private String productTraceCode;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTraceCode() {
        return productTraceCode;
    }

    public void setProductTraceCode(String productTraceCode) {
        this.productTraceCode = productTraceCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}

