package com.machine.common.config;

import cn.hutool.json.JSONUtil;
import com.machine.common.exception.BusinessException;
import com.machine.common.model.LoongAppResult;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class LoongErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey,
                            Response response) {
        Exception exception = super.decode(methodKey, response);
        if (exception instanceof RetryableException retryableException) {
            return retryableException;
        }

        FeignException feignException = (FeignException) exception;
        String contentUTF8 = feignException.contentUTF8();
        if(null == contentUTF8 || contentUTF8.trim().isEmpty()){
            return feignException;
        }
        LoongAppResult<Void> result = JSONUtil.toBean(feignException.contentUTF8(), LoongAppResult.class);
        return new BusinessException(result.getCode(), result.getMessage());
    }
}
