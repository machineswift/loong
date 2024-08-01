package com.machine.service.iam.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ILoongUserMapper extends BaseMapper<LoongUserEntity> {
}
