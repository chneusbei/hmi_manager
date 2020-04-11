package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlarmControler {


    @Autowired
    private AlarmService alarmService;
    @GetMapping("/getAlarm")
    public List<AlarmEntity> getAlarm(){
        List<AlarmEntity> alarm = alarmService.getAlarm();
        return alarm;
    }
  /*  public List<AlarmEntity> getAlarm(){
    return alarmService.getAlarm();
    }*/
}
