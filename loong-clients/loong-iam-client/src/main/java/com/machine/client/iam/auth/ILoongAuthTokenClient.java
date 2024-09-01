package com.machine.client.iam.auth;

import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenUpdateTokenDto;
import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "loong-iam-service/loong-iam-service/server/auth", configuration = LoongFeignConfig.class)
public interface ILoongAuthTokenClient {

    @PostMapping("add")
    int add(@RequestBody LoongAuthTokenAddDto dto);

    @DeleteMapping("deleteByUserName")
    int deleteByUserName(@RequestParam("userName") String userName);

    @PutMapping("updateToken")
    int updateToken(@RequestBody LoongAuthTokenUpdateTokenDto dto);

    @GetMapping("getBySeries")
    LoongAuthTokenDto getBySeries(@RequestParam("series") String series);
}
