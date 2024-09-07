package com.machine.common.exception.data;

import com.machine.common.exception.BusinessException;

public class DataBusinessException extends BusinessException {

    public DataBusinessException(String code,
                                 String message) {
        super(code, message);
    }

    public DataBusinessException(String code,
                                 String message,
                                 Throwable cause) {
        super(code, message, cause);
    }
}
