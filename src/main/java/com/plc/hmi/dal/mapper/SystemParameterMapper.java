package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.SystemParameterEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SystemParameterMapper {
    @Select("select * from system_parameter where is_deleted='0'")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "MAX_DISTANCE",property ="maxDistance"),
            @Result(column = "MAX_FORCE",property ="maxForce"),
            @Result(column = "MAX_SPEED",property ="maxSpeed"),
            @Result(column = "DEFAULT_BACK_SPEED",property ="defaultBackSpeed"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    SystemParameterEntity getSystemParameters();

    @Insert({"insert into system_parameter(id, MAX_DISTANCE, MAX_FORCE, MAX_SPEED, DEFAULT_BACK_SPEED, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{maxDistance}, #{maxForce}, #{maxSpeed}, #{defaultBackSpeed}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(SystemParameterEntity entity);

    @Update({"update system_parameter set MAX_DISTANCE=#{maxDistance}, MAX_FORCE= #{maxForce}, MAX_SPEED= #{maxSpeed}, DEFAULT_BACK_SPEED= #{defaultBackSpeed}, UPDATE_TIME=now() where ID=#{id}"})
    void update(SystemParameterEntity entity);

    @Update({"update system_parameter set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(SystemParameterEntity entity);
}
