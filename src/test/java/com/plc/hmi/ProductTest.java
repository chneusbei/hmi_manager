package com.plc.hmi;

import com.plc.hmi.dal.entity.ProductEntity;
import com.plc.hmi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductService service;



    @Test
    public void testSql() {
        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setProductCode("TestProduct");
        entity.setProductType("TYPE_A");
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
        ProductEntity result = service.getProduct("TestProduct");
//        service.delete(entity);


        System.out.println("END----------------");
    }


}
