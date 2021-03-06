package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureDataDao;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.PressureStatisticalDataEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class PressureDataService extends AbstractBaseService{
    @Resource
    PressureDataDao pressureDataDao;

    /**
     * 获取产品压装结果信息
     * @param productId
     * @return
     */
    public List<PressureDataEntity> getPressureData(Long productId) {
        return pressureDataDao.getPressureData(productId);
    }


    /**
     * 获取产品压装结果统计信息
     * @param
     * @return
     */
    public PressureStatisticalDataEntity getPressureStatisticalData() {
        return pressureDataDao.getPressureStatisticalData();
    }

    public void insert(PressureDataEntity entity) {
        pressureDataDao.insert(entity);
    }

//    public void update(PressureProgramEntity entity) {
//        pressureDataDaoDao.update(entity);
//    }

    public void delete(PressureDataEntity entity) {
        pressureDataDao.delete(entity);
    }


}
