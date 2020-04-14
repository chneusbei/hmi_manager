package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureCurveDao;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 压力曲线服务
 */
@Service
@Component
public class PressureCurveService extends AbstractBaseService{
    @Resource
    PressureCurveDao pressureCurveDao;

    /**
     * 获取产品压力曲线信息
     * @param pressDataId
     * @return
     */
    public List<PressureCurveEntity> getHisDateByCode(Long  pressDataId, Long xStart) {
        return pressureCurveDao.getCurveData(pressDataId, xStart);
    }

//    public List<PressureCurveEntity> getPressureCurveWithDate(String handleDate) {
//        return pressureCurveDao.getPressureCurveWithDate(handleDate);
//    }
//
//    public List<PressureCurveEntity> getPressureCurveWithDateStart(String handleDate) {
//        return pressureCurveDao.getPressureCurveWithDateStart(handleDate);
//    }

    public void insertList(List<PressureCurveEntity> entityList) {
        if(entityList != null) {
            for (PressureCurveEntity entity : entityList) {
                this.insert(entity);
            }
        }
    }

    public void insert(PressureCurveEntity entity) {
        pressureCurveDao.insert(entity);
    }

    public void batchInsert(List<PressureCurveEntity> entityList) {
        pressureCurveDao.batchInsert(entityList);
    }
}
