package com.machine.service.iam.role.dao.impl;

import com.machine.service.iam.role.dao.ILoongRoleDao;
import com.machine.service.iam.role.dao.mapper.ILoongRoleMapper;
import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoongRoleDaoImpl implements ILoongRoleDao {

    @Autowired
    private ILoongRoleMapper roleMapper;

    @Override
    public LoongRoleEntity detail(String roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public List<LoongRoleEntity> selectByUserId(String userId) {
        return roleMapper.selectByUserId(userId);
    }
}
