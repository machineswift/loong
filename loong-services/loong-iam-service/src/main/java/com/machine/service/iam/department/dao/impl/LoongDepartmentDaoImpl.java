package com.machine.service.iam.department.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
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
    public String insert(LoongDepartmentEntity insertEntity) {
        departmentMapper.insert(insertEntity);
        return insertEntity.getId();
    }

    @Override
    public LoongDepartmentEntity getById(String parentId) {
        return departmentMapper.selectById(parentId);
    }

    @Override
    public LoongDepartmentEntity getByName(String parentId,
                                           String name) {
        Wrapper<LoongDepartmentEntity> queryWrapper = new LambdaQueryWrapper<LoongDepartmentEntity>()
                .eq(LoongDepartmentEntity::getParentId, parentId)
                .eq(LoongDepartmentEntity::getName, name);
        return departmentMapper.selectOne(queryWrapper);
    }

    @Override
    public List<LoongDepartmentEntity> list(LoongDepartmentQueryListInputDto inputVo) {
        return departmentMapper.list(inputVo);
    }
}
