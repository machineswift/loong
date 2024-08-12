package com.machine.service.iam.permission.dao;

import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;

import java.util.List;

public interface ILoongPermissionDao {
    List<LoongPermissionEntity> selectByUserId(String userId);

    List<LoongPermissionEntity> selectByRoleIds(List<String> roleIdList);
}
