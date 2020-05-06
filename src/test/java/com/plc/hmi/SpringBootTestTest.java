package com.plc.hmi;
import org.junit.jupiter.api.Test;
class SpringBootTestTest {

//    @Test
    public void test(){
//        System.out.println(isOdd(3)) ;
}
    public boolean isOdd(int a){
        if((a&1) == 1){   //是奇数
            return true;
        }
        return false;
    }
}