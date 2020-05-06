package com.plc.hmi;

import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
public class PropertyConfigTest {
    @Autowired
    private PropertyService service;

    @Test
    public void testSql() {

        PropertyEntity entity = new PropertyEntity();
        entity.setPropGroup("groupT");
        entity.setPropName("testPropNEW");
        entity.setPropValue("testValueNEW");
        entity.setUpdateBy("NEW");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.update(entity);
//        service.insert(entity);
        List<PropertyEntity> list = service.getProperties();
        List<PropertyEntity> list2 = service.getPropertyWithGroup("group1");


        System.out.println("END----------------");
    }

}
