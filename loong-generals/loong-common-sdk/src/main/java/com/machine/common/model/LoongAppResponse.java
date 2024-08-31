package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.HttpURLConnection;

@Data
@NoArgsConstructor
public class LoongAppResponse<T> {

    public LoongAppResponse(int status,
                            String code,
                            String message,
                            T data) {
        this.status = status;
        this.code = code;
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
     * code码(成功:SUCCESS)
     */
    private String code;

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


    public static <T> LoongAppResponse<T> data(T data) {
        return data("操作成功", data);
    }

    public static <T> LoongAppResponse<T> data(String msg, T data) {
        return data(HttpURLConnection.HTTP_OK, "SUCCESS", msg, data);
    }

    public static <T> LoongAppResponse<T> data(int status, String code, String msg, T data) {
        return new LoongAppResponse<>(status, code, msg, data);
    }
}
