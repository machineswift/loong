package com.machine.service.iam.permission.service.impl;

import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.service.ILoongPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongPermissionServiceImpl implements ILoongPermissionService {

    @Autowired
    private ILoongPermissionDao permissionDao;

}
