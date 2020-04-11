package com.plc.hmi;

import com.plc.hmi.S7Connector.service.PlcConnectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class HmiApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(HmiApplicationTests.class);
//    @Autowired
//    private PlcConnectorService plcConnectorService;
    @Test
    void contextLoads() {

        logger.info("TEST >>>>>>>>....",this);
    }

}
