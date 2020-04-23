package com.plc.hmi.controler.zkcode;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Plc4xCurveDataControler {

    @Resource
    Plc4xCurveDataService plc4xCurveDataService;
    @Resource
    Plc4xEquipmentOperationService plc4xEquipmentOperationService;
    @ResponseBody
    @PostMapping("/getCurveData")
    PressureCurveEntity getcurrDate(){
        List<PressureCurveEntity> curveDatas = plc4xCurveDataService.getCurveDatas();
        System.out.println(curveDatas);
        return curveDatas.get(0);
    }


    @Resource
    Plc4xEquipmentStatusService plc4xEquipmentStatusService;

    /**
     *
     * @return 设备信息
     */
    @ResponseBody
    @GetMapping("/getEquipmentStatus")
    List<PlcEntity> getEquipmentDatas(){
        List<PlcEntity> datas=new ArrayList<>();
        PlcEntity plcEntity=new PlcEntity();
        plcEntity.setName("onLine");
        plcEntity.setValueOjb(true);
        datas.add(plcEntity);

        PlcEntity plcEntity1=new PlcEntity();
        plcEntity1.setName("6");
        plcEntity1.setValueOjb(false);
        datas.add(plcEntity1);

        PlcEntity plcEntity2=new PlcEntity();
        plcEntity2.setName("5");
        plcEntity2.setValueOjb(false);
        datas.add(plcEntity2);

        PlcEntity plcEntity3=new PlcEntity();
        plcEntity3.setName("4");
        plcEntity3.setValueOjb(false);
        datas.add(plcEntity3);


        PlcEntity plcEntity4=new PlcEntity();
        plcEntity4.setName("1");
        plcEntity4.setValueOjb(false);
        datas.add(plcEntity4);


        PlcEntity plcEntity5=new PlcEntity();
        plcEntity5.setName("2");
        plcEntity5.setValueOjb(false);
        datas.add(plcEntity5);

        PlcEntity plcEntity6=new PlcEntity();
        plcEntity6.setName("3");
        plcEntity6.setValueOjb(false);
        datas.add(plcEntity6);

/*
         List<PlcEntity> datas = plc4xEquipmentStatusService.getDatas();
*/
        datas.stream().forEach(p -> {
            if ("onLine".equals(p.getName())) {
                p.setName("在线");
            }else if ("running".equals(p.getName())){
                p.setName("运行中");
            }else if ("humanModel".equals(p.getName())){
                p.setName("手动操作");
            }else if ("autoModel".equals(p.getName())){
                p.setName("自动操作");
            }else if ("systemAlarm".equals(p.getName())){
                p.setName("系统报警");
            }else if ("safeAlarm".equals(p.getName())){
                p.setName("安全报警");
            }
        });
        return datas;
    }
    /**
     *
     * @return 获取服务器上当前的数据
     */

    @Resource
    Plc4xCurveStatusService plc4xCurveStatusService;
    @ResponseBody
    @GetMapping("/getCurveStatus")
    List<PlcEntity> getCurveData(){
/*
        List<PlcEntity> datas = plc4xCurveStatusService.getDatas();
*/
        List<PlcEntity> datas=new ArrayList<>();
        PlcEntity plcEntity=new PlcEntity();
        plcEntity.setName("position");
        PlcEntity plcEntity1=new PlcEntity();
        plcEntity1.setName("pressForce");
        PlcEntity plcEntity2=new PlcEntity();
        plcEntity2.setName("curSpeed");
        plcEntity2.setValueOjb(56456);
        datas.add(plcEntity);
        datas.add(plcEntity1);
        datas.add(plcEntity2);
        datas.stream().forEach(p->{
            if ("position".equals(p.getName())){
                p.setName("当前位置");
            }
            else if ("pressForce".equals(p.getName())){
                p.setName("当前压力");
            }
            else if ("curSpeed".equals(p.getName())){
                p.setName("当前速度");
            }
        });
        return datas;
    }

    @Resource
    Plc4xEquipmentIoStatusService plc4xEquipmentIoStatusService;
    @ResponseBody
    @GetMapping("/EquipmentIoStatus")
    /**
     * 获取设备io监控
     *
     * 页面中文LABEL: 输入_0 ,值变量名: input0 ,值类型 WORD
     页面中文LABEL: 输入_1 ,值变量名: input1 ,值类型 WORD
     页面中文LABEL: 输入_2 ,值变量名: input2 ,值类型 WORD
     页面中文LABEL: 输入_3 ,值变量名: input3 ,值类型 WORD
     页面中文LABEL: 输入_4 ,值变量名: input4 ,值类型 WORD
     页面中文LABEL: 输出_0 ,值变量名: output0 ,值类型 WORD
     页面中文LABEL: 输出_1 ,值变量名: output1 ,值类型 WORD
     页面中文LABEL: 输出_2 ,值变量名: output2 ,值类型 WORD
     页面中文LABEL: 输出_3 ,值变量名: output3 ,值类型 WORD
     页面中文LABEL: 输出_4 ,值变量名: output4 ,值类型 WORD
     */
    List<PlcEntity> getEquipmentIo(){

/*
        List<PlcEntity> datas = plc4xEquipmentIoStatusService.getDatas();
*/
        List<PlcEntity> datas = new ArrayList<>();
        PlcEntity plcEntity=new PlcEntity();
        plcEntity.setName("input0");
        plcEntity.setValueOjb(true);
        PlcEntity plcEntity1=new PlcEntity();
        plcEntity1.setName("input1");
        plcEntity1.setValueOjb(false);
        PlcEntity plcEntity2=new PlcEntity();
        plcEntity2.setName("input2");
        plcEntity2.setValueOjb(true);
        datas.add(plcEntity);
        datas.add(plcEntity1);
        datas.add(plcEntity2);


        datas.stream().forEach(p-> {
            if ("input0".equals(p.getName())) {
                p.setName("输入_0");
            } else if ("input1".equals(p.getName())) {
                p.setName("输入_1");
            } else if ("input2".equals(p.getName())) {
                p.setName("输入_2");
            } else if ("input3".equals(p.getName())) {
                p.setName("输入_3");
            } else if ("input4".equals(p.getName())) {
                p.setName("输入_4");
            } else if ("输出_0".equals(p.getName())) {
                p.setName("output0");
            } else if ("output1".equals(p.getName())) {
                p.setName("输出_1");
            } else if ("output2".equals(p.getName())) {
                p.setName("输出_2");
            } else if ("output3".equals(p.getName())) {
                p.setName("输出_3");
            } else if ("output4".equals(p.getName())) {
                p.setName("输出_4");
            }
        });

        return datas;
    }
    @GetMapping("/getCurveDatas")
    /**
     * 获取压装曲线监控
     */
    List<PressureCurveEntity> getCurveDatas(){
        return plc4xCurveDataService.getCurveDatas();
    }




    /**
     *  获得安全监控
     * @return
     */
    @ResponseBody
    @GetMapping("/getOperation")
    public List<PlcEntity> getDatas(){

/*
        List<PlcEntity> datas = plc4xEquipmentOperationService.getDatas();
*/
        List<PlcEntity> datas = new ArrayList<>();
        PlcEntity plcEntity=new PlcEntity();
        plcEntity.setValueOjb(true);
        plcEntity.setName("零件号");
        datas.add(plcEntity);

        datas.stream().forEach(p->{
            if ("productNo".equals(p.getName())){
                p.setName("零件号");
            }else if ("indenterChoice".equals(p.getName())){
                p.setName("压头选择");
            }else if ("choicePositive".equals(p.getName())){
                p.setName("选择正压");
            }else if ("choiceNegative".equals(p.getName())){
                p.setName("选择反压");
            }else if ("rasterClose".equals(p.getName())){
                p.setName("光栅屏蔽");
            }else if ("safeDoorClose".equals(p.getName())){
                p.setName("安全门屏蔽");
            }else if ("buzzerClose".equals(p.getName())){
                p.setName("蜂鸣器屏蔽");
            };
        });

        return datas;
    }
}
