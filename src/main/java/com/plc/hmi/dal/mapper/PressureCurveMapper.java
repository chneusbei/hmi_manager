package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PropertyEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface PressureCurveMapper {
    @Select("select * from pressure_curve where is_deleted='0' and RECORD_ID=#{recordId}")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "RECORD_ID",property ="recordId"),
            @Result(column = "PRODUCT_ID",property ="productId"),
            @Result(column = "RECORD_NO",property ="recordNo"),
            @Result(column = "POSITION",property ="position"),
            @Result(column = "PRESS_FORCE",property ="pressForce"),
            @Result(column = "CUR_SPEED",property ="curSpeed"),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "PRESS_DATE",property ="pressDate"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PressureCurveEntity> getCurveData(@Param("recordId") Long recordId);

//    @Select("select * from pressure_curve where is_deleted='0' and HANDLE_DATE=#{handleDate}")
//    List<HashMap> getPressureCurveWithDate(@Param("handleDate") String handleDate);

//    @Select("select * from pressure_curve where is_deleted='0' and HANDLE_DATE>=#{handleDate}")
//    List<HashMap> getPressureCurveWithDateStart(@Param("handleDate") String handleDate);

    @Insert({"insert into pressure_curve (ID, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, HANDLE_DATE,PRESS_DATE, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{recordId}, #{productId}, #{recordNo}, #{position}, #{pressForce}, #{curSpeed}, #{handleDate}, #{pressDate}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(@Param("entity") PressureCurveEntity entity);

    @Insert("<script>" +
            "insert into pressure_curve (ID, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, HANDLE_DATE,PRESS_DATE, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) " +
            "values " +
            "<foreach collection='entityList' item='entity' index='index' separator=','>" +
            "(null, #{entity.recordId}, #{entity.productId},#{entity.recordNo}, #{entity.position}, #{entity.pressForce}, #{entity.curSpeed}, #{entity.handleDate}, #{entity.pressDate}, '0', #{entity.createBy}, #{entity.createBy}, now(), now())" +
            "</foreach>" +
            "</script>")
    void batchInsert(@Param("entityList") List<PressureCurveEntity> entityList);

}
