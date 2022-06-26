package com.plc.hmi.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class initialController {
    @RequestMapping("/logins")
    public String logins(){
        return "logins";
    }
    @RequestMapping("/registerUser")
    public String registerUser(){
        return "registerUser";
    }
    @RequestMapping("/public")
    public String getpublic(){
        System.out.println("----");
        return "public";
    }
    @RequestMapping("/getpressresults")
    public String getpressresults(){
        return "getpressresults";
    }
    @RequestMapping("/login")
    public String login(){
        return "index";
    }
    @RequestMapping("/equipmentStatusMonitoring")
    public String equipmentStatusMonitoring(){
        return "equipmentStatusMonitoring";
    }
    @RequestMapping("/ioequipmentStatusMonitoring")
    public String ioequipmentStatusMonitoring(){
        return "ioequipmentStatusMonitoring";
    }
    @RequestMapping("/curveMonitoring")
    public String curveMonitoring(){
        return "curveMonitoring";
    }
    @RequestMapping("/manualOperation")
    public  String manualOperation(){
        return "manualOperation";
    }
    @RequestMapping("/parameterSetting")
    public String parameterSetting(){
        return"parameterSetting";
    }
    @RequestMapping("/programMing")
    public String programMing(){
        return "programMing";
    }
    @RequestMapping("/parameterCompilation")
    public String parameterCompilation(){
        return "parameterCompilation";
    }
    @RequestMapping("/parameterStorage")
    public String parameterStorage(){
        return "parameterStorage";
    }
    @RequestMapping("/curveAcquisition")
    public String curveAcquisition(){
        return "curveAcquisition";
    }
    @RequestMapping("/curveStorage")
    public String curveStorage(){
        return "curveStorage";
    }
    @RequestMapping("/dataQuery")
    public String dataQuery(){
        return "dataQuery";
    }
    @RequestMapping("/dataExport")
    public String dataExport(){
        return "dataExport";
    }
    @RequestMapping("/hisNokCurve")
    public String hisNokCurve(){
        return "hisNokCurve";
    }
    @RequestMapping("/accountManagement")
    public String accountManagement(){
        return "accountManagement";
    }
    @RequestMapping("/OperatorRole")
    public String OperatorRole(HttpServletRequest request){
        long id2=(long)request.getSession().getAttribute("userid");
        request.setAttribute("id",id2);
        return "OperatorRole";
    }
    @RequestMapping("/createAnAccount")
    public String createAnAccount(){
        return "createAnAccount";
    }
    @RequestMapping("/equipmentFaultAlarm")
    public String EquipmentFaultAlarm(){
        return "equipmentFaultAlarm";
    }
    @RequestMapping("/hisAlarm")
    public String hisAlarm(){
        return "hisAlarm";
    }
    @RequestMapping("/safetyMonitoring")
    public String safetyMonitoring(){
        return "safetyMonitoring";
    }
    @RequestMapping("/curveQuery")
    public String curveQuery(){
        return "curveQuery";
    }
    @RequestMapping("/hiscurveQuery")
    public String hiscurveQuery(){
        return "hiscurveQuery";
    }
    @RequestMapping("/hyperbola")
    public String hyperbola(){
        return "hyperbola";
    }
}
