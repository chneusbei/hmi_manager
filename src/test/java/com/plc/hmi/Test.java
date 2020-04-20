package com.plc.hmi;

import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xEquipmentStatusService;
import com.plc.hmi.util.HmiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class Test {
//    @Autowired
//    Plc4xCurveDataService plc4xEquipmentOperationService;
//    @Autowired
//    Plc4xEquipmentStatusService plc4xEquipmentStatusService;


    @org.junit.jupiter.api.Test
    public void testSql() {

//       String str =  HmiUtils.getFormatDateString();
        String str = HmiUtils.getMillFormatDateString(new Date());
//        BigDecimal one = new BigDecimal(1L);
//        BigDecimal one2 = new BigDecimal(1L);
//        BigDecimal zero = new BigDecimal(0L);
//        int a = one.compareTo(one2);
//        int b = one.compareTo(zero);
//        int c = zero.compareTo(one2);
        System.out.println("===================================");

    }


}
