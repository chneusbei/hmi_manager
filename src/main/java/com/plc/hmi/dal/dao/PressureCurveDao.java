package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.mapper.PressureCurveMapper;
import com.plc.hmi.enumeration.PressureCurveEntityEnum;
import com.plc.hmi.enumeration.PressureDataEntityEnum;
import com.plc.hmi.service.PressureDataService;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class PressureCurveDao{
    @Resource
    PressureCurveMapper pressureCurveMapper;



    public List<PressureCurveEntity> getCurveData(Long recordId) {
        List<PressureCurveEntity> list = pressureCurveMapper.getCurveData(recordId);
//        if(!CollectionUtils.isEmpty(list)) {
//            for(PressureCurveEntity entity : list) {
//                if(entity.getShortPressDate().compareTo(new BigDecimal(xStart)) == -1) {
//                  list.remove(entity);
//                }
//            }
//        }

       return list;
    }

//    public List<PressureCurveEntity> getPressureCurveWithDate(String handleDate) {
//        return this.getEntityList(pressureCurveMapper.getPressureCurveWithDate(handleDate));
//    }
//
//    public List<PressureCurveEntity> getPressureCurveWithDateStart(String handleDate) {
//        return this.getEntityList(pressureCurveMapper.getPressureCurveWithDateStart(handleDate));
//    }




    private void setDefaultValue(List<PressureCurveEntity> list ) {
        if(!CollectionUtils.isEmpty(list)) {
            for(PressureCurveEntity entity : list) {
                entity.setErrant(false);
                entity.setSolidLine(true);
            }
        }

        /*
        String dateString = entity.getPressDate().toString();
        if(null != dateString) {
            //20200409232605003
            String shortString = dateString.substring(10, dateString.length());
            entity.setShortPressDate(new BigDecimal(shortString));
        }  else {
            entity.setShortPressDate(new BigDecimal(0));
        }
        */
    }

    public void insert(PressureCurveEntity entity) {
        pressureCurveMapper.insert(entity);
    }

    public void batchInsert(List<PressureCurveEntity> entityList) {
        pressureCurveMapper.batchInsert(entityList);
    }

}
