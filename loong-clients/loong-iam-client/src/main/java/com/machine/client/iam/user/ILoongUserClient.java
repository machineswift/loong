package com.machine.client.iam.user;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "loong-iam-service", path = "loong-iam-service/serve/user")
public interface ILoongUserClient {

    @GetMapping("detail")
    LoongUserDetailDto detail(@RequestParam("userId") String userId);

}
