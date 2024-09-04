package com.machine.agent;

import com.machine.agent.transformer.CookieTransformer;
import com.machine.agent.transformer.LoongCookieTransformer;

import java.lang.instrument.Instrumentation;

public class LoongAgentMain {

    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new CookieTransformer());
        instrumentation.addTransformer(new LoongCookieTransformer());
    }
}
