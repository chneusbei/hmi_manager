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
            refresh = true;
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
            this.setErrandLineData(curveList, pressureProgramEntity.getErrandType1(),
                    pressureProgramEntity.getPositionMin1(), pressureProgramEntity.getPositionMax1(),
                    pressureProgramEntity.getPressMin1(), pressureProgramEntity.getPressMax1());
        } else  if(pressureProgramEntity.getErrandType2()>=0) {
            this.setErrandLineData(curveList, pressureProgramEntity.getErrandType2(),
                    pressureProgramEntity.getPositionMin2(), pressureProgramEntity.getPositionMax2(),
                    pressureProgramEntity.getPressMin2(), pressureProgramEntity.getPressMax2());
        } else  if(pressureProgramEntity.getErrandType3()>=0) {
            this.setErrandLineData(curveList, pressureProgramEntity.getErrandType3(),
                    pressureProgramEntity.getPositionMin3(), pressureProgramEntity.getPositionMax3(),
                    pressureProgramEntity.getPressMin3(), pressureProgramEntity.getPressMax3());
        } else  if(pressureProgramEntity.getErrandType4()>=0) {
            this.setErrandLineData(curveList, pressureProgramEntity.getErrandType4(),
                    pressureProgramEntity.getPositionMin4(), pressureProgramEntity.getPositionMax4(),
                    pressureProgramEntity.getPressMin4(), pressureProgramEntity.getPressMax4());
        } else  if(pressureProgramEntity.getErrandType5()>=0) {
            this.setErrandLineData(curveList, pressureProgramEntity.getErrandType5(),
                    pressureProgramEntity.getPositionMin5(), pressureProgramEntity.getPositionMax5(),
                    pressureProgramEntity.getPressMin5(), pressureProgramEntity.getPressMax5());
        }
        return curveList;
    }

    private void setErrandLineData(List<List<PressureCurveEntity>> curveList, int errandType,
                                   BigDecimal positionMin, BigDecimal positionMax,
                                   BigDecimal pressMin, BigDecimal pressMax) {
        List<PressureCurveEntity> lineLeft = new ArrayList<PressureCurveEntity>();
        List<PressureCurveEntity> lineRight = new ArrayList<PressureCurveEntity>();
        List<PressureCurveEntity> lineTop = new ArrayList<PressureCurveEntity>();
        List<PressureCurveEntity> lineBottom = new ArrayList<PressureCurveEntity>();
        List<PressureCurveEntity> middleH = new ArrayList<PressureCurveEntity>();
        List<PressureCurveEntity> middleS = new ArrayList<PressureCurveEntity>();
        BigDecimal middlePress = pressMin.add(pressMax).divide(new BigDecimal(2L));
        BigDecimal middlePosition = positionMin.add(positionMax).divide(new BigDecimal(2L));;

        switch(errandType) {
            case 0 :
                //最大位移窗口，两条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                break;
            case 1 :
                //最大压力窗口，两条线
                //下 虚线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, false));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 2 :
                //配合窗口， 四条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 虚线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, false));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, false));
                //下 实线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, true));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 3 :
                //右-下限制窗口， 四条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 实线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, true));
                //上 虚线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, false));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, false));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 4 :
                //穿越窗口， 四条线
                //左侧 实线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, true));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 虚线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, false));
                //上 虚线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, false));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, false));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 5 :
                //峰值窗口，四条线
                //左侧 实线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, true));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 实线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, true));
                //上 虚线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, false));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, false));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 6 :
                //左-上限制窗口， 四条线
                //左侧 实线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, true));
                //右侧 虚线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, false));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, false));
                //下 虚线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, false));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 7 :
                //右-上限制窗口， 四条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 虚线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, false));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 8 :
                //顶部结束窗口， 四条线
                //左侧 实线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, true));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 虚线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, false));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 9 :
                //右侧结束窗口， 四条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 实线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, true));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                break;
            case 10 :
                //平均值窗口， 五条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 虚线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, false));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, false));
                //下 实线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, true));
                //上 实线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, true));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, true));
                //中间 虚线
                middleH.add(getPressureCurveEntity(positionMin,middlePress, false));
                middleH.add(getPressureCurveEntity(positionMax,middlePress, false));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                curveList.add(middleH);
                break;
            case 11 :
                //拐点窗口，六条线
                //左侧 虚线
                lineLeft.add(getPressureCurveEntity(positionMin,pressMin, false));
                lineLeft.add(getPressureCurveEntity(positionMin,pressMax, false));
                //右侧 实线
                lineRight.add(getPressureCurveEntity(positionMax,pressMin, true));
                lineRight.add(getPressureCurveEntity(positionMax,pressMax, true));
                //下 实线
                lineBottom.add(getPressureCurveEntity(positionMin,pressMin, true));
                lineBottom.add(getPressureCurveEntity(positionMax,pressMin, true));
                //上 虚线
                lineTop.add(getPressureCurveEntity(positionMin,pressMax, false));
                lineTop.add(getPressureCurveEntity(positionMax,pressMax, false));

                //中间短横线 虚线
                middleH.add(getPressureCurveEntity(positionMin,middlePress, false));
                middleH.add(getPressureCurveEntity(middlePosition,middlePress, false));

                //中间短竖线 虚线
                middleS.add(getPressureCurveEntity(middlePosition,middlePress, false));
                middleS.add(getPressureCurveEntity(middlePosition,pressMax, false));
                curveList.add(lineLeft);
                curveList.add(lineRight);
                curveList.add(lineBottom);
                curveList.add(lineTop);
                curveList.add(middleH);
                curveList.add(middleS);
                break;
        }
    }

    private PressureCurveEntity getPressureCurveEntity(BigDecimal position,
                                                       BigDecimal force, boolean isSolidLine) {
        PressureCurveEntity  entity = new PressureCurveEntity();
        entity.setErrant(true);
        entity.setSolidLine(isSolidLine);
        entity.setPosition(position);
        entity.setPressForce(force);
        return entity;

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
