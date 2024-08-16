package com.machine.service.iam.department.server;

import com.machine.client.iam.department.ILoongDepartmentClient;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
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
    @PostMapping("list")
    public List<LoongDepartmentListOutputDto> list(@RequestBody LoongDepartmentQueryListInputVo inputVo) {
        return departmentService.list(inputVo);
    }
}
