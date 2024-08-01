package com.machine.service.iam.user.dao.impl;

import com.machine.service.iam.user.dao.ILoongUserDao;
import com.machine.service.iam.user.dao.mapper.ILoongUserMapper;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongUserDaoImpl implements ILoongUserDao {

    @Autowired
    private ILoongUserMapper loongUserMapper;

    @Override
    public LoongUserEntity detail(String userId) {
        return loongUserMapper.selectById(userId);
    }
}
