package com.plc.hmi.dal.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.dal.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDao {
    @Resource
    UserMapper userMapper;

    public UserEntity getUser(String userName) {
        return userMapper.getUser(userName);
    }

    public UserEntity getUser(long id) {
        return userMapper.getUserById(id);
    }

    public PageInfo<UserEntity> getUserList(String userName,Integer index) {
        PageHelper.startPage(index,100);
        List<UserEntity> userList = userMapper.getUserList(userName);
        PageInfo<UserEntity> page = new PageInfo<UserEntity>(userList);
        if(page.getList() != null && !page.getList().isEmpty()) {
            return  page;
        } else {
            return null;
        }
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
        UserEntity userEntity=userMapper.loginUser(name,pwd);
        return userEntity;
    }
    public void  updateUser(int rid,long uid, String name, String pwd){
        userMapper.updateUser(rid,uid,name,pwd);
    }

}
