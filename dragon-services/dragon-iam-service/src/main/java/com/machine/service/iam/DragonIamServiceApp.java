package com.machine.service.iam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.machine.service.iam.area.mapper")
public class DragonIamServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(DragonIamServiceApp.class, args);
    }

}
