package com.plc.hmi.thread;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.entity.PlcConfigEntity;
import com.plc.hmi.service.PlcConfigService;
import com.plc.hmi.service.PropertyService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class PlcTemperatureThread implements Runnable {
    private final Log logger = LogFactory.getLog(PlcTemperatureThread.class);
    @Autowired
    Plc4xTemperatureService service;
    @Autowired
    PlcConfigService plcConfigService;
    @Autowired
    PropertyService propertyService;

    public PlcTemperatureThread(Plc4xTemperatureService service, PropertyService propertyService, PlcConfigService plcConfigService) {
        this.service = service;
        this.plcConfigService = plcConfigService;
        this.propertyService = propertyService;
    }

    @Override
    public synchronized void run() {
        int temperatureFetchFrequency = HmiUtils.getIntValue(propertyService.getProperty(ConfigConstants.TEMPERATURE_FETCH_FREQUENCY));
        while(true) {
            List<PlcConfigEntity> plcList = plcConfigService.getPlcList();
            if(!CollectionUtils.isEmpty(plcList)) {
                for(PlcConfigEntity plcConfigEntity: plcList) {
                    service.getTemperatureFromPlc(plcConfigEntity);
                }
            } else {
                System.out.println(">>>PLC列表为空，或者未能正确获取到PLC列表， 请检查plc_config表中PLC配置");
            }

            try {
                //休眠
//                System.out.println("获取plc温度数据。。。。。。。。。。。。。。。。。。。。。");
                Thread.sleep(temperatureFetchFrequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
