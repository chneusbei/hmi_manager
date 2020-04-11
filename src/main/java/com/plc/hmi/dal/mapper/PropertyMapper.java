package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.PropertyEntity;
import org.apache.ibatis.annotations.*;
import java.util.HashMap;
import java.util.List;
@Mapper
public interface PropertyMapper{
    @Select("select * from property_config where is_deleted='0'")
    List<HashMap> getPropertyConfig();

    @Select("select * from property_config where is_deleted='0' and prop_group=#{prop_group}")
    List<HashMap> getPropertyWithGroup(@Param("prop_group") String group);

    @Insert({"insert into property_config(id, PROP_NAME, PROP_VALUE, PROP_GROUP, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{propName}, #{propValue}, #{propGroup}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(PropertyEntity entity);

    @Update({"update property_config set PROP_VALUE=#{propValue}, PROP_GROUP = #{propGroup}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void update(PropertyEntity entity);

    @Update({"update property_config set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(PropertyEntity entity);
}
