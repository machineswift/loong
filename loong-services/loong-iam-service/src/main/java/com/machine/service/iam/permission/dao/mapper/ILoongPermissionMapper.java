package com.machine.service.iam.permission.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.iam.permission.dao.mapper.entity.LoongPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ILoongPermissionMapper extends BaseMapper<LoongPermissionEntity> {

    List<LoongPermissionEntity> selectByUserId(@Param("userId") String userId);

    List<LoongPermissionEntity> selectByRoleIds(@Param("roleIds") Collection<String> roleIds);
}
