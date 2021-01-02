package com.lagou.ssm_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lagou.dao")
public class SsmSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmSpringbootApplication.class, args);
    }

}
