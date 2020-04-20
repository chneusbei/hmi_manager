package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureCurveDao;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.util.HmiUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 压力曲线服务
 */
@Service
@Component
public class PressureCurveService extends AbstractBaseService{
    @Resource
    PressureCurveDao pressureCurveDao;
    @Resource
    PressureDataService pressureDataService;

    //用户存储实时PLC曲线信息。线程安全的链表容器
    private static java.util.concurrent.ConcurrentLinkedDeque<PressureCurveEntity>
            synCurveDeque =  new java.util.concurrent.ConcurrentLinkedDeque<PressureCurveEntity>();;

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

    /**
     * 批量入库
     * @param entityList
     */
    public void batchInsert(List<PressureCurveEntity> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            return;
        }
        //需要对应插入一条数据到pressure_data, 同时建立pressure_data和pressure_curve的关联关系
        /**
         *  1 插入pressure_data
         */
        InsertPressureDate(entityList);
        /**
         * 1 插入pressure_curve
         */
        pressureCurveDao.batchInsert(entityList);
    }

    private void InsertPressureDate(List<PressureCurveEntity> entityList) {
        //生成 RECORD_ID， 一个产品一次压装对应一个RECORD_ID,一个产品可以进行多次压装
        //RECORD_ID生成规则，产品ID+当前时间戳
        Long productId = entityList.get(0).getProductId();
        StringBuffer recordIdBuffer = new StringBuffer();
        recordIdBuffer.append(productId);
        recordIdBuffer.append(HmiUtils.getFormatDateString());
        String recordId = recordIdBuffer.toString();
        //获取最大压力及最大压力时位移。
        PressureCurveEntity MaxPresscurve = entityList.get(0);
        for(PressureCurveEntity curveEntity : entityList) {
            curveEntity.setRecordId(recordId);
            MaxPresscurve=MaxPresscurve.getPressForce().compareTo(curveEntity.getPressForce()) < 0 ? curveEntity : MaxPresscurve;
        }
        PressureDataEntity pressureDataEntity = new PressureDataEntity();
        pressureDataEntity.setProductId(productId);
        pressureDataEntity.setRecordId(recordId);
        pressureDataEntity.setPressResult("1");//需要从PLC曲线信息中的OK/NOk 获取？ 还是从公差窗口计算
        pressureDataEntity.setMaxPress(MaxPresscurve.getPressForce());
        pressureDataEntity.setPositionOfMaxPress(MaxPresscurve.getPosition());
        pressureDataEntity.setStartDate(new BigDecimal(HmiUtils.getMillFormatDateString(entityList.get(0).getCreateTime())));
        pressureDataEntity.setEndDate(new BigDecimal(HmiUtils.getMillFormatDateString(entityList.get(entityList.size()-1).getCreateTime())));
        pressureDataEntity.setCreateBy("SYS");
        pressureDataEntity.setUpdateBy("SYS");
        pressureDataEntity.setCreateTime(new Date());
        pressureDataEntity.setUpdateTime(new Date());
        pressureDataService.insert(pressureDataEntity);
    }

    /**
     * 实时数据加入入库队列
     * @param entityList
     */
    public void curve2queue(List<PressureCurveEntity> entityList) {
        synCurveDeque.addAll(entityList);
        System.out.println("add data to queue  size :" + entityList.size());
    }


    /**
     * 自动批量入库
     */
    public void autoBatchInsert() {
        System.out.println("批量插入操作。。。。。。。。。。。。。。。。。。。。。 sie = " + synCurveDeque.size());
        if(!synCurveDeque.isEmpty()) {
            List<PressureCurveEntity> entityList = new ArrayList<PressureCurveEntity>();
            while(!synCurveDeque.isEmpty()) {
                entityList.add(synCurveDeque.removeFirst());
                if(entityList.size() ==50 ||synCurveDeque.size() ==0) {
                    this.batchInsert(entityList);
                    entityList.clear();
                }
            }
        }
    }


}
