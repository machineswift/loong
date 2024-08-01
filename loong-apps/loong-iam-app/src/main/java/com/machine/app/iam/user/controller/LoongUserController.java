package com.machine.app.iam.user.controller;

import com.machine.app.iam.user.business.ILoongUserBusiness;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class LoongUserController {

    @Autowired
    private ILoongUserBusiness loongUserBusiness;

    @GetMapping("detail")
    public LoongUserDetailResponseVo detail(@RequestParam("userId") String userId) {
        return loongUserBusiness.detail(userId);
    }

}