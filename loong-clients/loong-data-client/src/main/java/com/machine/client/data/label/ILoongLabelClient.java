package com.machine.client.data.label;

import com.machine.common.context.LoongOpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "loong-data-service/loong-data-service/server/label", configuration = LoongOpenFeignConfig.class)
public interface ILoongLabelClient {
}
