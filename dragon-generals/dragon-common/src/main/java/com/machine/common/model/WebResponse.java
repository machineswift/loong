package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WebResponse<T> {
    private int status;
    private String traceId;
    private String message;
    private LocalDateTime now = LocalDateTime.now();
    private String url;
    private T data;
}
