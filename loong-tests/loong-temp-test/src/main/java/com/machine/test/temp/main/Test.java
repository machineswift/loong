package com.machine.test.temp.main;

import java.util.Date;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {

        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));


        System.out.println(new Date().getTime());
    }
}
