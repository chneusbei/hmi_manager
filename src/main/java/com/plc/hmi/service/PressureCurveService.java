package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureCurveDao;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 压力曲线服务
 */
@Service
@Component
public class PressureCurveService extends AbstractBaseService{
    @Resource
    PressureCurveDao pressureCurveDao;

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
        pressureCurveDao.batchInsert(entityList);
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
