package com.plc.hmi;

import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.service.PressureProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PressureProgramTest {
    @Autowired
    private PressureProgramService service;

    @Test
    public void testSql() {
        PressureProgramEntity entity = new PressureProgramEntity();
        entity.setId(2L);
        entity.setProductId(1L);
        entity.setProductCode("prodCode");

        entity.setStep1(1);
        entity.setProgramType1("1");
        entity.setProgramValue1(new BigDecimal(1.1));
        entity.setSpeed1(new BigDecimal(1.2));
        entity.setAlarmDealType1(1);
        entity.setPosition1(new BigDecimal(1.3));
        entity.setProtectPress1(new BigDecimal(1.4));
        entity.setPress1(new BigDecimal(1.5));
        entity.setProtectPosition1(new BigDecimal(1.6));
        entity.setProtectTime1(new BigDecimal(1));

        entity.setStep2(2);
        entity.setProgramType2("2");
        entity.setProgramValue2(new BigDecimal(2.2));
        entity.setSpeed2(new BigDecimal(2.2));
        entity.setAlarmDealType2(2);
        entity.setPosition2(new BigDecimal(2.3));
        entity.setProtectPress2(new BigDecimal(2.4));
        entity.setPress2(new BigDecimal(2.5));
        entity.setProtectPosition2(new BigDecimal(2.6));
        entity.setProtectTime2(new BigDecimal(2));

        entity.setStep3(3);
        entity.setProgramType3("3");
        entity.setProgramValue3(new BigDecimal(3.1));
        entity.setSpeed3(new BigDecimal(3.2));
        entity.setAlarmDealType3(3);
        entity.setPosition3(new BigDecimal(3.3));
        entity.setProtectPress3(new BigDecimal(3.4));
        entity.setPress3(new BigDecimal(3.5));
        entity.setProtectPosition3(new BigDecimal(3.6));
        entity.setProtectTime3(new BigDecimal(3));

        entity.setStep4(4);
        entity.setProgramType4("4");
        entity.setProgramValue4(new BigDecimal(4.1));
        entity.setSpeed4(new BigDecimal(4.2));
        entity.setAlarmDealType4(4);
        entity.setPosition4(new BigDecimal(4.3));
        entity.setProtectPress4(new BigDecimal(4.4));
        entity.setPress4(new BigDecimal(4.5));
        entity.setProtectPosition4(new BigDecimal(4.6));
        entity.setProtectTime4(new BigDecimal(4));

        entity.setStep5(5);
        entity.setProgramType5("5");
        entity.setProgramValue5(new BigDecimal(5.1));
        entity.setSpeed5(new BigDecimal(5.2));
        entity.setAlarmDealType5(5);
        entity.setPosition5(new BigDecimal(5.3));
        entity.setProtectPress5(new BigDecimal(5.4));
        entity.setPress5(new BigDecimal(5.5));
        entity.setProtectPosition5(new BigDecimal(5.6));
        entity.setProtectTime5(new BigDecimal(5));

        entity.setStep6(6);
        entity.setProgramType6("6");
        entity.setProgramValue6(new BigDecimal(6.1));
        entity.setSpeed6(new BigDecimal(6.2));
        entity.setAlarmDealType6(6);
        entity.setPosition6(new BigDecimal(6.3));
        entity.setProtectPress6(new BigDecimal(6.4));
        entity.setPress6(new BigDecimal(6.5));
        entity.setProtectPosition6(new BigDecimal(6.6));
        entity.setProtectTime6(new BigDecimal(6));

        entity.setStep7(7);
        entity.setProgramType7("7");
        entity.setProgramValue7(new BigDecimal(7.1));
        entity.setSpeed7(new BigDecimal(7.2));
        entity.setAlarmDealType7(7);
        entity.setPosition7(new BigDecimal(7.3));
        entity.setProtectPress7(new BigDecimal(7.4));
        entity.setPress7(new BigDecimal(7.5));
        entity.setProtectPosition7(new BigDecimal(7.6));
        entity.setProtectTime7(new BigDecimal(7));

        entity.setStep8(8);
        entity.setProgramType8("8");
        entity.setProgramValue8(new BigDecimal(8.8));
        entity.setSpeed8(new BigDecimal(8.2));
        entity.setAlarmDealType8(8);
        entity.setPosition8(new BigDecimal(8.3));
        entity.setProtectPress8(new BigDecimal(8.4));
        entity.setPress8(new BigDecimal(8.5));
        entity.setProtectPosition8(new BigDecimal(8.6));
        entity.setProtectTime8(new BigDecimal(8));

        entity.setErrandType1("0");
        entity.setPositionMin1(new BigDecimal(1));
        entity.setPositionMax1(new BigDecimal(9.1));
        entity.setPressMin1(new BigDecimal(1.1));
        entity.setPressMax1(new BigDecimal(99.1));

        entity.setErrandType2("2");
        entity.setPositionMin2(new BigDecimal(2));
        entity.setPositionMax2(new BigDecimal(9.2));
        entity.setPressMin2(new BigDecimal(2.2));
        entity.setPressMax2(new BigDecimal(99.2));

        entity.setErrandType3("3");
        entity.setPositionMin3(new BigDecimal(2));
        entity.setPositionMax3(new BigDecimal(9.3));
        entity.setPressMin3(new BigDecimal(3.3));
        entity.setPressMax3(new BigDecimal(99.3));

        entity.setErrandType4("4");
        entity.setPositionMin4(new BigDecimal(2));
        entity.setPositionMax4(new BigDecimal(9.4));
        entity.setPressMin4(new BigDecimal(4.4));
        entity.setPressMax4(new BigDecimal(99.4));

        entity.setErrandType5("5");
        entity.setPositionMin5(new BigDecimal(2));
        entity.setPositionMax5(new BigDecimal(9.5));
        entity.setPressMin5(new BigDecimal(5.5));
        entity.setPressMax5(new BigDecimal(99.5));



        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
        List<PressureProgramEntity> result = service.getWithProductId(1L);
//        service.update(entity);
//        service.delete(entity);


        System.out.println("END----------------");
    }


}
