package com.machine.service.data.area.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.machine.service.data.area.dao.mapper.entity.LoongAreaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoongAreaMapper extends BaseMapper<LoongAreaEntity> {

    int insertBatch(List<LoongAreaEntity> entityList);

    List<LoongAreaEntity> selectByLevel(@Param("level") Integer level);

    List<LoongAreaEntity> selectByParentCode(@Param("parentCode")String parentCode);
}
