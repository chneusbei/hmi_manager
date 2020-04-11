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
public class TagTypeDao extends AbstractHmiBaseDao {
    @Resource
    TagTypeMapper tagTypeMapper;

    public  List<TagTypeEntity>  getTagType() {
        return  this.getEntityList(tagTypeMapper.getTagType());
    }


    @Override
    protected TagTypeEntity getEntity(HashMap map) {
        TagTypeEntity entity = new TagTypeEntity();
        super.setEntityBase(entity, map);
        entity.setDataType(HmiUtils.getString(map.get(TagTypeEntityEnum.DATA_TYPE.getCode())));
        entity.setDataLength(HmiUtils.getIntValue(map.get(TagTypeEntityEnum.DATA_LENGTH.getCode())));
        return entity;
    }

    public void insert(TagTypeEntity entity) {
        tagTypeMapper.insert(entity);
    }

    public void delete(TagTypeEntity entity) {
        tagTypeMapper.delete(entity);
    }
}
