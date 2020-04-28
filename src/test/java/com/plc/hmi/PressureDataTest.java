package com.plc.hmi;

import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.PressureStatisticalDataEntity;
import com.plc.hmi.service.PressureDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PressureDataTest {
    @Autowired
    private PressureDataService service;



    @Test
    public void testSql() {
        PressureDataEntity entity = new PressureDataEntity();
        entity.setId(1L);
        entity.setProductId(1L);
        entity.setProductNo("product0001");
        entity.setPressResult("1");
        entity.setRecordId("1");
        entity.setStartDate(new BigDecimal(20200409232605003L));
        entity.setEndDate(new BigDecimal(20200409232608003L));
        entity.setMaxPress(new BigDecimal(100L));
        entity.setPositionOfMaxPress(new BigDecimal(100L));
        entity.setUpdateBy("SYS");
        entity.setCreateBy("SYS");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
        List<PressureDataEntity> resultList = service.getPressureData(1L);
        PressureStatisticalDataEntity pressureStatisticalDataEntity  = service.getPressureStatisticalData();
//        service.delete(entity);


        System.out.println("END----------------");
    }


}
