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
import java.util.*;

@RestController
public class TemperatureController {

    @Autowired
    TemperatureService temperatureService;
    @Autowired
    Plc4xTemperatureService plc4xTemperatureService;
    @Autowired
    Plc4xCurveDataService plc4xCurveDataService;
    @Autowired
    PlcConfigService plcConfigService;



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
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("temperatureWarningValue1", temperatureWarningValue1);
        paraMap.put("temperatureWarningValue2", temperatureWarningValue2);
        plc4xCurveDataService.setDatas(HmiConstants.PLC_TAG_GROUP.TEMPERATURE_SETTING.getCode(), paraMap);
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

    @ResponseBody
    @GetMapping("/getPlcConfig")
    public List<PlcConfigEntity> getPlcConfig(){
        return  plcConfigService.getPlcList();
    }

    @RequestMapping("/insertPlcConfig")
    public String insertPlcConfig(@RequestParam(value = "plcName",required = true) String plcName,
                                  @RequestParam(value = "plcServerIp",required = true) String plcServerIp){
        if(!StringUtils.isNotBlank(plcName) || !StringUtils.isNotBlank(plcServerIp)) {
            return "新增失败，PLC名称和IP都必须填写， 请填写完整。";
        }

        PlcConfigEntity plcConfig = new PlcConfigEntity();
        plcConfig.setPlcName(plcName);
        plcConfig.setPlcServerIp(plcServerIp);
        plcConfig.setPlcServerRock("2");
        plcConfig.setPlcServerSlot("1");
        plcConfigService.insertPlcConfig(plcConfig);
        return "PLC信息添加完成";
    }

    @ResponseBody
    @RequestMapping("/updatePlcConfig")
    public String updatePlcConfig(@RequestParam(value = "id",required = true) String id,
                                  @RequestParam(value = "plcName",required = true) String plcName,
                                           @RequestParam(value = "plcServerIp",required = true) String plcServerIp){
        if(!StringUtils.isNotBlank(plcName) || !StringUtils.isNotBlank(plcServerIp)) {
            return "更新失败，PLC名称和IP都必须填写， 请填写完整。";
        }
        if(!StringUtils.isNotBlank(id) ) {
            return "更新失败，未找到对应的PLC信息。";
        }
        PlcConfigEntity plcConfig = new PlcConfigEntity();
        plcConfig.setId(Long.valueOf(id));
        plcConfig.setPlcName(plcName);
        plcConfig.setPlcServerIp(plcServerIp);
        plcConfig.setPlcServerRock("2");
        plcConfig.setPlcServerSlot("1");
        plcConfig.setUpdateTime(new Date());
        plcConfig.setUpdateBy("admin");
        plcConfigService.updatePlcConfig(plcConfig);
        return "PLC信息修改完成";
    }


    @ResponseBody
    @RequestMapping("/deletePlcConfig")
//    @PostMapping("/deletePlcConfig")
    public String deletePlcConfig(@RequestParam(value = "oid",required = true) String oid){
        if(!StringUtils.isNotBlank(oid) ) {
            return "删除失败，未找到对应的PLC信息。";
        }
        PlcConfigEntity plcConfig = new PlcConfigEntity();
        plcConfig.setId(Long.valueOf(oid));
        plcConfigService.deletePlcConfig(plcConfig);
        return "PLC信息删除成功";
    }


}
