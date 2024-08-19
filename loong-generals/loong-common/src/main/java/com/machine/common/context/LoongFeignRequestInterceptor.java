package com.machine.common.context;

import com.machine.common.constant.LoongContextConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class LoongFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header(LoongContextConstant.HEAD_USER_ID, LoongAuthContext.getContext().getUserId());
    }

}
