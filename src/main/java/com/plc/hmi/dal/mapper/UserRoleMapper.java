package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.dao.UserDao;
import com.plc.hmi.dal.entity.UserRoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserRoleMapper {

//    @Select({"<script>",
//            "select * from user_role where is_deleted='0' ",
//            "<if test='roleId!=null' >",
//            "and id=#{roleId}",
//            "</if>",
//            "</script>" })
    @Select("select * from user_role where is_deleted='0'")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "ROLE_NAME",property = "roleName"),
            @Result(column = "ROLE_DESCRIPTION",property = "roleDescription"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column ="IS_DELETED",property = "isDeleted"),
    })
    List<UserRoleEntity> getUserRoleList();


    @Select("select * from user_role where is_deleted='0' and id=#{roleId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "ROLE_NAME",property = "roleName"),
            @Result(column = "ROLE_DESCRIPTION",property = "roleDescription"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column ="IS_DELETED",property = "isDeleted"),
    })
    UserRoleEntity getUserRoleOne(long roleId);



    @Insert({"insert into user_role(id, ROLE_NAME, ROLE_DESCRIPTION, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{roleName}, #{roleDescription}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(UserRoleEntity entity);

    @Update({"update user_role set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(UserRoleEntity entity);

    @Update({"update user_role set ROLE_DESCRIPTION=#{roleDescription}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void update(UserRoleEntity entity);

}
