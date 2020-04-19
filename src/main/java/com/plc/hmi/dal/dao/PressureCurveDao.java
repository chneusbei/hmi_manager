package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.mapper.PressureCurveMapper;
import com.plc.hmi.enumeration.PressureCurveEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class PressureCurveDao extends AbstractHmiBaseDao {
    @Resource
    PressureCurveMapper pressureCurveMapper;

    public List<PressureCurveEntity> getCurveData(Long pressDataId, Long xStart) {
        List<PressureCurveEntity> list = this.getEntityList(pressureCurveMapper.getCurveData(pressDataId));
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



    @Override
    protected PressureCurveEntity getEntity(HashMap map) {
        PressureCurveEntity entity = new PressureCurveEntity();
        super.setEntityBase(entity, map);
        entity.setPressDataId(HmiUtils.getLongValue(map.get(PressureCurveEntityEnum.PRESS_DATA_ID.getCode())));
        entity.setPosition(HmiUtils.getBigDicimal(map.get(PressureCurveEntityEnum.POSITION.getCode())));
        entity.setPressForce(HmiUtils.getBigDicimal(map.get(PressureCurveEntityEnum.PRESS_FORCE.getCode())));
        entity.setRecordNo(HmiUtils.getIntValue(map.get(PressureCurveEntityEnum.RECORD_NO.getCode())));
        entity.setHandleDate(HmiUtils.getString(map.get(PressureCurveEntityEnum.HANDLE_DATE.getCode())));
        entity.setPressDate(HmiUtils.getBigDicimal(map.get(PressureCurveEntityEnum.PRESS_DATE.getCode())));
        entity.setCurSpeed(HmiUtils.getBigDicimal(map.get(PressureCurveEntityEnum.PRESS_DATE.getCode())));
        entity.setErrant(false);
        entity.setSolidLine(true);
        String dateString = entity.getPressDate().toString();
        if(null != dateString) {
            //20200409232605003
            String shortString = dateString.substring(10, dateString.length());
            entity.setShortPressDate(new BigDecimal(shortString));
        }  else {
            entity.setShortPressDate(new BigDecimal(0));
        }

        return entity;
    }

    public void insert(PressureCurveEntity entity) {
        pressureCurveMapper.insert(entity);
    }

    public void batchInsert(List<PressureCurveEntity> entityList) {
        pressureCurveMapper.batchInsert(entityList);
    }

}
