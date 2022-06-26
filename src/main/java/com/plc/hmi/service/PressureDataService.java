package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureDataDao;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.PressureStatisticalDataEntity;
import com.plc.hmi.util.HmiUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Component
public class PressureDataService extends AbstractBaseService{
    @Resource
    PressureDataDao pressureDataDao;

    /**
     * 获取产品压装结果信息
     * @param recordId
     * @return
     */
    public List<PressureDataEntity> getPressureData(Long recordId) {
        return pressureDataDao.getPressureData(recordId);
    }

    /**
     * 获取产品压装结果信息
     * @param startDate，endDate
     * @return
     */
    public List<PressureDataEntity> getPressureData(String startDate, String endDate) {
        return pressureDataDao.getPressureData(startDate, endDate);
    }

    /**
     * 获取产品压装失败信息列表
     * @param startDate，endDate
     * @return
     */
    public List<PressureDataEntity> getPressureDataWithStatus(String startDate, String endDate, String pressResult) {
        return pressureDataDao.getPressureData(startDate, endDate, pressResult);
    }

    /**
     * 获取最近产品压装结果信息
     * @param
     * @return
     */
    public PressureDataEntity getDataByHeadNo(int pressureHeadNo) {
        return pressureDataDao.getDataByHeadNo(pressureHeadNo);
    }




    /**
     * 获取产品压装结果统计信息
     * @param
     * @return
     */
    public PressureStatisticalDataEntity getPressureStatisticalData(int time,  BigDecimal defaultFailNum) {
        //根据当前时间点获取工时段， 早八点晚上八点第一班， 晚八点到早八点第二班
        Date currencyDay = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date preDay = calendar.getTime();
        String dayString = HmiUtils.getYYYYMMDDString(currencyDay);
        String preDayString = HmiUtils.getYYYYMMDDString(preDay);
        String millString = HmiUtils.getMillFormatDateString(currencyDay); //YYYYMMDDHHMMSSSSS;
        String hourString = millString.substring(8,10);
//        int hour = Integer.valueOf(hourString).intValue();
        String startDate = null;
        String endDate = null;
        if(time == 2) {
            //第二班 晚八点到早八点第二班
            startDate = preDayString + "200000000"; //20210622 21 47 06709
            endDate = dayString + "080000000";
        } else if(time == 1) {
            //第一班 早八点晚上八点
            startDate = dayString + "080000000";
            endDate = dayString + "200000000";
        } else {
            startDate = dayString + "000000000";
            endDate = dayString + "235959999";
        }
        //时间段 0-8  8-16 16-24
        PressureStatisticalDataEntity pressureStatisticalDataEntity =  pressureDataDao.getPressureStatisticalData(new BigDecimal(startDate), new BigDecimal(endDate));
        //如果设置了失败默认值，并且实际失败个数大于失败默认值， 返回默认值
        if(pressureStatisticalDataEntity.getFailAmount().compareTo(defaultFailNum) > 0 && defaultFailNum.intValue() > 0) {
            pressureStatisticalDataEntity.setFailAmount(defaultFailNum);
            pressureStatisticalDataEntity.setSuccessAmount(pressureStatisticalDataEntity.getTotalAmount().subtract(defaultFailNum));
        }
        //计算百分比
        pressureStatisticalDataEntity.setSuccessPercent(HmiUtils.getPercentString(pressureStatisticalDataEntity.getSuccessAmount(), pressureStatisticalDataEntity.getTotalAmount()));
        return pressureStatisticalDataEntity;
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
