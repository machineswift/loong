package com.machine.service.iam.role.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.iam.role.dao.mapper.entity.LoongRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ILoongRoleMapper extends BaseMapper<LoongRoleEntity> {
    List<LoongRoleEntity> selectByUserId(@Param("userId") String userId);
}
