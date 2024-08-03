package com.machine.client.iam.user;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.LoongUserUpdatePasswordDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loong-iam-service/loong-iam-service/serve/user")
public interface ILoongUserClient {

    @PostMapping("updatePassword")
    int updatePassword(@RequestBody LoongUserUpdatePasswordDto dto);

    @GetMapping("detail")
    LoongUserDetailDto detail(@RequestParam("userId") String userId);

    @GetMapping("getByUserName")
    LoongUserDto getByUserName(@RequestParam("userName") String userName);


}
