package com.plc.hmi.service.plcService;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.dao.TemperatureAlarmDao;
import com.plc.hmi.dal.dao.TemperatureDao;
import com.plc.hmi.dal.entity.PlcConfigEntity;
import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import com.plc.hmi.dal.entity.TemperatureEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.enumeration.PlcEntityEnum;
import com.plc.hmi.service.PlcConfigService;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/***
 * 温度信息-数据
 */
@Service
@Component
public class Plc4xTemperatureService extends Plc4xBaseService{
    @Resource
    TemperatureDao temperatureDao;
    @Resource
    TemperatureAlarmDao  temperatureAlarmDao;
    @Autowired
    Plc4xConnectorService plc4xConnectorService;
    @Autowired
    PlcConfigService  plcConfigService;

    private final Log logger = LogFactory.getLog(Plc4xTemperatureService.class);
    public static Map<String, TemperatureEntity> TemperatureMap = new HashMap<String, TemperatureEntity>();
    public static Map<String, TemperatureEntity> TemperatureMapNew = new HashMap<String, TemperatureEntity>();
    public static List<List<TemperatureEntity>> TemperatureList = new ArrayList<>();


    /**
     *  获取温度信息
     *  频率是每秒10钟一次
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    private List<PlcEntity> getTemperatureDatas(String tagGruop, PlcConfigEntity plcConfigEntity) {
        super.initQuereyList(tagGruop);
        return super.getDataByBuilder(plcConfigEntity);
    }

    /**
     * 新获取实时温度信息
     * @param plcConfigEntity
     */
    public void getTemperatureNewFromPlc(PlcConfigEntity plcConfigEntity) {
        List<PlcEntity> temperaturePlcEntityList = this.getTemperatureDatas(HmiConstants.PLC_TAG_GROUP.TEMPERATURE_NEW_DATA.getCode(), plcConfigEntity);
        List<List<TemperatureEntity>> temperatureEntityList = this.getTemperatureList(temperaturePlcEntityList, plcConfigEntity);
        List<TemperatureAlarmEntity> temperatureAlarmList = this.getTemperatureAlarmList(temperatureEntityList);
        if(!CollectionUtils.isEmpty(temperatureEntityList)) {
//            TemperatureMap.put(plcConfigEntity.getPlcName(),temperatureEntityList);
            TemperatureList = temperatureEntityList;
            //入库温度信息
            this.temperatureList2DB(temperatureEntityList);
            //入库温度报警信息
            if(!CollectionUtils.isEmpty(temperatureAlarmList)) {
                for(TemperatureAlarmEntity entity : temperatureAlarmList) {
                    temperatureAlarmDao.insert(entity);
                }
            }
        }
    }


    /**
     * 获取实时温度信息
     * @param plcConfigEntity
     */
    public void getTemperatureFromPlc(PlcConfigEntity plcConfigEntity) {
       /*
        List<PlcEntity> temperaturePlcEntityList = this.getTemperatureDatas(HmiConstants.PLC_TAG_GROUP.TEMPERATURE_DATA.getCode(), plcConfigEntity);
        TemperatureEntity temperatureEntity = this.getTemperature(temperaturePlcEntityList, plcConfigEntity);
        if(null != temperatureEntity) {
            TemperatureMap.put(plcConfigEntity.getPlcName(),temperatureEntity);
            this.temperature2DB(plcConfigEntity, temperatureEntity);
        }
        */
    }

    /***
     * 获取PLC上曲线状态和基本信息
     * 根据PLC获取信息，对应拆分 PLC1-PLC5 上的有线和无线信息
     * @param entityList
     * @return
     */
    public List<List<TemperatureEntity>> getTemperatureList(List<PlcEntity> entityList, PlcConfigEntity plcConfigEntity) {
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }

        Long batchId = System.currentTimeMillis();
        List<List<TemperatureEntity>> temperatureList = new ArrayList<>();
        List<TemperatureEntity> temperatureEntityList = new ArrayList<TemperatureEntity>();
        List<TemperatureEntity> temperatureEntityListWireless = new ArrayList<TemperatureEntity>();
        Map<String, BigDecimal> temperatureCommonPropertyMap = new HashMap<String, BigDecimal>();
        TemperatureEntity temperatureEntityP1 = new TemperatureEntity("p1", batchId, false);
        TemperatureEntity temperatureEntityP2 = new TemperatureEntity("p2",batchId, false);
        TemperatureEntity temperatureEntityP3 = new TemperatureEntity("p3",batchId, false);
        TemperatureEntity temperatureEntityP4 = new TemperatureEntity("p4",batchId, false);
        TemperatureEntity temperatureEntityP5 = new TemperatureEntity("p5",batchId, false);
        TemperatureEntity wirelessTemperatureEntityP1 = new TemperatureEntity("p1",batchId, true);
        TemperatureEntity wirelessTemperatureEntityP2 = new TemperatureEntity("p2",batchId, true);
        TemperatureEntity wirelessTemperatureEntityP3 = new TemperatureEntity("p3",batchId, true);
        TemperatureEntity wirelessTemperatureEntityP4 = new TemperatureEntity("p4",batchId, true);
        TemperatureEntity wirelessTemperatureEntityP5 = new TemperatureEntity("p5",batchId, true);

        temperatureEntityList.add(temperatureEntityP1);
        temperatureEntityList.add(temperatureEntityP2);
        temperatureEntityList.add(temperatureEntityP3);
        temperatureEntityList.add(temperatureEntityP4);
        temperatureEntityList.add(temperatureEntityP5);
        temperatureEntityListWireless.add(wirelessTemperatureEntityP1);
        temperatureEntityListWireless.add(wirelessTemperatureEntityP2);
        temperatureEntityListWireless.add(wirelessTemperatureEntityP3);
        temperatureEntityListWireless.add(wirelessTemperatureEntityP4);
        temperatureEntityListWireless.add(wirelessTemperatureEntityP5);
        temperatureList.add(temperatureEntityList);
        temperatureList.add(temperatureEntityListWireless);



        for (PlcEntity plcEntity : entityList) {
            if (null == plcEntity) {
                continue;
            }

            if (PlcEntityEnum.temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1低速轴偏心铜套温度检测1
                temperatureEntityP1.setLowSpeedAxisEccentricCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1低速轴偏心铜套温度检测2
                temperatureEntityP1.setLowSpeedAxisEccentricCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1低速轴偏心铜套温度检测3
                temperatureEntityP1.setLowSpeedAxisEccentricCopperSleeveTemperature3(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1LowSpeedAxisEccentricCopperSleeveTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1速轴偏心铜套温度检测4
                temperatureEntityP1.setLowSpeedAxisEccentricCopperSleeveTemperature4(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1HighSpeedAxisEccentricCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1高速轴偏心铜套温度检测1
                temperatureEntityP1.setHighSpeedAxisEccentricCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1HighSpeedAxisEccentricCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1高速轴偏心铜套温度检测2
                temperatureEntityP1.setHighSpeedAxisEccentricCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1FlywheelSupportBigAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1飞轮支撑大轴承温度检测1
                temperatureEntityP1.setFlywheelSupportBigAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1FlywheelSupportBigAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //飞压机1轮支撑大轴承温度检测2
                temperatureEntityP1.setFlywheelSupportBigAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2HighSpeedAxisRollingBearingTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2高速轴滚动轴承温度检测1
                temperatureEntityP2.setHighSpeedAxisRollingBearingTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2HighSpeedAxisRollingBearingTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2高速轴滚动轴承温度检测2
                temperatureEntityP2.setHighSpeedAxisRollingBearingTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2FlywheelSupportBigAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2飞轮支撑大轴承温度检测1
                temperatureEntityP2.setFlywheelSupportBigAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2FlywheelSupportBigAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2飞轮支撑大轴承温度检测2
                temperatureEntityP2.setFlywheelSupportBigAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3HighSpeedAxisRollingBearingTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3高速轴滚动轴承温度检测1
                temperatureEntityP3.setHighSpeedAxisRollingBearingTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3HighSpeedAxisRollingBearingTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3高速轴滚动轴承温度检测2
                temperatureEntityP3.setHighSpeedAxisRollingBearingTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3FlywheelSupportBigAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3飞轮支撑大轴承温度检测1
                temperatureEntityP3.setFlywheelSupportBigAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3FlywheelSupportBigAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3飞轮支撑大轴承温度检测2
                temperatureEntityP3.setFlywheelSupportBigAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4HighSpeedAxisRollingBearingTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4高速轴滚动轴承温度检测1
                temperatureEntityP4.setHighSpeedAxisRollingBearingTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4HighSpeedAxisRollingBearingTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4高速轴滚动轴承温度检测2
                temperatureEntityP4.setHighSpeedAxisRollingBearingTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4FlywheelSupportBigAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4飞轮支撑大轴承温度检测1
                temperatureEntityP4.setFlywheelSupportBigAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4FlywheelSupportBigAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4飞轮支撑大轴承温度检测2
                temperatureEntityP4.setFlywheelSupportBigAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5HighSpeedAxisRollingBearingTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5高速轴滚动轴承温度检测1
                temperatureEntityP5.setHighSpeedAxisRollingBearingTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5HighSpeedAxisRollingBearingTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5高速轴滚动轴承温度检测2
                temperatureEntityP5.setHighSpeedAxisRollingBearingTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5FlywheelSupportBigAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5飞轮支撑大轴承温度检测1
                temperatureEntityP5.setFlywheelSupportBigAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5FlywheelSupportBigAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5飞轮支撑大轴承温度检测2
                temperatureEntityP5.setFlywheelSupportBigAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_temperatureWarningValue1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //温度警戒值1
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_temperatureWarningValue1.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_temperatureWarningValue2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //温度警戒值2
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_temperatureWarningValue2.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature0.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测0
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature0.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测1
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature1.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测2
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature2.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测3
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature3.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测4
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature4.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature5.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测5
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature5.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature6.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测6
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature6.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature7.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测7
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature7.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature8.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测8
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature8.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature9.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测9
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature9.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature10.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测10
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature10.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature11.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测11
                temperatureCommonPropertyMap.put(PlcEntityEnum.temperature_data_backupTemperature11.getCode(), HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessLeftFrontTripodEccentricBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:左前三脚架偏心大铜套
                wirelessTemperatureEntityP1.setLeftFrontTripodEccentricBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessLeftRearTripodEccentricBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:左后三脚架偏心大铜套
                wirelessTemperatureEntityP1.setLeftRearTripodEccentricBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessRightFrontTripodEccentricBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:右前三脚架偏心大铜套
                wirelessTemperatureEntityP1.setRightFrontTripodEccentricBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessRightRearTripodEccentricBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:右后三脚架偏心大铜套
                wirelessTemperatureEntityP1.setRightRearTripodEccentricBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessLeftFrontTripodBottomCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:左前三脚架下端铜套
                wirelessTemperatureEntityP1.setLeftFrontTripodBottomCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessLeftRearTripodBottomCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:左后三脚架下端铜套
                wirelessTemperatureEntityP1.setLeftRearTripodBottomCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessRightFrontTripodBottomCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:右前三脚架下端铜套
                wirelessTemperatureEntityP1.setRightFrontTripodBottomCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p1WirelessRightRearTripodBottomCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机1无线温度:右后三脚架下端铜套
                wirelessTemperatureEntityP1.setRightRearTripodBottomCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessLeftFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:左前连杆大铜套
                wirelessTemperatureEntityP2.setLeftFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessLeftRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:左后连杆大铜套
                wirelessTemperatureEntityP2.setLeftRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessRightFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:右前连杆大铜套
                wirelessTemperatureEntityP2.setRightFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessRightRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:右后连杆大铜套
                wirelessTemperatureEntityP2.setRightRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessLeftFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:左前低速轴铜套
                wirelessTemperatureEntityP2.setLeftFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessLeftRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:左后低速轴铜套
                wirelessTemperatureEntityP2.setLeftRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessRightFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:右前低速轴铜套
                wirelessTemperatureEntityP2.setRightFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p2WirelessRightRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机2无线温度:右后低速轴铜套
                wirelessTemperatureEntityP2.setRightRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessLeftFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:左前连杆大铜套
                wirelessTemperatureEntityP3.setLeftFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessLeftRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:左后连杆大铜套
                wirelessTemperatureEntityP3.setLeftRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessRightFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:右前连杆大铜套
                wirelessTemperatureEntityP3.setRightFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessRightRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:右后连杆大铜套
                wirelessTemperatureEntityP3.setRightRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessLeftFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:左前低速轴铜套
                wirelessTemperatureEntityP3.setLeftFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessLeftRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:左后低速轴铜套
                wirelessTemperatureEntityP3.setLeftRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessRightFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:右前低速轴铜套
                wirelessTemperatureEntityP3.setRightFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p3WirelessRightRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机3无线温度:右后低速轴铜套
                wirelessTemperatureEntityP3.setRightRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            }else if (PlcEntityEnum.temperature_data_p4WirelessLeftFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:左前连杆大铜套
                wirelessTemperatureEntityP4.setLeftFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessLeftRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:左后连杆大铜套
                wirelessTemperatureEntityP4.setLeftRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessRightFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:右前连杆大铜套
                wirelessTemperatureEntityP4.setRightFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessRightRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:右后连杆大铜套
                wirelessTemperatureEntityP4.setRightRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessLeftFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:左前低速轴铜套
                wirelessTemperatureEntityP4.setLeftFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessLeftRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:左后低速轴铜套
                wirelessTemperatureEntityP4.setLeftRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessRightFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:右前低速轴铜套
                wirelessTemperatureEntityP4.setRightFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p4WirelessRightRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机4无线温度:右后低速轴铜套
                wirelessTemperatureEntityP4.setRightRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessLeftFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:左前连杆大铜套
                wirelessTemperatureEntityP5.setLeftFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessLeftRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:左后连杆大铜套
                wirelessTemperatureEntityP5.setLeftRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessRightFrontConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:右前连杆大铜套
                wirelessTemperatureEntityP5.setRightFrontConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessRightRearConnectingRodBigCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:右后连杆大铜套
                wirelessTemperatureEntityP5.setRightRearConnectingRodBigCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessLeftFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:左前低速轴铜套
                wirelessTemperatureEntityP5.setLeftFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessLeftRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:左后低速轴铜套
                wirelessTemperatureEntityP5.setLeftRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessRightFrontLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:右前低速轴铜套
                wirelessTemperatureEntityP5.setRightFrontLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_p5WirelessRightRearLowSpeedAxisCopperSleeve.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //压机5无线温度:右后低速轴铜套
                wirelessTemperatureEntityP5.setRightRearLowSpeedAxisCopperSleeve(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            }


        }
        //设置通用属性
        this.setTemperatureEntityCommonProperties(temperatureEntityList, temperatureCommonPropertyMap);

        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_1.getCode(), temperatureEntityP1);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_2.getCode(), temperatureEntityP2);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_3.getCode(), temperatureEntityP3);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_4.getCode(), temperatureEntityP4);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_5.getCode(), temperatureEntityP5);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_WIRELESS_1.getCode(), wirelessTemperatureEntityP1);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_WIRELESS_2.getCode(), wirelessTemperatureEntityP2);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_WIRELESS_3.getCode(), wirelessTemperatureEntityP3);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_WIRELESS_4.getCode(), wirelessTemperatureEntityP4);
        TemperatureMapNew.put(HmiConstants.PLC_NAME_GROUP.PLC_WIRELESS_5.getCode(), wirelessTemperatureEntityP5);

        return temperatureList;
    }

    /**
     * 设置温度对象通用信息
     * @param temperatureEntityList
     * @param temperatureCommonPropertyMap
     */
    private void setTemperatureEntityCommonProperties(List<TemperatureEntity> temperatureEntityList, Map<String, BigDecimal> temperatureCommonPropertyMap) {
        for(String key : temperatureCommonPropertyMap.keySet()) {
            for (TemperatureEntity temperatureEntity : temperatureEntityList) {
                if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_temperatureWarningValue1.getCode())) {
                    temperatureEntity.setTemperatureWarningValue1(temperatureCommonPropertyMap.get(key));
                    setTemperatureStatus(temperatureEntity);
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_temperatureWarningValue2.getCode())) {
                    temperatureEntity.setTemperatureWarningValue2(temperatureCommonPropertyMap.get(key));
                    setTemperatureStatus(temperatureEntity);
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature0.getCode())) {
                    temperatureEntity.setBackupTemperature0(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature1.getCode())) {
                    temperatureEntity.setBackupTemperature1(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature2.getCode())) {
                    temperatureEntity.setBackupTemperature2(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature3.getCode())) {
                    temperatureEntity.setBackupTemperature3(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature4.getCode())) {
                    temperatureEntity.setBackupTemperature4(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature5.getCode())) {
                    temperatureEntity.setBackupTemperature5(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature6.getCode())) {
                    temperatureEntity.setBackupTemperature6(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature7.getCode())) {
                    temperatureEntity.setBackupTemperature7(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature8.getCode())) {
                    temperatureEntity.setBackupTemperature8(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature9.getCode())) {
                    temperatureEntity.setBackupTemperature9(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature10.getCode())) {
                    temperatureEntity.setBackupTemperature10(temperatureCommonPropertyMap.get(key));
                } else if(key.equalsIgnoreCase(PlcEntityEnum.temperature_data_backupTemperature11.getCode())) {
                    temperatureEntity.setBackupTemperature11(temperatureCommonPropertyMap.get(key));
                }
            }
        }
    }


    /***
     * 获取PLC上曲线状态和基本信息
     * @param entityList
     * @return
     */
    /*
    public TemperatureEntity getTemperature(List<PlcEntity> entityList, PlcConfigEntity plcConfigEntity) {
        TemperatureEntity temperatureEntity = null;

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        for (PlcEntity plcEntity : entityList) {
            if (null == plcEntity) {
                continue;
            }
            temperatureEntity = new TemperatureEntity();
            if (PlcEntityEnum.temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //低速轴偏心铜套温度检测1
                temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //低速轴偏心铜套温度检测2
                temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //低速轴偏心铜套温度检测3
                temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature3(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_lowSpeedAxisEccentricCopperSleeveTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //速轴偏心铜套温度检测4
                temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature4(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_highSpeedAxisEccentricCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //高速轴偏心铜套温度检测1
                temperatureEntity.setHighSpeedAxisEccentricCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_highSpeedAxisEccentricCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //高速轴偏心铜套温度检测2
                temperatureEntity.setHighSpeedAxisEccentricCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_flywheelSupportBigAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //飞轮支撑大轴承温度检测1
                temperatureEntity.setFlywheelSupportBigAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_flywheelSupportBigAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //飞轮支撑大轴承温度检测2
                temperatureEntity.setFlywheelSupportBigAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测1
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测2
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测3
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature3(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测4
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature4(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature5.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测5
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature5(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature6.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测6
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature6(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature7.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测7
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature7(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_drawbarBothEndsCopperSleeveTemperature8.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //拉杆两端铜套温度检测8
                temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature8(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_smallBeltWheelSupportAxisTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //小皮带轮支撑轴承温度检测1
                temperatureEntity.setSmallBeltWheelSupportAxisTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_smallBeltWheelSupportAxisTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //小皮带轮支撑轴承温度检测2
                temperatureEntity.setSmallBeltWheelSupportAxisTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodBottomCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架下端铜套温度检测1
                temperatureEntity.setTripodBottomCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodBottomCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架下端铜套温度检测2
                temperatureEntity.setTripodBottomCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodBottomCopperSleeveTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架下端铜套温度检测3
                temperatureEntity.setTripodBottomCopperSleeveTemperature3(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodBottomCopperSleeveTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架下端铜套温度检测4
                temperatureEntity.setTripodBottomCopperSleeveTemperature4(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodEccentricBigCopperSleeveTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架偏心大铜套温度检测1
                temperatureEntity.setTripodEccentricBigCopperSleeveTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodEccentricBigCopperSleeveTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架偏心大铜套温度检测2
                temperatureEntity.setTripodEccentricBigCopperSleeveTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodEccentricBigCopperSleeveTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架偏心大铜套温度检测3
                temperatureEntity.setTripodEccentricBigCopperSleeveTemperature3(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_tripodEccentricBigCopperSleeveTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //三脚架偏心大铜套温度检测4
                temperatureEntity.setTripodEccentricBigCopperSleeveTemperature4(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature0.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测0
                temperatureEntity.setBackupTemperature0(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测1
                temperatureEntity.setBackupTemperature1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测2
                temperatureEntity.setBackupTemperature2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature3.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测3
                temperatureEntity.setBackupTemperature3(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature4.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测4
                temperatureEntity.setBackupTemperature4(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature5.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测5
                temperatureEntity.setBackupTemperature5(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature6.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测6
                temperatureEntity.setBackupTemperature6(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature7.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测7
                temperatureEntity.setBackupTemperature7(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature8.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测8
                temperatureEntity.setBackupTemperature8(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature9.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测9
                temperatureEntity.setBackupTemperature9(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature10.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测10
                temperatureEntity.setBackupTemperature10(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_backupTemperature11.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //备用温度检测11
                temperatureEntity.setBackupTemperature11(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_temperatureWarningValue1.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //温度警戒值1
                temperatureEntity.setTemperatureWarningValue1(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            } else if (PlcEntityEnum.temperature_data_temperatureWarningValue2.getCode().equalsIgnoreCase(plcEntity.getName())) {
                //温度警戒值2
                temperatureEntity.setTemperatureWarningValue2(HmiUtils.getBigDicimal(plcEntity.getValueOjb()));
            }
            temperatureEntity.setCreateBy("SYS");
            temperatureEntity.setUpdateBy("SYS");
            temperatureEntity.setPlcIp(plcConfigEntity.getPlcServerIp());
            temperatureEntity.setPlcName(plcConfigEntity.getPlcName());
            temperatureEntity.setHandleDate(HmiUtils.getYYYYMMDDString(temperatureEntity.getCreateTime()));
            temperatureEntity.setStatus(getTemperatureStatus(temperatureEntity));
        }
        return temperatureEntity;
    }
*/
    /**
     * 数据入库
     * @param temperatureEntity
     */
    private  void temperature2DB(TemperatureEntity temperatureEntity) {
        if (null == temperatureEntity) {
            logger.info("Plc4xTemperatureDataService.curve2DB: temperatureEntity is null");
            return;
        }

        temperatureDao.insert(temperatureEntity);
    }

    /**
     * 数据入库
     * @param temperatureEntityList
     */
    private  void temperatureList2DB(List<List<TemperatureEntity>> temperatureEntityList) {
        if (null == temperatureEntityList) {
//            System.out.println("Plc4xTemperatureDataService.temperatureList2DB: temperatureEntityList is null");
            logger.info("Plc4xTemperatureDataService.temperatureList2DB: temperatureEntityList is null");
            return;
        }

        for (List<TemperatureEntity> list : temperatureEntityList) {
            if (null == temperatureEntityList) {
                continue;
            }
            for (TemperatureEntity temperatureEntity : list) {
                temperatureDao.insert(temperatureEntity);
            }
        }

    }

    private void setTemperatureStatus(TemperatureEntity temperatureEntity) {
        BigDecimal temperatureWarningValue1 = temperatureEntity.getTemperatureWarningValue1();
        BigDecimal temperatureWarningValue2 = temperatureEntity.getTemperatureWarningValue2();
        if(null == temperatureWarningValue1 || null == temperatureWarningValue2) {
            return;
        }

        if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature1(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature2(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature3(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature4(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature1(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature2(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getFlywheelSupportBigAxisTemperature1(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getFlywheelSupportBigAxisTemperature2(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisRollingBearingTemperature1(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisRollingBearingTemperature2(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
                ||HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)
        ) {
            temperatureEntity.setStatus(HmiConstants.TEMPERATURE_STATUS_WARNING);
        }
    }


    /**
     *
     * 获取报警信息对象
     * @param temperatureEntityList
     * @return
     */
    private List<TemperatureAlarmEntity> getTemperatureAlarmList(List<List<TemperatureEntity>> temperatureEntityList) {
        List<TemperatureAlarmEntity> temperatureAlarmList = new ArrayList<TemperatureAlarmEntity>();
        for(List<TemperatureEntity> list : temperatureEntityList) {
            for (TemperatureEntity temperatureEntity : list ) {
                if (temperatureEntity.getStatus().equalsIgnoreCase(HmiConstants.TEMPERATURE_STATUS_NORMAL)) {
                    continue;
                }
                BigDecimal temperatureWarningValue1 = temperatureEntity.getTemperatureWarningValue1();
                BigDecimal temperatureWarningValue2 = temperatureEntity.getTemperatureWarningValue2();
                if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature1(), temperatureWarningValue1, temperatureWarningValue2)) {
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"低速轴偏心铜套温度检测1", temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature1(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature2(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"低速轴偏心铜套温度检测2", temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature2(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature3(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"低速轴偏心铜套温度检测3", temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature3(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature4(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"低速轴偏心铜套温度检测4", temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature4(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature1(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"高速轴偏心铜套温度检测1", temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature1(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature2(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"高速轴偏心铜套温度检测2", temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature2(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getFlywheelSupportBigAxisTemperature1(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"飞轮支撑大轴承温度检测1", temperatureEntity.getFlywheelSupportBigAxisTemperature1(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getFlywheelSupportBigAxisTemperature2(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"飞轮支撑大轴承温度检测2", temperatureEntity.getFlywheelSupportBigAxisTemperature2(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisRollingBearingTemperature1(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"高速轴滚动轴承温度检测1", temperatureEntity.getHighSpeedAxisRollingBearingTemperature1(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getHighSpeedAxisRollingBearingTemperature2(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"高速轴滚动轴承温度检测2", temperatureEntity.getHighSpeedAxisRollingBearingTemperature2(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左前三脚架偏心大铜套温度检测", temperatureEntity.getLeftFrontTripodEccentricBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左后三脚架偏心大铜套温度检测", temperatureEntity.getLeftRearTripodEccentricBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右前三脚架偏心大铜套温度检测", temperatureEntity.getRightFrontTripodEccentricBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearTripodEccentricBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右后三脚架偏心大铜套温度检测", temperatureEntity.getRightRearTripodEccentricBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左前三脚架下端铜套温度检测", temperatureEntity.getLeftFrontTripodBottomCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左后三脚架下端铜套温度检测", temperatureEntity.getLeftRearTripodBottomCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右前三脚架下端铜套温度检测", temperatureEntity.getRightFrontTripodBottomCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearTripodBottomCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右后三脚架下端铜套温度检测", temperatureEntity.getRightRearTripodBottomCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左前连杆大铜套", temperatureEntity.getLeftFrontConnectingRodBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左后连杆大铜套", temperatureEntity.getLeftRearConnectingRodBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右前连杆大铜套", temperatureEntity.getRightFrontConnectingRodBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearConnectingRodBigCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右后连杆大铜套", temperatureEntity.getRightRearConnectingRodBigCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftFrontLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左前低速轴铜套", temperatureEntity.getLeftFrontLowSpeedAxisCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getLeftRearLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"左后低速轴铜套", temperatureEntity.getLeftRearLowSpeedAxisCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightFrontLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右前低速轴铜套", temperatureEntity.getRightFrontLowSpeedAxisCopperSleeve(), temperatureWarningValue1));
                } else if(HmiUtils.isBigThanOrEquals(temperatureEntity.getRightRearLowSpeedAxisCopperSleeve(), temperatureWarningValue1, temperatureWarningValue2)){
                    temperatureAlarmList.add(new TemperatureAlarmEntity(temperatureEntity.getBatchId(),temperatureEntity.getHandleDate(),"右后低速轴铜套", temperatureEntity.getRightRearLowSpeedAxisCopperSleeve(), temperatureWarningValue1));
                }
            }
        }
        return temperatureAlarmList;
    }

    /**
     * 获取内存中的PLC温度信息
     * @return
     */
    public List<List<TemperatureEntity>> getTemperatureListNew() {
//        return TemperatureList;

        List<TemperatureEntity> temperatureEntityList1 = new ArrayList<TemperatureEntity>();
        List<TemperatureEntity> temperatureEntityList2 = new ArrayList<TemperatureEntity>();
        for(int i=0;i<10;i++) {
            TemperatureEntity temperatureEntity = new TemperatureEntity();
            temperatureEntity.setPlcIp("127.0.0." + i);
            if(i <= 4) {
                temperatureEntity.setPlcName("A-" + String.valueOf(i + 1));
                temperatureEntity.setWireless(false);
            } else {
                temperatureEntity.setPlcName("A-" + String.valueOf(i + 1));
                temperatureEntity.setWireless(true);
            }
//            temperatureEntity.setPlcConnectionStatus("Y");
            temperatureEntity.setHandleDate("2021-01-01 10:12:23");
            temperatureEntity.setStatus("OK");
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature1(new BigDecimal(70.01).add(new BigDecimal(i)));
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature2(new BigDecimal(10.02).add(new BigDecimal(i)));
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature3(new BigDecimal(10.03).add(new BigDecimal(i)));
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature4(new BigDecimal(10.04).add(new BigDecimal(i)));
            temperatureEntity.setHighSpeedAxisEccentricCopperSleeveTemperature1(new BigDecimal(10.05).add(new BigDecimal(i)));
            temperatureEntity.setHighSpeedAxisEccentricCopperSleeveTemperature2(new BigDecimal(10.06).add(new BigDecimal(i)));
            temperatureEntity.setFlywheelSupportBigAxisTemperature1(new BigDecimal(10.07).add(new BigDecimal(i)));
            temperatureEntity.setFlywheelSupportBigAxisTemperature2(new BigDecimal(10.08).add(new BigDecimal(i)));
            temperatureEntity.setHighSpeedAxisRollingBearingTemperature1(new BigDecimal(10.09).add(new BigDecimal(i)));
            temperatureEntity.setHighSpeedAxisRollingBearingTemperature2(new BigDecimal(10.10).add(new BigDecimal(i)));
            temperatureEntity.setLeftFrontTripodEccentricBigCopperSleeve(new BigDecimal(10.11).add(new BigDecimal(i)));
            temperatureEntity.setLeftRearTripodEccentricBigCopperSleeve(new BigDecimal(10.12).add(new BigDecimal(i)));
            temperatureEntity.setRightFrontTripodEccentricBigCopperSleeve(new BigDecimal(10.13).add(new BigDecimal(i)));
            temperatureEntity.setRightRearTripodEccentricBigCopperSleeve(new BigDecimal(10.14).add(new BigDecimal(i)));
            temperatureEntity.setLeftFrontTripodBottomCopperSleeve(new BigDecimal(10.15).add(new BigDecimal(i)));
            temperatureEntity.setLeftRearTripodBottomCopperSleeve(new BigDecimal(10.16).add(new BigDecimal(i)));
            temperatureEntity.setRightFrontTripodBottomCopperSleeve(new BigDecimal(10.17).add(new BigDecimal(i)));
            temperatureEntity.setRightRearTripodBottomCopperSleeve(new BigDecimal(10.18).add(new BigDecimal(i)));
            temperatureEntity.setLeftFrontConnectingRodBigCopperSleeve(new BigDecimal(10.19).add(new BigDecimal(i)));
            temperatureEntity.setLeftRearConnectingRodBigCopperSleeve(new BigDecimal(10.20).add(new BigDecimal(i)));
            temperatureEntity.setRightFrontConnectingRodBigCopperSleeve(new BigDecimal(10.21).add(new BigDecimal(i)));
            temperatureEntity.setRightRearConnectingRodBigCopperSleeve(new BigDecimal(10.22).add(new BigDecimal(i)));
            temperatureEntity.setLeftFrontLowSpeedAxisCopperSleeve(new BigDecimal(10.23).add(new BigDecimal(i)));
            temperatureEntity.setLeftRearLowSpeedAxisCopperSleeve(new BigDecimal(10.24).add(new BigDecimal(i)));
            temperatureEntity.setRightFrontLowSpeedAxisCopperSleeve(new BigDecimal(10.25).add(new BigDecimal(i)));
            temperatureEntity.setRightRearLowSpeedAxisCopperSleeve(new BigDecimal(10.26).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature0(new BigDecimal(10.27).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature1(new BigDecimal(10.28).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature2(new BigDecimal(10.29).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature3(new BigDecimal(10.30).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature4(new BigDecimal(10.31).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature5(new BigDecimal(10.32).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature6(new BigDecimal(10.33).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature7(new BigDecimal(10.34).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature8(new BigDecimal(10.35).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature9(new BigDecimal(10.36).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature10(new BigDecimal(10.37).add(new BigDecimal(i)));
            temperatureEntity.setBackupTemperature11(new BigDecimal(10.38).add(new BigDecimal(i)));
            temperatureEntity.setTemperatureWarningValue1(new BigDecimal(60.00).add(new BigDecimal(i)));
            temperatureEntity.setTemperatureWarningValue2(new BigDecimal(61.00).add(new BigDecimal(i)));
            temperatureEntity.setCreateTime(new Date());
            if(i <= 4) {
                temperatureEntityList1.add(temperatureEntity);
            } else {
                temperatureEntityList2.add(temperatureEntity);
            }

        }
        TemperatureList.add(temperatureEntityList1);
        TemperatureList.add(temperatureEntityList2);
        return TemperatureList;
    }

    /**
     * 获取内存中的PLC温度信息
     * @return
     */
    public List<TemperatureEntity> getTemperatureList() {
        List<TemperatureEntity> temperatureList = new ArrayList<TemperatureEntity>();
        Set<String> keySet = TemperatureMap.keySet();
        Set<String> connectionKeySet = plc4xConnectorService.plcConnectionMap.keySet();
        List<PlcConfigEntity> plcConfigList= plcConfigService.getPlcList();

        if(CollectionUtils.isEmpty(plcConfigList)) {
            return temperatureList;
        }

        for(PlcConfigEntity config : plcConfigList) {
            TemperatureEntity temperatureEntity = TemperatureMap.get(config.getPlcName());
            if(null != temperatureEntity) {
//                temperatureEntity.setPlcConnectionStatus(plc4xConnectorService.isConnected(config) ? "Y" :"N");
                temperatureList.add(temperatureEntity);
            } else {
                temperatureEntity = new TemperatureEntity();
//                temperatureEntity.setPlcConnectionStatus(plc4xConnectorService.isConnected(config) ? "Y" :"N");
                temperatureEntity.setPlcName(config.getPlcName());
                temperatureEntity.setStatus(HmiConstants.TEMPERATURE_STATUS_NORMAL);
            }
            temperatureList.add(temperatureEntity);
        }

/*
        for(int i=0;i<5;i++) {
            TemperatureEntity temperatureEntity = new TemperatureEntity();
            temperatureEntity.setPlcName(String.valueOf(i)+"名子会很长");
            temperatureEntity.setPlcConnectionStatus("Y");
            temperatureEntity.setPlcIp("127.0.0." + i);
            temperatureEntity.setStatus("OK");
            temperatureEntity.setHandleDate("2021-01-01");
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature1(new BigDecimal(10.0));
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature2(new BigDecimal(10.0));
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature3(new BigDecimal(10.0));
            temperatureEntity.setLowSpeedAxisEccentricCopperSleeveTemperature4(new BigDecimal(10.0));
            temperatureEntity.setHighSpeedAxisEccentricCopperSleeveTemperature1(new BigDecimal(10.0));
            temperatureEntity.setHighSpeedAxisEccentricCopperSleeveTemperature2(new BigDecimal(10.0));
            temperatureEntity.setFlywheelSupportBigAxisTemperature1(new BigDecimal(10.0));
            temperatureEntity.setFlywheelSupportBigAxisTemperature2(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature1(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature2(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature3(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature4(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature5(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature6(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature7(new BigDecimal(10.0));
            temperatureEntity.setDrawbarBothEndsCopperSleeveTemperature8(new BigDecimal(10.0));
            temperatureEntity.setSmallBeltWheelSupportAxisTemperature1(new BigDecimal(10.0));
            temperatureEntity.setSmallBeltWheelSupportAxisTemperature2(new BigDecimal(10.0));
            temperatureEntity.setTripodBottomCopperSleeveTemperature1(new BigDecimal(10.0));
            temperatureEntity.setTripodBottomCopperSleeveTemperature2(new BigDecimal(10.0));
            temperatureEntity.setTripodBottomCopperSleeveTemperature3(new BigDecimal(10.0));
            temperatureEntity.setTripodBottomCopperSleeveTemperature4(new BigDecimal(10.0));
            temperatureEntity.setTripodEccentricBigCopperSleeveTemperature1(new BigDecimal(10.0));
            temperatureEntity.setTripodEccentricBigCopperSleeveTemperature2(new BigDecimal(10.0));
            temperatureEntity.setTripodEccentricBigCopperSleeveTemperature3(new BigDecimal(10.0));
            temperatureEntity.setTripodEccentricBigCopperSleeveTemperature4(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature0(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature1(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature2(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature3(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature4(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature5(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature6(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature7(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature8(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature9(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature10(new BigDecimal(10.0));
            temperatureEntity.setBackupTemperature11(new BigDecimal(10.0));
            temperatureEntity.setTemperatureWarningValue1(new BigDecimal(35));
            temperatureEntity.setTemperatureWarningValue2(new BigDecimal(36));
            temperatureList.add(temperatureEntity);
        }
*/

        return temperatureList;
    }




}
