package com.plc.hmi.controler.zkcode;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.enumeration.PressureProgramEntityEnum;
import com.plc.hmi.service.PressureProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PressureProgramControler {
    /**
     * 产品压装程序设置页面
     *
     *
     */
    @Resource
    PressureProgramService pressureProgramService;

    @ResponseBody
    @RequestMapping("/getProgram")
    public PressureProgramEntity getProgram(
            @RequestParam(value = "programCode") String programCode){
        /*List<Map<String, String>> list = PressureProgramEntityEnum.getLIST();
        System.out.println(JSON.toJSONString(list));*/
//        System.out.println("===============getProgramCode==="+programEntity.getProgramCode());
        System.out.println("===============programCode==="+programCode);
//        if(null == programEntity.getProgramCode()) {
//            programEntity.setProgramCode("p1");
//        }
        if(null == programCode || "undefined".equalsIgnoreCase(programCode)) {
            programCode= "p1";
        }
        PressureProgramEntity entity =  pressureProgramService.getWithProgramCode(programCode);
        return entity;
    }


    @ResponseBody
    @RequestMapping("/setProgram")
    String setProgram(PressureProgramEntity pressureProgramEntity){
        System.out.println(pressureProgramEntity.toString());
        pressureProgramService.save(pressureProgramEntity);
        return "updated success";
    }
}
