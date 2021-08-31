package com.plc.hmi.service.plcService;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.dao.TemperatureDao;
import com.plc.hmi.dal.entity.PlcConfigEntity;
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
    @Autowired
    Plc4xConnectorService plc4xConnectorService;
    @Autowired
    PlcConfigService  plcConfigService;

    private final Log logger = LogFactory.getLog(Plc4xTemperatureService.class);
    public static Map<String, TemperatureEntity> TemperatureMap = new HashMap<String, TemperatureEntity>();

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
     * 获取实时温度信息
     * @param plcConfigEntity
     */
    public void getTemperatureFromPlc(PlcConfigEntity plcConfigEntity) {
        List<PlcEntity> temperaturePlcEntityList = this.getTemperatureDatas(HmiConstants.PLC_TAG_GROUP.TEMPERATURE_DATA.getCode(), plcConfigEntity);
        TemperatureEntity temperatureEntity = this.getTemperature(temperaturePlcEntityList, plcConfigEntity);
        if(null != temperatureEntity) {
            TemperatureMap.put(plcConfigEntity.getPlcName(),temperatureEntity);
            this.temperature2DB(plcConfigEntity, temperatureEntity);
        }
    }

    /***
     * 获取PCL上曲线状态和基本信息
     * @param entityList
     * @return
     */
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

    /**
     * 数据入库
     * @param plcConfigEntity
     * @param temperatureEntity
     */
    private  void temperature2DB(PlcConfigEntity plcConfigEntity, TemperatureEntity temperatureEntity) {
        if (null == temperatureEntity) {
            System.out.println("Plc4xTemperatureDataService.curve2DB: temperatureEntity is null");
            return;
        }

        temperatureDao.insert(temperatureEntity);
    }

    private String getTemperatureStatus(TemperatureEntity temperatureEntity) {
        String status = HmiConstants.TEMPERATURE_STATUS_NORMAL;
        BigDecimal temperatureWarningValue1 = temperatureEntity.getTemperatureWarningValue1();
        BigDecimal temperatureWarningValue2 = temperatureEntity.getTemperatureWarningValue2();
        if(temperatureWarningValue1.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getFlywheelSupportBigAxisTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getFlywheelSupportBigAxisTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature5()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature6()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature7()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature8()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getSmallBeltWheelSupportAxisTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getSmallBeltWheelSupportAxisTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature0()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature1()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature2()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature3()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature4()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature5()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature6()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature7()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature8()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature9()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature10()) <= 0
                || temperatureWarningValue1.compareTo(temperatureEntity.getBackupTemperature11()) <= 0
                ||temperatureWarningValue2.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getLowSpeedAxisEccentricCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getHighSpeedAxisEccentricCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getFlywheelSupportBigAxisTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getFlywheelSupportBigAxisTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature5()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature6()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature7()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getDrawbarBothEndsCopperSleeveTemperature8()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getSmallBeltWheelSupportAxisTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getSmallBeltWheelSupportAxisTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodBottomCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature3()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getTripodEccentricBigCopperSleeveTemperature4()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature0()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature1()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature2()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature3()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature4()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature5()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature6()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature7()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature8()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature9()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature10()) <= 0
                || temperatureWarningValue2.compareTo(temperatureEntity.getBackupTemperature11()) <= 0
        ) {
            status = HmiConstants.TEMPERATURE_STATUS_WARNING;
        }

        return status;
    }


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
                temperatureEntity.setPlcConnectionStatus(plc4xConnectorService.isConnected(config) ? "已连接" :"未连接");
                temperatureList.add(temperatureEntity);
            } else {
                temperatureEntity = new TemperatureEntity();
                temperatureEntity.setPlcConnectionStatus(plc4xConnectorService.isConnected(config) ? "已连接" :"未连接");
                temperatureEntity.setPlcName(config.getPlcName());
                temperatureEntity.setStatus(HmiConstants.TEMPERATURE_STATUS_NORMAL);
            }
            temperatureList.add(temperatureEntity);
        }
        return temperatureList;
    }


}
