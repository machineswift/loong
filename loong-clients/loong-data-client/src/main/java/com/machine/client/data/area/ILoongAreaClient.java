package com.machine.client.data.area;

import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "loong-data-service/loong-data-service/server/area", configuration = LoongFeignConfig.class)
public interface ILoongAreaClient {

    @GetMapping("init")
    void init();
}
