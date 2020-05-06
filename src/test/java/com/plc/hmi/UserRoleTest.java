package com.plc.hmi;

import com.plc.hmi.dal.entity.UserRoleEntity;
import com.plc.hmi.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

//@SpringBootTest
public class UserRoleTest {
    @Autowired
    private UserRoleService service;



//    @Test
    public void testSql() {
        /*
        UserRoleEntity entity = new UserRoleEntity();
        entity.setId(1L);
        entity.setRoleName("admin");
        entity.setRoleDescription("管理员,全权限");
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
         UserRoleEntity  result = service.getUserRole(1);
        List<UserRoleEntity> resultList = service.getUserRoleList();
//        service.update(entity);
//        service.delete(entity);

*/
        System.out.println("END----------------");
    }


}
