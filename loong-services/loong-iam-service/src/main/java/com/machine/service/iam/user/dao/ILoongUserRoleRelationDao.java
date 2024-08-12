package com.machine.service.iam.user.dao;

import com.machine.service.iam.user.dao.mapper.entity.LoongUserRoleRelationEntity;

public interface ILoongUserRoleRelationDao {
    LoongUserRoleRelationEntity detail(String id);
}
