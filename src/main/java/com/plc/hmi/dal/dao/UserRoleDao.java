package com.plc.hmi.dal.dao;


import com.plc.hmi.dal.entity.UserRoleEntity;
import com.plc.hmi.dal.mapper.UserRoleMapper;
import com.plc.hmi.enumeration.UserRoleEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRoleDao extends AbstractHmiBaseDao {

    @Resource
    UserRoleMapper userRoleMapper;

    public UserRoleEntity getUserRole(long roleId) {
        List<UserRoleEntity> userRoleList = this.getEntityList(userRoleMapper.getUserRole(roleId));
        if(userRoleList != null && !userRoleList.isEmpty()) {
            return userRoleList.get(0);
        } else {
            return null;
        }
    }

    public List<UserRoleEntity> getUserRole(Long roleId) {
        List<UserRoleEntity> userRoleList = this.getEntityList(userRoleMapper.getUserRole(roleId));
        if(userRoleList != null && !userRoleList.isEmpty()) {
            return userRoleList;
        } else {
            return null;
        }
    }


    @Override
    protected UserRoleEntity getEntity(HashMap map) {
        UserRoleEntity entity = new UserRoleEntity();
        super.setEntityBase(entity, map);
        entity.setRoleName(HmiUtils.getString(map.get(UserRoleEntityEnum.ROLE_NAME.getCode())));
        entity.setRoleDescription(HmiUtils.getString(map.get(UserRoleEntityEnum.ROLE_NAME.getCode())));
        return entity;
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
