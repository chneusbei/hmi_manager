package com.plc.hmi.controler.zkcode;

import com.alibaba.fastjson.JSON;
import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.enumeration.PressureProgramEntityEnum;
import com.plc.hmi.service.PressureProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    PressureProgramService pressureProgramService;

    /**
     *
     * @param productId 根据ID查询
     * @return 查询压装程序
     */
    @ResponseBody
    @GetMapping("/getWithProductId")
    List<PressureProgramEntity> getWithProductId(Long productId){
    return  pressureProgramService.getWithProductId(productId);
    }

    @RequestMapping("/save")
    String save(PressureProgramEntity entity){
        pressureProgramService.save(entity);
        return "";
    }


    @ResponseBody
    @RequestMapping("/getProgram")
    Map<String, String> getProgram(){
        Map<String, String> lookup = PressureProgramEntityEnum.getLookup();
        System.out.println(JSON.toJSONString(lookup));
        return lookup;
    }
}
