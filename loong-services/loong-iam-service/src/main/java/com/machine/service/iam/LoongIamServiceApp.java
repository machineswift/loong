package com.machine.service.iam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.machine.service.iam.area.mapper")
public class LoongIamServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongIamServiceApp.class, args);
    }

}
