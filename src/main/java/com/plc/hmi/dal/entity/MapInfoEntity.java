package com.plc.hmi.dal.entity;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class MapInfoEntity extends AbstractBaseEntity {

    public MapInfoEntity() {
        this.setCreateBy("ADMIN");
        this.setUpdateBy("ADMIN");
        this.setCreateTime(new Date());
        this.setUpdateTime(this.getCreateTime());
    }

    //点代码
    private String pointCode;
    //类型
    private String pointType;
    //起始点
    private BigDecimal startPoint;
    //结束点
    private BigDecimal endPoint;
    //线速度
    private BigDecimal lineVelocity;
    //角速度
    private BigDecimal angleVelocity;


    //地图名称
    private String mapName;
    //地图所属工厂
    private String factoryName;
    //地图所属车间
    private String workshop;
    //地图状态
    private String status;
    //AVG车辆信息
    private String avgName;

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public BigDecimal getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(BigDecimal startPoint) {
        this.startPoint = startPoint;
    }

    public BigDecimal getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(BigDecimal endPoint) {
        this.endPoint = endPoint;
    }

    public BigDecimal getLineVelocity() {
        return lineVelocity;
    }

    public void setLineVelocity(BigDecimal lineVelocity) {
        this.lineVelocity = lineVelocity;
    }

    public BigDecimal getAngleVelocity() {
        return angleVelocity;
    }

    public void setAngleVelocity(BigDecimal angleVelocity) {
        this.angleVelocity = angleVelocity;
    }


    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvgName() {
        return avgName;
    }

    public void setAvgName(String avgName) {
        this.avgName = avgName;
    }
}
