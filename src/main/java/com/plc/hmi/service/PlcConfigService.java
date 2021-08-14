package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PlcConfigDao;
import com.plc.hmi.dal.entity.PlcConfigEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
        List<PlcConfigEntity> plcList = plcConfigDao.getPlcList();
//        if(!CollectionUtils.isEmpty(plcList)) {
//            for(PlcConfigEntity property : plcList) {
//                staticPlcConfigList.add(property);
//            }
//        }
        return plcList;
    }
}
