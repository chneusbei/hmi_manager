package com.plc.hmi.service;


import com.plc.hmi.dal.dao.TagTypeDao;
import com.plc.hmi.dal.entity.TagTypeEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class TagTypeService extends AbstractBaseService{
    @Resource
    TagTypeDao tagTypeDao;

    public List<TagTypeEntity> getTagType() {
        return tagTypeDao.getTagType();
    }

    public void insert(TagTypeEntity entity) {
        tagTypeDao.insert(entity);
    }

    public void delete(TagTypeEntity entity) {
        tagTypeDao.delete(entity);
    }
}
