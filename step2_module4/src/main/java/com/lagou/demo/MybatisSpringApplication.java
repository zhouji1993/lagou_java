package com.lagou.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lagou.dao")
public class MybatisSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisSpringApplication.class, args);
    }

}
