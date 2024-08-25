package com.machine.service.iam.department.service;

import com.machine.client.iam.department.dto.input.LoongDepartmentCreateInputDto;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;

import java.util.List;

public interface ILoongDepartmentService {

    String create(LoongDepartmentCreateInputDto inputVo);

    List<LoongDepartmentListOutputDto> list(LoongDepartmentQueryListInputDto inputVo);
}
