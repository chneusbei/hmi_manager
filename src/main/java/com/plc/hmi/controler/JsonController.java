package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.PressureDataService;
import com.plc.hmi.service.PressureProgramService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
        List<PressureCurveEntity> list = plc4xCurveDataService.getCurveDatas();
//        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(0L, 1L);
//        List<List<PressureCurveEntity>>  errantList = new ArrayList<List<PressureCurveEntity>>();
        List<List<PressureCurveEntity>>  errantList = programService.getErrandDataforChart();
        if(!CollectionUtils.isEmpty(list)) {
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
}
