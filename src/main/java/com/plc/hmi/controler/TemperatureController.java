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
import java.lang.reflect.Array;
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
    @Autowired
    TemperatureAlarmService temperatureAlarmService;

    @GetMapping("/getHisTemperatureNew")
    public String getHisTemperatureNew(@RequestParam(value = "start",required = true) String start,
                                       @RequestParam(value = "stop",required = true) String stop) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        System.out.println(" getHisTemperature startDate" + startDate + ", endDate"+endDate + ", plcName = "+ plcName);
        List<TemperatureEntity> TemperatureList =  temperatureService.getTemperatureWithParam(start, stop,null, null);
        String json = JSON.toJSONString(TemperatureList);
        return json;
    }

    @GetMapping("/getHisTemperatureNewWithoutParam")
    public String getHisTemperatureNew() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        System.out.println(" getHisTemperature startDate" + startDate + ", endDate"+endDate + ", plcName = "+ plcName);
        List<TemperatureEntity> TemperatureList =  temperatureService.getTemperatureWithParam(null, null,null, null);
        String json = JSON.toJSONString(TemperatureList);
        return json;
    }

    /**
     * 温度曲线取值
     * @param startDate
     * @param endDate
     * @param plcName
     * @param temperatureName
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @GetMapping("/getHisTemperature")
    public List<TemperaturePointEntity> getHisTemperature(@RequestParam(value = "startDate",required = true) String startDate,
                                   @RequestParam(value = "endDate",required = true) String endDate,
                                   @RequestParam(value = "plcName",required = false) String plcName,
                                   @RequestParam(value = "temperatureName",required = false) String temperatureName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        System.out.println(" getHisTemperature startDate" + startDate + ", endDate"+endDate + ", plcName = "+ plcName);
        List<TemperaturePointEntity> temperaturePointList =  temperatureService.getTemperaturePointWithParam(startDate, endDate, plcName,temperatureName, null);
//        String json = JSON.toJSONString(temperaturePointList);
//        temperaturePointList = new ArrayList<TemperaturePointEntity>();
        if(CollectionUtils.isEmpty(temperaturePointList)) {
            TemperaturePointEntity entity = new TemperaturePointEntity();
            entity.setTemperatureValue(new BigDecimal(60));
            entity.setTemperatureTime(new Date());
            TemperaturePointEntity entity1 = new TemperaturePointEntity();
            entity1.setTemperatureValue(new BigDecimal(60));
            entity1.setTemperatureTime(new Date());
            temperaturePointList.add(entity);
            temperaturePointList.add(entity);
        }
        return temperaturePointList;
    }

    /**
     * 当前温度getCurrentTemperature
     */
    @ResponseBody
    @GetMapping("/getCurrentTemperatureNew")
    public List<List<TemperatureEntity>> getCurrentTemperatureNew(){
//        System.out.println(">>>> getCurrentTemperature");
        List<List<TemperatureEntity>> temperatureList = plc4xTemperatureService.getTemperatureListNew();
        return temperatureList;
    }


    /**
     * 历史报警信息查询
     */
    @ResponseBody
    @GetMapping("/geTemperatureHisAlarmWithoutParam")
    public List<TemperatureAlarmEntity> geTemperatureHisAlarmWithoutParam(){
        return geTemperatureHisAlarm(null, null);
    }

    /**
     * 历史报警信息查询
     */
    @ResponseBody
    @GetMapping("/geTemperatureHisAlarm")
    public List<TemperatureAlarmEntity> geTemperatureHisAlarm(@RequestParam(value = "start",required = true) String start,
                                                         @RequestParam(value = "stop",required = true) String stop){
        if(null == start || StringUtils.isEmpty(start)) {
            start = HmiUtils.getYYYYMMDDString(new Date());
        }
        if(null == stop || StringUtils.isEmpty(stop)) {
            stop = HmiUtils.getYYYYMMDDString(new Date());
        }

        if(!StringUtils.isEmpty(start)) {
            start=start.replace("-","");
        }

        if(!StringUtils.isEmpty(stop)) {
            stop=stop.replace("-","");
        }

        List<TemperatureAlarmEntity> temperatureAlarmList = temperatureAlarmService.getTemperatureAlarmWithParam(start, stop);
        if(null == temperatureAlarmList) {
            temperatureAlarmList = new ArrayList<TemperatureAlarmEntity>();
        }
        return temperatureAlarmList;

    }

    /**
     * 当前温度getCurrentTemperature
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
//                if("Y".equalsIgnoreCase(temperatureEntity.getPlcConnectionStatus())) {
                    entity = temperatureEntity;
                    break;
//                }
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
