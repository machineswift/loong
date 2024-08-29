package com.machine.service.iam.permission.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.dao.mapper.ILoongPermissionMapper;
import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoongPermissionDaoImpl implements ILoongPermissionDao {

    @Autowired
    private ILoongPermissionMapper permissionMapper;

    @Override
    public String insert(LoongPermissionEntity insertEntity) {
        permissionMapper.insert(insertEntity);
        return insertEntity.getId();
    }

    @Override
    public void delete(String permissionId) {
        permissionMapper.deleteById(permissionId);
    }

    @Override
    public LoongPermissionEntity getById(String parentId) {
        return permissionMapper.selectById(parentId);
    }

    @Override
    public LoongPermissionEntity getByCode(String code) {
        Wrapper<LoongPermissionEntity> queryWrapper = new LambdaQueryWrapper<LoongPermissionEntity>()
                .eq(LoongPermissionEntity::getCode, code);
        return permissionMapper.selectOne(queryWrapper);
    }

    @Override
    public LoongPermissionEntity getByName(String parentId, String name) {
        Wrapper<LoongPermissionEntity> queryWrapper = new LambdaQueryWrapper<LoongPermissionEntity>()
                .eq(LoongPermissionEntity::getParentId, parentId)
                .eq(LoongPermissionEntity::getName, name);
        return permissionMapper.selectOne(queryWrapper);
    }

    @Override
    public List<LoongPermissionEntity> selectByUserId(String userId) {
        return permissionMapper.selectByUserId(userId);
    }

    @Override
    public List<LoongPermissionEntity> selectByRoleIds(List<String> roleIdList) {
        return permissionMapper.selectByRoleIds(roleIdList);
    }

    @Override
    public List<LoongPermissionEntity> selectList(LoongPermissionQueryListInputDto inputDto) {
        Wrapper<LoongPermissionEntity> queryWrapper = new LambdaQueryWrapper<LoongPermissionEntity>()
                .like(LoongPermissionEntity::getName, inputDto.getName());
        return permissionMapper.selectList(queryWrapper);
    }

}
