package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.HttpURLConnection;

@Data
@NoArgsConstructor
public class LoongAppResult<T> {

    public LoongAppResult(int status,
                          String message,
                          T data) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    /**
     * 状态码
     * {@link HttpURLConnection}
     */
    private int status;

    /**
     * 消息内容
     */
    private String message;

    private Long timestamp;

    /**
     * 链路追踪Id
     */
    private String traceId;

    private T data;

    public static <T> LoongAppResult<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> LoongAppResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> LoongAppResult<T> success(String msg, T data) {
        return generate(HttpURLConnection.HTTP_OK, msg, data);
    }

    public static <T> LoongAppResult<T> fail(String msg) {
        return fail(msg, null);
    }

    public static <T> LoongAppResult<T> fail(int status, String msg) {
        return generate(status, msg, null);
    }

    public static <T> LoongAppResult<T> fail(String msg, T data) {
        return generate(HttpURLConnection.HTTP_INTERNAL_ERROR, msg, data);
    }


    public static <T> LoongAppResult<T> generate(int status, String msg, T data) {
        return new LoongAppResult<>(status, msg, data);
    }

}
