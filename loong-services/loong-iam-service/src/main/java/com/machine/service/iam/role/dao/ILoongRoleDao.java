package com.machine.service.iam.role.dao;

import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;

import java.util.List;

public interface ILoongRoleDao {
    LoongRoleEntity detail(String roleId);

    List<LoongRoleEntity> selectByUserId(String userId);
}
