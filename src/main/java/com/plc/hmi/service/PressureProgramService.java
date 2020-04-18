package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureProgramDao;
import com.plc.hmi.dal.entity.PressureProgramEntity;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class PressureProgramService extends AbstractBaseService{
    private static Logger logger = LoggerFactory.getLogger(PressureProgramService.class);
    @Resource
    PressureProgramDao pressureProgramDao;

    //所有公差窗口数据缓存
    private static Map<Long, PressureProgramEntity> programMap = new HashMap<Long, PressureProgramEntity>();

    public List<PressureProgramEntity> getWithProductId(Long productId) {
        return pressureProgramDao.getWithProductId(productId);
    }

    public Map<Long, List<PressureProgramEntity>> getAllDatas() {
        return pressureProgramDao.getAllDatas();
    }

    public void save(PressureProgramEntity entity) {
//    如果这个产品数据不存在就做insert， 如果有，就做update
        if(null ==entity.getProductId()) {
            logger.info("productId is null! exist.");
            return;
        }

        if(null == entity.getId()) {
            List<PressureProgramEntity>  entityList = getWithProductId(entity.getProductId());
            if(CollectionUtils.isEmpty(entityList)) {
                insert(entity);
            } else {
                entity.setId(entityList.get(0).getId());
                update(entity);
            }
        } else {
            update(entity);
        }

    }
    public void insert(PressureProgramEntity entity) {
        pressureProgramDao.insert(entity);
    }

    public void update(PressureProgramEntity entity) {
        pressureProgramDao.update(entity);
    }

    public void delete(PressureProgramEntity entity) {
        pressureProgramDao.delete(entity);
    }


}
