package com.machine.service.data.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.data.area.mapper.entity.AreaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoongAreaMapper extends BaseMapper<AreaEntity> {

    int insertBatch(List<AreaEntity> entityList);

    List<AreaEntity> selectByLevel(@Param("level") Integer level);

    List<AreaEntity> selectByParentCode(@Param("parentCode")String parentCode);
}
