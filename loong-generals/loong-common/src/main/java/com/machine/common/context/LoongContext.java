package com.machine.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

public class LoongContext {

    private static final ThreadLocal<LoongContext> THREAD_LOCAL = new TransmittableThreadLocal<>();

    private String userId;


    public static LoongContext getContext() {
        LoongContext result = THREAD_LOCAL.get();

        if (result == null) {
            result = new LoongContext();
            THREAD_LOCAL.set(result);
        }

        return result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
