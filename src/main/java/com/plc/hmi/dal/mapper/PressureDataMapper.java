package com.plc.hmi.dal.mapper;


import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.PressureStatisticalDataEntity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface PressureDataMapper {
    @Select("select * from pressure_data where is_deleted='0' and RECORD_ID=#{recordId}")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PRESSURE_HEAD_NO",property ="pressureHeadNo"),
            @Result(column = "PRODUCT_ID",property ="productId"),
            @Result(column = "PRODUCT_NO",property ="productNo"),
            @Result(column = "PRESS_RESULT",property ="pressResult"),
            @Result(column = "RECORD_ID",property ="recordId"),
            @Result(column = "START_DATE",property ="startDate"),
            @Result(column = "END_DATE",property ="endDate"),
            @Result(column = "MAX_PRESS",property ="maxPress"),
            @Result(column = "POSITION_OF_MAX_PRESS",property ="positionOfMaxPress"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PressureDataEntity>  getPressureData(@Param("recordId") Long recordId);

    @Select("select * from pressure_data where is_deleted='0' and PRESSURE_HEAD_NO=#{pressureHeadNo}  order by id desc limit 0,1")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PRESSURE_HEAD_NO",property ="pressureHeadNo"),
            @Result(column = "PRODUCT_ID",property ="productId"),
            @Result(column = "PRODUCT_NO",property ="productNo"),
            @Result(column = "PRESS_RESULT",property ="pressResult"),
            @Result(column = "RECORD_ID",property ="recordId"),
            @Result(column = "START_DATE",property ="startDate"),
            @Result(column = "END_DATE",property ="endDate"),
            @Result(column = "MAX_PRESS",property ="maxPress"),
            @Result(column = "POSITION_OF_MAX_PRESS",property ="positionOfMaxPress"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    PressureDataEntity  getDataByHeadNo(@Param("pressureHeadNo") int pressureHeadNo);


    @Select({"<script>",
            "select * from pressure_data where IS_DELETED='0'",
            "<if test='startDate!=null' >",
            "and END_DATE &gt;= #{startDate}",
            "</if>",
            "<if test='endDate!=null' >",
            "and END_DATE &lt;= #{endDate}",
            "</if>",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PRESSURE_HEAD_NO",property ="pressureHeadNo"),
            @Result(column = "PRODUCT_ID",property ="productId"),
            @Result(column = "PRODUCT_NO",property ="productNo"),
            @Result(column = "PRESS_RESULT",property ="pressResult"),
            @Result(column = "RECORD_ID",property ="recordId"),
            @Result(column = "START_DATE",property ="startDate"),
            @Result(column = "END_DATE",property ="endDate"),
            @Result(column = "MAX_PRESS",property ="maxPress"),
            @Result(column = "POSITION_OF_MAX_PRESS",property ="positionOfMaxPress"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PressureDataEntity>  getPressureDataByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Select("select PRESS_RESULT, count(1) as COUNT from pressure_data where PRESSURE_HEAD_NO = '1' and is_deleted='0' and END_DATE between #{startDate} and #{endDate} group by PRESS_RESULT")
    List<HashMap> getPressureStatisticalData(@Param("startDate") BigDecimal startDate, @Param("endDate") BigDecimal endDate);

    @Insert({"insert into pressure_data(id, PRESSURE_HEAD_NO, PRODUCT_ID, PRODUCT_NO, PRESS_RESULT, RECORD_ID, START_DATE, END_DATE, MAX_PRESS, POSITION_OF_MAX_PRESS, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{pressureHeadNo}, #{productId}, #{productNo}, #{pressResult}, #{recordId},#{startDate},#{endDate},#{maxPress},#{positionOfMaxPress},'0', #{createBy}, #{createBy}, now(), now())"})
    void insert(PressureDataEntity entity);

//    @Update({"update pressure_data set PROGRAM_TYPE=#{programType}, PROGRAM_VALUE=#{programValue}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
//    void update(PressureProgramEntity entity);



    @Update({"update pressure_data set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(PressureDataEntity entity);
}
