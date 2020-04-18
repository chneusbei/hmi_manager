package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JsonController {

    @Autowired
    PressureCurveService pressureCurveService;
    @Autowired
    Plc4xCurveDataService curveDataService;
    @RequestMapping("/getHisDateByCode")
    public String getHisDateByCode(@RequestParam(value = "pressDataId") Long pressDataId){
        pressDataId = 1L;
//        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(1L, pressDataId);
        List<PressureCurveEntity> list =curveDataService.getCurveDatas();
                String json = JSON.toJSONString(list);
        return json;
    }

    @RequestMapping("/getCurveQueryByCode")
    public String getHisDateByCode1(){
        List<List<PressureCurveEntity>> lists=new ArrayList<List<PressureCurveEntity>>();
        // lists=curveDataService.getCurveDatas();

       List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(1L, 1l);

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
        System.out.println(json);
        return json;
    }
}
