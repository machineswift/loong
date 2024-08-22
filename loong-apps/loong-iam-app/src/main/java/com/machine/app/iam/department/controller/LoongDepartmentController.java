package com.machine.app.iam.department.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.machine.app.iam.department.business.ILoongDepartmentBusiness;
import com.machine.app.iam.department.controller.vo.request.LoongDepartmentCreateRequestVo;
import com.machine.app.iam.department.controller.vo.request.LoongDepartmentQueryListRequestVo;
import com.machine.app.iam.department.controller.vo.response.LoongDepartmentListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "部门模块")
@RestController
@RequestMapping("department")
public class LoongDepartmentController {

    @Autowired
    private ILoongDepartmentBusiness departmentBusiness;

    @Operation(summary = "创建部门")
    @ApiOperationSupport(order = 10)
    @PostMapping("create")
    public String create(@RequestBody LoongDepartmentCreateRequestVo requestVo) {
        return departmentBusiness.create(requestVo);
    }

    @PostMapping("list")
    public List<LoongDepartmentListResponseVo> list(@RequestBody LoongDepartmentQueryListRequestVo requestVo) {
        return departmentBusiness.list(requestVo);
    }

}