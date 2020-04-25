package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.SystemParameterEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.Plc4xServoLimitSetService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/ServoLimitSet")
@Controller
public class Plc4xServoLimitSetControler {

    /**
     * 系统参数设置
     */
    @Resource
    Plc4xServoLimitSetService plc4xServoLimitSetService;

    @ResponseBody
    @GetMapping("/getSystemParameter")
    public SystemParameterEntity getSystemParameter(){
        List<PlcEntity> queryList = plc4xServoLimitSetService.getDatas();
        SystemParameterEntity entity = new SystemParameterEntity();
        if(!CollectionUtils.isEmpty(queryList)) {
            for(PlcEntity plcEntity : queryList) {
                if("maxForce".equalsIgnoreCase(plcEntity.getName())) {
                    entity.setMaxForce(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if("maxSpeed".equalsIgnoreCase(plcEntity.getName())) {
                    entity.setMaxSpeed(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if("maxDistance".equalsIgnoreCase(plcEntity.getName())) {
                    entity.setMaxDistance(new BigDecimal(plcEntity.getValueOjb().toString()));
                } else if("defaultBackSpeed".equalsIgnoreCase(plcEntity.getName())) {
                    entity.setDefaultBackSpeed(new BigDecimal(plcEntity.getValueOjb().toString()));
                }
            }
        }
        return  entity;
    }

    @RequestMapping("/updateSystemParameter")
    public String updateSystemParameter(SystemParameterEntity systemParameterEntity){
        Map<String, String> paraMap = new HashMap<>();
        if(null != systemParameterEntity.getMaxDistance()) {
            paraMap.put("maxForce", systemParameterEntity.getMaxDistance().toString());
        }
        if(null != systemParameterEntity.getMaxForce()) {
            paraMap.put("maxSpeed",systemParameterEntity.getMaxForce().toString());
        }
        if(null != systemParameterEntity.getMaxSpeed()) {
            paraMap.put("maxDistance",systemParameterEntity.getMaxSpeed().toString());
        }
        if(null != systemParameterEntity.getDefaultBackSpeed()) {
            paraMap.put("defaultBackSpeed",systemParameterEntity.getMaxSpeed().toString());
        }

        plc4xServoLimitSetService.setDatas(paraMap);
        return "";
    }
}
