package com.machine.server.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoongGatewayServerApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongGatewayServerApp.class, args);
    }

}