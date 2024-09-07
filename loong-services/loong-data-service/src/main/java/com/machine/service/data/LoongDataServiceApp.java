package com.machine.service.data;

import com.machine.common.config.LoongWebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = {
        "com.machine.client"
})
@SpringBootApplication(scanBasePackages = {
        "com.machine.starter",
        "com.machine.service.data"
})

@Import({LoongWebMvcConfig.class})
public class LoongDataServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongDataServiceApp.class, args);
    }

}
