package com.plc.hmi;

import com.plc.hmi.dal.entity.HisAlarmEntity;
import com.plc.hmi.service.HisAlarmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class HisAlarmTest {
    @Autowired
    private HisAlarmService service;



    @Test
    public void testSql() {
        HisAlarmEntity entity = new HisAlarmEntity();
        entity.setId(1L);
        entity.setAlarmId(10032L);
        entity.setAlarmStartTime(new Date());
        entity.setAlarmStopTime(new Date());
        entity.setAlarmStatus("0");
        entity.setAlarmCfmStatus("1");
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
        List<HisAlarmEntity> result = service.getHisAlarm();
//        entity.setAlarmStatus("1");
//        entity.setAlarmCfmStatus("0");
//        service.update(entity);
//        service.delete(entity);


        System.out.println("END----------------");
    }


}
