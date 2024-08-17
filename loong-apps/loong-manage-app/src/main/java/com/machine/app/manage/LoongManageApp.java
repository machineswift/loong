package com.machine.app.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoongManageApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongManageApp.class, args);
    }

}
