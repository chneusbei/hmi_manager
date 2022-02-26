package com.plc.hmi.service;

import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.thread.PlcTemperatureThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 报警服务
 */
@Service
@Component
public class StartRunService implements ApplicationRunner {
    @Resource
    PlcConfigService plcConfigService;
    @Resource
    PropertyService propertyService;
    @Resource
    Plc4xTemperatureService plc4xTemperatureDataService;
    @Resource
    TemperatureService temperatureService;

    private final Log logger = LogFactory.getLog(StartRunService.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PlcTemperatureThread plcTemperatureThread = new PlcTemperatureThread(plc4xTemperatureDataService,propertyService,plcConfigService, temperatureService);
        plcTemperatureThread.run();
    }
}
