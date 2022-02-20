package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.AlarmService;
import com.plc.hmi.service.plcService.Plc4xEquipmentAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class AlarmControler {


    @Resource
    private AlarmService alarmService;
    @Resource
    private Plc4xEquipmentAlarmService plc4xEquipmentAlarmService;

    @ResponseBody
    @GetMapping("/getAlarm")
    public List<AlarmEntity> getAlarm(){
//        List<PlcEntity> plcEntities= plc4xEquipmentAlarmService.getDatas();
        List<AlarmEntity> alarm = alarmService.getAlarm();
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

  /*  public List<AlarmEntity> getAlarm(){
    return alarmService.getAlarm();
    }*/
}
