package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.Plc4xServoOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@RequestMapping("/ServoOperation")
@Controller
public class Plc4xServoOperationControler {
    Plc4xServoOperationService plc4xServoOperationService;

    @ResponseBody
    @GetMapping("/getServoOperation")
    /**
     * 获取信息getDatas()
     */
    List<PlcEntity> getCurveDatas(){
    return  plc4xServoOperationService.getDatas();
    }
    @RequestMapping("/setDatas")
    String setDatas(List<PlcEntity> queryList){
        plc4xServoOperationService.setDatas(queryList);
        return "manualOperation";
    }
}
