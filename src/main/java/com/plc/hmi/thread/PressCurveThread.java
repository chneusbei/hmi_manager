package com.plc.hmi.thread;

import com.plc.hmi.service.PressureCurveService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class PressCurveThread implements Runnable {
    private PressureCurveService service;
    public PressCurveThread (PressureCurveService service ) {
        this.service = service;
    }

    @Override
    public void run() {
//        while(true) {
//            service.autoBatchInsert();
//            try {
//                //休眠两秒
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
