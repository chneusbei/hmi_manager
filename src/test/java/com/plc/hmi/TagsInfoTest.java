package com.plc.hmi;

import com.plc.hmi.dal.entity.TagsInfoEntity;
import com.plc.hmi.service.TagsInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TagsInfoTest {
    @Autowired
    private TagsInfoService service;



    @Test
    public void testSql() {
        TagsInfoEntity entity = new TagsInfoEntity();
        entity.setId(1L);
        entity.setTagName("Tag1");
        entity.setTagTypeId(1);
        entity.setTagTypeDes("bool");
        entity.setTagArea("Ox84");
        entity.setDbNo(300);
        entity.setAdderss(100);
        entity.setTagBit(0);
        entity.setTagGroup("group");
        entity.setUpdateBy("SYS");
        entity.setCreateBy("SYS");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        service.insert(entity);
        List<TagsInfoEntity> result = service.getTags();
//        entity.setTagName("Tag2");
//        entity.setTagTypeId(11);
//        entity.setTagArea("Ox81");
//        entity.setDbNo(111);
//        entity.setAdderss(21);
//        entity.setTagBit(31);
//         entity.setTagGroup("group1");
//        service.update(entity);
//        service.delete(entity);


        System.out.println("END----------------");
    }


}
