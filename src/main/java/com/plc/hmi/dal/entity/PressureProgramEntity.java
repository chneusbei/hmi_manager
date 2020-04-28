package com.plc.hmi.dal.entity;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.math.BigDecimal;

public class PressureProgramEntity extends AbstractBaseEntity {
    private Long productId;
    //产品代码
    private String productCode;

    //步骤
    private int step1;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType1;
    //程序参数值
    private BigDecimal programValue1;
    //速度
    private BigDecimal speed1;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType1;
    //位置
    private BigDecimal position1;
    //保护压力值
    private BigDecimal protectPress1;
    //压力
    private BigDecimal press1;
    //保护位置值
    private BigDecimal protectPosition1;
    //保压时间值 timer类型
    private BigDecimal protectTime1;
    //步骤
    private int step2;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType2;
    //程序参数值
    private BigDecimal programValue2;
    //速度
    private BigDecimal speed2;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType2;
    //位置
    private BigDecimal position2;
    //保护压力值
    private BigDecimal protectPress2;
    //压力
    private BigDecimal press2;
    //保护位置值
    private BigDecimal protectPosition2;
    //保压时间值 timer类型
    private BigDecimal protectTime2;
    //步骤
    private int step3;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType3;
    //程序参数值
    private BigDecimal programValue3;
    //速度
    private BigDecimal speed3;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType3;
    //位置
    private BigDecimal position3;
    //保护压力值
    private BigDecimal protectPress3;
    //压力
    private BigDecimal press3;
    //保护位置值
    private BigDecimal protectPosition3;
    //保压时间值 timer类型
    private BigDecimal protectTime3;
    //步骤
    private int step4;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType4;
    //程序参数值
    private BigDecimal programValue4;
    //速度
    private BigDecimal speed4;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType4;
    //位置
    private BigDecimal position4;
    //保护压力值
    private BigDecimal protectPress4;
    //压力
    private BigDecimal press4;
    //保护位置值
    private BigDecimal protectPosition4;
    //保压时间值 timer类型
    private BigDecimal protectTime4;
    //步骤
    private int step5;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType5;
    //程序参数值
    private BigDecimal programValue5;
    //速度
    private BigDecimal speed5;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType5;
    //位置
    private BigDecimal position5;
    //保护压力值
    private BigDecimal protectPress5;
    //压力
    private BigDecimal press5;
    //保护位置值
    private BigDecimal protectPosition5;
    //保压时间值 timer类型
    private BigDecimal protectTime5;
    //步骤
    private int step6;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType6;
    //程序参数值
    private BigDecimal programValue6;
    //速度
    private BigDecimal speed6;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType6;
    //位置
    private BigDecimal position6;
    //保护压力值
    private BigDecimal protectPress6;
    //压力
    private BigDecimal press6;
    //保护位置值
    private BigDecimal protectPosition6;
    //保压时间值 timer类型
    private BigDecimal protectTime6;
    //步骤
    private int step7;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType7;
    //程序参数值
    private BigDecimal programValue7;
    //速度
    private BigDecimal speed7;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType7;
    //位置
    private BigDecimal position7;
    //保护压力值
    private BigDecimal protectPress7;
    //压力
    private BigDecimal press7;
    //保护位置值
    private BigDecimal protectPosition7;
    //保压时间值 timer类型
    private BigDecimal protectTime7;
    //步骤
    private int step8;
    //程序类型 1：绝对位置方式；2：压力方式；4：保压时间;  0:END
    private String programType8;
    //程序参数值
    private BigDecimal programValue8;
    //速度
    private BigDecimal speed8;
    //报警处理方式	INT	Y	1：停止；2：返回原点；4：继续
    private int alarmDealType8;
    //位置
    private BigDecimal position8;
    //保护压力值
    private BigDecimal protectPress8;
    //压力
    private BigDecimal press8;
    //保护位置值
    private BigDecimal protectPosition8;
    //保压时间值 timer类型
    private BigDecimal protectTime8;

    //程序类型 窗口类型_10:disable;1-12:见：公差窗口判据说明
    private int errandType1;
    // '位置下限_1'
    private BigDecimal  positionMin1;
    // '位置上限_1'
    private BigDecimal  positionMax1;
    // '压力下限_1'
    private BigDecimal  pressMin1;
    // '压力上限_1'
    private BigDecimal  pressMax1;
    //程序类型 窗口类型_10:disable;1-12:见：公差窗口判据说明
    private int errandType2;
    // '位置下限'
    private BigDecimal  positionMin2;
    // '位置上限'
    private BigDecimal  positionMax2;
    // '压力下限'
    private BigDecimal  pressMin2;
    // '压力上限'
    private BigDecimal  pressMax2;
    //程序类型 窗口类型_10:disable;1-12:见：公差窗口判据说明
    private int errandType3;
    // '位置下限'
    private BigDecimal  positionMin3;
    // '位置上限'
    private BigDecimal  positionMax3;
    // '压力下限'
    private BigDecimal  pressMin3;
    // '压力上限'
    private BigDecimal  pressMax3;
    //程序类型 窗口类型_10:disable;1-12:见：公差窗口判据说明
    private int errandType4;
    // '位置下限'
    private BigDecimal  positionMin4;
    // '位置上限'
    private BigDecimal  positionMax4;
    // '压力下限'
    private BigDecimal  pressMin4;
    // '压力上限'
    private BigDecimal  pressMax4;
    //程序类型 窗口类型_10:disable;1-12:见：公差窗口判据说明
    private int errandType5;
    // '位置下限'
    private BigDecimal  positionMin5;
    // '位置上限'
    private BigDecimal  positionMax5;
    // '压力下限'
    private BigDecimal  pressMin5;
    // '压力上限'
    private BigDecimal  pressMax5;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getStep1() {
        return step1;
    }

    public void setStep1(int step1) {
        this.step1 = step1;
    }

    public String getProgramType1() {
        return programType1;
    }

    public void setProgramType1(String programType1) {
        this.programType1 = programType1;
    }

    public BigDecimal getProgramValue1() {
        return programValue1;
    }

    public void setProgramValue1(BigDecimal programValue1) {
        this.programValue1 = programValue1;
    }

    public BigDecimal getSpeed1() {
        return speed1;
    }

    public void setSpeed1(BigDecimal speed1) {
        this.speed1 = speed1;
    }

    public int getAlarmDealType1() {
        return alarmDealType1;
    }

    public void setAlarmDealType1(int alarmDealType1) {
        this.alarmDealType1 = alarmDealType1;
    }

    public BigDecimal getPosition1() {
        return position1;
    }

    public void setPosition1(BigDecimal position1) {
        this.position1 = position1;
    }

    public BigDecimal getProtectPress1() {
        return protectPress1;
    }

    public void setProtectPress1(BigDecimal protectPress1) {
        this.protectPress1 = protectPress1;
    }

    public BigDecimal getPress1() {
        return press1;
    }

    public void setPress1(BigDecimal press1) {
        this.press1 = press1;
    }

    public BigDecimal getProtectPosition1() {
        return protectPosition1;
    }

    public void setProtectPosition1(BigDecimal protectPosition1) {
        this.protectPosition1 = protectPosition1;
    }

    public BigDecimal getProtectTime1() {
        return protectTime1;
    }

    public void setProtectTime1(BigDecimal protectTime1) {
        this.protectTime1 = protectTime1;
    }

    public int getStep2() {
        return step2;
    }

    public void setStep2(int step2) {
        this.step2 = step2;
    }

    public String getProgramType2() {
        return programType2;
    }

    public void setProgramType2(String programType2) {
        this.programType2 = programType2;
    }

    public BigDecimal getProgramValue2() {
        return programValue2;
    }

    public void setProgramValue2(BigDecimal programValue2) {
        this.programValue2 = programValue2;
    }

    public BigDecimal getSpeed2() {
        return speed2;
    }

    public void setSpeed2(BigDecimal speed2) {
        this.speed2 = speed2;
    }

    public int getAlarmDealType2() {
        return alarmDealType2;
    }

    public void setAlarmDealType2(int alarmDealType2) {
        this.alarmDealType2 = alarmDealType2;
    }

    public BigDecimal getPosition2() {
        return position2;
    }

    public void setPosition2(BigDecimal position2) {
        this.position2 = position2;
    }

    public BigDecimal getProtectPress2() {
        return protectPress2;
    }

    public void setProtectPress2(BigDecimal protectPress2) {
        this.protectPress2 = protectPress2;
    }

    public BigDecimal getPress2() {
        return press2;
    }

    public void setPress2(BigDecimal press2) {
        this.press2 = press2;
    }

    public BigDecimal getProtectPosition2() {
        return protectPosition2;
    }

    public void setProtectPosition2(BigDecimal protectPosition2) {
        this.protectPosition2 = protectPosition2;
    }

    public BigDecimal getProtectTime2() {
        return protectTime2;
    }

    public void setProtectTime2(BigDecimal protectTime2) {
        this.protectTime2 = protectTime2;
    }

    public int getStep3() {
        return step3;
    }

    public void setStep3(int step3) {
        this.step3 = step3;
    }

    public String getProgramType3() {
        return programType3;
    }

    public void setProgramType3(String programType3) {
        this.programType3 = programType3;
    }

    public BigDecimal getProgramValue3() {
        return programValue3;
    }

    public void setProgramValue3(BigDecimal programValue3) {
        this.programValue3 = programValue3;
    }

    public BigDecimal getSpeed3() {
        return speed3;
    }

    public void setSpeed3(BigDecimal speed3) {
        this.speed3 = speed3;
    }

    public int getAlarmDealType3() {
        return alarmDealType3;
    }

    public void setAlarmDealType3(int alarmDealType3) {
        this.alarmDealType3 = alarmDealType3;
    }

    public BigDecimal getPosition3() {
        return position3;
    }

    public void setPosition3(BigDecimal position3) {
        this.position3 = position3;
    }

    public BigDecimal getProtectPress3() {
        return protectPress3;
    }

    public void setProtectPress3(BigDecimal protectPress3) {
        this.protectPress3 = protectPress3;
    }

    public BigDecimal getPress3() {
        return press3;
    }

    public void setPress3(BigDecimal press3) {
        this.press3 = press3;
    }

    public BigDecimal getProtectPosition3() {
        return protectPosition3;
    }

    public void setProtectPosition3(BigDecimal protectPosition3) {
        this.protectPosition3 = protectPosition3;
    }

    public BigDecimal getProtectTime3() {
        return protectTime3;
    }

    public void setProtectTime3(BigDecimal protectTime3) {
        this.protectTime3 = protectTime3;
    }

    public int getStep4() {
        return step4;
    }

    public void setStep4(int step4) {
        this.step4 = step4;
    }

    public String getProgramType4() {
        return programType4;
    }

    public void setProgramType4(String programType4) {
        this.programType4 = programType4;
    }

    public BigDecimal getProgramValue4() {
        return programValue4;
    }

    public void setProgramValue4(BigDecimal programValue4) {
        this.programValue4 = programValue4;
    }

    public BigDecimal getSpeed4() {
        return speed4;
    }

    public void setSpeed4(BigDecimal speed4) {
        this.speed4 = speed4;
    }

    public int getAlarmDealType4() {
        return alarmDealType4;
    }

    public void setAlarmDealType4(int alarmDealType4) {
        this.alarmDealType4 = alarmDealType4;
    }

    public BigDecimal getPosition4() {
        return position4;
    }

    public void setPosition4(BigDecimal position4) {
        this.position4 = position4;
    }

    public BigDecimal getProtectPress4() {
        return protectPress4;
    }

    public void setProtectPress4(BigDecimal protectPress4) {
        this.protectPress4 = protectPress4;
    }

    public BigDecimal getPress4() {
        return press4;
    }

    public void setPress4(BigDecimal press4) {
        this.press4 = press4;
    }

    public BigDecimal getProtectPosition4() {
        return protectPosition4;
    }

    public void setProtectPosition4(BigDecimal protectPosition4) {
        this.protectPosition4 = protectPosition4;
    }

    public BigDecimal getProtectTime4() {
        return protectTime4;
    }

    public void setProtectTime4(BigDecimal protectTime4) {
        this.protectTime4 = protectTime4;
    }

    public int getStep5() {
        return step5;
    }

    public void setStep5(int step5) {
        this.step5 = step5;
    }

    public String getProgramType5() {
        return programType5;
    }

    public void setProgramType5(String programType5) {
        this.programType5 = programType5;
    }

    public BigDecimal getProgramValue5() {
        return programValue5;
    }

    public void setProgramValue5(BigDecimal programValue5) {
        this.programValue5 = programValue5;
    }

    public BigDecimal getSpeed5() {
        return speed5;
    }

    public void setSpeed5(BigDecimal speed5) {
        this.speed5 = speed5;
    }

    public int getAlarmDealType5() {
        return alarmDealType5;
    }

    public void setAlarmDealType5(int alarmDealType5) {
        this.alarmDealType5 = alarmDealType5;
    }

    public BigDecimal getPosition5() {
        return position5;
    }

    public void setPosition5(BigDecimal position5) {
        this.position5 = position5;
    }

    public BigDecimal getProtectPress5() {
        return protectPress5;
    }

    public void setProtectPress5(BigDecimal protectPress5) {
        this.protectPress5 = protectPress5;
    }

    public BigDecimal getPress5() {
        return press5;
    }

    public void setPress5(BigDecimal press5) {
        this.press5 = press5;
    }

    public BigDecimal getProtectPosition5() {
        return protectPosition5;
    }

    public void setProtectPosition5(BigDecimal protectPosition5) {
        this.protectPosition5 = protectPosition5;
    }

    public BigDecimal getProtectTime5() {
        return protectTime5;
    }

    public void setProtectTime5(BigDecimal protectTime5) {
        this.protectTime5 = protectTime5;
    }

    public int getStep6() {
        return step6;
    }

    public void setStep6(int step6) {
        this.step6 = step6;
    }

    public String getProgramType6() {
        return programType6;
    }

    public void setProgramType6(String programType6) {
        this.programType6 = programType6;
    }

    public BigDecimal getProgramValue6() {
        return programValue6;
    }

    public void setProgramValue6(BigDecimal programValue6) {
        this.programValue6 = programValue6;
    }

    public BigDecimal getSpeed6() {
        return speed6;
    }

    public void setSpeed6(BigDecimal speed6) {
        this.speed6 = speed6;
    }

    public int getAlarmDealType6() {
        return alarmDealType6;
    }

    public void setAlarmDealType6(int alarmDealType6) {
        this.alarmDealType6 = alarmDealType6;
    }

    public BigDecimal getPosition6() {
        return position6;
    }

    public void setPosition6(BigDecimal position6) {
        this.position6 = position6;
    }

    public BigDecimal getProtectPress6() {
        return protectPress6;
    }

    public void setProtectPress6(BigDecimal protectPress6) {
        this.protectPress6 = protectPress6;
    }

    public BigDecimal getPress6() {
        return press6;
    }

    public void setPress6(BigDecimal press6) {
        this.press6 = press6;
    }

    public BigDecimal getProtectPosition6() {
        return protectPosition6;
    }

    public void setProtectPosition6(BigDecimal protectPosition6) {
        this.protectPosition6 = protectPosition6;
    }

    public BigDecimal getProtectTime6() {
        return protectTime6;
    }

    public void setProtectTime6(BigDecimal protectTime6) {
        this.protectTime6 = protectTime6;
    }

    public int getStep7() {
        return step7;
    }

    public void setStep7(int step7) {
        this.step7 = step7;
    }

    public String getProgramType7() {
        return programType7;
    }

    public void setProgramType7(String programType7) {
        this.programType7 = programType7;
    }

    public BigDecimal getProgramValue7() {
        return programValue7;
    }

    public void setProgramValue7(BigDecimal programValue7) {
        this.programValue7 = programValue7;
    }

    public BigDecimal getSpeed7() {
        return speed7;
    }

    public void setSpeed7(BigDecimal speed7) {
        this.speed7 = speed7;
    }

    public int getAlarmDealType7() {
        return alarmDealType7;
    }

    public void setAlarmDealType7(int alarmDealType7) {
        this.alarmDealType7 = alarmDealType7;
    }

    public BigDecimal getPosition7() {
        return position7;
    }

    public void setPosition7(BigDecimal position7) {
        this.position7 = position7;
    }

    public BigDecimal getProtectPress7() {
        return protectPress7;
    }

    public void setProtectPress7(BigDecimal protectPress7) {
        this.protectPress7 = protectPress7;
    }

    public BigDecimal getPress7() {
        return press7;
    }

    public void setPress7(BigDecimal press7) {
        this.press7 = press7;
    }

    public BigDecimal getProtectPosition7() {
        return protectPosition7;
    }

    public void setProtectPosition7(BigDecimal protectPosition7) {
        this.protectPosition7 = protectPosition7;
    }

    public BigDecimal getProtectTime7() {
        return protectTime7;
    }

    public void setProtectTime7(BigDecimal protectTime7) {
        this.protectTime7 = protectTime7;
    }

    public int getStep8() {
        return step8;
    }

    public void setStep8(int step8) {
        this.step8 = step8;
    }

    public String getProgramType8() {
        return programType8;
    }

    public void setProgramType8(String programType8) {
        this.programType8 = programType8;
    }

    public BigDecimal getProgramValue8() {
        return programValue8;
    }

    public void setProgramValue8(BigDecimal programValue8) {
        this.programValue8 = programValue8;
    }

    public BigDecimal getSpeed8() {
        return speed8;
    }

    public void setSpeed8(BigDecimal speed8) {
        this.speed8 = speed8;
    }

    public int getAlarmDealType8() {
        return alarmDealType8;
    }

    public void setAlarmDealType8(int alarmDealType8) {
        this.alarmDealType8 = alarmDealType8;
    }

    public BigDecimal getPosition8() {
        return position8;
    }

    public void setPosition8(BigDecimal position8) {
        this.position8 = position8;
    }

    public BigDecimal getProtectPress8() {
        return protectPress8;
    }

    public void setProtectPress8(BigDecimal protectPress8) {
        this.protectPress8 = protectPress8;
    }

    public BigDecimal getPress8() {
        return press8;
    }

    public void setPress8(BigDecimal press8) {
        this.press8 = press8;
    }

    public BigDecimal getProtectPosition8() {
        return protectPosition8;
    }

    public void setProtectPosition8(BigDecimal protectPosition8) {
        this.protectPosition8 = protectPosition8;
    }

    public BigDecimal getProtectTime8() {
        return protectTime8;
    }

    public void setProtectTime8(BigDecimal protectTime8) {
        this.protectTime8 = protectTime8;
    }

    public int getErrandType1() {
        return errandType1;
    }

    public void setErrandType1(int errandType1) {
        this.errandType1 = errandType1;
    }

    public BigDecimal getPositionMin1() {
        return positionMin1;
    }

    public void setPositionMin1(BigDecimal positionMin1) {
        this.positionMin1 = positionMin1;
    }

    public BigDecimal getPositionMax1() {
        return positionMax1;
    }

    public void setPositionMax1(BigDecimal positionMax1) {
        this.positionMax1 = positionMax1;
    }

    public BigDecimal getPressMin1() {
        return pressMin1;
    }

    public void setPressMin1(BigDecimal pressMin1) {
        this.pressMin1 = pressMin1;
    }

    public BigDecimal getPressMax1() {
        return pressMax1;
    }

    public void setPressMax1(BigDecimal pressMax1) {
        this.pressMax1 = pressMax1;
    }

    public int getErrandType2() {
        return errandType2;
    }

    public void setErrandType2(int errandType2) {
        this.errandType2 = errandType2;
    }

    public BigDecimal getPositionMin2() {
        return positionMin2;
    }

    public void setPositionMin2(BigDecimal positionMin2) {
        this.positionMin2 = positionMin2;
    }

    public BigDecimal getPositionMax2() {
        return positionMax2;
    }

    public void setPositionMax2(BigDecimal positionMax2) {
        this.positionMax2 = positionMax2;
    }

    public BigDecimal getPressMin2() {
        return pressMin2;
    }

    public void setPressMin2(BigDecimal pressMin2) {
        this.pressMin2 = pressMin2;
    }

    public BigDecimal getPressMax2() {
        return pressMax2;
    }

    public void setPressMax2(BigDecimal pressMax2) {
        this.pressMax2 = pressMax2;
    }

    public int getErrandType3() {
        return errandType3;
    }

    public void setErrandType3(int errandType3) {
        this.errandType3 = errandType3;
    }

    public BigDecimal getPositionMin3() {
        return positionMin3;
    }

    public void setPositionMin3(BigDecimal positionMin3) {
        this.positionMin3 = positionMin3;
    }

    public BigDecimal getPositionMax3() {
        return positionMax3;
    }

    public void setPositionMax3(BigDecimal positionMax3) {
        this.positionMax3 = positionMax3;
    }

    public BigDecimal getPressMin3() {
        return pressMin3;
    }

    public void setPressMin3(BigDecimal pressMin3) {
        this.pressMin3 = pressMin3;
    }

    public BigDecimal getPressMax3() {
        return pressMax3;
    }

    public void setPressMax3(BigDecimal pressMax3) {
        this.pressMax3 = pressMax3;
    }

    public int getErrandType4() {
        return errandType4;
    }

    public void setErrandType4(int errandType4) {
        this.errandType4 = errandType4;
    }

    public BigDecimal getPositionMin4() {
        return positionMin4;
    }

    public void setPositionMin4(BigDecimal positionMin4) {
        this.positionMin4 = positionMin4;
    }

    public BigDecimal getPositionMax4() {
        return positionMax4;
    }

    public void setPositionMax4(BigDecimal positionMax4) {
        this.positionMax4 = positionMax4;
    }

    public BigDecimal getPressMin4() {
        return pressMin4;
    }

    public void setPressMin4(BigDecimal pressMin4) {
        this.pressMin4 = pressMin4;
    }

    public BigDecimal getPressMax4() {
        return pressMax4;
    }

    public void setPressMax4(BigDecimal pressMax4) {
        this.pressMax4 = pressMax4;
    }

    public int getErrandType5() {
        return errandType5;
    }

    public void setErrandType5(int errandType5) {
        this.errandType5 = errandType5;
    }

    public BigDecimal getPositionMin5() {
        return positionMin5;
    }

    public void setPositionMin5(BigDecimal positionMin5) {
        this.positionMin5 = positionMin5;
    }

    public BigDecimal getPositionMax5() {
        return positionMax5;
    }

    public void setPositionMax5(BigDecimal positionMax5) {
        this.positionMax5 = positionMax5;
    }

    public BigDecimal getPressMin5() {
        return pressMin5;
    }

    public void setPressMin5(BigDecimal pressMin5) {
        this.pressMin5 = pressMin5;
    }

    public BigDecimal getPressMax5() {
        return pressMax5;
    }

    public void setPressMax5(BigDecimal pressMax5) {
        this.pressMax5 = pressMax5;
    }

    @Override
    public String toString() {
        return "PressureProgramEntity{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", step1=" + step1 +
                ", programType1='" + programType1 + '\'' +
                ", programValue1=" + programValue1 +
                ", speed1=" + speed1 +
                ", alarmDealType1=" + alarmDealType1 +
                ", position1=" + position1 +
                ", protectPress1=" + protectPress1 +
                ", press1=" + press1 +
                ", protectPosition1=" + protectPosition1 +
                ", protectTime1=" + protectTime1 +
                ", step2=" + step2 +
                ", programType2='" + programType2 + '\'' +
                ", programValue2=" + programValue2 +
                ", speed2=" + speed2 +
                ", alarmDealType2=" + alarmDealType2 +
                ", position2=" + position2 +
                ", protectPress2=" + protectPress2 +
                ", press2=" + press2 +
                ", protectPosition2=" + protectPosition2 +
                ", protectTime2=" + protectTime2 +
                ", step3=" + step3 +
                ", programType3='" + programType3 + '\'' +
                ", programValue3=" + programValue3 +
                ", speed3=" + speed3 +
                ", alarmDealType3=" + alarmDealType3 +
                ", position3=" + position3 +
                ", protectPress3=" + protectPress3 +
                ", press3=" + press3 +
                ", protectPosition3=" + protectPosition3 +
                ", protectTime3=" + protectTime3 +
                ", step4=" + step4 +
                ", programType4='" + programType4 + '\'' +
                ", programValue4=" + programValue4 +
                ", speed4=" + speed4 +
                ", alarmDealType4=" + alarmDealType4 +
                ", position4=" + position4 +
                ", protectPress4=" + protectPress4 +
                ", press4=" + press4 +
                ", protectPosition4=" + protectPosition4 +
                ", protectTime4=" + protectTime4 +
                ", step5=" + step5 +
                ", programType5='" + programType5 + '\'' +
                ", programValue5=" + programValue5 +
                ", speed5=" + speed5 +
                ", alarmDealType5=" + alarmDealType5 +
                ", position5=" + position5 +
                ", protectPress5=" + protectPress5 +
                ", press5=" + press5 +
                ", protectPosition5=" + protectPosition5 +
                ", protectTime5=" + protectTime5 +
                ", step6=" + step6 +
                ", programType6='" + programType6 + '\'' +
                ", programValue6=" + programValue6 +
                ", speed6=" + speed6 +
                ", alarmDealType6=" + alarmDealType6 +
                ", position6=" + position6 +
                ", protectPress6=" + protectPress6 +
                ", press6=" + press6 +
                ", protectPosition6=" + protectPosition6 +
                ", protectTime6=" + protectTime6 +
                ", step7=" + step7 +
                ", programType7='" + programType7 + '\'' +
                ", programValue7=" + programValue7 +
                ", speed7=" + speed7 +
                ", alarmDealType7=" + alarmDealType7 +
                ", position7=" + position7 +
                ", protectPress7=" + protectPress7 +
                ", press7=" + press7 +
                ", protectPosition7=" + protectPosition7 +
                ", protectTime7=" + protectTime7 +
                ", step8=" + step8 +
                ", programType8='" + programType8 + '\'' +
                ", programValue8=" + programValue8 +
                ", speed8=" + speed8 +
                ", alarmDealType8=" + alarmDealType8 +
                ", position8=" + position8 +
                ", protectPress8=" + protectPress8 +
                ", press8=" + press8 +
                ", protectPosition8=" + protectPosition8 +
                ", protectTime8=" + protectTime8 +
                ", errandType1=" + errandType1 +
                ", positionMin1=" + positionMin1 +
                ", positionMax1=" + positionMax1 +
                ", pressMin1=" + pressMin1 +
                ", pressMax1=" + pressMax1 +
                ", errandType2=" + errandType2 +
                ", positionMin2=" + positionMin2 +
                ", positionMax2=" + positionMax2 +
                ", pressMin2=" + pressMin2 +
                ", pressMax2=" + pressMax2 +
                ", errandType3=" + errandType3 +
                ", positionMin3=" + positionMin3 +
                ", positionMax3=" + positionMax3 +
                ", pressMin3=" + pressMin3 +
                ", pressMax3=" + pressMax3 +
                ", errandType4=" + errandType4 +
                ", positionMin4=" + positionMin4 +
                ", positionMax4=" + positionMax4 +
                ", pressMin4=" + pressMin4 +
                ", pressMax4=" + pressMax4 +
                ", errandType5=" + errandType5 +
                ", positionMin5=" + positionMin5 +
                ", positionMax5=" + positionMax5 +
                ", pressMin5=" + pressMin5 +
                ", pressMax5=" + pressMax5 +
                '}';
    }
}
