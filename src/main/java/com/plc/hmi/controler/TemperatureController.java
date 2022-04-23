package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.dal.entity.common.ResponseEntity;
import com.plc.hmi.service.*;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

@RestController
public class TemperatureController {

    @Resource
    TemperatureService temperatureService;
    @Resource
    Plc4xTemperatureService plc4xTemperatureService;
    @Resource
    Plc4xCurveDataService plc4xCurveDataService;
    @Resource
    PlcConfigService plcConfigService;
    @Resource
    TemperatureAlarmService temperatureAlarmService;
    @Resource
    PropertyService propertyService;

    private static Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    @GetMapping("/getHisTemperatureNew")
    public String getHisTemperatureNew(@RequestParam(value = "plcName",required = false) String plcName,
                                       @RequestParam(value = "temperatureName",required = false) String temperatureName,
                                       @RequestParam(value = "start",required = true) String start,
                                       @RequestParam(value = "stop",required = true) String stop,
                                       @RequestParam(value = "page",required = false) int page,
                                       @RequestParam(value = "pageSize",required = false) int pageSize) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info(" getHisTemperature startDate={}, endDate={}，plcName = {}", start, stop , plcName);
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        page = page <=0 ? 1: page;
        pageSize = pageSize > 0 ? pageSize : 10;
        String json = null;
        PageHelper.startPage(page,pageSize);

        if(StringUtils.isEmpty(temperatureName) || StringUtils.isBlank(temperatureName)) {
//            List<TemperatureEntity> temperatureList =  temperatureService.getTemperatureWithParam(start, stop,plcName, null, lineType, 20);
            PageInfo<TemperatureEntity> pageInfo = new PageInfo<TemperatureEntity>(temperatureService.getTemperatureWithParam(start, stop,plcName, null, lineType));
//            List<TemperatureEntity> temperatureList  = null;
//            if (null != pageInfo.getList().get(0)) {
//                System.out.println(pageInfo.getList().get(0).getId());
//            }
            json = JSON.toJSONString(pageInfo);
        } else {
//            List<TemperaturePointEntity> TemperaturePointList =  temperatureService.getHisTemperature(start,  stop,  plcName,  temperatureName, 20);
            PageInfo<TemperaturePointEntity> pageInfo = new PageInfo<TemperaturePointEntity>(temperatureService.getHisTemperature(start,  stop,  plcName,  temperatureName));
//            List<TemperaturePointEntity> TemperaturePointList = null;
            json = JSON.toJSONString(pageInfo);
        }

        return json;
    }

    @GetMapping("/getHisTemperatureNewWithoutParam")
    public String getHisTemperatureNew() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info(" getHisTemperature start.");
        PageHelper.startPage(1,10);
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
//        List<TemperatureEntity> TemperatureList =  temperatureService.getTemperatureWithParam(null, null,null, null, lineType, 20);
        PageInfo<TemperatureEntity> pageInfo = new PageInfo<TemperatureEntity>(temperatureService.getTemperatureWithParam(null, null,null, null, lineType));
//        List<TemperatureEntity> TemperatureList = null;
        String json = JSON.toJSONString(pageInfo);
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
    public List<List<TemperaturePointEntity>> getHisTemperature(@RequestParam(value = "startDate",required = false) String startDate,
                                   @RequestParam(value = "endDate",required = false) String endDate,
                                   @RequestParam(value = "plcName",required = false) String plcName,
                                   @RequestParam(value = "temperatureName",required = false) String temperatureName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        logger.info(" getHisTemperature startDate={}, endDate={}, plcName={}, temperatureName={}", startDate, endDate, plcName, temperatureName);
        List<List<TemperaturePointEntity>> temperaturePointList =  temperatureService.getHisTemperatureList(startDate,  endDate,  plcName,  temperatureName);
        return temperaturePointList;
    }

    /**
     * 当前温度getCurrentTemperature
     */
    @ResponseBody
    @GetMapping("/getCurrentTemperatureNew")
    public List<List<TemperatureEntity>> getCurrentTemperatureNew(){
        logger.info(" getCurrentTemperature start .");
//        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
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
     * 获取A线B线信息
     */
    @ResponseBody
    @GetMapping("/geLineType")
    public String geLineType(){
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        return lineType;
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
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        List<TemperatureAlarmEntity> temperatureAlarmList = temperatureAlarmService.getTemperatureAlarmWithParam(start, stop, lineType, 20);
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
        logger.info("getCurrentTemperature start.");
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
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));
        TemperatureEntity entity = new TemperatureEntity(lineType);
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

    @ResponseBody
    @GetMapping("/getTemperatureConfig")
    public List<PropertyEntity> getTemperatureConfig(){
        List<PropertyEntity> propertyEntityList = propertyService.getProperties();
        return  propertyEntityList;
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    @GetMapping("/updatePropertyConfig")
    public ResponseEntity updatePropertyConfig(@RequestParam(value = "id",required = true) String id,
                                                       @RequestParam(value = "propName",required = true) String propName,
                                                       @RequestParam(value = "propValue",required = true) String propValue){

        ResponseEntity responseEntity = new ResponseEntity();
        if(!StringUtils.isNotBlank(id) ) {
            responseEntity.setCode("9999");
            responseEntity.setMsg("更新失败，未找到对应的配置项信息。");
            return responseEntity;
        }
        if(!StringUtils.isNotBlank(propValue)) {
            responseEntity.setCode("9999");
            responseEntity.setMsg( "更新失败，属性值必须填写， 请填写完整。");
            return responseEntity;
        }

        if(ConfigConstants.TEMPERATURE_FETCH_FREQUENCY.equalsIgnoreCase(propName)) {
            BigDecimal value = new BigDecimal(propValue);
            if(value.compareTo(new BigDecimal(1)) <= 0) {
                responseEntity.setCode("9999");
                responseEntity.setMsg( "刷新时间设置必须大于或者等于1分钟");
                return responseEntity;
            }
        } else if(ConfigConstants.TEMPERATURE_LINE_TYPE.equalsIgnoreCase(propName)) {
            if (! HmiConstants.LINE_TYPE.LINE_TYPE_B.getCode().equalsIgnoreCase(propValue)) {
                propValue =  HmiConstants.LINE_TYPE.LINE_TYPE_A.getCode();
            }
        } else if(ConfigConstants.TEMPERATURE_DATA_CLEAR.equalsIgnoreCase(propName)) {
            BigDecimal value = new BigDecimal(propValue);
            if(value.compareTo(new BigDecimal(1)) <= 0) {
                responseEntity.setCode("9999");
                responseEntity.setMsg( "数据保留时间必须大于或者等于1个月");
                return responseEntity;
            }
        }

        PropertyEntity config = new PropertyEntity();
        config.setId(Long.valueOf(id));
        config.setPropName(propName);
        config.setPropValue(propValue);
        config.setUpdateTime(new Date());
        config.setUpdateBy("admin");
        int row = propertyService.update(config);
//        propertyService.getProperties();
//        String json = JSON.toJSONString("SUCESS");
//        String json = JSON.toJSONString("配置更新成功");
        responseEntity.setCode("0000");
        responseEntity.setMsg( "配置更新成功");
        return responseEntity;
    }
}
