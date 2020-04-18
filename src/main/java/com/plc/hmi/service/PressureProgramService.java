package com.plc.hmi.service;

import com.plc.hmi.dal.dao.PressureProgramDao;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureProgramEntity;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
public class PressureProgramService extends AbstractBaseService{
    private static Logger logger = LoggerFactory.getLogger(PressureProgramService.class);
    @Resource
    PressureProgramDao pressureProgramDao;

    private static Long refreshTimeMillion = 0L;

    //所有公差窗口数据缓存
    private static Map<Long, PressureProgramEntity> programMap = new HashMap<Long, PressureProgramEntity>();

    public List<PressureProgramEntity> getWithProductId(Long productId) {
        return pressureProgramDao.getWithProductId(productId);
    }

    /**
     * 获取在界面chart中画公差窗口所需数据
     * 这里将PressureProgramEntity对象转化成了PressureCurveEntity对象， 用户chart paint时统一渲染规则
     * @param productId
     * @return
     */
    public List<List<PressureCurveEntity>> getDataforChart(Long productId) {
        boolean refresh =false;
        if(refreshTimeMillion > 0) {
            //5分钟从数据库获取一次
            refresh = System.currentTimeMillis() - refreshTimeMillion >= 1000*5*60 ? true : false;
        } else {
            refresh = false;
        }

        if(refresh) {
            programMap = pressureProgramDao.getAllDatas();
            refreshTimeMillion = System.currentTimeMillis();
        }
        return toChartData(this.programMap.get(productId));
    }

    /**
     * 将PressureProgramEntity对象转化成了PressureCurveEntity对象
     * @param pressureProgramEntity
     * @return
     */
    private List<List<PressureCurveEntity>> toChartData(PressureProgramEntity pressureProgramEntity) {
        List<List<PressureCurveEntity>> curveList = new ArrayList<>();
        if(pressureProgramEntity.getErrandType1()>=0) {
            this.setErrandLineDate(curveList, pressureProgramEntity.getErrandType1(),
                    pressureProgramEntity.getPositionMin1(), pressureProgramEntity.getPositionMax1(),
                    pressureProgramEntity.getPressMin1(), pressureProgramEntity.getPressMax1());
        } else  if(pressureProgramEntity.getErrandType2()>=0) {
            this.setErrandLineDate(curveList, pressureProgramEntity.getErrandType2(),
                    pressureProgramEntity.getPositionMin2(), pressureProgramEntity.getPositionMax2(),
                    pressureProgramEntity.getPressMin2(), pressureProgramEntity.getPressMax2());
        } else  if(pressureProgramEntity.getErrandType3()>=0) {
            this.setErrandLineDate(curveList, pressureProgramEntity.getErrandType3(),
                    pressureProgramEntity.getPositionMin3(), pressureProgramEntity.getPositionMax3(),
                    pressureProgramEntity.getPressMin3(), pressureProgramEntity.getPressMax3());
        } else  if(pressureProgramEntity.getErrandType4()>=0) {
            this.setErrandLineDate(curveList, pressureProgramEntity.getErrandType4(),
                    pressureProgramEntity.getPositionMin4(), pressureProgramEntity.getPositionMax4(),
                    pressureProgramEntity.getPressMin4(), pressureProgramEntity.getPressMax4());
        } else  if(pressureProgramEntity.getErrandType5()>=0) {
            this.setErrandLineDate(curveList, pressureProgramEntity.getErrandType5(),
                    pressureProgramEntity.getPositionMin5(), pressureProgramEntity.getPositionMax5(),
                    pressureProgramEntity.getPressMin5(), pressureProgramEntity.getPressMax5());
        }
        return curveList;
    }

    private void setErrandLineDate( List<List<PressureCurveEntity>> curveList, int errandType1,
                                   BigDecimal positionMin1, BigDecimal positionMax1,
                                   BigDecimal pressMin1, BigDecimal pressMax1) {
        List<PressureCurveEntity> list = new ArrayList<PressureCurveEntity>();
//        PressureCurveEntity DottedLine1 = new PressureCurveEntity();
//        PressureCurveEntity solidLine1 = new PressureCurveEntity();
//        PressureCurveEntity DottedLine2 = new PressureCurveEntity();
//        PressureCurveEntity solidLine2 = new PressureCurveEntity();
//        list.add(DottedLine1);
//        list.add(solidLine1);
//        list.add(DottedLine2);
//        list.add(solidLine2);
        switch(errandType1) {
            case 0 :
                //最大位移窗口，两条线


                break;
            case 1 :
                //最大压力窗口，两条线

                break;
            case 2 :
                //配合窗口， 四条线
                break;
            case 3 :
                //右-下限制窗口， 四条线
                break;
            case 4 :
                //穿越窗口， 四条线
                break;
            case 5 :
                //峰值窗口，四条线
                break;
            case 6 :
                ///左-上限制窗口， 四条线
                break;
            case 7 :
                ///右-上限制窗口， 四条线
                break;
            case 8 :
                //顶部结束窗口， 四条线
                break;
            case 9 :
                //右侧结束窗口， 四条线
                break;
            case 10 :
                //平均值窗口， 五条线
                break;
            case 11 :
                //拐点窗口，六条线
                break;
        }
    }


    public void save(PressureProgramEntity entity) {
//    如果这个产品数据不存在就做insert， 如果有，就做update
        if(null ==entity.getProductId()) {
            logger.info("productId is null! exist.");
            return;
        }

        if(null == entity.getId()) {
            List<PressureProgramEntity>  entityList = getWithProductId(entity.getProductId());
            if(CollectionUtils.isEmpty(entityList)) {
                insert(entity);
            } else {
                entity.setId(entityList.get(0).getId());
                update(entity);
            }
        } else {
            update(entity);
        }

    }
    public void insert(PressureProgramEntity entity) {
        pressureProgramDao.insert(entity);
    }

    public void update(PressureProgramEntity entity) {
        pressureProgramDao.update(entity);
    }

    public void delete(PressureProgramEntity entity) {
        pressureProgramDao.delete(entity);
    }


}
