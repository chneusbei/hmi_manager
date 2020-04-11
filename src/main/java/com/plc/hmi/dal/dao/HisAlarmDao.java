package com.plc.hmi.dal.dao;


import com.plc.hmi.dal.entity.HisAlarmEntity;
import com.plc.hmi.dal.mapper.HisAlarmMapper;
import com.plc.hmi.enumeration.HisAlarmEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class HisAlarmDao extends AbstractHmiBaseDao {
    @Resource
    HisAlarmMapper hisAlarmMapper;

    public List<HisAlarmEntity> getHisAlarm() {
        return this.getEntityList(hisAlarmMapper.getHisAlarm());
    }

    @Override
    protected HisAlarmEntity getEntity(HashMap map) {
        HisAlarmEntity entity = new HisAlarmEntity();
        super.setEntityBase(entity, map);
        entity.setAlarmId(HmiUtils.getLongValue(map.get(HisAlarmEntityEnum.ALARM_ID.getCode())));
        entity.setAlarmStartTime(HmiUtils.getDate(map.get(HisAlarmEntityEnum.ALARM_START_TIME.getCode())));
        entity.setAlarmStopTime(HmiUtils.getDate(map.get(HisAlarmEntityEnum.ALARM_STOP_TIME.getCode())));
        entity.setAlarmStatus(HmiUtils.getString(map.get(HisAlarmEntityEnum.ALARM_STATUS.getCode())));
        entity.setAlarmCfmStatus(HmiUtils.getString(map.get(HisAlarmEntityEnum.ALARM_CFM_STATUS.getCode())));
        return entity;
    }

    public void insert(HisAlarmEntity entity) {
        hisAlarmMapper.insert(entity);
    }

    public void updateHisAlarm(HisAlarmEntity entity) {
        hisAlarmMapper.updateHisAlarm(entity);
    }

    public void delete(HisAlarmEntity entity) {
        hisAlarmMapper.delete(entity);
    }


}
