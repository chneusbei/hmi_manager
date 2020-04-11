package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.dal.mapper.PressureProgramMapper;
import com.plc.hmi.enumeration.PressureProgramEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class PressureProgramDao extends AbstractHmiBaseDao {
    @Resource
    PressureProgramMapper pressureProgramMapper;

    public List<PressureProgramEntity> getWithProductId(Long productId) {
        return this.getEntityList(pressureProgramMapper.getWithProductId(productId));
    }

    @Override
    protected PressureProgramEntity getEntity(HashMap map) {
        PressureProgramEntity entity = new PressureProgramEntity();
        super.setEntityBase(entity, map);
        entity.setProductId(HmiUtils.getLongValue(map.get(PressureProgramEntityEnum.PRODUCT_ID.getCode())));
        entity.setProductCode(HmiUtils.getString(map.get(PressureProgramEntityEnum.PRODUCT_CODE.getCode())));
        entity.setStep1(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_1.getCode())));
        entity.setProgramType1(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_1.getCode())));
        entity.setProgramValue1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_1.getCode())));
        entity.setSpeed1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_1.getCode())));
        entity.setAlarmDealType1(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_1.getCode())));
        entity.setPosition1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_1.getCode())));
        entity.setProtectPress1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_1.getCode())));
        entity.setPress1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_1.getCode())));
        entity.setProtectPosition1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_1.getCode())));
        entity.setProtectTime1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_1.getCode())));

        entity.setStep2(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_2.getCode())));
        entity.setProgramType2(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_2.getCode())));
        entity.setProgramValue2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_2.getCode())));
        entity.setSpeed2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_2.getCode())));
        entity.setAlarmDealType2(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_2.getCode())));
        entity.setPosition2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_2.getCode())));
        entity.setProtectPress2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_2.getCode())));
        entity.setPress2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_2.getCode())));
        entity.setProtectPosition2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_2.getCode())));
        entity.setProtectTime2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_2.getCode())));

        entity.setStep3(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_3.getCode())));
        entity.setProgramType3(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_3.getCode())));
        entity.setProgramValue3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_3.getCode())));
        entity.setSpeed3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_3.getCode())));
        entity.setAlarmDealType3(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_3.getCode())));
        entity.setPosition3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_3.getCode())));
        entity.setProtectPress3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_3.getCode())));
        entity.setPress3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_3.getCode())));
        entity.setProtectPosition3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_3.getCode())));
        entity.setProtectTime3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_3.getCode())));

        entity.setStep4(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_4.getCode())));
        entity.setProgramType4(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_4.getCode())));
        entity.setProgramValue4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_4.getCode())));
        entity.setSpeed4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_4.getCode())));
        entity.setAlarmDealType4(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_4.getCode())));
        entity.setPosition4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_4.getCode())));
        entity.setProtectPress4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_4.getCode())));
        entity.setPress4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_4.getCode())));
        entity.setProtectPosition4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_4.getCode())));
        entity.setProtectTime4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_4.getCode())));

        entity.setStep5(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_5.getCode())));
        entity.setProgramType5(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_5.getCode())));
        entity.setProgramValue5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_5.getCode())));
        entity.setSpeed5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_5.getCode())));
        entity.setAlarmDealType5(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_5.getCode())));
        entity.setPosition5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_5.getCode())));
        entity.setProtectPress5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_5.getCode())));
        entity.setPress5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_5.getCode())));
        entity.setProtectPosition5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_5.getCode())));
        entity.setProtectTime5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_5.getCode())));

        entity.setStep6(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_6.getCode())));
        entity.setProgramType6(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_6.getCode())));
        entity.setProgramValue6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_6.getCode())));
        entity.setSpeed6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_6.getCode())));
        entity.setAlarmDealType6(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_6.getCode())));
        entity.setPosition6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_6.getCode())));
        entity.setProtectPress6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_6.getCode())));
        entity.setPress6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_6.getCode())));
        entity.setProtectPosition6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_6.getCode())));
        entity.setProtectTime6(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_6.getCode())));

        entity.setStep7(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_7.getCode())));
        entity.setProgramType7(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_7.getCode())));
        entity.setProgramValue7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_7.getCode())));
        entity.setSpeed7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_7.getCode())));
        entity.setAlarmDealType7(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_7.getCode())));
        entity.setPosition7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_7.getCode())));
        entity.setProtectPress7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_7.getCode())));
        entity.setPress7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_7.getCode())));
        entity.setProtectPosition7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_7.getCode())));
        entity.setProtectTime7(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_7.getCode())));

        entity.setStep8(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.STEP_8.getCode())));
        entity.setProgramType8(HmiUtils.getString(map.get(PressureProgramEntityEnum.PROGRAM_TYPE_8.getCode())));
        entity.setProgramValue8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROGRAM_VALUE_8.getCode())));
        entity.setSpeed8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.SPEED_8.getCode())));
        entity.setAlarmDealType8(HmiUtils.getIntValue(map.get(PressureProgramEntityEnum.ALARM_DEAL_TYPE_8.getCode())));
        entity.setPosition8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_8.getCode())));
        entity.setProtectPress8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_PRESS_8.getCode())));
        entity.setPress8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_8.getCode())));
        entity.setProtectPosition8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_POSITION_8.getCode())));
        entity.setProtectTime8(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PROTECT_TIME_8.getCode())));

        entity.setErrandType1(HmiUtils.getString(map.get(PressureProgramEntityEnum.ERRAND_TYPE_1.getCode())));
        entity.setPositionMin1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MIN_1.getCode())));
        entity.setPositionMax1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MAX_1.getCode())));
        entity.setPressMin1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MIN_1.getCode())));
        entity.setPressMax1(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MAX_1.getCode())));

        entity.setErrandType2(HmiUtils.getString(map.get(PressureProgramEntityEnum.ERRAND_TYPE_2.getCode())));
        entity.setPositionMin2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MIN_2.getCode())));
        entity.setPositionMax2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MAX_2.getCode())));
        entity.setPressMin2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MIN_2.getCode())));
        entity.setPressMax2(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MAX_2.getCode())));

        entity.setErrandType3(HmiUtils.getString(map.get(PressureProgramEntityEnum.ERRAND_TYPE_3.getCode())));
        entity.setPositionMin3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MIN_3.getCode())));
        entity.setPositionMax3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MAX_3.getCode())));
        entity.setPressMin3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MIN_3.getCode())));
        entity.setPressMax3(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MAX_3.getCode())));

        entity.setErrandType4(HmiUtils.getString(map.get(PressureProgramEntityEnum.ERRAND_TYPE_4.getCode())));
        entity.setPositionMin4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MIN_4.getCode())));
        entity.setPositionMax4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MAX_4.getCode())));
        entity.setPressMin4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MIN_4.getCode())));
        entity.setPressMax4(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MAX_4.getCode())));

        entity.setErrandType5(HmiUtils.getString(map.get(PressureProgramEntityEnum.ERRAND_TYPE_5.getCode())));
        entity.setPositionMin5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MIN_5.getCode())));
        entity.setPositionMax5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.POSITION_MAX_5.getCode())));
        entity.setPressMin5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MIN_5.getCode())));
        entity.setPressMax5(HmiUtils.getBigDicimal(map.get(PressureProgramEntityEnum.PRESS_MAX_5.getCode())));
        return entity;

    }

    public void insert(PressureProgramEntity entity) {
        pressureProgramMapper.insert(entity);
    }

    public void update(PressureProgramEntity entity) {
        pressureProgramMapper.update(entity);
    }

    public void delete(PressureProgramEntity entity) {
        pressureProgramMapper.delete(entity);
    }



}
