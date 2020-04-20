package com.plc.hmi.dal.mapper;


import com.plc.hmi.dal.entity.PressureDataEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PressureDataMapper {
    @Select("select * from pressure_data where is_deleted='0' and PRODUCT_ID=#{productId}")
    List<HashMap> getPressureData(@Param("productId") Long productId);

    @Select("select PRESS_RESULT, count(1) as COUNT from pressure_data where is_deleted='0' group by PRESS_RESULT")
    List<HashMap> getPressureStatisticalData();

    @Insert({"insert into pressure_data(id, PRODUCT_ID, PRODUCT_NO, PRESS_RESULT, RECORD_ID, START_DATE, END_DATE, MAX_PRESS, POSITION_OF_MAX_PRESS, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{productId}, #{productNo}, #{pressResult}, #{recordId},#{startDate},#{endDate},#{maxPress},#{positionOfMaxPress},'0', #{createBy}, #{createBy}, now(), now())"})
    void insert(PressureDataEntity entity);

//    @Update({"update pressure_data set PROGRAM_TYPE=#{programType}, PROGRAM_VALUE=#{programValue}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
//    void update(PressureProgramEntity entity);



    @Update({"update pressure_data set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(PressureDataEntity entity);
}
