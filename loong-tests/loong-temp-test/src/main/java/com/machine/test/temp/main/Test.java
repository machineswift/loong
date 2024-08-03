package com.machine.test.temp.main;

import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Date;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {

        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));


        System.out.println(new Date().getTime());

        System.out.println(System.currentTimeMillis());
        System.out.println(SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8().encode("123456"));
        System.out.println(System.currentTimeMillis());
        System.out.println(SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8().encode("123456"));        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println(SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8().encode("123456"));

    }
}
