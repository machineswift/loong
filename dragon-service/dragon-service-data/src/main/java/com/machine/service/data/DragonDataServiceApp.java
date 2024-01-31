package com.machine.service.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.machine.service.data.area.mapper")
public class DragonDataServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(DragonDataServiceApp.class, args);
    }

}
