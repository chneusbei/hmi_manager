package com.plc.hmi.service;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.dal.dao.AlarmDao;
import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
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
    Plc4xCurveDataService plc4xCurveDataService;
    @Resource
    private PressureCurveService pressureCurveService;
    private final Log logger = LogFactory.getLog(StartRunService.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        while (true){
//            plc4xCurveDataService.getCurveDatasFromPlc();
//        }
        //启动事实曲线获取线程
//        PlcPressCurveThread plcPressCurveThread = new PlcPressCurveThread(plc4xCurveDataService);
//        plcPressCurveThread.run();

////        启动曲线自动入库线程, 启动失败， 好想不能启动两个线程。
//        PressCurveThread pressCurveThread = new PressCurveThread(pressureCurveService) ;
//        pressCurveThread.run();
    }
}
