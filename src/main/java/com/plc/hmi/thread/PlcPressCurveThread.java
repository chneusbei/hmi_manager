package com.plc.hmi.thread;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.service.PropertyService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.util.HmiUtils;
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
        int curveFetchFrequency = HmiUtils.getIntValue(propertyService.getProperty(ConfigConstants.CURVE_FETCH_FREQUENCY));
        int heartbeatFrequency = HmiUtils.getIntValue(propertyService.getProperty(ConfigConstants.HEARTBEAT_FREQUENCY));
        int curveFetchSleepTime =  HmiUtils.getIntValue(propertyService.getProperty(ConfigConstants.CURVE_FETCH_SLEEP_TIME));
//        boolean  isdubbpress = propertyService.isDubblePress();
//        System.out.println("curveFetchFrequency = "+curveFetchFrequency
//                +", heartbeatFrequency=" +heartbeatFrequency
//                + ", isdubbpress="+isdubbpress
//                + ", curveFetchSleepTime="+curveFetchSleepTime);
//        int loop =0;
        while(true) {
            service.getCurveDatasFromPlc();
           /* loop++;
            if(heartbeatFrequency >= 0 && loop*curveFetchFrequency>=heartbeatFrequency) {
//                logger.info("do heart beat >>>>>>>>>>>>>>>>>>>>>>>>>");
//             service.doHeartBeat();
                loop=0;
            }*/
            try {
                //休眠
//                System.out.println("获取plc曲线数据。。。。。。。。。。。。。。。。。。。。。");
                if(!service.isPlcConnected(null)){
                    logger.info("cant connected to plc, get curve date thread sleep "+ curveFetchSleepTime +"millSeconds");
                    Thread.sleep(curveFetchSleepTime);
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
