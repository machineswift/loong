package com.machine.app.manage.customer.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "会员模块")
@RestController
@RequestMapping("member")
public class LoongMemberController {


    @Operation(summary = "创建会员")
    @ApiOperationSupport(order = 10)
    @PostMapping("create")
    public String create() {
        return "success";
    }

}