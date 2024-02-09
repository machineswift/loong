package com.machine.service.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.machine.common",
        "com.machine.service"
})
public class LoongDataServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(LoongDataServiceApp.class, args);
    }

}
