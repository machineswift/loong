package com.machine.service.data.config.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.data.config.dao.mapper.entity.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoongConfigMapper extends BaseMapper<ConfigEntity> {

}
