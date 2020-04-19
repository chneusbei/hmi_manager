package com.plc.hmi.dal.dao;


import com.plc.hmi.dal.entity.TagsInfoEntity;
import com.plc.hmi.dal.mapper.TagsInfoMapper;
import com.plc.hmi.enumeration.TagsInfoEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class TagsInfoDao extends AbstractHmiBaseDao {
    @Resource
    TagsInfoMapper tagsInfoMapper;

    public List<TagsInfoEntity> getTags() {
        return this.getEntityList(tagsInfoMapper.getTagsInfo());
    }

    public List<TagsInfoEntity> getTagsByGroup(String tagGroup) {
        return this.getEntityList(tagsInfoMapper.getTagsInfoByGroup(tagGroup));
    }

    public List<TagsInfoEntity> getTagsByGroupAndName(String tagGroup, String tagEnName) {
        return this.getEntityList(tagsInfoMapper.getTagsInfoByGroupAndName(tagGroup, tagEnName));
    }



    @Override
    protected TagsInfoEntity getEntity(HashMap map) {
        TagsInfoEntity entity = new TagsInfoEntity();
        super.setEntityBase(entity, map);
        entity.setTagName(HmiUtils.getString(map.get(TagsInfoEntityEnum.TAG_NAME.getCode())));
        entity.setTagEnName(HmiUtils.getString(map.get(TagsInfoEntityEnum.TAG_EN_NAME.getCode())));
        entity.setTagTypeId(HmiUtils.getIntValue(map.get(TagsInfoEntityEnum.TAG_TYPE_ID.getCode())));
        entity.setTagTypeDes(HmiUtils.getString(map.get(TagsInfoEntityEnum.TAG_TYPE_DES.getCode())));
        entity.setTagArea(HmiUtils.getString(map.get(TagsInfoEntityEnum.TAG_AREA.getCode())));
        entity.setTagAreaName(HmiUtils.getString(map.get(TagsInfoEntityEnum.TAG_AREA_NAME.getCode())));
        entity.setDbNo(HmiUtils.getIntValue(map.get(TagsInfoEntityEnum.DB_NO.getCode())));
        entity.setAdderss(HmiUtils.getIntValue(map.get(TagsInfoEntityEnum.ADDRESS.getCode())));
        entity.setTagBit(HmiUtils.getIntValue(map.get(TagsInfoEntityEnum.TAG_BIT.getCode())));
        entity.setTagGroup(HmiUtils.getString(map.get(TagsInfoEntityEnum.TAG_GROUP.getCode())));

        return entity;
    }

    public void insert(TagsInfoEntity entity) {
        tagsInfoMapper.insert(entity);
    }

    public void update(TagsInfoEntity entity) {
        tagsInfoMapper.update(entity);
    }


    public void delete(TagsInfoEntity entity) {
        tagsInfoMapper.delete(entity);
    }


}
