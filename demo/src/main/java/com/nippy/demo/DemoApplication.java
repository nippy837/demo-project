package com.nippy.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 告诉 Spring去 com.nippy.demo.mapper 包下找所有 Mapper 接口并注册成 Bean
@MapperScan("com.nippy.demo.mapper")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
