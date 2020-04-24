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
public class TagsInfoDao {
    @Resource
    TagsInfoMapper tagsInfoMapper;

    public List<TagsInfoEntity> getTags() {
        return tagsInfoMapper.getTagsInfo();
    }

    public List<TagsInfoEntity> getTagsByGroup(String tagGroup) {
        return tagsInfoMapper.getTagsInfoByGroup(tagGroup);
    }

    public TagsInfoEntity getTagsByGroupAndName(String tagGroup, String tagEnName) {
        return tagsInfoMapper.getTagsInfoByGroupAndName(tagGroup, tagEnName);
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
