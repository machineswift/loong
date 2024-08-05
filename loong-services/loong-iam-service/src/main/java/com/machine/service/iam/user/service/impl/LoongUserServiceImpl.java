package com.machine.service.iam.user.service.impl;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.LoongUserUpdatePasswordDto;
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
    private ILoongUserDao userDao;

    @Override
    public int updatePassword(LoongUserUpdatePasswordDto dto) {
        LoongUserEntity entity = userDao.detail(dto.getUserId());
        if(null == entity){
            return 0;
        }
        return userDao.updatePassword(dto.getUserId(),dto.getPassword());
    }

    @Override
    public LoongUserDetailDto detail(String userId) {
        LoongUserEntity entity = userDao.detail(userId);

        LoongUserDetailDto detailDto = new LoongUserDetailDto();
        detailDto.setUserId(entity.getId());
        detailDto.setUserName(entity.getUserName());
        detailDto.setPassword(entity.getPassword());
        detailDto.setFullName(entity.getFullName());
        detailDto.setPhone(entity.getPhone());
        detailDto.setEnabled(entity.getEnabled());

        return detailDto;
    }

    @Override
    public LoongUserDto getByUserName(String userName) {
        LoongUserEntity entity = userDao.getByUserName(userName);
        if (entity == null) {
            return null;
        }

        LoongUserDto dto = new LoongUserDto();
        dto.setUserId(entity.getId());
        return dto;
    }
}
