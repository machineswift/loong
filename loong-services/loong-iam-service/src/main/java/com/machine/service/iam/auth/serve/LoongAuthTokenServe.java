package com.machine.service.iam.auth.serve;

import com.machine.client.iam.auth.ILoongAuthTokenClient;
import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenUpdateTokenDto;
import com.machine.service.iam.auth.service.ILoongAuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("serve/auth")
public class LoongAuthTokenServe implements ILoongAuthTokenClient {

    @Autowired
    private ILoongAuthTokenService authTokenService;

    @Override
    @PostMapping("add")
    public int add(@RequestBody LoongAuthTokenAddDto dto) {
        return authTokenService.add(dto);
    }

    @Override
    @DeleteMapping("deleteByUserName")
    public int deleteByUserName(@RequestParam("userName") String userName) {
        return authTokenService.deleteByUserName(userName);
    }

    @Override
    @PutMapping("updateToken")
    public int updateToken(@RequestBody LoongAuthTokenUpdateTokenDto dto) {
        return authTokenService.updateToken(dto);
    }

    @Override
    @GetMapping("getBySeries")
    public LoongAuthTokenDto getBySeries(@RequestParam("series") String series) {
        return authTokenService.getBySeries(series);
    }
}
