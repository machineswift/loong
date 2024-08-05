package com.machine.service.iam.role.service.impl;

import com.machine.client.iam.role.dto.LoongRoleDetailDto;
import com.machine.service.iam.role.dao.ILoongRoleDao;
import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;
import com.machine.service.iam.role.service.ILoongRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongRoleServiceImpl implements ILoongRoleService {

    @Autowired
    private ILoongRoleDao roleDao;

    @Override
    public LoongRoleDetailDto detail(String roleId) {
        LoongRoleEntity entity = roleDao.detail(roleId);

        LoongRoleDetailDto detailDto = new LoongRoleDetailDto();
        detailDto.setRoleId(entity.getId());
        return detailDto;
    }
}
