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
            @Result(column = "PRESSURE_HEAD_NO",property ="pressureHeadNo"),
            @Result(column = "RECORD_ID",property ="recordId"),
            @Result(column = "PRODUCT_ID",property ="productId"),
            @Result(column = "RECORD_NO",property ="recordNo"),
            @Result(column = "POSITION",property ="position"),
            @Result(column = "PRESS_FORCE",property ="pressForce"),
            @Result(column = "CUR_SPEED",property ="curSpeed"),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "PRESS_DATE",property ="pressDate"),
            @Result(column = "PRESS_FLAG",property ="pressFlag"),
            @Result(column = "PRESSURE_OUT_RANGE",property ="pressureOutRange"),
            @Result(column = "TRACE_CODE",property ="traceCode"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PressureCurveEntity> getCurveData(@Param("recordId") Long recordId);


    @Select({"<script>",
            "select * from pressure_curve where IS_DELETED='0'",
            "<if test='recordId!=null' >",
            "and RECORD_ID = #{recordId}",
            "</if>",
            "<if test='pressureHeadNo!=null' >",
            "and PRESSURE_HEAD_NO = #{pressureHeadNo}",
            "</if>",
            "order by record_ID, PRESSURE_HEAD_NO",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PRESSURE_HEAD_NO",property ="pressureHeadNo"),
            @Result(column = "RECORD_ID",property ="recordId"),
            @Result(column = "PRODUCT_ID",property ="productId"),
            @Result(column = "RECORD_NO",property ="recordNo"),
            @Result(column = "POSITION",property ="position"),
            @Result(column = "PRESS_FORCE",property ="pressForce"),
            @Result(column = "CUR_SPEED",property ="curSpeed"),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "PRESS_DATE",property ="pressDate"),
            @Result(column = "PRESS_FLAG",property ="pressFlag"),
            @Result(column = "PRESSURE_OUT_RANGE",property ="pressureOutRange"),
            @Result(column = "TRACE_CODE",property ="traceCode"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PressureCurveEntity> getCurveDataWithDate(@Param("recordId") String recordId, @Param("pressureHeadNo") int pressureHeadNo);
//    @Select("select * from pressure_curve where is_deleted='0' and HANDLE_DATE=#{handleDate}")
//    List<HashMap> getPressureCurveWithDate(@Param("handleDate") String handleDate);

//    @Select("select * from pressure_curve where is_deleted='0' and HANDLE_DATE>=#{handleDate}")
//    List<HashMap> getPressureCurveWithDateStart(@Param("handleDate") String handleDate);

    @Insert({"insert into pressure_curve (ID, PRESSURE_HEAD_NO, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, HANDLE_DATE,PRESS_DATE, PRESS_FLAG, PRESSURE_OUT_RANGE, TRACE_CODE, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{pressureHeadNo}, #{recordId}, #{productId}, #{recordNo}, #{position}, #{pressForce}, #{curSpeed}, #{handleDate}, #{pressDate}, #{pressFlag}, #{pressureOutRange}, #{traceCode}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(@Param("entity") PressureCurveEntity entity);

    @Insert("<script>" +
            "insert into pressure_curve (ID, PRESSURE_HEAD_NO, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, HANDLE_DATE,PRESS_DATE, PRESS_FLAG, PRESSURE_OUT_RANGE, TRACE_CODE, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) " +
            "values " +
            "<foreach collection='entityList' item='entity' index='index' separator=','>" +
            "(null, #{entity.pressureHeadNo}, #{entity.recordId}, #{entity.productId},#{entity.recordNo}, #{entity.position}, #{entity.pressForce}, #{entity.curSpeed}, #{entity.handleDate}, #{entity.pressDate}, #{entity.pressFlag}, #{entity.pressureOutRange}, #{entity.traceCode}, '0', #{entity.createBy}, #{entity.createBy}, now(), now())" +
            "</foreach>" +
            "</script>")
    void batchInsert(@Param("entityList") List<PressureCurveEntity> entityList);

}
