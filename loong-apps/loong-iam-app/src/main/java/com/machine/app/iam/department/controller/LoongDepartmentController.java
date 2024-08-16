package com.machine.app.iam.department.controller;

import com.machine.app.iam.department.business.ILoongDepartmentBusiness;
import com.machine.app.iam.department.controller.vo.request.LoongDepartmentQueryListRequestVo;
import com.machine.app.iam.department.controller.vo.response.LoongDepartmentListResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class LoongDepartmentController {

    @Autowired
    private ILoongDepartmentBusiness departmentBusiness;

    @PostMapping("list")
    public List<LoongDepartmentListResponseVo> list(@RequestBody LoongDepartmentQueryListRequestVo requestVo) {
        return departmentBusiness.list(requestVo);
    }

}