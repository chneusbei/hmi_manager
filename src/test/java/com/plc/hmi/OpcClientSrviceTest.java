package com.plc.hmi;

import com.plc.hmi.service.OpcClientService;
import com.plc.hmi.dto.Result;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OpcClientSrviceTest {
    private static Logger logger = LoggerFactory.getLogger(PlcConnectserviceTest.class);

    @Autowired
    private OpcClientService opcClientService;

    @Test
    public void testOpcClient() {
        String host = "127.0.0.1";// server
        String domain = "";// domain
        String progId = "OPC.SimaticNET.S7OPT";//"ICONICS.SimulatorOPCDA.2"
        String user = "";// server上的访问用户OpcUser
        String password = "";// 访问用户的密码xxxxxx

        OpcClientService opcClient = new OpcClientService();
// 1.显示server上的opc server应用列表
        opcClient.showAllOPCServer(host, user, password, domain);

// 2.连接指定的opc server
        boolean ret = opcClient.connectServer(host, progId, user, password, domain);
        if (!ret) {
            System.out.println("connect opc server fail");
            return;
        }

// 3.检查opc server上的检测点
        List<String> itemIdList = new ArrayList<String>();
        itemIdList.add("TEST.FA");
        itemIdList.add("TEST.FB");
        ret = opcClient.checkItemList(itemIdList);
        if (!ret) {
            System.out.println("not contain item list");
            return;
        }

// 4.注册回调
        opcClient.subscribe(new Observer() {
            @Override
            public void update(Observable observable, Object arg) {
                Result result = (Result) arg;
                System.out.println("update result=" + result);
            }
        });

// 5.添加监听检测点的数据
// client和server在不同网段，可以访问
        opcClient.syncReadObject("TEST.FA", 500);
/**
 * TODO 问题
 * client和server在不同网段，访问失败，比如：server为10.1.1.132，该网段下面又连接了扩展路由器，192.168.1.x，client为192.168.1.100
 */
        opcClient.asyncReadObject("TEST.FB", 500);

// 延迟
//        delay(5 * 60 * 1000);
        System.out.println("TEST>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info("TEST>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


//    private static void delay(long time) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
