package com.plc.hmi.thread;

import com.plc.hmi.service.PropertyService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlcPressCurveThread implements Runnable {
    private final Log logger = LogFactory.getLog(PlcPressCurveThread.class);
    @Autowired
    Plc4xCurveDataService service;
    @Autowired
    PropertyService propertyService;

    public PlcPressCurveThread(Plc4xCurveDataService service,  PropertyService propertyService) {
        this.service = service;
        this.propertyService=propertyService;
    }

    @Override
    public synchronized void run() {
        int curveFetchFrequency = propertyService.getcurveFetchFrequency();
        int heartbeatFrequency = propertyService.getHeartbeatFrequency();
        boolean  isdubbpress = propertyService.isDubblePress();
//        System.out.println("curveFetchFrequency = "+curveFetchFrequency+", heartbeatFrequency"+heartbeatFrequency + ", isdubbpress"+isdubbpress);
        int i =0;
        while(true) {
            service.getCurveDatasFromPlc();
            i++;
            if(i==(heartbeatFrequency/curveFetchFrequency)) {
//                logger.info("do heart beat >>>>>>>>>>>>>>>>>>>>>>>>>");
             service.doHeartBeat();
             i=0;
            }
            try {
                //休眠
//                System.out.println("获取plc曲线数据。。。。。。。。。。。。。。。。。。。。。");

                if(!service.isPlcConnected()){
                    logger.info("cant connected to plc, get curve date thread sleep 30 seconds");
                    Thread.sleep(30000);
                } else {
                    Thread.sleep(curveFetchFrequency);
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
