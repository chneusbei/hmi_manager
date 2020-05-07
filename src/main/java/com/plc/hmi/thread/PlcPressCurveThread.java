package com.plc.hmi.thread;

import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;


public class PlcPressCurveThread implements Runnable {
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
                Thread.sleep(2);
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
