package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.ProductParameterEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductParameterMapper {
    @Select("select * from product_parameter where is_deleted='0' and  PRODUCT_CODE=#{productCode}")
    List<HashMap> getProductParameterWithProductCode(@Param("productCode") String productCode);

    @Insert({"insert into product_parameter(id, PRODUCT_CODE, MAX_DISTANCE, MAX_FORCE, MAX_SPEED, DEFAULT_BACK_SPEED, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{productCode}, #{maxDistance}, #{maxForce}, #{maxSpeed}, #{defaultBackSpeed}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(ProductParameterEntity entity);

    @Update({"update product_parameter set MAX_DISTANCE=#{maxDistance}, MAX_FORCE=#{maxForce}, MAX_SPEED=#{maxSpeed},  DEFAULT_BACK_SPEED=#{defaultBackSpeed},  UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void update(ProductParameterEntity entity);

    @Update({"update product_parameter set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(ProductParameterEntity entity);
}
