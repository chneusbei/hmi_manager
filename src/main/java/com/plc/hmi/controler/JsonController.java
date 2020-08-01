package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.service.*;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveStatusService;
import com.plc.hmi.util.HmiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @RequestMapping("/getHisDateByCode")
    public String getHisDateByCode(@RequestParam(value = "recordId",required = false) Long recordId){
//        System.out.println(pressDataId);
        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(recordId);
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

    @RequestMapping("/getCurveQueryByCode")
    public String getCurveQueryByCode(){
        /*
        0
            1
            2
            5

            下进右出
            下进下出
            左进右出
            左进上出
            下进不出
            左进右不出
         */
        /**
         *  曲线信息
         * */

        List<PressureCurveEntity> list = plc4xCurveDataService.getCurveDatas();


//        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(0L, 1L);
//        List<List<PressureCurveEntity>>  errantList = new ArrayList<List<PressureCurveEntity>>();

        /**
         * 公差窗口
         */
        String programCode = "p1+";
        if(null != list && null != list.get(0)) {
            programCode = HmiUtils.getProgrameCode("1",  HmiUtils.getString(list.get(0).getProductId()));
        }
        List<List<PressureCurveEntity>>  errantList = programService.getErrandDataforChart(programCode);
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


//        List<List<PressureCurveEntity>> lists=new ArrayList<List<PressureCurveEntity>>();
        // lists=curveDataService.getCurveDatas();
//       List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(1L, 1l);
//       List<List<PressureCurveEntity>>  errantList = programService.getDataforChart(1L);
//       errantList.add(list);
/*
        List<PressureCurveEntity> list1=new ArrayList<PressureCurveEntity>();
        PressureCurveEntity pc=new PressureCurveEntity();
        pc.setErrant(false);
        pc.setSolidLine(false);
        pc.setPressForce(new BigDecimal("3") );
        list1.add(pc);

        PressureCurveEntity pc1=new PressureCurveEntity();
        pc1.setErrant(false);
        pc1.setSolidLine(false);
        pc1.setPressForce(new BigDecimal("6") );
        list1.add(pc1);

        List<PressureCurveEntity> list2=new ArrayList<PressureCurveEntity>();
        PressureCurveEntity pc3=new PressureCurveEntity();
        pc3.setErrant(true);
        pc3.setSolidLine(true);
        pc3.setPressForce(new BigDecimal("7") );
        list2.add(pc3);

        PressureCurveEntity pc4=new PressureCurveEntity();
        pc4.setErrant(true);
        pc4.setSolidLine(true);
        pc4.setPressForce(new BigDecimal("9") );
        list2.add(pc4);


       lists.add(list);
       lists.add(list1);
       lists.add(list2);


        String json = JSON.toJSONString(lists);
        */
        String json = JSON.toJSONString(errantList);
//        System.out.println(json);
//        System.out.println("-----------------------------------------");
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
//        System.out.println(json);
        return json;
    }

    @RequestMapping("/startPlc")
    public String startPlc(){
//        System.out.println("启动PLC到达后台-");
//        plc4xCurveStatusService.setDatas();
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("choice", "true");
        plc4xCurveDataService.setDatas(paraMap);
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
        List<PressureDataEntity> pressureDataList= pressureDataService.get2RecentData();
        boolean isOK = true;
        boolean isOK2 = true;
        if(!CollectionUtils.isEmpty(pressureDataList)) {
            int i=0;
            for (PressureDataEntity pressureDataEntity : pressureDataList) {
                if(null !=pressureDataEntity) {
                    if(i==0) {
                        isOK=pressureDataEntity.getPressResult().equalsIgnoreCase("1");
                    } else {
                        isOK2=pressureDataEntity.getPressResult().equalsIgnoreCase("1");
                    }
                }
                i++;
            }
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



}
