package com.plc.hmi.service;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.dal.dao.AlarmDao;
import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报警服务
 */
@Service
@Component
public class StartRunService implements ApplicationRunner {
    @Resource
    Plc4xCurveDataService plc4xCurveDataService;
    private final Log logger = LogFactory.getLog(StartRunService.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        while (true){
//            plc4xCurveDataService.getCurveDatasFromPlc();
//        }
    }
}
