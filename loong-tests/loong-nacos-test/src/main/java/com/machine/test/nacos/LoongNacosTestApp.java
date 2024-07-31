package com.machine.test.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoongNacosTestApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongNacosTestApp.class, args);
    }

}
