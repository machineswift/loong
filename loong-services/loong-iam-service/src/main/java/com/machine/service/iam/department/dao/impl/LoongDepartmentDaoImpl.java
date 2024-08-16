package com.machine.service.iam.department.dao.impl;

import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
import com.machine.service.iam.department.dao.ILoongDepartmentDao;
import com.machine.service.iam.department.dao.mapper.ILoongDepartmentMapper;
import com.machine.service.iam.department.dao.mapper.entity.LoongDepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoongDepartmentDaoImpl implements ILoongDepartmentDao {

    @Autowired
    private ILoongDepartmentMapper departmentMapper;

    @Override
    public List<LoongDepartmentEntity> list(LoongDepartmentQueryListInputVo inputVo) {
        return departmentMapper.list(inputVo);
    }
}
