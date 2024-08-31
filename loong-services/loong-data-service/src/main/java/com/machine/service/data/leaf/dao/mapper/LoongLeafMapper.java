package com.machine.service.data.leaf.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.data.leaf.dao.mapper.entity.LoongLeafEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongLeafMapper extends BaseMapper<LoongLeafEntity> {

    Integer updateMaxId( @Param("bizTag") String bizTag,
                         @Param("maxId") Long maxId);
}
