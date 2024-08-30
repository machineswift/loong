package com.machine.client.data.tag;

import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "loong-iam-service/loong-data-service/serve/tag", configuration = LoongFeignConfig.class)
public interface ILoongTagClient {
}
