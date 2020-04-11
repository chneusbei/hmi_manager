package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.SystemParameterEntity;
import com.plc.hmi.service.SystemParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SystemParameterControler {

@Resource
private SystemParameterService systemParameterService;
    @ResponseBody
    @GetMapping("/getSystemParameter")
    public SystemParameterEntity getSystemParameter(){
    return  systemParameterService.getSystemParameters().get(0);
    }

    @RequestMapping("/updateSystemParameter")
    public String updateSystemParameter( SystemParameterEntity systemParameterEntity){
        systemParameterService.update(systemParameterEntity);
        return "parameterSetting";
    }
}
