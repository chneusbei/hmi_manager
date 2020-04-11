package com.plc.hmi.service;


import com.plc.hmi.dal.dao.HisAlarmDao;
import com.plc.hmi.dal.entity.HisAlarmEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 历史报警服务
 */
@Service
@Component
public class HisAlarmService extends AbstractBaseService{
    @Resource
    HisAlarmDao hisAlarmDao;
    /**
     * 获取历史报警信息
     * @return
     */
    public List<HisAlarmEntity> getHisAlarm() {
        return hisAlarmDao.getHisAlarm();
    }

    public void insert(HisAlarmEntity entity) {
        hisAlarmDao.insert(entity);
    }

    public void update(HisAlarmEntity entity) {
        hisAlarmDao.updateHisAlarm(entity);
    }

    public void delete(HisAlarmEntity entity) {
        hisAlarmDao.delete(entity);
    }
}
