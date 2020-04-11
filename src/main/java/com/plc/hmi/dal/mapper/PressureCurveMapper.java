package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PropertyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface PressureCurveMapper {
    @Select("select * from pressure_curve where is_deleted='0' and PRESS_DATA_ID=#{pressDataId}")
    List<HashMap> getCurveData(@Param("pressDataId") Long pressDataId);

//    @Select("select * from pressure_curve where is_deleted='0' and HANDLE_DATE=#{handleDate}")
//    List<HashMap> getPressureCurveWithDate(@Param("handleDate") String handleDate);

//    @Select("select * from pressure_curve where is_deleted='0' and HANDLE_DATE>=#{handleDate}")
//    List<HashMap> getPressureCurveWithDateStart(@Param("handleDate") String handleDate);

    @Insert({"insert into pressure_curve (ID, PRESS_DATA_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_PRESS, HANDLE_DATE,PRESS_DATE, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{pressDataId}, #{recordNo}, #{position}, #{pressForce}, #{curSpeed}, #{handleDate}, #{pressDate}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(PressureCurveEntity entity);

}
