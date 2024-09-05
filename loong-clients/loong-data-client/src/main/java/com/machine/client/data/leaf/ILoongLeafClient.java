package com.machine.client.data.leaf;

import com.machine.common.config.LoongOpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loong-data-service/loong-data-service/server/leaf", configuration = LoongOpenFeignConfig.class)
public interface ILoongLeafClient {

    @GetMapping("hykh")
    String getHykh();

}
