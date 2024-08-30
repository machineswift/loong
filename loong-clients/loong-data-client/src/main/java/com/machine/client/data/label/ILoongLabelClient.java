package com.machine.client.data.label;

import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "loong-iam-service/loong-data-service/serve/label", configuration = LoongFeignConfig.class)
public interface ILoongLabelClient {
}
