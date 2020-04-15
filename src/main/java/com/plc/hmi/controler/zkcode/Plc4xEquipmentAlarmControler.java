package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.Plc4xEquipmentAlarmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/EquipmentAlarm")
@Controller
public class Plc4xEquipmentAlarmControler {
    /**
     * 获取实时报警信息
     */

    @Resource
    Plc4xEquipmentAlarmService plc4xEquipmentAlarmService;

    @ResponseBody
    @GetMapping("/getDatas")
    List<PlcEntity> getDatas(){
        return plc4xEquipmentAlarmService.getDatas();
    }
}
