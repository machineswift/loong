package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LoongAppResponse<T> {
    /**
     * 状态码
     */
    private int code;
    private String requestId;
    private String message;
    private LocalDateTime now = LocalDateTime.now();
    private T data;
}
