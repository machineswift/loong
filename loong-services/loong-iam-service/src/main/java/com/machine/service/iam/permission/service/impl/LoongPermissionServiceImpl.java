package com.machine.service.iam.permission.service.impl;

import com.machine.client.iam.permission.dto.input.LoongPermissionCreateInputDto;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionDetailOutputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionListOutputDto;
import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.service.ILoongPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoongPermissionServiceImpl implements ILoongPermissionService {

    @Autowired
    private ILoongPermissionDao permissionDao;

    @Override
    public String create(LoongPermissionCreateInputDto inputDto) {
        return "";
    }

    @Override
    public void delete(String permissionId) {

    }

    @Override
    public LoongPermissionDetailOutputDto detail(String permissionId) {
        return null;
    }

    @Override
    public List<LoongPermissionListOutputDto> list(LoongPermissionQueryListInputDto inputDto) {
        return List.of();
    }
}
