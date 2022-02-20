package com.plc.hmi.controler.lsjcode;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.dal.entity.HisAlarmEntity;
import com.plc.hmi.service.HisAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
public class HisAlarmController {

    @Resource
    private HisAlarmService  hisAlarmService;
    @GetMapping("/getHisAlarm")
    public List<HisAlarmEntity> getHisAlarm(){
        List<HisAlarmEntity> alarm = hisAlarmService.getHisAlarm();
//        AlarmEntity alarmEntity=new AlarmEntity();
//        alarmEntity.setAlarmStatus("0");
//        alarmEntity.setAlarmInfo("dasfas");
//        alarmEntity.setAlarmType("W");
//        alarmEntity.setCreateTime(new Date());
//        AlarmEntity alarmEntity2=new AlarmEntity();
//        alarmEntity2.setAlarmStatus("1");
//        alarmEntity2.setAlarmInfo("jhfdhgf");
//        alarmEntity2.setAlarmType("I");
//        alarmEntity2.setCreateTime(new Date());
//        alarm.add(alarmEntity);
//        alarm.add(alarmEntity2);
        return alarm;
    }
}
