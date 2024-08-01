package com.machine.service.iam.user.serve;

import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.service.iam.user.service.ILoongUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("serve/user")
public class LoongUserServe implements ILoongUserClient {

    @Autowired
    private ILoongUserService loongUserService;

    @Override
    @GetMapping("detail")
    public LoongUserDetailDto detail(@RequestParam("userId") String userId) {
        return loongUserService.detail(userId);
    }
}
