package com.plc.hmi;

import com.plc.hmi.dal.entity.TagTypeEntity;
import com.plc.hmi.service.TagTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TagTypeTest {
    @Autowired
    private TagTypeService service;



    @Test
    public void testSql() {
        TagTypeEntity entity = new TagTypeEntity();
        entity.setId(1L);
        entity.setDataType("bool");
        entity.setDataLength(1);
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
        List<TagTypeEntity> result = service.getTagType();
//        service.delete(entity);
//        service.deleteProduct(entity);


        System.out.println("END----------------");
    }
}
