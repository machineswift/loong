package com.machine.service.iam.role.dao.impl;

import com.machine.service.iam.role.dao.ILoongRolePermissionRelationDao;
import com.machine.service.iam.role.dao.mapper.ILoongRolePermissionRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongRolePermissionRelationDaoImpl implements ILoongRolePermissionRelationDao {

    @Autowired
    private ILoongRolePermissionRelationMapper rolePermissionRelationMapper;

}
