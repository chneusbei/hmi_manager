package com.plc.hmi;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.service.PressureCurveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PressureCurveTest {
    @Autowired
    private PressureCurveService service;



    @Test
    public void testSql() {
        PressureCurveEntity entity = new PressureCurveEntity();
//        entity.setId(1L);
        entity.setPressDataId(1L);
        entity.setRecordNo(3);
        entity.setPosition(new BigDecimal(3.0));
        entity.setPressForce(new BigDecimal(6.1));
        entity.setPressDate(new BigDecimal(20200409232605003L));
        entity.setHandleDate("20200409");
        entity.setUpdateBy("SYS");
        entity.setCreateBy("SYS");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
//        List<PressureCurveEntity> resultList = service.getCurrDate();
//        List<PressureCurveEntity> hisList =service.getHisDateByCode(1L);
        System.out.println("END----------------");
    }


}
