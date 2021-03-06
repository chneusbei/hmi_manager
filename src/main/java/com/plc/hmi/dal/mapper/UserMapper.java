package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.dao.UserRoleDao;
import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.dal.entity.UserRoleEntity;
import com.plc.hmi.enumeration.PressureCurveEntityEnum;
import com.plc.hmi.enumeration.UserEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from USER where is_deleted='0' and USER_NAME=#{userName}")
    List<HashMap> getUser(String userName);
    @Select("select * from USER where is_deleted='0' and ID=#{id}")
    List<HashMap> getUserId(Long id);
    /*新增查询所有用户*/
    @Select({"<script>",
            "select * from USER where USER.IS_DELETED='0'",
            "<if test='userName!=null' >",
            "and USER_NAME=#{userName}",
            "</if>",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column ="USER_NAME",property = "userName"),
            @Result(column ="USER_PASSWORD",property = "userPassword"),
            @Result(column ="CREATE_BY",property = "createBy"),
            @Result(column ="UPDATE_BY",property = "updateBy"),
            @Result(column ="CREATE_TIME",property = "createTime"),
            @Result(column ="UPDATE_TIME",property = "updateTime"),
            @Result(column = "ROLE_ID",property="userRoleEntity",one =@One(select = "com.plc.hmi.dal.mapper.UserRoleMapper.getUserRoleOne")),
    })
    List<UserEntity> getUserList(String userName);

//    @Select("select * from USER where is_deleted='0'")
//    List<HashMap> getUserList();

    @Insert({"insert into USER(id, USER_NAME, USER_PASSWORD, ROLE_ID, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{userName}, #{userPassword}, #{roleId}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(UserEntity entity);

    @Update({"update USER set ROLE_ID=#{roleId}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void updateUserRole(UserEntity entity);

    @Update({"update USER set USER_PASSWORD=#{userPassword}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void updatePassword(UserEntity entity);

    @Update({"update USER set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(UserEntity entity);
}
