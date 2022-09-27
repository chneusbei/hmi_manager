package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.service.*;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveStatusService;
import com.plc.hmi.util.HmiUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@RestController
public class JsonController {

    @Autowired
    PressureCurveService pressureCurveService;
    @Autowired
    Plc4xCurveDataService plc4xCurveDataService;
    @Autowired
    PressureProgramService programService;
    @Autowired
    Plc4xCurveStatusService plc4xCurveStatusService;
    @Autowired
    ProductService productService;
    @Autowired
    PropertyService propertyService;
    @Resource
    PressureDataService pressureDataService;

//    public static int theCount =0;


    @RequestMapping("/getHisDataByCode")
    public String getHisDataByCode(@RequestParam(value = "recordId",required = false) Long recordId){
//        System.out.println(pressDataId);
        List<PressureCurveEntity> list =pressureCurveService.getHisDataByCode(recordId);
        list = null;
        List<PressureDataEntity> pressureData = pressureDataService.getPressureData(recordId);
        if(CollectionUtils.isEmpty(list)) {
            list = new  ArrayList<PressureCurveEntity>();
            PressureCurveEntity pressureCurveEntity = new PressureCurveEntity();
            pressureCurveEntity.setSolidLine(true);
            pressureCurveEntity.setErrant(false);
            pressureCurveEntity.setPosition(new BigDecimal(100));
            pressureCurveEntity.setPressForce(new BigDecimal(100));
            list.add(pressureCurveEntity);
        }
        if(CollectionUtils.isEmpty(pressureData)) {
            pressureData = new ArrayList<PressureDataEntity>();
            PressureDataEntity pressureDataEntity = new PressureDataEntity();
            pressureData.add(pressureDataEntity);
        }
        List<List<? extends AbstractBaseEntity>> list1=new ArrayList<>();
        list1.add(pressureData);
        list1.add(list);
        String json = JSON.toJSONString(list1);
        return json;
    }

    @RequestMapping("/getHisCurveByCode")
    public String getHisCurveByCode(@RequestParam(value = "recordId",required = false) Long recordId){

        /**
         *  曲线信息
         * */
        List<PressureCurveEntity> list =pressureCurveService.getHisDataByCode(recordId);
//        list = null;
        List<PressureDataEntity> pressureData = pressureDataService.getPressureData(recordId);
        if(CollectionUtils.isEmpty(list)) {
            list = new  ArrayList<PressureCurveEntity>();
            PressureCurveEntity pressureCurveEntity = new PressureCurveEntity();
            pressureCurveEntity.setSolidLine(true);
            pressureCurveEntity.setErrant(false);
            pressureCurveEntity.setPosition(new BigDecimal(100));
            pressureCurveEntity.setPressForce(new BigDecimal(100));
            list.add(pressureCurveEntity);
        }
        if(CollectionUtils.isEmpty(pressureData)) {
            pressureData = new ArrayList<PressureDataEntity>();
            PressureDataEntity pressureDataEntity = new PressureDataEntity();
            pressureData.add(pressureDataEntity);
        }
        List<List<? extends AbstractBaseEntity>> list1=new ArrayList<>();
        list1.add(pressureData);

//        String json = JSON.toJSONString(list1);
//        return json;


        /**
         * 公差窗口
         */
        boolean showErrantd = HmiUtils.getBooleanValue(propertyService.getProperty(ConfigConstants.ERRAND_USE_FLAG));
        List<List<PressureCurveEntity>> errantList = new ArrayList<List<PressureCurveEntity>>();
        if (showErrantd) {
            String programCode = "p1+";
            if (null != list && null != list.get(0)) {
                programCode = HmiUtils.getProgrameCode("1", HmiUtils.getString(list.get(0).getProductId()));
            }
//            programCode = "p1-";
//            System.out.println("======================programCode================"+programCode);
            errantList = programService.getErrandDataforChart(programCode);

        }

        if(!CollectionUtils.isEmpty(errantList)) {
//            PressureCurveEntity fristCurve = list.get(0);
//            System.out.println("----------fristCurve recordNo--------"+  fristCurve.getRecordNo());
            //产品信息
            /*
            ProductEntity  product = productService.getStaticProduct(HmiUtils.getString(fristCurve.getProductId()));
            if(null != product) {
                fristCurve.setProductCode(product.getProductCode());
                fristCurve.setProductName(product.getProductName());
                fristCurve.setProductTraceCode(product.getProductTraceCode());
            } */
//            errantList.add(list);
            for(List<PressureCurveEntity> err:errantList) {
                list1.add(err);
            }

        }

        list1.add(list);

        String json = JSON.toJSONString(list1);
        return json;
    }

    @RequestMapping("/getNokHisData")
    public String getNokHisData( @RequestParam(value = "startDate",required = false) String startDate,
                                 @RequestParam(value = "endDate",required = false) String endDate){
        startDate = StringUtils.isBlank(startDate) ? null : startDate.replace("-","");
        endDate = StringUtils.isBlank(endDate) ? null : endDate.replace("-","");
        if (StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate)) {
            startDate = HmiUtils.getYYYYMMDDString(new Date());
            endDate = startDate;
        } else if (StringUtils.isBlank(startDate)) {
            startDate = endDate;
        } else {
            endDate = startDate;
        }
        List<PressureDataEntity> list = pressureDataService.getPressureDataWithStatus(startDate+"000000000", endDate+"235900000", null);
        String json = JSON.toJSONString(list);
        return json;
    }

    @RequestMapping("/getCurveQueryByCode")
    public String getCurveQueryByCode(){
        /**
         *  曲线信息
         * */

        List<PressureCurveEntity> list = plc4xCurveDataService.getCurveDatas();
//        List<PressureCurveEntity> list = null;
//        if(theCount<100) {
//            System.out.println("====================================GET CHART 1, count= " + theCount);
//            list = initList();
//            theCount ++;
//        } else {
//            System.out.println("====================================GET CHART2,  count= " + theCount);
//            list = initList1();
//        }

        /**
         * 公差窗口
         */
        boolean showErrantd = HmiUtils.getBooleanValue(propertyService.getProperty(ConfigConstants.ERRAND_USE_FLAG));
        List<List<PressureCurveEntity>> errantList = new ArrayList<List<PressureCurveEntity>>();
        if (showErrantd) {
            String programCode = "p1+";
            if (null != list && null != list.get(0)) {
                programCode = HmiUtils.getProgrameCode("1", HmiUtils.getString(list.get(0).getProductId()));
            }
//            programCode = "p1-";
//            System.out.println("======================programCode================"+programCode);
            errantList = programService.getErrandDataforChart(programCode);

        }
        if(!CollectionUtils.isEmpty(list)) {
            PressureCurveEntity fristCurve = list.get(0);
//            System.out.println("----------fristCurve recordNo--------"+  fristCurve.getRecordNo());
            //产品信息
            ProductEntity  product = productService.getStaticProduct(HmiUtils.getString(fristCurve.getProductId()));
            if(null != product) {
                fristCurve.setProductCode(product.getProductCode());
                fristCurve.setProductName(product.getProductName());
                fristCurve.setProductTraceCode(product.getProductTraceCode());
            }
            errantList.add(list);
        }

        String json = JSON.toJSONString(errantList);
//        System.out.println(" lin 1 "+json);
        return json;
    }

    @RequestMapping("/getCurveQueryByCode2")
    public String getCurveQueryByCode2(){
        /**
         *  曲线信息
         * */
        List<PressureCurveEntity> list = plc4xCurveDataService.getCurveDatas2();
        /**
         * 公差窗口
         */
        String programCode = "p2+";
        if(null != list && null != list.get(0)) {
            programCode = HmiUtils.getProgrameCode("2", HmiUtils.getString(list.get(0).getProductId()));
        }
//        System.out.println("======================programCode 2================"+programCode);
        List<List<PressureCurveEntity>>  errantList = programService.getErrandDataforChart(programCode);
        if(!CollectionUtils.isEmpty(list)) {
            PressureCurveEntity fristCurve = list.get(0);
            //产品信息
            ProductEntity  product = productService.getStaticProduct(HmiUtils.getString(fristCurve.getProductId()));
            if(null != product) {
                fristCurve.setProductCode(product.getProductCode());
                fristCurve.setProductName(product.getProductName());
                fristCurve.setProductTraceCode(product.getProductTraceCode());
            }
            errantList.add(list);
        }
        String json = JSON.toJSONString(errantList);
//        System.out.println(" lin 2 "+json);
        return json;
    }

    @RequestMapping("/startPlc")
    public String startPlc(){
//        System.out.println("启动PLC到达后台-");
//        plc4xCurveStatusService.setDatas();
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("startPlc", "true");
//        paraMap.put("traceCode31", "65");
//        paraMap.put("traceCode0", "100");
//        plc4xCurveDataService.setDatas(HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode(), paraMap);
        plc4xCurveDataService.setDatas(HmiConstants.PLC_TAG_GROUP.START_PLC.getCode(), paraMap);

//        System.out.println("-----------------------------------------");
        return "SUCCESS";
    }

    @RequestMapping("/setAxis")
    public String setAxis(HttpServletRequest request, HttpServletResponse response, AxisEntity axis){
//        System.out.println("setAxis到达后台-");
        //        1.获取前台轴设置
        AxisEntity axisEntity = JSON.parseObject(request.getParameter("axis"), AxisEntity.class);
//        2.更新后台轴设置
        if(axisEntity.getxMin() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.XMIN);
            entity.setPropValue(String.valueOf(axisEntity.getxMin()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }
        if(axisEntity.getxMax() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.XMAX);
            entity.setPropValue(String.valueOf(axisEntity.getxMax()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }

        if(axisEntity.getyMin() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.YMIN);
            entity.setPropValue(String.valueOf(axisEntity.getyMin()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }

        if(axisEntity.getyMax() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.YMAX);
            entity.setPropValue(String.valueOf(axisEntity.getyMax()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }

        if(axisEntity.getxMin2() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.XMIN2);
            entity.setPropValue(String.valueOf(axisEntity.getxMin2()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }
        if(axisEntity.getxMax2() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.XMAX2);
            entity.setPropValue(String.valueOf(axisEntity.getxMax2()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }

        if(axisEntity.getyMin2() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.YMIN2);
            entity.setPropValue(String.valueOf(axisEntity.getyMin2()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }

        if(axisEntity.getyMax2() >= 0) {
            PropertyEntity entity = new PropertyEntity();
            entity.setPropName(ConfigConstants.YMAX2);
            entity.setPropValue(String.valueOf(axisEntity.getyMax2()));
            entity.setPropGroup(ConfigConstants.AXIS_GROUP);
            entity.setUpdateBy("SYS");
            propertyService.update(entity);
        }

//        System.out.println("-----------------------------------------");
        return "SUCCESS";
    }

    @RequestMapping("/getAxisProperty")
    public String getAxisProperty() {
//        System.out.println("============getAxisProperty");
        List<PropertyEntity>  propertyList = propertyService.getPropertyWithGroup(ConfigConstants.AXIS_GROUP);
        AxisEntity axisEntity=new AxisEntity();
        for(PropertyEntity prop : propertyList) {
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.XMIN)) {
                axisEntity.setxMin(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.XMAX)) {
                axisEntity.setxMax(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.YMIN)) {
                axisEntity.setyMin(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.YMAX)) {
                axisEntity.setyMax(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.XMIN2)) {
                axisEntity.setxMin2(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.XMAX2)) {
                axisEntity.setxMax2(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.YMIN2)) {
                axisEntity.setyMin2(HmiUtils.getIntValue(prop.getPropValue()));
            }
            if(prop.getPropName().equalsIgnoreCase(ConfigConstants.YMAX2)) {
                axisEntity.setyMax2(HmiUtils.getIntValue(prop.getPropValue()));
            }

        }


        String json = JSON.toJSONString(axisEntity);
//        System.out.println(json);
//        System.out.println("-----------------------------------------");
        return json;
    }


    @RequestMapping("/getStatus")
    public String getStatus() {
//        System.out.println("============getStatus");
        StatusInfoEntity statusInfoEntity = new StatusInfoEntity();
        //上一条数据得压装结果，如果是双压头， 需要获取两条。
        boolean isOK = true;
        boolean isOK2 = true;
        PressureDataEntity pressureData1 = pressureDataService.getDataByHeadNo(1);
        if(null !=pressureData1) {
            isOK = pressureData1.getPressResult().equalsIgnoreCase("1");
        }

        if(propertyService.isDubblePress()) {
            PressureDataEntity pressureData2 = pressureDataService.getDataByHeadNo(2);
            isOK2=pressureData2.getPressResult().equalsIgnoreCase("1");
        }

        statusInfoEntity.setCurveOk(isOK);
        statusInfoEntity.setCurveOk2(isOK2);
        //PLC连接
        statusInfoEntity.setPlcConnected(plc4xCurveDataService.isPlcConnected());
        statusInfoEntity.setSystemConnection(plc4xCurveDataService.isPlcConnected());
        String json = JSON.toJSONString(statusInfoEntity);
//        System.out.println(json);
//        System.out.println("--------------------------getStatus---------------" + json);
        return json;
    }

    /**
     * 获取压装结果本工时段的统计信息
     * @return
     */
    @RequestMapping("/getPressureSumData")
    public List<PressureStatisticalDataEntity> getPressureSumData(@RequestParam(value = "time",required = false) Long time, @RequestParam(value = "defaultFailNum",required = false) BigDecimal defaultFailNum) {
//        System.out.println("============getPressureSumData . time = " + time + ", defaultFailNum =" +defaultFailNum);
        if(null == defaultFailNum) {
            defaultFailNum = new BigDecimal(0);
        }
        PressureStatisticalDataEntity pressureStatisticalDataEntity = pressureDataService.getPressureStatisticalData(time.intValue(),defaultFailNum);
        String json = JSON.toJSONString(pressureStatisticalDataEntity);
//        System.out.println(json);
//        System.out.println("--------------------------getPressureSumData---------------" + json);
        List<PressureStatisticalDataEntity> list = new ArrayList<PressureStatisticalDataEntity>();

        list.add(pressureStatisticalDataEntity);
        return list;
    }

    private List<PressureCurveEntity> initList() {
        List<PressureCurveEntity> list = new ArrayList<PressureCurveEntity>();

        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(0.1),  new BigDecimal( 1.41      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(1.7),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(3.4),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(5.8),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(9.1),  new BigDecimal( 1.39      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(11.6),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(13.3),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(15.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(18.3),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(20.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(23.3),  new BigDecimal( 1.42     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(25.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(28.3),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(31.6),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(34.1),  new BigDecimal( 1.41     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(35.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(39.1),  new BigDecimal( 1.41     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(40.8),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(44.1),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(45.8),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(49.1),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(51.6),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(54.1),  new BigDecimal( 1.38     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(56.6),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(58.3),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(60.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(64.1),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(66.6),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(69.1),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(70.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(73.3),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(76.6),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(79.1),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(80.8),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(84.1),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(86.6),  new BigDecimal( 1.41     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(89.1),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(90.8),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(94.1),  new BigDecimal( 1.39     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(96.6),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(98.3),  new BigDecimal( 1.4      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(101.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(104.1),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(106.6),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(109.1),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(111.6),  new BigDecimal( 1.42    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(114.1),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(116.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(119.1),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(121.6),  new BigDecimal( 1.41    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(124.1),  new BigDecimal( 1.38    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(126.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(129.1),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(130.8),  new BigDecimal( 1.38    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(133.3),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(136.6),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(139.1),  new BigDecimal( 1.38    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(140.8),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(143.3),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(146.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(149),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(149.8),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(150.1),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(150.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(151.2),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(151.8),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(152.6),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(153.2),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(153.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(154.4),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(155),  new BigDecimal( 1.39      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(155.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(156),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(156.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(157.4),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(157.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(158.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(159),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(159.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(160.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(161),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(161.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(162),  new BigDecimal( 1.39      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(162.8),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(163.4),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(164),  new BigDecimal( 1.39      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(164.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(165),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(165.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(166.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(167),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(167.4),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(168.2),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(168.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(169.2),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(170),  new BigDecimal( 1.39      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(170.6),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(171.2),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(171.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(172.4),  new BigDecimal( 1.38    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(172.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(173.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(173.9),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.1),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.2),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.6),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.7),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.9),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175),  new BigDecimal( 1.39      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.2),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.4),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.5),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.7),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.8),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.9),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.1),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.3),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.4),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.5),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.7),  new BigDecimal( 1.39    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.8),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177),  new BigDecimal( 1.4       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.1),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.3),  new BigDecimal( 1.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.5),  new BigDecimal( 1.41    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.6),  new BigDecimal( 1.41    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.7),  new BigDecimal( 1.41    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.9),  new BigDecimal( 1.42    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.1),  new BigDecimal( 1.42    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.2),  new BigDecimal( 1.42    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.3),  new BigDecimal( 1.44    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.5),  new BigDecimal( 1.47    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.7),  new BigDecimal( 1.53    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.8),  new BigDecimal( 1.61    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179),  new BigDecimal( 2.1       ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.1),  new BigDecimal( 2.85    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.3),  new BigDecimal( 3.84    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.4),  new BigDecimal( 4.97    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.6),  new BigDecimal( 6.1     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.7),  new BigDecimal( 7.15    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.9),  new BigDecimal( 7.86    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180),  new BigDecimal( 8.32      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.2),  new BigDecimal( 8.55    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.3),  new BigDecimal( 8.71    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.5),  new BigDecimal( 8.67    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.6),  new BigDecimal( 8.64    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.7),  new BigDecimal( 8.49    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.9),  new BigDecimal( 8.23    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.1),  new BigDecimal( 7.97    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.2),  new BigDecimal( 7.82    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.3),  new BigDecimal( 7.54    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.5),  new BigDecimal( 7.25    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.6),  new BigDecimal( 7.18    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.8),  new BigDecimal( 7.16    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182),  new BigDecimal( 7.17      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.1),  new BigDecimal( 7.23    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.3),  new BigDecimal( 7.29    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.4),  new BigDecimal( 7.35    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.5),  new BigDecimal( 7.4     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.7),  new BigDecimal( 7.5     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.9),  new BigDecimal( 7.67    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183),  new BigDecimal( 7.76      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.2),  new BigDecimal( 7.98    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.3),  new BigDecimal( 8.1     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.4),  new BigDecimal( 8.29    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.6),  new BigDecimal( 8.55    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.8),  new BigDecimal( 8.7     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.9),  new BigDecimal( 8.84    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184),  new BigDecimal( 9.04      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.2),  new BigDecimal( 9.25    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.4),  new BigDecimal( 9.52    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.5),  new BigDecimal( 9.76    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.6),  new BigDecimal( 9.93    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.8),  new BigDecimal( 10.07   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185),  new BigDecimal( 10.33     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.1),  new BigDecimal( 10.56   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.3),  new BigDecimal( 10.72   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.4),  new BigDecimal( 10.94   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.6),  new BigDecimal( 11.14   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.7),  new BigDecimal( 11.38   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.9),  new BigDecimal( 11.61   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186),  new BigDecimal( 11.88     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.1),  new BigDecimal( 12.01   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.3),  new BigDecimal( 12.26   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.5),  new BigDecimal( 12.45   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.6),  new BigDecimal( 12.56   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.8),  new BigDecimal( 12.8    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.9),  new BigDecimal( 12.95   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.1),  new BigDecimal( 13.14   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.2),  new BigDecimal( 13.27   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.3),  new BigDecimal( 13.4    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.5),  new BigDecimal( 13.45   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.6),  new BigDecimal( 13.66   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.8),  new BigDecimal( 13.87   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188),  new BigDecimal( 13.97     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.1),  new BigDecimal( 14.09   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.2),  new BigDecimal( 14.36   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.4),  new BigDecimal( 14.55   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.6),  new BigDecimal( 14.73   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.7),  new BigDecimal( 14.8    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.9),  new BigDecimal( 15.1    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189),  new BigDecimal( 15.22     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.2),  new BigDecimal( 15.44   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.3),  new BigDecimal( 15.57   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.5),  new BigDecimal( 15.78   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.6),  new BigDecimal( 15.98   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.8),  new BigDecimal( 16.16   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.9),  new BigDecimal( 16.35   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190),  new BigDecimal( 16.48     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.2),  new BigDecimal( 16.73   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.4),  new BigDecimal( 16.9    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.5),  new BigDecimal( 16.95   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.7),  new BigDecimal( 17.17   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.8),  new BigDecimal( 17.4    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.9),  new BigDecimal( 17.46   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.1),  new BigDecimal( 17.58   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.3),  new BigDecimal( 17.76   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.4),  new BigDecimal( 17.93   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.5),  new BigDecimal( 18.06   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.7),  new BigDecimal( 18.23   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.8),  new BigDecimal( 18.48   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192),  new BigDecimal( 18.7      ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.2),  new BigDecimal( 18.88   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.3),  new BigDecimal( 18.99   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.4),  new BigDecimal( 19.19   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.6),  new BigDecimal( 19.38   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.8),  new BigDecimal( 19.53   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.9),  new BigDecimal( 19.6    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193),  new BigDecimal( 19.64     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.2),  new BigDecimal( 19.8    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.4),  new BigDecimal( 19.87   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.5),  new BigDecimal( 20.1    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.7),  new BigDecimal( 20.26   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.8),  new BigDecimal( 20.47   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194),  new BigDecimal( 20.65     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.1),  new BigDecimal( 20.85   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.3),  new BigDecimal( 21.05   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.4),  new BigDecimal( 21.3    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.5),  new BigDecimal( 21.42   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.7),  new BigDecimal( 21.66   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.9),  new BigDecimal( 21.87   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195),  new BigDecimal( 22.03     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.1),  new BigDecimal( 22.26   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.3),  new BigDecimal( 22.44   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.5),  new BigDecimal( 22.74   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.6),  new BigDecimal( 22.88   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.8),  new BigDecimal( 23.21   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.9),  new BigDecimal( 23.42   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196),  new BigDecimal( 23.53     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.2),  new BigDecimal( 23.77   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.4),  new BigDecimal( 23.98   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.5),  new BigDecimal( 24.1    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.7),  new BigDecimal( 24.4    ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.8),  new BigDecimal( 24.66   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197),  new BigDecimal( 24.86     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.1),  new BigDecimal( 25.01   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.3),  new BigDecimal( 25.21   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.4),  new BigDecimal( 25.32   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.6),  new BigDecimal( 25.66   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.7),  new BigDecimal( 25.98   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.8),  new BigDecimal( 26.16   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198),  new BigDecimal( 26.61     ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.2),  new BigDecimal( 26.91   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.3),  new BigDecimal( 27.06   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.4),  new BigDecimal( 27.34   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.6),  new BigDecimal( 27.43   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.8),  new BigDecimal( 27.62   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.9),  new BigDecimal( 27.71   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.1),  new BigDecimal( 28.01   ), "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.2),  new BigDecimal( 28.05   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.3),  new BigDecimal( 28.28   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.5),  new BigDecimal( 28.51   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.7),  new BigDecimal( 28.67   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.8),  new BigDecimal( 28.76   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200),  new BigDecimal( 28.96     ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.1),  new BigDecimal( 29.1    ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.2),  new BigDecimal( 29.21   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.4),  new BigDecimal( 29.45   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.6),  new BigDecimal( 29.65   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.7),  new BigDecimal( 29.8    ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.9),  new BigDecimal( 30.05   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201),  new BigDecimal( 30.25     ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.1),  new BigDecimal( 30.41   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.3),  new BigDecimal( 30.58   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.5),  new BigDecimal( 30.85   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.6),  new BigDecimal( 30.99   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.7),  new BigDecimal( 31.18   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.9),  new BigDecimal( 31.34   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.1),  new BigDecimal( 31.48   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.2),  new BigDecimal( 31.58   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.3),  new BigDecimal( 31.77   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.5),  new BigDecimal( 32.01   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.7),  new BigDecimal( 32.16   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.8),  new BigDecimal( 32.25   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203),  new BigDecimal( 32.43     ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.1),  new BigDecimal( 32.57   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.3),  new BigDecimal( 32.79   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.4),  new BigDecimal( 32.84   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.6),  new BigDecimal( 32.93   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.7),  new BigDecimal( 33.02   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.9),  new BigDecimal( 33.32   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204),  new BigDecimal( 33.62     ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204.1),  new BigDecimal( 34.01   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204.1),  new BigDecimal( 33.43   ), "20220926",true));
//        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204.1),  new BigDecimal( 33.59   ), "20220926",true));

        return list;
    }
    private List<PressureCurveEntity> initList1() {
        List<PressureCurveEntity> list = new ArrayList<PressureCurveEntity>();

        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(0.1 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(1.7 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(4.3 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(6.6 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(9.1 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(11.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(14.1 ), new BigDecimal( 1.38  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(16.6 ), new BigDecimal( 1.41  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(19.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(21.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(24.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(26.6 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(29.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(31.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(34.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(36.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(39.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(41.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(44.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(46.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(49.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(51.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(54.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(56.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(59.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(61.6 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(64.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(66.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(69.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(71.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(74.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(76.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(79.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(81.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(84.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(86.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(89.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(91.6 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(94.1 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(96.6 ), new BigDecimal( 1.39  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(99.1 ), new BigDecimal( 1.4   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(101.6 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(104.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(106.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(109.1 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(111.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(114.1 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(116.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(119.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(121.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(124.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(126.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(129.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(131.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(134.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(136.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(139.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(141.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(144.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(146.6 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(149 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(149.8 ), new BigDecimal( 1.38 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(150.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(150.8 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(151.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(152 ), new BigDecimal( 1.39   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(152.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(153.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(153.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(154.4 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(155 ), new BigDecimal( 1.39   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(155.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(156.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(156.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(157.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(158 ), new BigDecimal( 1.39   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(158.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(159.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(159.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(160.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(161 ), new BigDecimal( 1.39   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(161.6 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(162.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(162.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(163.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(164 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(164.6 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(165.2 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(165.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(166.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(167 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(167.6 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(168.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(168.8 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(169.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(170 ), new BigDecimal( 1.39   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(170.6 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(171.2 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(171.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(172.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(173 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(173.6 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(173.9 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.3 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.4 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.6 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(174.9 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.1 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.5 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.7 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(175.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176 ), new BigDecimal( 1.4    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.1 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.3 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.4 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.6 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.7 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(176.9 ), new BigDecimal( 1.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177 ), new BigDecimal( 1.41   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.2 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.3 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.5 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.6 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.8 ), new BigDecimal( 1.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(177.9 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.1 ), new BigDecimal( 1.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.2 ), new BigDecimal( 1.42 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.4 ), new BigDecimal( 1.42 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.5 ), new BigDecimal( 1.43 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.7 ), new BigDecimal( 1.43 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(178.8 ), new BigDecimal( 1.46 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179 ), new BigDecimal( 1.53   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.1 ), new BigDecimal( 1.82 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.3 ), new BigDecimal( 2.44 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.4 ), new BigDecimal( 3.33 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.6 ), new BigDecimal( 4.47 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.7 ), new BigDecimal( 5.73 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(179.9 ), new BigDecimal( 6.88 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180 ), new BigDecimal( 7.88   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.2 ), new BigDecimal( 8.66 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.3 ), new BigDecimal( 8.95 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.5 ), new BigDecimal( 8.92 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.6 ), new BigDecimal( 8.84 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.8 ), new BigDecimal( 8.81 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(180.9 ), new BigDecimal( 8.64 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.1 ), new BigDecimal( 8.47 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.2 ), new BigDecimal( 8.38 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.4 ), new BigDecimal( 8.37 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.5 ), new BigDecimal( 8.34 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.7 ), new BigDecimal( 8.34 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(181.8 ), new BigDecimal( 8.36 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182 ), new BigDecimal( 8.36   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.1 ), new BigDecimal( 8.38 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.3 ), new BigDecimal( 8.37 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.4 ), new BigDecimal( 8.4  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.6 ), new BigDecimal( 8.41 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.7 ), new BigDecimal( 8.47 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(182.9 ), new BigDecimal( 8.52 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183 ), new BigDecimal( 8.57   )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.2 ), new BigDecimal( 8.57 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.3 ), new BigDecimal( 8.66 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.5 ), new BigDecimal( 8.73 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.6 ), new BigDecimal( 8.83 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.8 ), new BigDecimal( 9    )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(183.9 ), new BigDecimal( 9.16 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.1 ), new BigDecimal( 9.39 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.2 ), new BigDecimal( 9.53 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.4 ), new BigDecimal( 9.64 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.5 ), new BigDecimal( 9.87 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.7 ), new BigDecimal( 9.99 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(184.8 ), new BigDecimal( 10.2 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185 ), new BigDecimal( 10.37  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.1 ), new BigDecimal( 10.62)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.3 ), new BigDecimal( 10.84)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.4 ), new BigDecimal( 11.09)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.6 ), new BigDecimal( 11.34)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.7 ), new BigDecimal( 11.52)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(185.9 ), new BigDecimal( 11.74)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186 ), new BigDecimal( 11.91  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.2 ), new BigDecimal( 12.05)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.3 ), new BigDecimal( 12.19)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.5 ), new BigDecimal( 12.4 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.6 ), new BigDecimal( 12.56)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.8 ), new BigDecimal( 12.75)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(186.9 ), new BigDecimal( 12.87)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.1 ), new BigDecimal( 13.08)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.2 ), new BigDecimal( 13.23)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.4 ), new BigDecimal( 13.45)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.5 ), new BigDecimal( 13.61)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.7 ), new BigDecimal( 13.88)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(187.8 ), new BigDecimal( 14.04)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188 ), new BigDecimal( 14.28  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.1 ), new BigDecimal( 14.5 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.3 ), new BigDecimal( 14.7 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.4 ), new BigDecimal( 14.9 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.6 ), new BigDecimal( 15.11)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.7 ), new BigDecimal( 15.31)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(188.9 ), new BigDecimal( 15.51)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189 ), new BigDecimal( 15.75  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.2 ), new BigDecimal( 15.95)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.3 ), new BigDecimal( 16.21)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.5 ), new BigDecimal( 16.29)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.6 ), new BigDecimal( 16.5 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.8 ), new BigDecimal( 16.6 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(189.9 ), new BigDecimal( 16.77)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.1 ), new BigDecimal( 16.88)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.2 ), new BigDecimal( 17.09)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.4 ), new BigDecimal( 17.16)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.5 ), new BigDecimal( 17.3 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.7 ), new BigDecimal( 17.43)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(190.8 ), new BigDecimal( 17.6 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191 ), new BigDecimal( 17.84  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.1 ), new BigDecimal( 17.96)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.3 ), new BigDecimal( 18.18)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.4 ), new BigDecimal( 18.32)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.6 ), new BigDecimal( 18.51)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.7 ), new BigDecimal( 18.77)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(191.9 ), new BigDecimal( 18.87)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192 ), new BigDecimal( 19.06  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.2 ), new BigDecimal( 19.21)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.3 ), new BigDecimal( 19.47)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.5 ), new BigDecimal( 19.7 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.6 ), new BigDecimal( 19.99)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.8 ), new BigDecimal( 20.21)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(192.9 ), new BigDecimal( 20.26)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.1 ), new BigDecimal( 20.4 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.2 ), new BigDecimal( 20.49)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.4 ), new BigDecimal( 20.76)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.5 ), new BigDecimal( 20.88)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.7 ), new BigDecimal( 21.1 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(193.8 ), new BigDecimal( 21.19)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194 ), new BigDecimal( 21.32  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.1 ), new BigDecimal( 21.47)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.3 ), new BigDecimal( 21.71)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.4 ), new BigDecimal( 21.83)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.6 ), new BigDecimal( 21.96)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.7 ), new BigDecimal( 22.14)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(194.9 ), new BigDecimal( 22.38)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195 ), new BigDecimal( 22.68  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.2 ), new BigDecimal( 23.01)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.3 ), new BigDecimal( 23.23)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.5 ), new BigDecimal( 23.44)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.6 ), new BigDecimal( 23.7 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.8 ), new BigDecimal( 23.92)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(195.9 ), new BigDecimal( 24.17)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.1 ), new BigDecimal( 24.34)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.2 ), new BigDecimal( 24.58)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.4 ), new BigDecimal( 24.85)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.5 ), new BigDecimal( 25.02)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.7 ), new BigDecimal( 25.16)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(196.8 ), new BigDecimal( 25.41)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197 ), new BigDecimal( 25.47  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.1 ), new BigDecimal( 25.68)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.3 ), new BigDecimal( 25.83)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.4 ), new BigDecimal( 25.97)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.6 ), new BigDecimal( 26.06)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.7 ), new BigDecimal( 26.12)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(197.9 ), new BigDecimal( 26.27)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198 ), new BigDecimal( 26.44  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.2 ), new BigDecimal( 26.63)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.3 ), new BigDecimal( 26.89)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.5 ), new BigDecimal( 27.08)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.6 ), new BigDecimal( 27.17)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.8 ), new BigDecimal( 27.36)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(198.9 ), new BigDecimal( 27.52)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.1 ), new BigDecimal( 27.55)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.2 ), new BigDecimal( 27.77)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.4 ), new BigDecimal( 27.81)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.5 ), new BigDecimal( 27.97)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.7 ), new BigDecimal( 28.07)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(199.8 ), new BigDecimal( 28.14)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200 ), new BigDecimal( 28.36  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.1 ), new BigDecimal( 28.53)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.3 ), new BigDecimal( 28.7 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.4 ), new BigDecimal( 28.91)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.6 ), new BigDecimal( 29.19)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.7 ), new BigDecimal( 29.37)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(200.9 ), new BigDecimal( 29.53)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201 ), new BigDecimal( 29.84  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.2 ), new BigDecimal( 30.15)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.3 ), new BigDecimal( 30.36)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.5 ), new BigDecimal( 30.41)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.6 ), new BigDecimal( 30.47)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.8 ), new BigDecimal( 30.74)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(201.9 ), new BigDecimal( 30.8 )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.1 ), new BigDecimal( 30.98)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.2 ), new BigDecimal( 31.02)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.4 ), new BigDecimal( 31.11)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.5 ), new BigDecimal( 31.28)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.7 ), new BigDecimal( 31.31)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(202.8 ), new BigDecimal( 31.38)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203 ), new BigDecimal( 31.38  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.1 ), new BigDecimal( 31.35)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.3 ), new BigDecimal( 31.33)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.4 ), new BigDecimal( 31.34)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.6 ), new BigDecimal( 31.47)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.7 ), new BigDecimal( 31.67)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(203.9 ), new BigDecimal( 32.11)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204 ), new BigDecimal( 32.69  )  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204.1 ), new BigDecimal( 32.89)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204.1 ), new BigDecimal( 32.27)  , "20220926",true));
        list.add(new PressureCurveEntity(1,"id001", 10002l,  new BigDecimal(204.1 ), new BigDecimal( 32.33)  , "20220926",true));

        return list;
    }


}
