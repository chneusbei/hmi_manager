package com.plc.hmi.dal.dao;


import com.plc.hmi.dal.entity.UserRoleEntity;
import com.plc.hmi.dal.mapper.UserRoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserRoleDao {

    @Resource
    UserRoleMapper userRoleMapper;

    public List<UserRoleEntity> getUserRoleList() {
        List<UserRoleEntity> userRoleList = userRoleMapper.getUserRoleList();
        return userRoleList;
    }

    public UserRoleEntity getUserRole(Long roleId) {
        UserRoleEntity userRole =  userRoleMapper.getUserRoleOne(roleId);
        return userRole;
    }

    public void insert(UserRoleEntity entity) {
        userRoleMapper.insert(entity);
    }

    public void update(UserRoleEntity entity) {
        userRoleMapper.update(entity);
    }

    public void delete(UserRoleEntity entity) {
        userRoleMapper.delete(entity);
    }


}
