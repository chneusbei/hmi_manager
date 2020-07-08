package com.plc.hmi.service;

import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.thread.PlcPressCurveThread;
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
    Plc4xCurveDataService plc4xCurveDataService;
    @Autowired
    PropertyService propertyService;
//    @Resource
//    Plc4xCurveDataService1 plc4xCurveDataService1;
//    @Resource
//    Plc4xCurveDataService2 plc4xCurveDataService2;
//    @Resource
//    private PressureCurveService pressureCurveService;
    private final Log logger = LogFactory.getLog(StartRunService.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //启动实时曲线获取线程
        PlcPressCurveThread plcPressCurveThread = new PlcPressCurveThread(plc4xCurveDataService,propertyService);
        plcPressCurveThread.run();



//        plc4xCurveDataService1.setTagGroup();
//        PlcPressCurveThread plcPressCurveThread1 = new PlcPressCurveThread(plc4xCurveDataService1);
//        plcPressCurveThread1.run();
//        System.out.println("1="+plcPressCurveThread1.getService().getTagGroup());
//        plc4xCurveDataService2.setTagGroup();
//        PlcPressCurveThread plcPressCurveThread2 = new PlcPressCurveThread(plc4xCurveDataService2);
//        plcPressCurveThread2.run();

//        while (true){
//            plc4xCurveDataService.getCurveDatasFromPlc();
//        }

//        启动曲线自动入库线程
//        PressCurveThread pressCurveThread = new PressCurveThread(pressureCurveService) ;
//        pressCurveThread.run();
    }
}
