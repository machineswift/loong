package com.machine.common.exception.data;

import com.machine.common.exception.BusinessException;

public  class DataBusinessException extends BusinessException {

    public DataBusinessException(String message) {
        super(message);
    }

    public DataBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBusinessException(Throwable cause) {
        super(cause);
    }
}
