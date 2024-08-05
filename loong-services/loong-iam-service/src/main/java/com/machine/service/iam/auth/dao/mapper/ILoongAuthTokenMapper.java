package com.machine.service.iam.auth.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.iam.auth.dao.mapper.entity.LoongAuthTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ILoongAuthTokenMapper extends BaseMapper<LoongAuthTokenEntity> {
}
