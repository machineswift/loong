package com.machine.service.iam.role.dao;

import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;

public interface ILoongRoleDao {
    LoongRoleEntity detail(String roleId);
}
