package com.plc.hmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimeZone;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class HmiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(HmiApplication.class, args);
    }
    @RequestMapping("/")
    public String hello() {
        return "test word of chensubei";
    }

}
