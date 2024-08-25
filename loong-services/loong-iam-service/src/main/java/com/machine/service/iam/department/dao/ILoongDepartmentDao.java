package com.machine.service.iam.department.dao;

import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
import com.machine.service.iam.department.dao.mapper.entity.LoongDepartmentEntity;

import java.util.List;

public interface ILoongDepartmentDao {

    String insert(LoongDepartmentEntity insertEntity);

    LoongDepartmentEntity getById(String parentId);

    LoongDepartmentEntity getByName(String parentId, String name);

    List<LoongDepartmentEntity> list(LoongDepartmentQueryListInputDto inputVo);

}
