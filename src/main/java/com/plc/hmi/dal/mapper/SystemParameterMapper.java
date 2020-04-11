package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.SystemParameterEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SystemParameterMapper {
    @Select("select * from system_parameter where is_deleted='0'")
    List<HashMap> getSystemParameters();

    @Insert({"insert into system_parameter(id, MAX_DISTANCE, MAX_FORCE, MAX_SPEED, DEFAULT_BACK_SPEED, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{maxDistance}, #{maxForce}, #{maxSpeed}, #{defaultBackSpeed}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(SystemParameterEntity entity);

    @Update({"update system_parameter set MAX_DISTANCE=#{maxDistance}, MAX_FORCE= #{maxForce}, MAX_SPEED= #{maxSpeed}, DEFAULT_BACK_SPEED= #{defaultBackSpeed}, UPDATE_TIME=now() where ID=#{id}"})
    void update(SystemParameterEntity entity);

    @Update({"update system_parameter set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(SystemParameterEntity entity);
}
