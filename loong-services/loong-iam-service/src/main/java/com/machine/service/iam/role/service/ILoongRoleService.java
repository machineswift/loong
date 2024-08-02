package com.machine.service.iam.role.service;

import com.machine.client.iam.role.dto.LoongRoleDetailDto;

public interface ILoongRoleService {
    LoongRoleDetailDto detail(String roleId);
}
