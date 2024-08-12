package com.machine.app.iam.user.controller;

import com.machine.app.iam.user.business.ILoongUserBusiness;
import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class LoongUserController {

    @Autowired
    private ILoongUserBusiness loongUserBusiness;

    @PostMapping("create")
    public String create(@RequestBody LoongUserCreateRequestVo requestVo) {
        return loongUserBusiness.create(requestVo);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam("userId") String userId) {

    }

    @GetMapping("detail")
    public LoongUserDetailResponseVo detail(@RequestParam("userId") String userId) {
        return loongUserBusiness.detail(userId);
    }

}