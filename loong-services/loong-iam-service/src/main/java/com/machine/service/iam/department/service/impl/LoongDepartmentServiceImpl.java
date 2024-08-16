package com.machine.service.iam.department.service.impl;

import cn.hutool.json.JSONUtil;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;
import com.machine.service.iam.department.dao.ILoongDepartmentDao;
import com.machine.service.iam.department.dao.mapper.entity.LoongDepartmentEntity;
import com.machine.service.iam.department.service.ILoongDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoongDepartmentServiceImpl implements ILoongDepartmentService {

    @Autowired
    private ILoongDepartmentDao departmentDao;

    @Override
    public List<LoongDepartmentListOutputDto> list(LoongDepartmentQueryListInputVo inputVo) {
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
