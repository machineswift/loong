package com.machine.service.iam.user.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;

import java.util.List;

public interface ILoongUserDao {

    String insert(LoongUserEntity insertEntity);

    int updatePassword(String userId,
                       String password);

    LoongUserEntity detail(String userId);

    LoongUserEntity getByUserName(String userName);

    Page<LoongUserEntity> selectPage(LoongUserQueryPageInputVo inputVo);

}
