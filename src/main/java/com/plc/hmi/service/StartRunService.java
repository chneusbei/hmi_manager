package com.plc.hmi.service;

import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.thread.PlcTemperatureThread;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(StartRunService.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PlcTemperatureThread plcTemperatureThread = new PlcTemperatureThread(plc4xTemperatureDataService,propertyService,plcConfigService, temperatureService);
        plcTemperatureThread.run();
        logger.info("系统守护线程启动成功");
    }
}
