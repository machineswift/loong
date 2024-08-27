package com.machine.app.iam.permission.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.machine.app.iam.permission.business.ILoongPermissionBusiness;
import com.machine.app.iam.permission.controller.vo.request.LoongPermissionCreateRequestVo;
import com.machine.app.iam.permission.controller.vo.request.LoongPermissionQueryListRequestVo;
import com.machine.app.iam.permission.controller.vo.response.LoongPermissionDetailResponseVo;
import com.machine.app.iam.permission.controller.vo.response.LoongPermissionListResponseVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "权限模块")
@RestController
@RequestMapping("permission")
public class LoongPermissionController {

    @Autowired
    private ILoongPermissionBusiness permissionBusiness;

    @Operation(summary = "创建权限")
    @ApiOperationSupport(order = 10)
    @PostMapping("create")
    public String create(@RequestBody @Validated LoongPermissionCreateRequestVo requestVo) {
        return permissionBusiness.create(requestVo);
    }

    @Operation(summary = "删除权限")
    @Parameter(name = "permissionId", description = "权限Id", in = ParameterIn.QUERY)
    @ApiOperationSupport(order = 20)
    @DeleteMapping("delete")
    public void delete(@RequestParam("permissionId") String permissionId) {
        permissionBusiness.delete(permissionId);
    }

    @Operation(summary = "详情")
    @Parameter(name = "permissionId", description = "权限Id", in = ParameterIn.QUERY)
    @ApiOperationSupport(order = 40)
    @GetMapping("detail")
    public LoongPermissionDetailResponseVo detail(@RequestParam("permissionId") String permissionId) {
        return permissionBusiness.detail(permissionId);
    }

    @Operation(summary = "列表")
    @ApiOperationSupport(order = 50)
    @PostMapping("list")
    public List<LoongPermissionListResponseVo> selectList(@RequestBody LoongPermissionQueryListRequestVo requestVo) {
        return permissionBusiness.list(requestVo);
    }

}