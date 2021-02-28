package com.zyh.webstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zyh.webstudy.mapper")
@SpringBootApplication
public class WebstudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebstudyApplication.class, args);
    }

}
