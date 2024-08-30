package com.machine.service.data.tag.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.data.tag.dao.mapper.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoongTagMapper extends BaseMapper<TagEntity> {
}
