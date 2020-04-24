package com.plc.hmi.dal.dao;


import com.plc.hmi.dal.entity.TagTypeEntity;
import com.plc.hmi.dal.mapper.TagTypeMapper;
import com.plc.hmi.enumeration.TagTypeEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class TagTypeDao {
    @Resource
    TagTypeMapper tagTypeMapper;

    public List<TagTypeEntity> getTagType() {
        return  tagTypeMapper.getTagType();
    }

    public void insert(TagTypeEntity entity) {
        tagTypeMapper.insert(entity);
    }

    public void delete(TagTypeEntity entity) {
        tagTypeMapper.delete(entity);
    }
}
