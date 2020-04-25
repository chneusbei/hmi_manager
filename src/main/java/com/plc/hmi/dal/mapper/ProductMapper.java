package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.ProductEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product_info where is_deleted='0' and PRODUCT_CODE=#{productCode}")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PRODUCT_CODE",property ="productCode"),
            @Result(column = "PRODUCT_TYPE",property ="productType"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    ProductEntity getProduct(String productCode);

    @Insert({"insert into product_info(id, PRODUCT_CODE, PRODUCT_TYPE,  IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{productCode}, #{productType}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(ProductEntity entity);

    @Update({"update product_info set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(ProductEntity entity);
}
