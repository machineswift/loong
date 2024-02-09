package com.machine.service.data.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.mapper.entity.DragonMaterialTemporaryEntity;
import com.machine.service.data.material.rest.request.DragonMaterialTemporarySelectPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DragonMaterialTemporaryMapper extends BaseMapper<DragonMaterialTemporaryEntity> {

    IPage<DragonMaterialTemporaryEntity> selectMaterialTemporaryPage(Page<DragonMaterialTemporaryEntity> page,
                                                                     @Param("select") DragonMaterialTemporarySelectPageRequest request);
}
