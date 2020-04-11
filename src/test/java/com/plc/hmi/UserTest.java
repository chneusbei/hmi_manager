package com.plc.hmi;
import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService service;

    @Test
    public void testSql() {

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUserName("admin");
        entity.setUserPassword("admin");
        entity.setRoleId(1);
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.inert(entity);
//        service.update(entity);
//        service.updatePassword(entity);
//        service.delete(entity);
        UserEntity userEntity =service.getUser("admin");


        System.out.println("END----------------");
    }

}
