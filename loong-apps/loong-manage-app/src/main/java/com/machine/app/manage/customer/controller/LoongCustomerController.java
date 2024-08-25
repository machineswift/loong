package com.machine.app.manage.customer.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "客户模块")
@RestController
@RequestMapping("customer")
public class LoongCustomerController {


    @Operation(summary = "创建客户")
    @ApiOperationSupport(order = 10)
    @PostMapping("create")
    public String create() {
        return "success";
    }


    @Operation(summary = "详情")
    @ApiOperationSupport(order = 20)
    @GetMapping("detail")
    public String detail() {
        return "success";
    }

}