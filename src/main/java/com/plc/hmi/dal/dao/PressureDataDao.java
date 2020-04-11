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
public class PressureDataDao extends AbstractHmiBaseDao {
    @Resource
    PressureDataMapper pressureDataMapper;

    public List<PressureDataEntity> getPressureData(Long productId) {
        return this.getEntityList(pressureDataMapper.getPressureData(productId));
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

    @Override
    protected PressureDataEntity getEntity(HashMap map) {
        PressureDataEntity entity = new PressureDataEntity();
        super.setEntityBase(entity, map);
        entity.setProductId(HmiUtils.getLongValue(map.get(PressureDataEntityEnum.PRODUCT_ID.getCode())));
        entity.setProductNo(HmiUtils.getString(map.get(PressureDataEntityEnum.RECORD_ID.getCode())));
        entity.setPressResult(HmiUtils.getString(map.get(PressureDataEntityEnum.PRESS_RESULT.getCode())));
        entity.setRecordId(HmiUtils.getLongValue(map.get(PressureDataEntityEnum.RECORD_ID.getCode())));
        entity.setStartDate(HmiUtils.getBigDicimal(map.get(PressureDataEntityEnum.START_DATE.getCode())));
        entity.setEndDate(HmiUtils.getBigDicimal(map.get(PressureDataEntityEnum.END_DATE.getCode())));
        entity.setMaxPress(HmiUtils.getBigDicimal(map.get(PressureDataEntityEnum.MAX_PRESS.getCode())));
        entity.setPositionOfMaxPress(HmiUtils.getBigDicimal(map.get(PressureDataEntityEnum.POSITION_OF_MAX_PRESS.getCode())));
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
