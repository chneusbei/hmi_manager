package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

public class TagsInfoEntity extends AbstractBaseEntity {
    //变量名
    private String tagName;
    //变量英文名
    private String tagEnName;
    //变量类型
    private int tagTypeId;
    //变量类型描述
    private String tagTypeDes;
    //数据区
    private String tagArea;
    //数据区名字
    private String tagAreaName;
    //数据块地址
    private int dbNo;
    //地址
    private int adderss;
    //位
    private int tagBit;
    //分组
    private String tagGroup;

    public String getTagEnName() {
        return tagEnName;
    }

    public void setTagEnName(String tagEnName) {
        this.tagEnName = tagEnName;
    }

    public String getTagAreaName() {
        return tagAreaName;
    }

    public void setTagAreaName(String tagAreaName) {
        this.tagAreaName = tagAreaName;
    }

    public String getTagTypeDes() {
        return tagTypeDes;
    }

    public void setTagTypeDes(String tagTypeDes) {
        this.tagTypeDes = tagTypeDes;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(int tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public String getTagArea() {
        return tagArea;
    }

    public void setTagArea(String tagArea) {
        this.tagArea = tagArea;
    }

    public int getDbNo() {
        return dbNo;
    }

    public void setDbNo(int dbNo) {
        this.dbNo = dbNo;
    }

    public int getAdderss() {
        return adderss;
    }

    public void setAdderss(int adderss) {
        this.adderss = adderss;
    }

    public int getTagBit() {
        return tagBit;
    }

    public void setTagBit(int tagBit) {
        this.tagBit = tagBit;
    }

    public String getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(String tagGroup) {
        this.tagGroup = tagGroup;
    }
}
