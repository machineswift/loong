package com.machine.service.iam.user.dao.impl;

import com.machine.service.iam.user.dao.ILoongUserRoleRelationDao;
import com.machine.service.iam.user.dao.mapper.ILoongUserRoleRelationMapper;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserRoleRelationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongUserRoleRelationDaoImpl implements ILoongUserRoleRelationDao {

    @Autowired
    private ILoongUserRoleRelationMapper userRoleRelationMapper;

    @Override
    public LoongUserRoleRelationEntity detail(String id) {
        return userRoleRelationMapper.selectById(id);
    }
}
