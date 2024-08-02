package com.machine.service.iam.role.dao.impl;

import com.machine.service.iam.role.dao.ILoongRoleDao;
import com.machine.service.iam.role.dao.mapper.ILoongRoleMapper;
import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongRoleDaoImpl implements ILoongRoleDao {

    @Autowired
    private ILoongRoleMapper loongRoleMapper;

    @Override
    public LoongRoleEntity detail(String roleId) {
        return loongRoleMapper.selectById(roleId);
    }
}
