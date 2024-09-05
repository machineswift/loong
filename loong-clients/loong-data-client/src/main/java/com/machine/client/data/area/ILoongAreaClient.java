package com.machine.client.data.area;

import com.machine.common.config.LoongOpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loong-data-service/loong-data-service/server/area", configuration = LoongOpenFeignConfig.class)
public interface ILoongAreaClient {

    @GetMapping("init")
    void init();
}
