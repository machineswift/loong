package com.machine.service.iam.permission.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.machine.client.iam.permission.dto.input.LoongPermissionCreateInputDto;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionDetailOutputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionListOutputDto;
import com.machine.service.iam.permission.dao.ILoongPermissionDao;
import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;
import com.machine.service.iam.permission.service.ILoongPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoongPermissionServiceImpl implements ILoongPermissionService {

    @Autowired
    private ILoongPermissionDao permissionDao;

    @Override
    public String create(LoongPermissionCreateInputDto inputDto) {
        //验证 parentId 是否存在
        LoongPermissionEntity entityById = permissionDao.getById(inputDto.getParentId());
        if (null == entityById) {
            throw new InvalidParameterException("父ID不存在");
        }

        //验证 code 是否存在
        LoongPermissionEntity entityByCode = permissionDao.getByCode(inputDto.getCode());
        if (null != entityByCode) {
            throw new InvalidParameterException("编码已经存在");
        }

        //验证名称在同一层级是否存在
        LoongPermissionEntity entityByName = permissionDao.getByName(inputDto.getParentId(), inputDto.getName());
        if (null != entityByName) {
            throw new InvalidParameterException("权限名称已经存在");
        }

        LoongPermissionEntity insertEntity = new LoongPermissionEntity();
        insertEntity.setParentId(inputDto.getParentId());
        insertEntity.setCode(inputDto.getCode());
        insertEntity.setName(inputDto.getName());
        insertEntity.setType(inputDto.getType().getCode());
        insertEntity.setDescription(inputDto.getDescription());
        return permissionDao.insert(insertEntity);
    }

    @Override
    public void delete(String permissionId) {
        permissionDao.delete(permissionId);
    }

    @Override
    public LoongPermissionDetailOutputDto detail(String permissionId) {
        LoongPermissionEntity entity = permissionDao.getById(permissionId);
        LoongPermissionDetailOutputDto outputDto = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongPermissionDetailOutputDto.class);
        outputDto.setPermissionId(entity.getId());
        return outputDto;
    }

    @Override
    public List<LoongPermissionListOutputDto> list(LoongPermissionQueryListInputDto inputDto) {
        List<LoongPermissionEntity> entityList = permissionDao.selectList(inputDto);
        List<LoongPermissionListOutputDto> outputDtoList = new ArrayList<>();
        for (LoongPermissionEntity entity : entityList) {
            LoongPermissionListOutputDto outputDto = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongPermissionListOutputDto.class);
            outputDto.setPermissionId(entity.getId());
            outputDtoList.add(outputDto);
        }
        return outputDtoList;
    }
}
