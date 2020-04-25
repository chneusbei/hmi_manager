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
public class HisAlarmDao {
    @Resource
    HisAlarmMapper hisAlarmMapper;

    public List<HisAlarmEntity> getHisAlarm() {
        return hisAlarmMapper.getHisAlarm();
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
