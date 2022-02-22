package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.PropertyEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Mapper
public interface PropertyMapper{
    @Transactional
    @Select("select * from property_config where is_deleted='0'")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PROP_NAME",property ="propName"),
            @Result(column = "PROP_VALUE",property ="propValue"),
            @Result(column = "PROP_GROUP",property ="propGroup"),
            @Result(column = "DESCRIPTION",property ="description"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PropertyEntity> getPropertyConfig();

    @Transactional
    @Select("select * from property_config where is_deleted='0' and prop_group=#{prop_group}")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PROP_NAME",property ="propName"),
            @Result(column = "PROP_VALUE",property ="propValue"),
            @Result(column = "PROP_GROUP",property ="propGroup"),
            @Result(column = "DESCRIPTION",property ="description"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PropertyEntity> getPropertyWithGroup(@Param("prop_group") String group);

    @Insert({"insert into property_config(id, PROP_NAME, PROP_VALUE, PROP_GROUP, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{propName}, #{propValue}, #{propGroup}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(PropertyEntity entity);

    @Update({"update property_config set PROP_VALUE=#{propValue}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where PROP_NAME=#{propName}"})
    void update(PropertyEntity entity);

    @Update({"update property_config set PROP_VALUE=#{propValue}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where id=#{id}"})
    void updateById(PropertyEntity entity);

    @Update({"update property_config set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(PropertyEntity entity);
}
