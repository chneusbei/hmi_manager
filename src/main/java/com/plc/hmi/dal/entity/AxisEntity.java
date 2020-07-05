package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.util.Date;

public class AxisEntity extends AbstractBaseEntity {
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private int xMin2;
    private int xMax2;
    private int yMin2;
    private int yMax2;

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public int getxMin2() {
        return xMin2;
    }

    public void setxMin2(int xMin2) {
        this.xMin2 = xMin2;
    }

    public int getxMax2() {
        return xMax2;
    }

    public void setxMax2(int xMax2) {
        this.xMax2 = xMax2;
    }

    public int getyMin2() {
        return yMin2;
    }

    public void setyMin2(int yMin2) {
        this.yMin2 = yMin2;
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public int getyMax2() {
        return yMax2;
    }

    public void setyMax2(int yMax2) {
        this.yMax2 = yMax2;
    }
}
