package com.machine.service.crm;

import com.machine.common.config.LoongWebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
        "com.machine.client"
})
@SpringBootApplication(scanBasePackages = {
        "com.machine.starter",
        "com.machine.service.crm"
})
@Import(LoongWebMvcConfig.class)
@EnableTransactionManagement
public class LoongCrmServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongCrmServiceApp.class, args);
    }

}
