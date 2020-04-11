package com.plc.hmi.service;

import com.plc.hmi.dal.dao.TagsInfoDao;
import com.plc.hmi.dal.entity.TagsInfoEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class TagsInfoService extends AbstractBaseService{
    @Resource
    TagsInfoDao tagsInfoDao;

    public List<TagsInfoEntity> getTags() {
        return tagsInfoDao.getTags();
    }

    public List<TagsInfoEntity> getTagsByGroup(String tagGroup) {
        return tagsInfoDao.getTagsByGroup(tagGroup);
    }

    public void insert(TagsInfoEntity entity) {
        tagsInfoDao.insert(entity);
    }

    public void update(TagsInfoEntity entity) {
        tagsInfoDao.update(entity);
    }

    public void delete(TagsInfoEntity entity) {
        tagsInfoDao.delete(entity);
    }
}
