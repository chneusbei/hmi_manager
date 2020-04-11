package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.service.PressureDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PressureDataControler {
    @Resource
    private PressureDataService pressureDataService;

    @GetMapping("/getPressureData")
    public List<PressureDataEntity> getPressureData(@RequestParam("productId") Long productId){
        List<PressureDataEntity> pressureData = pressureDataService.getPressureData(productId);
        return pressureDataService.getPressureData(productId);
    }
}
