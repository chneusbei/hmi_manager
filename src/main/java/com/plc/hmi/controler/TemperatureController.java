package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.*;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveStatusService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TemperatureController {

    @Autowired
    TemperatureService temperatureService;
    @Autowired
    Plc4xTemperatureService plc4xTemperatureService;



    @GetMapping("/getHisTemperature")
    public String getHisTemperature(@RequestParam(value = "startDate",required = true) String startDate,
                                   @RequestParam(value = "endDate",required = true) String endDate,
                                   @RequestParam(value = "plcName",required = false) String plcName,
                                   @RequestParam(value = "temperatureName",required = false) String temperatureName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        System.out.println(" getHisTemperature startDate" + startDate + ", endDate"+endDate + ", plcName = "+ plcName);
        List<TemperaturePointEntity> TemperaturePointList =  temperatureService.getTemperaturePointWithParam(startDate, endDate, plcName,temperatureName, null);
        String json = JSON.toJSONString(TemperaturePointList);
        return json;
    }

    /**
     * 当前温度
     */
    @ResponseBody
    @GetMapping("/getCurrentTemperature")
    public List<TemperatureEntity> getCurrentTemperature(){
//        System.out.println(">>>> getCurrentTemperature");
        List<TemperatureEntity> temperatureList = plc4xTemperatureService.getTemperatureList();
        return temperatureList;

    }

    @RequestMapping("/updateTemperatureSetting")
    public String updateTemperatureSetting(@RequestParam(value = "temperatureWarningValue1",required = true) String temperatureWarningValue1,
                                           @RequestParam(value = "temperatureWarningValue2",required = true) String temperatureWarningValue2){
       if(!StringUtils.isNotBlank(temperatureWarningValue1) || !StringUtils.isNotBlank(temperatureWarningValue2)) {
           return "更新失败，存在未填写的阈值项， 请填写完整。";
       }
        return "阈值更新成功";
    }

    @ResponseBody
    @GetMapping("/getTemperatureSetting")
    public TemperatureEntity getSystemParameter(){
        TemperatureEntity entity = new TemperatureEntity();
        List<TemperatureEntity> temperatureList = plc4xTemperatureService.getTemperatureList();
        if(!CollectionUtils.isEmpty(temperatureList)) {
            for(TemperatureEntity temperatureEntity : temperatureList) {
                if("Y".equalsIgnoreCase(temperatureEntity.getPlcConnectionStatus())) {
                    entity = temperatureEntity;
                    break;
                }
            }
        }
        return entity;
    }


}
