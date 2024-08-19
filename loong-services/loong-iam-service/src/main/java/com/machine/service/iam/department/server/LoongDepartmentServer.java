package com.machine.service.iam.department.server;

import com.machine.client.iam.department.ILoongDepartmentClient;
import com.machine.client.iam.department.dto.input.LoongDepartmentCreateInputDto;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;
import com.machine.service.iam.department.service.ILoongDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/department")
public class LoongDepartmentServer implements ILoongDepartmentClient {

    @Autowired
    private ILoongDepartmentService departmentService;

    @Override
    @PostMapping("create")
    public String create(@RequestBody LoongDepartmentCreateInputDto inputVo) {
        return departmentService.create(inputVo);
    }

    @Override
    @PostMapping("list")
    public List<LoongDepartmentListOutputDto> list(@RequestBody LoongDepartmentQueryListInputDto inputVo) {
        return departmentService.list(inputVo);
    }
}
