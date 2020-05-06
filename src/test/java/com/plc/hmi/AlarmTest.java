package com.plc.hmi;

import com.plc.hmi.dal.entity.AlarmEntity;
import com.plc.hmi.service.AlarmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

//@SpringBootTest
public class AlarmTest {
    @Autowired
    private AlarmService service;



//    @Test
    public void testSql() {
        /*
        AlarmEntity entity = new AlarmEntity();
        entity.setId(1L);
        entity.setTriggerDb(100);
        entity.setTriggerOffset(1);
        entity.setTriggerBit(2);
        entity.setAlarmType("E");
        entity.setAlarmGroup(3);
        entity.setActive("0");
        entity.setAlarmInfo("INFOOOOOO");
        entity.setAlarmHelp("手工排除");
        entity.setAlarmStatus("0");
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        service.insert(entity);
        List<AlarmEntity> result = service.getAlarm();
//        entity.setAlarmStatus("1");
//        service.update(entity);
//        service.delete(entity);
*/

        System.out.println("END----------------");
    }


}
