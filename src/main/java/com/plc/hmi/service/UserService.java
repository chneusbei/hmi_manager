package com.plc.hmi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.hmi.dal.dao.UserDao;
import com.plc.hmi.dal.entity.UserEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.naming.Name;
import java.util.List;

@Service
@Component
public class UserService extends AbstractBaseService{
    @Resource
    UserDao userDao;

    public UserEntity getUser(String userName) {
        return userDao.getUser(userName);
    }
    public UserEntity getUser(Long id) {
        return userDao.getUser(id);
    }

    public PageInfo<UserEntity> getUserList(String userName,Integer index) {
       if ("null".equals(userName)){
            userName=null;
       }
        return userDao.getUserList(userName,index);
    }

    public void insert(UserEntity entity) {
        userDao.insert(entity);
    }

    public void update(UserEntity entity) {
        userDao.update(entity);
    }

    public void updatePassword(UserEntity entity) {
        userDao.updatePassword(entity);
    }

    public void delete(UserEntity entity) {
        userDao.delete(entity);
    }

    public UserEntity loginUser(String name,String pwd){
        return userDao.loginUser(name,pwd);
    };
    public void  updateUser(int rid,long uid, String name, String pwd){
        userDao.updateUser(rid,uid,name,pwd);
    };
}
