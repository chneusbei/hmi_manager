package com.plc.hmi.controler;

import com.plc.hmi.dal.entity.*;
import com.plc.hmi.dal.entity.plc.ConnectionInfoEntity;
import com.plc.hmi.service.PlcConfigService;
import com.plc.hmi.service.PropertyService;
import com.plc.hmi.service.TemperatureAlarmService;
import com.plc.hmi.service.TemperatureService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AvgController {

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

    private static Logger logger = LoggerFactory.getLogger(AvgController.class);

    @ResponseBody
    @GetMapping("/getAvgConfig")
    public List<AvgEntity> getAvgConfig(){
//        return  plcConfigService.getPlcList();
        ArrayList avgList = new ArrayList<AvgEntity>();
        AvgEntity avg1 = new AvgEntity();
        AvgEntity avg2 = new AvgEntity();

        avg1.setId(101L);
        avg1.setAvgName("AVG一号");
        avg1.setAvgLong(new BigDecimal(5));
        avg1.setAvgWide(new BigDecimal(2));
        avg1.setNaviIp("192.168.0.0.1:8091");
        avg1.setVarIp("192.168.0.0.2:8092");
        avg1.setLineVelocity(new BigDecimal(1));
        avg1.setAngleVelocity(new BigDecimal(0.5));
        avg1.setIsActivated("正常");
        avg1.setCreateTime(new Date());
        avg1.setMapName("1-1地图");
        avg1.setWorkshopName("1-1车间");
        avg1.setFactoryName("一号工厂");

        avg2.setId(102L);
        avg2.setAvgName("AVG二号");
        avg2.setAvgLong(new BigDecimal(5));
        avg2.setAvgWide(new BigDecimal(2));
        avg2.setNaviIp("192.168.0.0.3:8093");
        avg2.setVarIp("192.168.0.0.3:8094");
        avg2.setLineVelocity(new BigDecimal(1));
        avg2.setAngleVelocity(new BigDecimal(0.5));
        avg2.setIsActivated("正常");
        avg2.setCreateTime(new Date());
        avg2.setMapName("2-1地图");
        avg2.setWorkshopName("2-1车间");
        avg2.setFactoryName("二号工厂");

        avgList.add(avg1);
        avgList.add(avg2);

        return avgList;
    }

    @RequestMapping("/insertAvgConfig")
    public String insertPlcConfig(@RequestParam(value = "avgName",required = true) String plcName,
                                  @RequestParam(value = "plcServerIp",required = true) String plcServerIp){
//        if(!StringUtils.isNotBlank(plcName) || !StringUtils.isNotBlank(plcServerIp)) {
//            return "新增失败，PLC名称和IP都必须填写， 请填写完整。";
//        }
//
        AvgEntity avgConfig = new AvgEntity();
        //        plcConfig.setPlcName(plcName);
//        plcConfig.setPlcServerIp(plcServerIp);
//        plcConfig.setPlcServerRock("2");
//        plcConfig.setPlcServerSlot("1");
//        plcConfigService.insertPlcConfig(plcConfig);
        return "AVG配置添加完成";
    }

    @RequestMapping("/insertAvgDbConfig")
    public String insertAvgDbConfig(@RequestParam(value = "avgName",required = true) String plcName,
                                  @RequestParam(value = "plcServerIp",required = true) String plcServerIp){
//        if(!StringUtils.isNotBlank(plcName) || !StringUtils.isNotBlank(plcServerIp)) {
//            return "新增失败，PLC名称和IP都必须填写， 请填写完整。";
//        }
//
        AvgEntity avgConfig = new AvgEntity();
        //        plcConfig.setPlcName(plcName);
//        plcConfig.setPlcServerIp(plcServerIp);
//        plcConfig.setPlcServerRock("2");
//        plcConfig.setPlcServerSlot("1");insertAvgDbConfig
//        plcConfigService.insertPlcConfig(plcConfig);
        return "AVG数据库配置添加完成";
    }

    @ResponseBody
    @GetMapping("/getAvgDbConfig")
    public List<AvgDbEntity> getAvgDbConfig(){
//        return  plcConfigService.getPlcList();
        ArrayList avgList = new ArrayList<AvgDbEntity>();
        AvgDbEntity avgDb1 = new AvgDbEntity();
        AvgDbEntity avgDb2 = new AvgDbEntity();

        avgDb1.setId(67L);
        avgDb1.setDbName("AVG数据库一");
        avgDb1.setDbType("MYSQL");
        avgDb1.setIp("192.168.0.0.1");
        avgDb1.setPort("1521");
        avgDb1.setUserName("ROOT");
        avgDb1.setPassword("*******");
        avgDb1.setCreateTime(new Date());

        avgDb2.setId(68L);
        avgDb2.setDbName("AVG备份数据库一");
        avgDb2.setDbType("MYSQL");
        avgDb2.setIp("192.168.0.0.2");
        avgDb2.setPort("1522");
        avgDb2.setUserName("ADMIN");
        avgDb2.setPassword("*******");
        avgDb2.setCreateTime(new Date());

        avgList.add(avgDb1);
        avgList.add(avgDb2);

        return avgList;
    }

    @ResponseBody
    @GetMapping("/getAvgInfoList")
    public List<AvgInfoEntity> getAvgInfoList(){
//        return  plcConfigService.getPlcList();
        ArrayList avgInfoList = new ArrayList<AvgInfoEntity>();
        AvgInfoEntity avgInfo1 = new AvgInfoEntity();
        AvgInfoEntity avgInfo2 = new AvgInfoEntity();

        avgInfo1.setAvgName("一号车");
        avgInfo1.setElectricity("关闭");
        avgInfo1.setChargeSwitch("关闭");
        avgInfo1.setChargeStatus("未充电");
        avgInfo1.setCell("100% 60.00V 0.50A");
        avgInfo1.setNavigationMode("0");
        avgInfo1.setLaserTaskStatus("0 无任务");
        avgInfo1.setLaserCurrentPointId("1001");
        avgInfo1.setLaserTargetId("0");
        avgInfo1.setLaserCoordinate("12.27, 12.27, 1.40");
        avgInfo1.setQrCodeId("12");
        avgInfo1.setQrCodeStatus("0 空闲");
        avgInfo1.setQrCodeCoordinate("0,0,0.00");
        avgInfo1.setHydraulicPressureAction("无动作");
        avgInfo1.setHydraulicPressurePosition("无限位");
        avgInfo1.setHydraulicPressureError("无");

        avgInfo2.setAvgName("二号车");
        avgInfo2.setElectricity("打开");
        avgInfo2.setChargeSwitch("打开");
        avgInfo2.setChargeStatus("充电中");
        avgInfo2.setCell("45% 60.00V 0.50A");
        avgInfo2.setNavigationMode("0");
        avgInfo2.setLaserTaskStatus("0 无任务");
        avgInfo2.setLaserCurrentPointId("256");
        avgInfo2.setLaserTargetId("183");
        avgInfo2.setLaserCoordinate("13.28, 13.28, 1.00");
        avgInfo2.setQrCodeId("245");
        avgInfo2.setQrCodeStatus("0 空闲");
        avgInfo2.setQrCodeCoordinate("0,0,0.00");
        avgInfo2.setHydraulicPressureAction("无动作");
        avgInfo2.setHydraulicPressurePosition("无限位");
        avgInfo2.setHydraulicPressureError("无");


        avgInfoList.add(avgInfo1);
        avgInfoList.add(avgInfo2);

        return avgInfoList;
    }

    @ResponseBody
    @GetMapping("/getMapInfo")
    public List<MapInfoEntity> getMapInfo(){
//        return  plcConfigService.getPlcList();
        ArrayList mapInfoList = new ArrayList<MapInfoEntity>();
        MapInfoEntity mapInfo1 = new MapInfoEntity();
        MapInfoEntity mapInfo2 = new MapInfoEntity();
//        MapInfoEntity mapInfo3 = new MapInfoEntity();

        mapInfo1.setPointCode("E1");
        mapInfo1.setPointType("type-avg1");
        mapInfo1.setStartPoint(new BigDecimal(51));
        mapInfo1.setEndPoint(new BigDecimal(126));
        mapInfo1.setLineVelocity(new BigDecimal(5));
        mapInfo1.setAngleVelocity(new BigDecimal(0.2));
        mapInfo1.setMapName("1-1地图");
        mapInfo1.setFactoryName("一号工厂");
        mapInfo1.setWorkshop("1-1车间");
        mapInfo1.setStatus("正常");
        mapInfo1.setAvgName("101号车");

        mapInfo2.setPointCode("M2");
        mapInfo2.setPointType("type-avg5");
        mapInfo2.setStartPoint(new BigDecimal(12));
        mapInfo2.setEndPoint(new BigDecimal(58));
        mapInfo2.setLineVelocity(new BigDecimal(3));
        mapInfo2.setAngleVelocity(new BigDecimal(0.1));
        mapInfo2.setMapName("2-1地图");
        mapInfo2.setFactoryName("二号工厂");
        mapInfo2.setWorkshop("2-1车间");
        mapInfo2.setStatus("正常");
        mapInfo2.setAvgName("201号车");

//        mapInfo3.setPointCode("A2");
//        mapInfo3.setPointType("type-avg3");
//        mapInfo3.setStartPoint(new BigDecimal(27));
//        mapInfo3.setEndPoint(new BigDecimal(33));
//        mapInfo3.setLineVelocity(new BigDecimal(2));
//        mapInfo3.setAngleVelocity(new BigDecimal(0.4));

        mapInfoList.add(mapInfo1);
        mapInfoList.add(mapInfo2);
//        mapInfoList.add(mapInfo3);

        return mapInfoList;
    }

    @ResponseBody
    @GetMapping("/getFactoryInfo")
    public List<FactoryInfoEntity> getFactoryInfo(){
//        return  plcConfigService.getPlcList();
        ArrayList factoryInfoList = new ArrayList<FactoryInfoEntity>();
        FactoryInfoEntity factoryInfo1 = new FactoryInfoEntity();
        FactoryInfoEntity factoryInfo2 = new FactoryInfoEntity();

        factoryInfo1.setId(1L);
        factoryInfo1.setFactoryName("一号工厂");
        factoryInfo1.setWorkshopName("1-1车间");
        factoryInfo1.setMapAccount(new BigDecimal(1));
        factoryInfo1.setMapName("1-1地图");

        factoryInfo2.setId(2L);
        factoryInfo2.setFactoryName("二号工厂");
        factoryInfo2.setWorkshopName("2-1车间");
        factoryInfo2.setMapAccount(new BigDecimal(1));
        factoryInfo2.setMapName("2-1地图");

        factoryInfoList.add(factoryInfo1);
        factoryInfoList.add(factoryInfo2);

        return factoryInfoList;
    }

    @RequestMapping("/addFactoryInfo")
    public String addFactoryInfo(@RequestParam(value = "factoryName",required = true) String factoryName,
                                  @RequestParam(value = "workshopName",required = true) String workshopName){
//        if(!StringUtils.isNotBlank(plcName) || !StringUtils.isNotBlank(plcServerIp)) {
//            return "新增失败，PLC名称和IP都必须填写， 请填写完整。";
//        }
//
//        AvgEntity avgConfig = new AvgEntity();
        //        plcConfig.setPlcName(plcName);
//        plcConfig.setPlcServerIp(plcServerIp);
//        plcConfig.setPlcServerRock("2");
//        plcConfig.setPlcServerSlot("1");
//        plcConfigService.insertPlcConfig(plcConfig);
        return "添加工厂信息完成";
    }


    @ResponseBody
    @GetMapping("/getConnectionInfo")
    public List<ConnectionInfoEntity> getConnectionInfo(){
//        return  plcConfigService.getPlcList();
        ArrayList connectionInfoList = new ArrayList<ConnectionInfoEntity>();
        ConnectionInfoEntity connectionInfo1 = new ConnectionInfoEntity();
        ConnectionInfoEntity connectionInfo2 = new ConnectionInfoEntity();

        connectionInfo1.setId(1L);
        connectionInfo1.setConnectionName("通讯连接一");
        connectionInfo1.setConnectionIp("192.168.1.101");
        connectionInfo1.setConnectionPort("8088");
        connectionInfo1.setUserName("admin");
        connectionInfo1.setPassword("******");

        connectionInfo2.setId(2L);
        connectionInfo2.setConnectionName("通讯连接二");
        connectionInfo2.setConnectionIp("192.168.1.102");
        connectionInfo2.setConnectionPort("9002");
        connectionInfo2.setUserName("user2");
        connectionInfo2.setPassword("******");

        connectionInfoList.add(connectionInfo1);
        connectionInfoList.add(connectionInfo2);

        return connectionInfoList;
    }


}
