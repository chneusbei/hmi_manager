package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TagsInfoEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TagsInfoMapper {
    @Select("select * from TAGS_INFO where is_deleted='0'")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "TAG_NAME",property ="tagName"),
            @Result(column = "TAG_EN_NAME",property ="tagEnName"),
            @Result(column = "TAG_TYPE_ID",property ="tagTypeId"),
            @Result(column = "TAG_TYPE_DES",property ="tagTypeDes"),
            @Result(column = "TAG_AREA",property ="tagArea"),
            @Result(column = "TAG_AREA_NAME",property ="tagAreaName"),
            @Result(column = "DB_NO",property ="dbNo"),
            @Result(column = "ADDRESS",property ="address"),
            @Result(column = "TAG_BIT",property ="tagBit"),
            @Result(column = "TAG_GROUP",property ="tagGroup"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<TagsInfoEntity> getTagsInfo();

    @Select("select * from TAGS_INFO where is_deleted='0' AND TAG_GROUP=#{tagGroup}")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "TAG_NAME",property ="tagName"),
            @Result(column = "TAG_EN_NAME",property ="tagEnName"),
            @Result(column = "TAG_TYPE_ID",property ="tagTypeId"),
            @Result(column = "TAG_TYPE_DES",property ="tagTypeDes"),
            @Result(column = "TAG_AREA",property ="tagArea"),
            @Result(column = "TAG_AREA_NAME",property ="tagAreaName"),
            @Result(column = "DB_NO",property ="dbNo"),
            @Result(column = "ADDRESS",property ="address"),
            @Result(column = "TAG_BIT",property ="tagBit"),
            @Result(column = "TAG_GROUP",property ="tagGroup"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<TagsInfoEntity> getTagsInfoByGroup(String tagGroup);

    @Select("select * from TAGS_INFO where is_deleted='0' AND TAG_GROUP=#{tagGroup} and TAG_EN_NAME=#{tagEnName}")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "TAG_NAME",property ="tagName"),
            @Result(column = "TAG_EN_NAME",property ="tagEnName"),
            @Result(column = "TAG_TYPE_ID",property ="tagTypeId"),
            @Result(column = "TAG_TYPE_DES",property ="tagTypeDes"),
            @Result(column = "TAG_AREA",property ="tagArea"),
            @Result(column = "TAG_AREA_NAME",property ="tagAreaName"),
            @Result(column = "DB_NO",property ="dbNo"),
            @Result(column = "ADDRESS",property ="address"),
            @Result(column = "TAG_BIT",property ="tagBit"),
            @Result(column = "TAG_GROUP",property ="tagGroup"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    TagsInfoEntity getTagsInfoByGroupAndName(String tagGroup, String tagEnName);

    @Insert({"insert into TAGS_INFO(id, TAG_NAME, TAG_EN_NAME, TAG_TYPE_ID, TAG_TYPE_DES, TAG_AREA, TAG_AREA_NAME, DB_NO, ADDRESS, TAG_BIT, TAG_GROUP,IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{tagName}, #{tagEnName}, #{tagTypeId}, #{tagTypeDes}, #{tagArea}, #{tagAreaName}, #{dbNo}, #{adderss}, #{tagBit}, #{tagGroup}, '0', #{createBy}, #{createBy}, now(), now())"})
    void insert(TagsInfoEntity entity);

    @Update({"update TAGS_INFO set  TAG_NAME=#{tagName}, TAG_EN_NAME=#{tagEnName}, TAG_TYPE_ID= #{tagTypeId}, TAG_TYPE_DES=#{tagTypeDes}, TAG_AREA=#{tagArea}, TAG_AREA_NAME=#{tagAreaName}, DB_NO=#{dbNo}, ADDRESS=#{adderss}, TAG_BIT= #{tagBit}, TAG_GROUP=#{tagGroup}, UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void update(TagsInfoEntity entity);

    @Update({"update TAGS_INFO set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(TagsInfoEntity entity);
}
