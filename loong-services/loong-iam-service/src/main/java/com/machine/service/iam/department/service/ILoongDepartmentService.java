package com.machine.service.iam.department.service;

import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;

import java.util.List;

public interface ILoongDepartmentService {
    List<LoongDepartmentListOutputDto> list(LoongDepartmentQueryListInputVo inputVo);
}
