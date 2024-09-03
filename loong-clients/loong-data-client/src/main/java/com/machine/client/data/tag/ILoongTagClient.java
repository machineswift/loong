package com.machine.client.data.tag;

import com.machine.common.context.LoongOpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "loong-iam-service/loong-data-service/serve/tag", configuration = LoongOpenFeignConfig.class)
public interface ILoongTagClient {
}
