package com.plc.hmi.dal.dao;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.PressureStatisticalDataEntity;
import com.plc.hmi.dal.mapper.PressureDataMapper;
import com.plc.hmi.enumeration.PressureDataEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class PressureDataDao {
    @Resource
    PressureDataMapper pressureDataMapper;

    public List<PressureDataEntity> getPressureData(Long recordId) {
        return pressureDataMapper.getPressureData(recordId);
    }

    public List<PressureDataEntity> get2RecentData() {
        return pressureDataMapper.get2RecentData();
    }

    public List<PressureDataEntity> getPressureData(String startDate, String endDate) {
        return pressureDataMapper.getPressureDataByDate(startDate, endDate);
    }



    public PressureStatisticalDataEntity getPressureStatisticalData() {
        List<HashMap> resList = pressureDataMapper.getPressureStatisticalData();
        PressureStatisticalDataEntity entity = new PressureStatisticalDataEntity();
        String pressResult = null;
        entity.setSuccessAmount(new BigDecimal(0));
        entity.setFailAmount(new BigDecimal(0));
        if(!CollectionUtils.isEmpty(resList)) {
            for(HashMap map : resList) {
               pressResult = HmiUtils.getString(map.get(PressureDataEntityEnum.PRESS_RESULT.getCode()));
               if(HmiConstants.OK.equalsIgnoreCase(pressResult)) {
                   entity.setSuccessAmount(HmiUtils.getBigDicimal(map.get(PressureDataEntityEnum.COUNT.getCode())));
               } else if(HmiConstants.NOK.equalsIgnoreCase(pressResult)) {
                   entity.setFailAmount(HmiUtils.getBigDicimal(map.get(PressureDataEntityEnum.COUNT.getCode())));
               }
            }
        }
        entity.setTotalAmount(entity.getSuccessAmount().add(entity.getFailAmount()));
        return entity;
    }

    public void insert(PressureDataEntity entity) {
        pressureDataMapper.insert(entity);
    }

//    public void update(PressureDataEntity entity) {
//        pressureDataMapper.update(entity);
//    }

    public void delete(PressureDataEntity entity) {
        pressureDataMapper.delete(entity);
    }



}
