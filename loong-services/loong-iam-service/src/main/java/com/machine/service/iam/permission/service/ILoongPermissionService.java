package com.machine.service.iam.permission.service;

import com.machine.client.iam.permission.dto.input.LoongPermissionCreateInputDto;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionDetailOutputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionListOutputDto;

import java.util.List;

public interface ILoongPermissionService {
    String create(LoongPermissionCreateInputDto inputDto);

    void delete(String permissionId);

    LoongPermissionDetailOutputDto detail(String permissionId);

    List<LoongPermissionListOutputDto> list(LoongPermissionQueryListInputDto inputDto);
}
