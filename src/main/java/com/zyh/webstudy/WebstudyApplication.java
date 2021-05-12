package com.zyh.webstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.zyh.webstudy.mapper")
@SpringBootApplication
@EnableTransactionManagement // 开启事务
public class WebstudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebstudyApplication.class, args);
    }

}
