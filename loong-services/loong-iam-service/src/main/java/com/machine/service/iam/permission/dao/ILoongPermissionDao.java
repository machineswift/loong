package com.machine.service.iam.permission.dao;

import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;

import java.util.List;

public interface ILoongPermissionDao {

    String insert(LoongPermissionEntity insertEntity);

    void delete(String permissionId);

    LoongPermissionEntity getById(String parentId);

    LoongPermissionEntity getByCode(String code);

    LoongPermissionEntity getByName(String parentId,
                                    String name);

    List<LoongPermissionEntity> selectByUserId(String userId);

    List<LoongPermissionEntity> selectByRoleIds(List<String> roleIdList);

    List<LoongPermissionEntity> selectList(LoongPermissionQueryListInputDto inputDto);
}
