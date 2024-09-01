package com.machine.client.data.label;

import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "loong-data-service/loong-data-service/server/label", configuration = LoongFeignConfig.class)
public interface ILoongLabelClient {
}
