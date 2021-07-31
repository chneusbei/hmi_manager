package com.plc.hmi;

import com.plc.hmi.service.plcService.Plc4xCurveDataService;
import com.plc.hmi.service.plcService.Plc4xEquipmentStatusService;
import com.plc.hmi.util.HmiUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.util.*;

//@SpringBootTest
public class Test {
//    @Autowired
//    Plc4xCurveDataService plc4xEquipmentOperationService;
//    @Autowired
//    Plc4xEquipmentStatusService plc4xEquipmentStatusService;


    @org.junit.jupiter.api.Test
    public void testSql() {
        StringBuffer sb  = new StringBuffer();
        sb.append("abcdefghijk%");
        String s = sb.substring(0, sb.length()-1);
        System.out.println(s);

//        PersistenceExceptionTranslationPostProcessor p;
        /*
         int a = Integer.valueOf(123).intValue();
         float f = 100.01f;
         BigDecimal b = new BigDecimal(1.00);
        ByteBuf buf = Unpooled.buffer(4);
        buf.writeFloat(f);
        byte[] bytes = buf.array();
        System.out.println(bytes);
*/
//       String str =  HmiUtils.getFormatDateString();
//        String str = HmiUtils.getMillFormatDateString(new Date());
//        BigDecimal one = new BigDecimal(1L);
//        BigDecimal one2 = new BigDecimal(1L);
//        BigDecimal zero = new BigDecimal(0L);
//        int a = one.compareTo(one2);
//        int b = one.compareTo(zero);
//        int c = zero.compareTo(one2);

//        Map<Integer, List<Boolean>> errandResltMap = new HashMap<Integer, List<Boolean>>();
//        errandResltMap.put(1,new ArrayList<Boolean>());
//        errandResltMap.put(2,new ArrayList<Boolean>());
//        errandResltMap.put(3,new ArrayList<Boolean>());
//        errandResltMap.put(4,new ArrayList<Boolean>());
//        errandResltMap.put(5,new ArrayList<Boolean>());
//        errandResltMap.get(1).add(new Boolean(true));
//        Boolean b = errandResltMap.get(1).get(0) ;
//        b =new Boolean(false);
        System.out.println("===================================");

    }


}
