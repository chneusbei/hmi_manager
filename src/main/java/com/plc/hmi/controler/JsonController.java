package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.PressureProgramService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping("/getHisDateByCode")
    public String getHisDateByCode(@RequestParam(value = "pressDataId") Long pressDataId){
        pressDataId = 1L;
//        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(1L, pressDataId);
        List<PressureCurveEntity> list = plc4xCurveDataService.getCurveDatas();
        List<List<PressureCurveEntity>>  errantList = programService.getErrandDataforChart(1L);
        errantList.add(list);
        String json = JSON.toJSONString(list);
        return json;
    }

    @RequestMapping("/getCurveQueryByCode")
    public String getCurveQueryByCode(){
        List<PressureCurveEntity> list = plc4xCurveDataService.getCurveDatas();
//        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(0L, 1L);
//        List<List<PressureCurveEntity>>  errantList = new ArrayList<List<PressureCurveEntity>>();
        List<List<PressureCurveEntity>>  errantList = programService.getErrandDataforChart(1L);
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
        System.out.println("启动PLC到达后台-");
//        plc4xCurveStatusService.setDatas();
        plc4xCurveDataService.setDatas();
        System.out.println("-----------------------------------------");
        return "完成";
    }
}
