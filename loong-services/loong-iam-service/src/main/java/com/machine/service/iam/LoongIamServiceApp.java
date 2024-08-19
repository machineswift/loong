package com.machine.service.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {
        "com.machine.common",
        "com.machine.starter",
        "com.machine.service"
})
public class LoongIamServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongIamServiceApp.class, args);
    }

}
