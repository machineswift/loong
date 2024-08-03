package com.machine.service.iam.user.dao;

import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;

public interface ILoongUserDao {

    int updatePassword(String userId,
                       String password);

    LoongUserEntity detail(String userId);

    LoongUserEntity getByUserName(String userName);
}
