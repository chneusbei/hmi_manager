package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.util.HmiUtils;

import java.math.BigDecimal;
import java.util.Date;

public class PressureCurveEntity extends AbstractBaseEntity {
    public PressureCurveEntity(){
    }
    public PressureCurveEntity(int pressureHeadNo, String recordId, Long recordNo, BigDecimal position, BigDecimal pressForce, String handleDate, Boolean isOK) {
        this.pressureHeadNo = pressureHeadNo;
        this.recordId = recordId;
        this.recordNo = recordNo;
        this.position = position;
        this.pressForce = pressForce;
        this.handleDate = handleDate;
        this.isOK = isOK;
    }

    //压头序号
    private int pressureHeadNo;
    //位置/压力曲线ID
    private String recordId;
    //产品ID
    private Long productId;
    //曲线记录开始状态
    private Boolean curveRecording;
    //位置点序号
    private Long recordNo;
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
    //正压反压  1是正压 2是反压
    private int pressFlag;
    //压力是否超限
    private int pressureOutRange;
    //扫码枪启用
    private Boolean traceCodeFlag;
    //追溯码
    private String traceCode;
    private String traceCode0;
    private String traceCode1;
    private String traceCode2;
    private String traceCode3;
    private String traceCode4;
    private String traceCode5;
    private String traceCode6;
    private String traceCode7;
    private String traceCode8;
    private String traceCode9;
    private String traceCode10;
    private String traceCode11;
    private String traceCode12;
    private String traceCode13;
    private String traceCode14;
    private String traceCode15;
    private String traceCode16;
    private String traceCode17;
    private String traceCode18;
    private String traceCode19;
    private String traceCode20;
    private String traceCode21;
    private String traceCode22;
    private String traceCode23;
    private String traceCode24;
    private String traceCode25;
    private String traceCode26;
    private String traceCode27;
    private String traceCode28;
    private String traceCode29;
    private String traceCode30;
    private String traceCode31;



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


    /**
     * 产品属性
     */
    //产品代码
    private String productCode;
    //产品名称
    private String productName;
    //产品追溯码
    private String productTraceCode;
    //压装结果
    private Boolean isOK;

    /**
     * 曲线是否入库，需要清理属性
     */
    //是否已入库
    private boolean isToDb = false;
    //是否已经被前端页面读取
    private boolean isPaint = false;


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

    public Long getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(Long recordNo) {
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

    public int getPressureHeadNo() {
        return pressureHeadNo;
    }

    public void setPressureHeadNo(int pressureHeadNo) {
        this.pressureHeadNo = pressureHeadNo;
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

    public Boolean getOK() {
        return isOK;
    }

    public void setOK(Boolean OK) {
        isOK = OK;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getPressFlag() {
        return pressFlag;
    }

    public void setPressFlag(int pressFlag) {
        this.pressFlag = pressFlag;
    }

    public int getPressureOutRange() {
        return pressureOutRange;
    }

    public void setPressureOutRange(int pressureOutRange) {
        this.pressureOutRange = pressureOutRange;
    }

    public String getTraceCode0() {
        return traceCode0;
    }

    public void setTraceCode0(String traceCode0) {
        this.traceCode0 = traceCode0;
    }

    public String getTraceCode1() {
        return traceCode1;
    }

    public void setTraceCode1(String traceCode1) {
        this.traceCode1 = traceCode1;
    }

    public String getTraceCode2() {
        return traceCode2;
    }

    public void setTraceCode2(String traceCode2) {
        this.traceCode2 = traceCode2;
    }

    public String getTraceCode3() {
        return traceCode3;
    }

    public void setTraceCode3(String traceCode3) {
        this.traceCode3 = traceCode3;
    }

    public String getTraceCode4() {
        return traceCode4;
    }

    public void setTraceCode4(String traceCode4) {
        this.traceCode4 = traceCode4;
    }

    public String getTraceCode5() {
        return traceCode5;
    }

    public void setTraceCode5(String traceCode5) {
        this.traceCode5 = traceCode5;
    }

    public String getTraceCode6() {
        return traceCode6;
    }

    public void setTraceCode6(String traceCode6) {
        this.traceCode6 = traceCode6;
    }

    public String getTraceCode7() {
        return traceCode7;
    }

    public void setTraceCode7(String traceCode7) {
        this.traceCode7 = traceCode7;
    }

    public String getTraceCode8() {
        return traceCode8;
    }

    public void setTraceCode8(String traceCode8) {
        this.traceCode8 = traceCode8;
    }

    public String getTraceCode9() {
        return traceCode9;
    }

    public void setTraceCode9(String traceCode9) {
        this.traceCode9 = traceCode9;
    }

    public String getTraceCode10() {
        return traceCode10;
    }

    public void setTraceCode10(String traceCode10) {
        this.traceCode10 = traceCode10;
    }

    public String getTraceCode11() {
        return traceCode11;
    }

    public void setTraceCode11(String traceCode11) {
        this.traceCode11 = traceCode11;
    }

    public String getTraceCode12() {
        return traceCode12;
    }

    public void setTraceCode12(String traceCode12) {
        this.traceCode12 = traceCode12;
    }

    public String getTraceCode13() {
        return traceCode13;
    }

    public void setTraceCode13(String traceCode13) {
        this.traceCode13 = traceCode13;
    }

    public String getTraceCode14() {
        return traceCode14;
    }

    public void setTraceCode14(String traceCode14) {
        this.traceCode14 = traceCode14;
    }

    public String getTraceCode15() {
        return traceCode15;
    }

    public void setTraceCode15(String traceCode15) {
        this.traceCode15 = traceCode15;
    }

    public String getTraceCode16() {
        return traceCode16;
    }

    public void setTraceCode16(String traceCode16) {
        this.traceCode16 = traceCode16;
    }

    public String getTraceCode17() {
        return traceCode17;
    }

    public void setTraceCode17(String traceCode17) {
        this.traceCode17 = traceCode17;
    }

    public String getTraceCode18() {
        return traceCode18;
    }

    public void setTraceCode18(String traceCode18) {
        this.traceCode18 = traceCode18;
    }

    public String getTraceCode19() {
        return traceCode19;
    }

    public void setTraceCode19(String traceCode19) {
        this.traceCode19 = traceCode19;
    }

    public String getTraceCode20() {
        return traceCode20;
    }

    public void setTraceCode20(String traceCode20) {
        this.traceCode20 = traceCode20;
    }

    public String getTraceCode21() {
        return traceCode21;
    }

    public void setTraceCode21(String traceCode21) {
        this.traceCode21 = traceCode21;
    }

    public String getTraceCode22() {
        return traceCode22;
    }

    public void setTraceCode22(String traceCode22) {
        this.traceCode22 = traceCode22;
    }

    public String getTraceCode23() {
        return traceCode23;
    }

    public void setTraceCode23(String traceCode23) {
        this.traceCode23 = traceCode23;
    }

    public String getTraceCode24() {
        return traceCode24;
    }

    public void setTraceCode24(String traceCode24) {
        this.traceCode24 = traceCode24;
    }

    public String getTraceCode25() {
        return traceCode25;
    }

    public void setTraceCode25(String traceCode25) {
        this.traceCode25 = traceCode25;
    }

    public String getTraceCode26() {
        return traceCode26;
    }

    public void setTraceCode26(String traceCode26) {
        this.traceCode26 = traceCode26;
    }

    public String getTraceCode27() {
        return traceCode27;
    }

    public void setTraceCode27(String traceCode27) {
        this.traceCode27 = traceCode27;
    }

    public String getTraceCode28() {
        return traceCode28;
    }

    public void setTraceCode28(String traceCode28) {
        this.traceCode28 = traceCode28;
    }

    public String getTraceCode29() {
        return traceCode29;
    }

    public void setTraceCode29(String traceCode29) {
        this.traceCode29 = traceCode29;
    }

    public String getTraceCode30() {
        return traceCode30;
    }

    public void setTraceCode30(String traceCode30) {
        this.traceCode30 = traceCode30;
    }

    public String getTraceCode31() {
        return traceCode31;
    }

    public void setTraceCode31(String traceCode31) {
        this.traceCode31 = traceCode31;
    }

    public Boolean getTraceCodeFlag() {
        return traceCodeFlag;
    }

    public void setTraceCodeFlag(Boolean traceCodeFlag) {
        this.traceCodeFlag = traceCodeFlag;
    }

    public String getTraceCode() {
        return traceCode;
    }

    public void setTraceCode(String traceCode) {
        this.traceCode = traceCode;
    }

    public boolean isToDb() {
        return isToDb;
    }

    public void setToDb(boolean toDb) {
        isToDb = toDb;
    }

    public boolean isPaint() {
        return isPaint;
    }

    public void setPaint(boolean paint) {
        isPaint = paint;
    }

    public void setTraceCodeForce() {
        StringBuffer sb= new StringBuffer();
        sb.append(HmiUtils.getStringNoNull(traceCode0));
        sb.append(HmiUtils.getStringNoNull(traceCode1));
        sb.append(HmiUtils.getStringNoNull(traceCode2));
        sb.append(HmiUtils.getStringNoNull(traceCode3));
        sb.append(HmiUtils.getStringNoNull(traceCode4));
        sb.append(HmiUtils.getStringNoNull(traceCode5));
        sb.append(HmiUtils.getStringNoNull(traceCode6));
        sb.append(HmiUtils.getStringNoNull(traceCode7));
        sb.append(HmiUtils.getStringNoNull(traceCode8));
        sb.append(HmiUtils.getStringNoNull(traceCode9));
        sb.append(HmiUtils.getStringNoNull(traceCode10));
        sb.append(HmiUtils.getStringNoNull(traceCode11));
        sb.append(HmiUtils.getStringNoNull(traceCode12));
        sb.append(HmiUtils.getStringNoNull(traceCode13));
        sb.append(HmiUtils.getStringNoNull(traceCode14));
        sb.append(HmiUtils.getStringNoNull(traceCode15));
        sb.append(HmiUtils.getStringNoNull(traceCode16));
        sb.append(HmiUtils.getStringNoNull(traceCode17));
        sb.append(HmiUtils.getStringNoNull(traceCode18));
        sb.append(HmiUtils.getStringNoNull(traceCode19));
        sb.append(HmiUtils.getStringNoNull(traceCode20));
        sb.append(HmiUtils.getStringNoNull(traceCode21));
        sb.append(HmiUtils.getStringNoNull(traceCode22));
        sb.append(HmiUtils.getStringNoNull(traceCode23));
        sb.append(HmiUtils.getStringNoNull(traceCode24));
//        sb.append(HmiUtils.getStringNoNull(traceCode25));
//        sb.append(HmiUtils.getStringNoNull(traceCode26));
//        sb.append(HmiUtils.getStringNoNull(traceCode27));
//        sb.append(HmiUtils.getStringNoNull(traceCode28));
//        sb.append(HmiUtils.getStringNoNull(traceCode29));
//        sb.append(HmiUtils.getStringNoNull(traceCode30));
//        sb.append(HmiUtils.getStringNoNull(traceCode31));
        this.traceCode = sb.toString().trim();
        if(null != traceCode) {
            if(sb.length()>0) {
                traceCode = sb.substring(0, sb.length()-1);
            }
            traceCode = traceCode.replace("/", "");
            traceCode = traceCode.replace("\\", "");
        }
    }
}
