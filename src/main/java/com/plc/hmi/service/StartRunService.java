package com.plc.hmi.service;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.dao.AlarmDao;
import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService1;
import com.plc.hmi.service.plcService.Plc4xCurveDataService2;
import com.plc.hmi.thread.PlcPressCurveThread;
import com.plc.hmi.thread.PressCurveThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
    Plc4xCurveDataService1 plc4xCurveDataService1;
    @Resource
    Plc4xCurveDataService2 plc4xCurveDataService2;
//    @Resource
//    private PressureCurveService pressureCurveService;
    private final Log logger = LogFactory.getLog(StartRunService.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //启动事实曲线获取线程
        plc4xCurveDataService1.setTagGroup();
        PlcPressCurveThread plcPressCurveThread1 = new PlcPressCurveThread(plc4xCurveDataService1);
        plcPressCurveThread1.run();
        plc4xCurveDataService2.setTagGroup();
        PlcPressCurveThread plcPressCurveThread2 = new PlcPressCurveThread(plc4xCurveDataService2);
        plcPressCurveThread2.run();

//        while (true){
//            plc4xCurveDataService.getCurveDatasFromPlc();
//        }
////        启动曲线自动入库线程
//        PressCurveThread pressCurveThread = new PressCurveThread(pressureCurveService) ;
//        pressCurveThread.run();
    }
}
