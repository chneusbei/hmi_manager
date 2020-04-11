package com.plc.hmi;

import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xEquipmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    Plc4xCurveDataService plc4xEquipmentOperationService;
    @Autowired
    Plc4xEquipmentStatusService plc4xEquipmentStatusService;


    @org.junit.jupiter.api.Test
    public void testSql() {

        System.out.println("plc4xEquipmentStatusService.getTestString()");

    }


}
