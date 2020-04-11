package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.PressureCurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonController {

        @Autowired
        PressureCurveService pressureCurveService;

    @RequestMapping("/getHisDateByCode")
    public String getHisDateByCode(@RequestParam(value = "pressDataId") Long pressDataId){
       /* System.out.println("pressDataId:"+pressDataId);*/

        List<PressureCurveEntity> list =pressureCurveService.getHisDateByCode(1L, pressDataId);
                String json = JSON.toJSONString(list);
               // System.out.println(json);
        return json;
    }
}
