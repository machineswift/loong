package com.machine.service.iam.permission.dao.impl;

import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.dao.mapper.ILoongPermissionMapper;
import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoongPermissionDaoImpl implements ILoongPermissionDao {

    @Autowired
    private ILoongPermissionMapper permissionMapper;

    @Override
    public List<LoongPermissionEntity> selectByUserId(String userId) {
        return permissionMapper.selectByUserId(userId);
    }

    @Override
    public List<LoongPermissionEntity> selectByRoleIds(List<String> roleIdList) {
        return permissionMapper.selectByRoleIds(roleIdList);
    }
}
