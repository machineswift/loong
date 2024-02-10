package com.machine.service.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {
        "com.machine.service"
})
public class LoongDataServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongDataServiceApp.class, args);
    }

}
