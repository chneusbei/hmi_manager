package com.plc.hmi.service;


import com.plc.hmi.dal.dao.UserRoleDao;
import com.plc.hmi.dal.entity.UserRoleEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class UserRoleService extends AbstractBaseService{
    @Resource
    UserRoleDao dao;

    public UserRoleEntity getUserRole(long roleId) {
        return dao.getUserRole(roleId);
    }

    public List<UserRoleEntity> getUserRoleList() {
        return dao.getUserRole(null);
    }

    public void insert(UserRoleEntity entity) {
        dao.insert(entity);
    }

    public void update(UserRoleEntity entity) {
        dao.update(entity);
    }


    public void delete(UserRoleEntity entity) {dao.delete(entity);
    }
}
