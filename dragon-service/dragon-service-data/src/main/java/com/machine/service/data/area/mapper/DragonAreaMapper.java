package com.machine.service.data.area.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.data.area.mapper.entity.DragonAreaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DragonAreaMapper extends BaseMapper<DragonAreaEntity> {

    int insertBatch(List<DragonAreaEntity> entityList);

    List<DragonAreaEntity> selectByLevel(@Param("level") Integer level);

    List<DragonAreaEntity> selectByParentCode(@Param("parentCode")String parentCode);
}
