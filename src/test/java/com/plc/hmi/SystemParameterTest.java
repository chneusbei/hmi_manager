package com.plc.hmi;

import com.plc.hmi.dal.entity.SystemParameterEntity;
import com.plc.hmi.service.SystemParameterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//@SpringBootTest
public class SystemParameterTest {
    @Autowired
    private SystemParameterService service;

//    @Test
    public void testSql() {
/*
        SystemParameterEntity entity = new SystemParameterEntity();
        entity.setId(1L);
        entity.setMaxDistance(new BigDecimal(1));
        entity.setMaxSpeed(new BigDecimal(2));
        entity.setMaxForce(new BigDecimal(3));
        entity.setDefaultBackSpeed(new BigDecimal(4));
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
         SystemParameterEntity  list=  service.getSystemParameters();
        entity.setMaxDistance(new BigDecimal(1.1));
        entity.setMaxSpeed(new BigDecimal(2.1));
        entity.setMaxForce(new BigDecimal(3.1));
        entity.setDefaultBackSpeed(new BigDecimal(4.1));
//        service.update(entity);
//        service.delete(entity);
*/
        System.out.println("END----------------");
    }

}
