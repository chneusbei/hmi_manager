package com.plc.hmi.service;

import com.plc.hmi.dal.dao.AlarmDao;
import com.plc.hmi.dal.entity.AlarmEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报警服务
 */
@Service
@Component
public class AlarmService extends AbstractBaseService{
    @Resource
    AlarmDao alarmDao;

    /**
     * 获取实时报警信息
     * @return
     */
    public List<AlarmEntity> getAlarm() {
        return alarmDao.getAlarm();
    }

    /**
     * 获取历史报警信息
     * @return
     */

    public void insert(AlarmEntity entity) {
        alarmDao.insert(entity);
    }

    public void update(AlarmEntity entity) {
        alarmDao.updateAlarm(entity);
    }

    public void delete(AlarmEntity entity) {
        alarmDao.delete(entity);
    }
}
