package com.machine.service.iam.department.service.impl;

import cn.hutool.json.JSONUtil;
import com.machine.client.iam.department.dto.input.LoongDepartmentCreateInputDto;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;
import com.machine.service.iam.department.dao.ILoongDepartmentDao;
import com.machine.service.iam.department.dao.mapper.entity.LoongDepartmentEntity;
import com.machine.service.iam.department.service.ILoongDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoongDepartmentServiceImpl implements ILoongDepartmentService {

    @Autowired
    private ILoongDepartmentDao departmentDao;

    @Override
    public String create(LoongDepartmentCreateInputDto inputVo) {
        //验证 parentId 是否存在
        LoongDepartmentEntity entityById = departmentDao.getById(inputVo.getParentId());
        if (null == entityById) {
            throw new InvalidParameterException("父 ID 不存在");
        }

        //验证名称在同一层级是否存在
        LoongDepartmentEntity entityByName = departmentDao.getByName(inputVo.getParentId(),inputVo.getName());
        if (null != entityByName) {
            throw new InvalidParameterException("部门名称已经存在");
        }

        LoongDepartmentEntity insertEntity = new LoongDepartmentEntity();
        insertEntity.setParentId(inputVo.getParentId());
        insertEntity.setName(inputVo.getName());
        insertEntity.setDescription(inputVo.getDescription());
        return departmentDao.insert(insertEntity);
    }

    @Override
    public List<LoongDepartmentListOutputDto> list(LoongDepartmentQueryListInputDto inputVo) {
        List<LoongDepartmentEntity> entityList = departmentDao.list(inputVo);
        List<LoongDepartmentListOutputDto> outputDtoList = new ArrayList<>();
        for (LoongDepartmentEntity entity : entityList) {
            LoongDepartmentListOutputDto outputDto = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongDepartmentListOutputDto.class);
            outputDto.setDepartmentId(entity.getId());
            outputDtoList.add(outputDto);
        }
        return outputDtoList;
    }
}
