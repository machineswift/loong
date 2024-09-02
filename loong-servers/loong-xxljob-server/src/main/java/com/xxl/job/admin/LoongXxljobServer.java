package com.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoongXxljobServer {

    public static void main(String[] args) {
        SpringApplication.run(LoongXxljobServer.class, args);
    }

}
