package com.machine.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

public class LoongAuthContext {

    private static final ThreadLocal<LoongAuthContext> THREAD_LOCAL = new TransmittableThreadLocal<>();

    private String userId;

    public static LoongAuthContext getContext() {
        LoongAuthContext result = THREAD_LOCAL.get();

        if (result == null) {
            result = new LoongAuthContext();
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

    public void clear() {
        THREAD_LOCAL.remove();
    }
}
