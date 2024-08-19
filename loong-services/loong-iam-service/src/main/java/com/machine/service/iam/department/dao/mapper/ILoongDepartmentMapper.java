package com.machine.service.iam.department.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
import com.machine.service.iam.department.dao.mapper.entity.LoongDepartmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ILoongDepartmentMapper extends BaseMapper<LoongDepartmentEntity> {
    List<LoongDepartmentEntity> list(@Param("inputVo") LoongDepartmentQueryListInputDto inputVo);
}
