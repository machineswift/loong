package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class LoongAppResult<T> {

    public LoongAppResult(int status,
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
     * {@link HttpStatus}
     */
    private int status;

    /**
     * 错误码
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

    public static <T> LoongAppResult<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> LoongAppResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> LoongAppResult<T> success(String msg, T data) {
        return generate(HttpStatus.OK.value(), "SUCCESS", msg, data);
    }

    public static <T> LoongAppResult<T> fail(String code,
                                             String msg) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), code, msg);
    }

    public static <T> LoongAppResult<T> fail(int status,
                                             String code,
                                             String msg) {
        return fail(status, code, msg, null);
    }

    public static <T> LoongAppResult<T> fail(int status,
                                             String code,
                                             String msg,
                                             T data) {
        return generate(status, code, msg, data);
    }


    public static <T> LoongAppResult<T> generate(int status,
                                                 String code,
                                                 String msg,
                                                 T data) {
        return new LoongAppResult<>(status, code, msg, data);
    }

}
