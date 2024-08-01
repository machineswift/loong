package com.machine.service.iam.user.service.impl;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.service.iam.user.dao.ILoongUserDao;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;
import com.machine.service.iam.user.service.ILoongUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongUserServiceImpl implements ILoongUserService {

    @Autowired
    private ILoongUserDao loongUserDao;

    @Override
    public LoongUserDetailDto detail(String userId) {
        LoongUserEntity entity = loongUserDao.detail(userId);

        LoongUserDetailDto detailDto = new LoongUserDetailDto();
        detailDto.setUserId(entity.getId());
        return detailDto;
    }
}
