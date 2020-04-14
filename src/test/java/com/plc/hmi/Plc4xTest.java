package com.plc.hmi;


import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Plc4xTest {
    @Autowired
    private Plc4xCurveDataService service;
//    @Autowired
//    private Plc4xTestService testService;

    @Test
    public void testSql() {
        service.getCurveDatasFromPlc();
        List<PressureCurveEntity>  curveEntityList = service.getCurveDatas();
//        List<PlcEntity> testList = testService.getDatas();
        System.out.println("END----------------");
    }


}
