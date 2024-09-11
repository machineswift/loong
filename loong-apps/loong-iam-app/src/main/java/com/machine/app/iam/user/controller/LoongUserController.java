package com.machine.app.iam.user.controller;

import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.machine.app.iam.user.business.ILoongUserBusiness;
import com.machine.app.iam.user.controller.vo.request.LoongUserChangePasswordRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserQueryPageRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserListResponseVo;
import com.machine.common.model.LoongPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "用户模块")
@RestController
@RequestMapping("user")
public class LoongUserController {

    @Autowired
    private ILoongUserBusiness loongUserBusiness;

    @Operation(summary = "创建用户")
    @ApiOperationSupport(order = 10)
    @PostMapping("create")
    public String create(@RequestBody @Validated LoongUserCreateRequestVo requestVo) {
        log.info("创建用户,request: {}", JSONUtil.toJsonStr(requestVo));
        return loongUserBusiness.create(requestVo);
    }

    @Operation(summary = "删除用户")
    @Parameter(name = "userId", description = "用户Id", in = ParameterIn.QUERY)
    @ApiOperationSupport(order = 20)
    @DeleteMapping("delete")
    public void delete(@RequestParam("userId") String userId) {
        log.info("删除用户,userId: {}", userId);

    }

    @Operation(summary = "修改密码")
    @ApiOperationSupport(order = 30)
    @PutMapping("change_password")
    public void changePassword(@RequestBody LoongUserChangePasswordRequestVo requestVo) {
        log.info("修改密码");
        loongUserBusiness.changePassword(requestVo);
    }

    @Operation(summary = "详情")
    @ApiOperationSupport(order = 40)
    @GetMapping("detail")
    public LoongUserDetailResponseVo detail(@RequestParam("userId") String userId) {
        return loongUserBusiness.detail(userId);
    }

    @Operation(summary = "分页查询")
    @ApiOperationSupport(order = 50)
    @PostMapping("page")
    public LoongPageResponse<LoongUserListResponseVo> selectPage(@RequestBody LoongUserQueryPageRequestVo requestVo) {
        return loongUserBusiness.selectPage(requestVo);
    }

}