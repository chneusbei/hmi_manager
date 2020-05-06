package com.plc.hmi;


import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 测试邮件发送服务
 * <p>
 * 注意LitemallNotifyService采用异步线程操作
 * 因此测试的时候需要睡眠一会儿，保证任务执行
 * <p>
 * 开发者需要确保：
 * 1. 在相应的邮件服务器设置正确notify.properties已经设置正确
 * 2. 在相应的邮件服务器设置正确
 */
//@SpringBootTest
public class PlcConnectserviceTest {
    private static Logger logger = LoggerFactory.getLogger(PlcConnectserviceTest.class);
//    @Autowired
//    private PlcConnectorService plcConnectorService;

    @Autowired
    private Plc4xConnectorService plc4xConnectorService;

//    @Test
    public void testPlcService() {
//        plc4xConnectorService.getData();
        System.out.println("TEST>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info("TEST>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    public void testPlcService2() {
//        PlcDataDto plcDataDto =  plcConnectorService.getData();
//        System.out.println("TEST>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        logger.info("TEST>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

//    @Configuration
//    @Import(Application.class)
//    static class ContextConfiguration {
//        @Bean
//        @Primary
//        public Executor executor() {
//            return new SyncTaskExecutor();
//        }
//    }


}
