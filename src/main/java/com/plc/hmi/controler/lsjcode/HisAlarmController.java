package com.plc.hmi.controler.lsjcode;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
@RestController
public class HisAlarmController {

    @Autowired
    private AlarmService alarmService;
    @GetMapping("/getHisAlarm")
    public List<AlarmEntity> getHisAlarm(){
        List<AlarmEntity> alarm = alarmService.getAlarm();
        AlarmEntity alarmEntity=new AlarmEntity();
        alarmEntity.setAlarmStatus("0");
        alarmEntity.setAlarmInfo("dasfas");
        alarmEntity.setAlarmType("W");
        alarmEntity.setCreateTime(new Date());
        AlarmEntity alarmEntity2=new AlarmEntity();
        alarmEntity2.setAlarmStatus("1");
        alarmEntity2.setAlarmInfo("jhfdhgf");
        alarmEntity2.setAlarmType("I");
        alarmEntity2.setCreateTime(new Date());
        alarm.add(alarmEntity);
        alarm.add(alarmEntity2);
        return alarm;
    }
    @RequestMapping("/queryHisAlarm")
    public  List<AlarmEntity> queryHisAlarm(String start,String stop){
        System.out.println(start);
        System.out.println(stop);
        return null;
    }
}
