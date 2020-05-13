package com.plc.hmi.controler.zkcode;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.plcService.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class Plc4xCurveDataControler {

    @Resource
    Plc4xCurveDataService plc4xCurveDataService;
    @Resource
    Plc4xEquipmentOperationService plc4xEquipmentOperationService;
    @ResponseBody
    @GetMapping("/getCurrentDate")
    PressureCurveEntity getCurrentDate(){
        List<PressureCurveEntity> curveDatas = plc4xCurveDataService.getCurveDatas();
//        System.out.println(curveDatas);
        if(CollectionUtils.isEmpty(curveDatas)) {
            return new PressureCurveEntity();
        } else {
            return curveDatas.get(0);
        }
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
         List<PlcEntity> datas = plc4xEquipmentStatusService.getDatas();
         if(CollectionUtils.isEmpty(datas)) {
             List<PlcEntity> defalutDatas=new ArrayList<>();
             PlcEntity plcEntity=new PlcEntity();
             plcEntity.setName("在线");
             plcEntity.setValueOjb(false);
             defalutDatas.add(plcEntity);

             PlcEntity plcEntity1=new PlcEntity();
             plcEntity1.setName("运行中");
             plcEntity1.setValueOjb(false);
             defalutDatas.add(plcEntity1);

             PlcEntity plcEntity2=new PlcEntity();
             plcEntity2.setName("手动操作");
             plcEntity2.setValueOjb(false);
             defalutDatas.add(plcEntity2);

             PlcEntity plcEntity3=new PlcEntity();
             plcEntity3.setName("自动操作");
             plcEntity3.setValueOjb(false);
             defalutDatas.add(plcEntity3);


             PlcEntity plcEntity4=new PlcEntity();
             plcEntity4.setName("系统报警");
             plcEntity4.setValueOjb(false);
             defalutDatas.add(plcEntity4);


             PlcEntity plcEntity5=new PlcEntity();
             plcEntity5.setName("安全报警");
             plcEntity5.setValueOjb(false);
             defalutDatas.add(plcEntity5);
             datas=defalutDatas;
         }

         datas.stream().forEach(p -> {
             if ("onLine".equals(p.getName())) {
                 p.setName("在线");
             } else if ("running".equals(p.getName())) {
                 p.setName("运行中");
             } else if ("humanModel".equals(p.getName())) {
                 p.setName("手动操作");
             } else if ("autoModel".equals(p.getName())) {
                 p.setName("自动操作");
             } else if ("systemAlarm".equals(p.getName())) {
                 p.setName("系统报警");
             } else if ("safeAlarm".equals(p.getName())) {
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

        List<PlcEntity> datas = plc4xCurveStatusService.getDatas();
/*
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

 */
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
        List<PlcEntity> datas = plc4xEquipmentIoStatusService.getDatas();
        if(CollectionUtils.isEmpty(datas)) {
            List<PlcEntity> defalutDates = new ArrayList<PlcEntity>();
            PlcEntity plcEntity=new PlcEntity();
            plcEntity.setName("input0");
            plcEntity.setValueOjb(false);
            PlcEntity plcEntity1=new PlcEntity();
            plcEntity1.setName("input1");
            plcEntity1.setValueOjb(false);
            PlcEntity plcEntity2=new PlcEntity();
            plcEntity2.setName("input2");
            plcEntity2.setValueOjb(false);
            PlcEntity plcEntity3=new PlcEntity();
            plcEntity3.setName("input3");
            plcEntity3.setValueOjb(false);
            PlcEntity plcEntity4=new PlcEntity();
            plcEntity4.setName("input4");
            plcEntity4.setValueOjb(false);
            PlcEntity plcEntity5=new PlcEntity();
            plcEntity5.setName("output0");
            plcEntity5.setValueOjb(false);
            PlcEntity plcEntity6=new PlcEntity();
            plcEntity6.setName("output1");
            plcEntity6.setValueOjb(false);
            PlcEntity plcEntity7=new PlcEntity();
            plcEntity7.setName("output2");
            plcEntity7.setValueOjb(false);
            PlcEntity plcEntity8=new PlcEntity();
            plcEntity8.setName("output3");
            plcEntity8.setValueOjb(false);
            PlcEntity plcEntity9=new PlcEntity();
            plcEntity9.setName("output4");
            plcEntity9.setValueOjb(false);
            defalutDates.add(plcEntity);
            defalutDates.add(plcEntity1);
            defalutDates.add(plcEntity2);
            defalutDates.add(plcEntity3);
            defalutDates.add(plcEntity4);
            defalutDates.add(plcEntity5);
            defalutDates.add(plcEntity6);
            defalutDates.add(plcEntity7);
            defalutDates.add(plcEntity8);
            defalutDates.add(plcEntity9);
            datas = defalutDates;
        }

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
            } else if ("output0".equals(p.getName())) {
                p.setName("输出_0");
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
        List<PlcEntity> datas = plc4xEquipmentOperationService.getDatas();
        if(CollectionUtils.isEmpty(datas)) {
            List<PlcEntity> defaultDatas = new ArrayList<>();
            PlcEntity plcEntity=new PlcEntity();
            plcEntity.setValueOjb(false);
            plcEntity.setName("零件号");
            PlcEntity plcEntity1=new PlcEntity();
            plcEntity1.setValueOjb(false);
            plcEntity1.setName("压头选择");
            PlcEntity plcEntity2=new PlcEntity();
            plcEntity2.setValueOjb(false);
            plcEntity2.setName("选择正压");
            PlcEntity plcEntity3=new PlcEntity();
            plcEntity3.setValueOjb(false);
            plcEntity3.setName("选择反压");
            PlcEntity plcEntity4=new PlcEntity();
            plcEntity4.setValueOjb(false);
            plcEntity4.setName("光栅屏蔽");
            PlcEntity plcEntity5=new PlcEntity();
            plcEntity5.setValueOjb(false);
            plcEntity5.setName("安全门屏蔽");
            PlcEntity plcEntity6=new PlcEntity();
            plcEntity6.setValueOjb(false);
            plcEntity6.setName("蜂鸣器屏蔽");

            defaultDatas.add(plcEntity);
            defaultDatas.add(plcEntity1);
            defaultDatas.add(plcEntity2);
            defaultDatas.add(plcEntity3);
            defaultDatas.add(plcEntity4);
            defaultDatas.add(plcEntity5);
            defaultDatas.add(plcEntity6);
            datas = defaultDatas;
        } else {
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
        }

        return datas;
    }
}
