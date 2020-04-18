package com.plc.hmi.dal.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.dal.mapper.UserMapper;
import com.plc.hmi.enumeration.UserEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDao extends AbstractHmiBaseDao {
    @Resource
    UserMapper userMapper;

    public UserEntity getUser(Object obj) {
        List<UserEntity> userList=null;
        if (obj.getClass()==Long.class && obj instanceof  Long){
            userList = this.getEntityList(userMapper.getUserId(Long.valueOf(obj.toString())));
        }else{
            userList =this.getEntityList(userMapper.getUser(obj.toString()));
        }

        if(userList != null && !userList.isEmpty()) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public PageInfo<UserEntity> getUserList(String userName,Integer index) {
        PageHelper.startPage(index,2);
        List<UserEntity> userList = userMapper.getUserList(userName);
        PageInfo<UserEntity> page = new PageInfo<UserEntity>(userList);
        if(page.getList() != null && !page.getList().isEmpty()) {
            return  page;
        } else {
            return null;
        }
    }

//    public List<UserEntity> getUserList() {
//        return  this.getEntityList(userMapper.getUserList());
//    }

    @Override
    protected UserEntity getEntity(HashMap map) {
        UserEntity entity = new UserEntity();
        super.setEntityBase(entity, map);
        entity.setUserName(HmiUtils.getString(map.get(UserEntityEnum.USER_NAME.getCode())));
        entity.setUserPassword(HmiUtils.getString(map.get(UserEntityEnum.USER_PASSOWORD.getCode())));
        entity.setRoleId(HmiUtils.getIntValue(map.get(UserEntityEnum.ROLE_ID.getCode())));
        return entity;
    }

    public void insert(UserEntity entity) {
        userMapper.insert(entity);
    }

    public void update(UserEntity entity) {
        userMapper.updateUserRole(entity);
    }

    public void updatePassword(UserEntity entity) {
        userMapper.updatePassword(entity);
    }

    public void delete(UserEntity entity) {
        userMapper.delete(entity);
    }

    public UserEntity loginUser(String name,String pwd){
        HashMap hashMap=userMapper.loginUser(name,pwd);
        UserEntity userEntity=null;
        if(hashMap==null){
            userEntity=null;
        }else{
            userEntity=this.getEntity(userMapper.loginUser(name,pwd));
        }
        return userEntity;
    }
    public void  updateUser(int rid,long uid, String name, String pwd){
        userMapper.updateUser(rid,uid,name,pwd);
    }

}
