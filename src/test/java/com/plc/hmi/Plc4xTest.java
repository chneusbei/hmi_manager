package com.plc.hmi;


import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Plc4xTest {
    @Autowired
    private Plc4xCurveDataService service;

    @Test
    public void testSql() {
        List<PressureCurveEntity>  curveEntityList = service.getCurveDatas();
        System.out.println("END----------------");
    }


}
