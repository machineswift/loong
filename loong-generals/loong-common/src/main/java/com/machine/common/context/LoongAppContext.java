package com.machine.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

public class LoongAppContext {

    private static final ThreadLocal<LoongAppContext> THREAD_LOCAL = new TransmittableThreadLocal<>();

    private String userId;

    public static LoongAppContext getContext() {
        LoongAppContext result = THREAD_LOCAL.get();

        if (result == null) {
            result = new LoongAppContext();
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
