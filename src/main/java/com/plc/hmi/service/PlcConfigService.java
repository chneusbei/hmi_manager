package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PlcConfigDao;
import com.plc.hmi.dal.entity.PlcConfigEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统配置信息服务， 非业务表， 用于技术配置
 */
@Service
@Component
public class PlcConfigService extends AbstractBaseService{
    @Resource
    PlcConfigDao plcConfigDao;
//    public static List<PlcConfigEntity> staticPlcConfigList= new ArrayList<PlcConfigEntity>();

    public List<PlcConfigEntity> getPlcList() {
        List<PlcConfigEntity> plcList = null;
//        if(CollectionUtils.isEmpty(staticPlcConfigList)) {
            plcList = plcConfigDao.getPlcList();
//            staticPlcConfigList.addAll(plcList);
//        } else {
//            return staticPlcConfigList;
//        }

        return plcList;
    }

    public void insertPlcConfig(PlcConfigEntity entity) {
        plcConfigDao.insert(entity);
    }

    public void updatePlcConfig(PlcConfigEntity entity) {
        plcConfigDao.update(entity);
    }

    public void deletePlcConfig(PlcConfigEntity entity) {
        plcConfigDao.delete(entity);
    }
}
