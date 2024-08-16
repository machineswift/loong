package com.machine.app.iam.user.controller;

import com.machine.app.iam.user.business.ILoongUserBusiness;
import com.machine.app.iam.user.controller.vo.request.LoongUserChangePasswordRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserQueryPageRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserListResponseVo;
import com.machine.common.model.LoongPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class LoongUserController {

    @Autowired
    private ILoongUserBusiness loongUserBusiness;

    @PostMapping("create")
    public String create(@RequestBody @Validated LoongUserCreateRequestVo requestVo) {
        return loongUserBusiness.create(requestVo);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam("userId") String userId) {

    }

    @PutMapping("change_password")
    public void changePassword(@RequestBody LoongUserChangePasswordRequestVo requestVo) {
        loongUserBusiness.changePassword(requestVo);
    }

    @GetMapping("detail")
    public LoongUserDetailResponseVo detail(@RequestParam("userId") String userId) {
        return loongUserBusiness.detail(userId);
    }

    @PostMapping("page")
    public LoongPageResponse<LoongUserListResponseVo> selectPage(@RequestBody LoongUserQueryPageRequestVo requestVo) {
        return loongUserBusiness.selectPage(requestVo);
    }

}