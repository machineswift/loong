package com.machine.service.iam.permission.dao.impl;

import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.dao.mapper.ILoongPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongPermissionDaoImpl implements ILoongPermissionDao {

    @Autowired
    private ILoongPermissionMapper permissionMapper;

}
