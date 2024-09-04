package com.machine.test.temp.main;

import com.machine.test.temp.agent.LoongCookie;

public class LoongAgentTest {
    public static void main(String[] args) {
        LoongCookie cookie = new LoongCookie();
        cookie.setPath("0000/");
        System.out.println(cookie.getPath());
    }
}
