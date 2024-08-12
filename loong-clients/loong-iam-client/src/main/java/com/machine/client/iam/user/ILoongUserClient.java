package com.machine.client.iam.user;

import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.LoongUserUpdatePasswordDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "loong-iam-service/loong-iam-service/serve/user")
public interface ILoongUserClient {

    @PutMapping("updatePassword")
    int updatePassword(@RequestBody LoongUserUpdatePasswordDto dto);

    @GetMapping("detail")
    LoongUserDetailDto detail(@RequestParam("userId") String userId);

    @GetMapping("auth_detail")
    LoongUserAuthDetailDto authDetail(@RequestParam("userId") String userId);


    @GetMapping("getByUserName")
    LoongUserDto getByUserName(@RequestParam("userName") String userName);


}
