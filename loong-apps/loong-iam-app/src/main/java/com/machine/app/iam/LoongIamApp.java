package com.machine.app.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.machine.client")
public class LoongIamApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongIamApp.class, args);
    }

}
