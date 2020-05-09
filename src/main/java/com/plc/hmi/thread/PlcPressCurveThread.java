package com.plc.hmi.thread;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class PlcPressCurveThread implements Runnable {
    private final Log logger = LogFactory.getLog(PlcPressCurveThread.class);
    private Plc4xCurveDataService service;
    public PlcPressCurveThread(Plc4xCurveDataService service ) {
        this.service = service;
    }

    @Override
    public synchronized void run() {
        while(true) {
            service.getCurveDatasFromPlc();
            try {
                //休眠
//                System.out.println("获取plc曲线数据。。。。。。。。。。。。。。。。。。。。。");

                if(!service.isPlcConnected()){
                    logger.info("cant connected to plc, get curve date thread sleep  30 seconds");
                    Thread.sleep(30000);
                } else {
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Plc4xCurveDataService getService() {
        return service;
    }

    public void setService(Plc4xCurveDataService service) {
        this.service = service;
    }
}
