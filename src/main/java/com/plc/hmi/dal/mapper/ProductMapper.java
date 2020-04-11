package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.ProductEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product_info where is_deleted='0' and PRODUCT_CODE=#{productCode}")
    List<HashMap> getProduct(String productCode);

    @Insert({"insert into product_info(id, PRODUCT_CODE, PRODUCT_TYPE,  IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{productCode}, #{productType}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(ProductEntity entity);

    @Update({"update product_info set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(ProductEntity entity);
}
