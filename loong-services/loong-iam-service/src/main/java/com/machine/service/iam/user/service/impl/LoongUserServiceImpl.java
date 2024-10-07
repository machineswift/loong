package com.machine.service.iam.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.input.LoongUserCreateInputDto;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.client.iam.user.dto.input.LoongUserUpdatePasswordInputDto;
import com.machine.client.iam.user.dto.output.LoongUserListOutputDto;
import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;
import com.machine.service.iam.role.dao.ILoongRoleDao;
import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;
import com.machine.service.iam.user.dao.ILoongUserDao;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;
import com.machine.service.iam.user.service.ILoongUserService;
import com.machine.starter.mybatis.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoongUserServiceImpl implements ILoongUserService {

    @Autowired
    private ILoongUserDao userDao;

    @Autowired
    private ILoongRoleDao roleDao;

    @Autowired
    private ILoongPermissionDao permissionDao;

    @Override
    public String create(LoongUserCreateInputDto inputDto) {
        //验证用户名是否存在
        LoongUserEntity userNameEntity = userDao.getByUserName(inputDto.getUserName());
        if (null != userNameEntity) {
            throw new IllegalArgumentException("用户名已经存在");
        }

        //验证手机号是否存在
        LoongUserEntity phoneEntity = userDao.getByUserName(inputDto.getPhone());
        if (null != phoneEntity) {
            throw new IllegalArgumentException("手机号已经存在");
        }

        LoongUserEntity insertEntity = new LoongUserEntity();
        insertEntity.setUserName(inputDto.getUserName());
        insertEntity.setPassword(inputDto.getPassword());
        insertEntity.setFullName(inputDto.getFullName());
        insertEntity.setPhone(inputDto.getPhone());
        insertEntity.setGender(inputDto.getGender());
        return userDao.insert(insertEntity);
    }

    @Override
    public int updatePassword(LoongUserUpdatePasswordInputDto dto) {
        LoongUserEntity entity = userDao.detail(dto.getUserId());
        if (null == entity) {
            return 0;
        }
        return userDao.updatePassword(dto.getUserId(), dto.getPassword());
    }

    @Override
    public LoongUserDetailDto detail(String userId) {
        LoongUserEntity entity = userDao.detail(userId);
        if (null == entity) {
            return null;
        }

        LoongUserDetailDto detailDto = new LoongUserDetailDto();
        detailDto.setUserId(entity.getId());
        detailDto.setUserName(entity.getUserName());
        detailDto.setFullName(entity.getFullName());
        detailDto.setPhone(entity.getPhone());
        detailDto.setGender(entity.getGender());
        detailDto.setEnabled(entity.getEnabled());

        return detailDto;
    }

    @Override
    public LoongUserAuthDetailDto authDetail(String userId) {
        //查询用户信息
        LoongUserEntity userEntity = userDao.detail(userId);
        if (null == userEntity) {
            return null;
        }

        //查询用户角色信息
        List<LoongRoleEntity> rolesByUserId = roleDao.selectByUserId(userId);

        //查询角色权限信息
        List<LoongPermissionEntity> permissionsByUserId = permissionDao.selectByUserId(userId);
        List<LoongPermissionEntity> permissionsByRoleIds = permissionDao.selectByRoleIds(
                rolesByUserId.stream().map(BaseEntity::getId).collect(Collectors.toList()));

        LoongUserAuthDetailDto detailDto = new LoongUserAuthDetailDto();
        detailDto.setUserId(userEntity.getId());
        detailDto.setUserName(userEntity.getUserName());
        detailDto.setPassword(userEntity.getPassword());
        detailDto.setFullName(userEntity.getFullName());
        detailDto.setPhone(userEntity.getPhone());
        detailDto.setEnabled(userEntity.getEnabled());
        if (CollectionUtil.isNotEmpty(rolesByUserId)) {
            detailDto.setRoleCodeList(rolesByUserId.stream().map(LoongRoleEntity::getCode).collect(Collectors.toList()));
        }

        Set<String> permissionCodeSet = new HashSet<>();
        if (CollectionUtil.isNotEmpty(permissionsByUserId)) {
            permissionCodeSet.addAll(permissionsByUserId.stream().map(LoongPermissionEntity::getCode).toList());
        }
        if (CollectionUtil.isNotEmpty(permissionsByRoleIds)) {
            permissionCodeSet.addAll(permissionsByRoleIds.stream().map(LoongPermissionEntity::getCode).toList());

        }

        detailDto.setPermissionCodeList(new ArrayList<>(permissionCodeSet));
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
        dto.setUserName(entity.getUserName());
        return dto;
    }

    @Override
    public Page<LoongUserListOutputDto> selectPage(LoongUserQueryPageInputVo inputVo) {
        Page<LoongUserEntity> page = userDao.selectPage(inputVo);

        Page<LoongUserListOutputDto> pageResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<LoongUserListOutputDto> outputDtoList = new ArrayList<>();
        for (LoongUserEntity entity : page.getRecords()) {
            LoongUserListOutputDto outputDto = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongUserListOutputDto.class);
            outputDto.setUserId(entity.getId());
            outputDtoList.add(outputDto);
        }
        pageResult.setRecords(outputDtoList);
        return pageResult;
    }
}
