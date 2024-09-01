package com.machine.client.data.leaf;

import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loong-data-service/loong-data-service/server/leaf", configuration = LoongFeignConfig.class)
public interface ILoongLeafClient {

    @GetMapping("hykh")
    String getHykh();

}
