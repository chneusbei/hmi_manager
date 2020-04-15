package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.Plc4xServoLimitSetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import java.util.List;

@RequestMapping("/ServoLimitSet")
@Controller
public class Plc4xServoLimitSetControler {

    /**
     * 产品压装程序设置
     */
    Plc4xServoLimitSetService plc4xServoLimitSetService;

    @ResponseBody
    @GetMapping("/getDatas")
    List<PlcEntity> getDatas(){
       return  plc4xServoLimitSetService.getDatas();
    }
    @RequestMapping("/setDatas")
    String setDatas(List<PlcEntity> queryList){
    plc4xServoLimitSetService.setDatas(queryList);
    return "";
    }
}
