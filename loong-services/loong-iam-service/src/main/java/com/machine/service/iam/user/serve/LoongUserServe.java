package com.machine.service.iam.user.serve;

import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.LoongUserUpdatePasswordDto;
import com.machine.service.iam.user.service.ILoongUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("serve/user")
public class LoongUserServe implements ILoongUserClient {

    @Autowired
    private ILoongUserService userService;

    @Override
    @PutMapping("updatePassword")
    public int updatePassword(@RequestBody LoongUserUpdatePasswordDto dto) {
        return userService.updatePassword(dto);
    }

    @Override
    @GetMapping("detail")
    public LoongUserDetailDto detail(@RequestParam("userId") String userId) {
        return userService.detail(userId);
    }

    @Override
    @GetMapping("getByUserName")
    public LoongUserDto getByUserName(@RequestParam("userName") String userName) {
        return userService.getByUserName(userName);
    }
}
