package com.plc.hmi.service;

import com.plc.hmi.HmiApplication;
import com.plc.hmi.util.HmiUtils;
import org.springframework.boot.SpringApplication;

import java.math.BigDecimal;

public class TestMain {
    public static void main(String[] args) {

        BigDecimal b2 = new BigDecimal(20);
        System.out.println(HmiUtils.getStringNoNull(b2));
    }
}
