package com.plc.hmi.thread;

import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;


public class PlcPressCurveThread implements Runnable {
    private Plc4xCurveDataService service;
    public PlcPressCurveThread(Plc4xCurveDataService service ) {
        this.service = service;
    }

    @Override
    public void run() {
        while(true) {
            service.getCurveDatasFromPlc();
//            try {
//                //休眠两秒
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
