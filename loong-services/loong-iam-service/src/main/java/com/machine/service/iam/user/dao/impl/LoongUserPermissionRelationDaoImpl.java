package com.machine.service.iam.user.dao.impl;

import com.machine.service.iam.user.dao.ILoongUserPermissionRelationDao;
import com.machine.service.iam.user.dao.mapper.ILoongUserPermissionRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongUserPermissionRelationDaoImpl implements ILoongUserPermissionRelationDao {

    @Autowired
    private ILoongUserPermissionRelationMapper userPermissionRelationMapper;
}
