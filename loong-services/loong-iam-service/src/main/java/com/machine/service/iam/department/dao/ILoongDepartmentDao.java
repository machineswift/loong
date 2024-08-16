package com.machine.service.iam.department.dao;

import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
import com.machine.service.iam.department.dao.mapper.entity.LoongDepartmentEntity;

import java.util.List;

public interface ILoongDepartmentDao {

    List<LoongDepartmentEntity> list(LoongDepartmentQueryListInputVo inputVo);
}
