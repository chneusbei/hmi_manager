package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.dal.mapper.AlarmMapper;
import com.plc.hmi.enumeration.AlarmEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class AlarmDao extends AbstractHmiBaseDao {
    @Resource
    AlarmMapper alarmMapper;

    public List<AlarmEntity> getAlarm() {
        return this.getEntityList(alarmMapper.getAlarm());
    }


    @Override
    protected AlarmEntity getEntity(HashMap map) {
        AlarmEntity entity = new AlarmEntity();
        super.setEntityBase(entity, map);
        entity.setTriggerDb(HmiUtils.getIntValue(map.get(AlarmEntityEnum.TRIGGER_DB.getCode())));
        entity.setTriggerOffset(HmiUtils.getIntValue(map.get(AlarmEntityEnum.TRIGGER_OFFSET.getCode())));
        entity.setTriggerBit(HmiUtils.getIntValue(map.get(AlarmEntityEnum.TRIGGER_BIT.getCode())));
        entity.setAlarmType(HmiUtils.getString(map.get(AlarmEntityEnum.ALARM_TYPE.getCode())));
        entity.setAlarmGroup(HmiUtils.getIntValue(map.get(AlarmEntityEnum.ALARM_GROUP.getCode())));
        entity.setActive(HmiUtils.getString(map.get(AlarmEntityEnum.ACTIVE.getCode())));
        entity.setAlarmInfo(HmiUtils.getString(map.get(AlarmEntityEnum.ALARM_INFO.getCode())));
        entity.setAlarmHelp(HmiUtils.getString(map.get(AlarmEntityEnum.ALARM_HELP.getCode())));
        entity.setAlarmStatus(HmiUtils.getString(map.get(AlarmEntityEnum.ALARM_STATUS.getCode())));
        entity.setAlarmTime(HmiUtils.getDate(map.get(AlarmEntityEnum.ALARM_TIME.getCode())));
        return entity;
    }

    public void insert(AlarmEntity entity) {
        alarmMapper.insert(entity);
    }

    public void updateAlarm(AlarmEntity entity) {
        alarmMapper.updateAlarm(entity);
    }

    public void delete(AlarmEntity entity) {
        alarmMapper.delete(entity);
    }


}
