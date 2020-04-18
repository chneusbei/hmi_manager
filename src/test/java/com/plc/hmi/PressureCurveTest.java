package com.plc.hmi;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.thread.PressCurveThread;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PressureCurveTest {
    @Autowired
    private PressureCurveService service;

    PressCurveThread pressCurveThread;



    @Test
    public void testSql() {

        List<PressureCurveEntity> entityList = new ArrayList<PressureCurveEntity>();
        for(int i=1; i<11; i++) {
            PressureCurveEntity entity = new PressureCurveEntity();
//        entity.setId(1L);
            entity.setPressDataId(1L);
            entity.setRecordNo(3);
            entity.setPosition(new BigDecimal(3.0*i));
            entity.setPressForce(new BigDecimal(0.5*i));
            entity.setPressDate(new BigDecimal(20200409232606003L+(i*1000)));
            entity.setCurSpeed(new BigDecimal(1.23));
            entity.setHandleDate("20200409");
            entity.setUpdateBy("SYS");
            entity.setCreateBy("SYS");
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            entityList.add(entity);
        }
        service.curve2queue(entityList);
        pressCurveThread = new PressCurveThread(service) ;
        pressCurveThread.run();
//        service.batchInsert(entityList);
//        service.insert(entity);
//        List<PressureCurveEntity> resultList = service.getCurrDate();
//        List<PressureCurveEntity> hisList =service.getHisDateByCode(1L);
        System.out.println("END----------------");

        /*
        java.util.concurrent.ConcurrentLinkedDeque<String>
                deque = new java.util.concurrent.ConcurrentLinkedDeque<String>();
        List<String> list = new ArrayList<String>();
        List<String> list1 = new ArrayList<String>();
        for(int i=0; i<5; i++) {
            list.add("String"+(i+1));
            list1.add("BBBB"+(i+1));
        }
        deque.addAll(list);
        deque.addAll(list1);
        deque.addFirst("frist");
        deque.addLast("last");
        list1.clear();
        String temp = deque.getFirst();
        String temp0 = deque.getLast();
        String temp1=deque.peek();
        String temp2=deque.peekFirst();
        String temp3 =deque.peekLast();
        String temp4=deque.removeFirst();//这个
        String temp7=deque.removeLast();
        String temp9=deque.remove();
        System.out.println("END----------------");

         */
    }

//    @Test
//    private void testQueue() {
////        java.util.concurrent.ConcurrentLinkedQueue quee ;
//
//
//
//    }


}
