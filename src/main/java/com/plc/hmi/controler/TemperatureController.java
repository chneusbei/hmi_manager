package com.plc.hmi.controler;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.*;
import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xCurveStatusService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.util.HmiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TemperatureController {

    @Autowired
    TemperatureService temperatureService;
    @Autowired
    Plc4xTemperatureService plc4xTemperatureService;


    @RequestMapping("/getHisTemperature")
    public String getHisDateByCode(@RequestParam(value = "startDate",required = true) String startDate,
                                   @RequestParam(value = "endDate",required = true) String endDate,
                                   @RequestParam(value = "plcIp",required = false) String plcIp){
//        System.out.println(" getHisTemperature startDate" + startDate + ", endDate"+endDate + ", plcIp = "+ plcIp);
        List<TemperatureEntity> TemperatureList =  temperatureService.getTemperatureWithParam(startDate, endDate, plcIp, null);
//        if(CollectionUtils.isEmpty(list)) {
//            list = new  ArrayList<PressureCurveEntity>();
//            PressureCurveEntity pressureCurveEntity = new PressureCurveEntity();
//            pressureCurveEntity.setSolidLine(true);
//            pressureCurveEntity.setErrant(false);
//            pressureCurveEntity.setPosition(new BigDecimal(100));
//            pressureCurveEntity.setPressForce(new BigDecimal(100));
//            list.add(pressureCurveEntity);
//        }
//        if(CollectionUtils.isEmpty(pressureData)) {
//           pressureData = new ArrayList<PressureDataEntity>();
//            PressureDataEntity pressureDataEntity = new PressureDataEntity();
//            pressureData.add(pressureDataEntity);
//        }
//        List<List<? extends AbstractBaseEntity>> list1=new ArrayList<>();
//        list1.add(pressureData);
//        list1.add(list);
        String json = JSON.toJSONString(TemperatureList);
        return json;
    }

    @ResponseBody
    @GetMapping("/currentTemperature")
    /**
     * 当前温度
     */
    public List<TemperatureEntity> getEquipmentIo(){
        List<TemperatureEntity> temperatureList = plc4xTemperatureService.getTemperatureList();
        return temperatureList;
//        if(CollectionUtils.isEmpty(datas)) {
//            List<PlcEntity> defalutDates = new ArrayList<PlcEntity>();
//            PlcEntity plcEntity=new PlcEntity();
//            plcEntity.setName("input0");
//            plcEntity.setValueOjb(false);
//            PlcEntity plcEntity1=new PlcEntity();
//            plcEntity1.setName("input1");
//            plcEntity1.setValueOjb(false);
//            PlcEntity plcEntity2=new PlcEntity();
//            plcEntity2.setName("input2");
//            plcEntity2.setValueOjb(false);
//            PlcEntity plcEntity3=new PlcEntity();
//            plcEntity3.setName("input3");
//            plcEntity3.setValueOjb(false);
//            PlcEntity plcEntity4=new PlcEntity();
//            plcEntity4.setName("input4");
//            plcEntity4.setValueOjb(false);
//            PlcEntity plcEntity5=new PlcEntity();
//            plcEntity5.setName("output0");
//            plcEntity5.setValueOjb(false);
//            PlcEntity plcEntity6=new PlcEntity();
//            plcEntity6.setName("output1");
//            plcEntity6.setValueOjb(false);
//            PlcEntity plcEntity7=new PlcEntity();
//            plcEntity7.setName("output2");
//            plcEntity7.setValueOjb(false);
//            PlcEntity plcEntity8=new PlcEntity();
//            plcEntity8.setName("output3");
//            plcEntity8.setValueOjb(false);
//            PlcEntity plcEntity9=new PlcEntity();
//            plcEntity9.setName("output4");
//            plcEntity9.setValueOjb(false);
//            defalutDates.add(plcEntity);
//            defalutDates.add(plcEntity1);
//            defalutDates.add(plcEntity2);
//            defalutDates.add(plcEntity3);
//            defalutDates.add(plcEntity4);
//            defalutDates.add(plcEntity5);
//            defalutDates.add(plcEntity6);
//            defalutDates.add(plcEntity7);
//            defalutDates.add(plcEntity8);
//            defalutDates.add(plcEntity9);
//            datas = defalutDates;
//        }
//
//        datas.stream().forEach(p-> {
//            if ("input0".equals(p.getName())) {
//                p.setName("输入_0");
//            } else if ("input1".equals(p.getName())) {
//                p.setName("输入_1");
//            } else if ("input2".equals(p.getName())) {
//                p.setName("输入_2");
//            } else if ("input3".equals(p.getName())) {
//                p.setName("输入_3");
//            } else if ("input4".equals(p.getName())) {
//                p.setName("输入_4");
//            } else if ("output0".equals(p.getName())) {
//                p.setName("输出_0");
//            } else if ("output1".equals(p.getName())) {
//                p.setName("输出_1");
//            } else if ("output2".equals(p.getName())) {
//                p.setName("输出_2");
//            } else if ("output3".equals(p.getName())) {
//                p.setName("输出_3");
//            } else if ("output4".equals(p.getName())) {
//                p.setName("输出_4");
//            }
//        });


    }


}
