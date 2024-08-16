package com.machine.service.iam.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ILoongUserMapper extends BaseMapper<LoongUserEntity> {
    Page<LoongUserEntity> selectUserPage(@Param("inputVo") LoongUserQueryPageInputVo inputVo,
                                         IPage<LoongUserEntity> page);
}
