package com.machine.service.iam.user.dao;

import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;

public interface ILoongUserDao {
    LoongUserEntity detail(String userId);
}
