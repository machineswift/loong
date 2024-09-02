package com.machine.server.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LoongGatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(LoongGatewayServer.class, args);
    }

}
