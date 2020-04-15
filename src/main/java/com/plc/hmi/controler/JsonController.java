package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
