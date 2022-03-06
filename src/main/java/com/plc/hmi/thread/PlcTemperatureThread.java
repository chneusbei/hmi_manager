package com.plc.hmi.thread;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.entity.PlcConfigEntity;
import com.plc.hmi.service.PlcConfigService;
import com.plc.hmi.service.PropertyService;
import com.plc.hmi.service.TemperatureService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.util.HmiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class PlcTemperatureThread implements Runnable {
    private static Logger logger =  LoggerFactory.getLogger(PlcTemperatureThread.class);
    @Autowired
    Plc4xTemperatureService service;
    @Autowired
    PlcConfigService plcConfigService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    TemperatureService temperatureService;


    public PlcTemperatureThread(Plc4xTemperatureService service, PropertyService propertyService, PlcConfigService plcConfigService, TemperatureService temperatureService) {
        this.service = service;
        this.plcConfigService = plcConfigService;
        this.propertyService = propertyService;
        this.temperatureService = temperatureService;
    }

    @Override
    public synchronized void run() {
        int temperatureFetchFrequency = HmiUtils.getIntValue(propertyService.getProperty(ConfigConstants.TEMPERATURE_FETCH_FREQUENCY));
        String lineType = HmiUtils.getString(propertyService.getProperty(ConfigConstants.TEMPERATURE_LINE_TYPE));

        while(true) {
            List<PlcConfigEntity> plcList = plcConfigService.getPlcList();
            if(!CollectionUtils.isEmpty(plcList)) {
                for(PlcConfigEntity plcConfigEntity: plcList) {
                    logger.info("开始获取PLC温度数据: {}",HmiUtils.getFormatDateString(new Date()));
                    service.getTemperatureNewFromPlc(plcConfigEntity, lineType);
                    logger.info("获取PLC温度数据完成: {}",HmiUtils.getFormatDateString(new Date()));
                    temperatureService.deleteHistoryData();
                }
            } else {
                System.out.println(">>>PLC列表为空，或者未能正确获取到PLC列表， 请检查plc_config表中PLC配置");
            }

            try {
                //休眠
//                System.out.println("休眠时间:"+temperatureFetchFrequency*60*1000);
                Thread.sleep(temperatureFetchFrequency*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
