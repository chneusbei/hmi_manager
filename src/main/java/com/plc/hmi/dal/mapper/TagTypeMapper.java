package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TagTypeEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TagTypeMapper {
    @Select("select * from tag_type where is_deleted='0'")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column ="DATA_TYPE",property = "dataType"),
            @Result(column ="DATA_LENGTH",property = "dataLength"),
            @Result(column ="CREATE_BY",property = "createBy"),
            @Result(column ="UPDATE_BY",property = "updateBy"),
            @Result(column ="CREATE_TIME",property = "createTime"),
            @Result(column ="UPDATE_TIME",property = "updateTime"),
            @Result(column ="IS_DELETED",property = "isDeleted"),
    })
    List<TagTypeEntity> getTagType();

    @Insert({"insert into tag_type(id, DATA_TYPE, DATA_LENGTH, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{dataType}, #{dataLength},  '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(TagTypeEntity entity);

    @Update({"update tag_type set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(TagTypeEntity entity);
}
