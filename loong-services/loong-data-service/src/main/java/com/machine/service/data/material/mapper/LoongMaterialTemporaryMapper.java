package com.machine.service.data.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.mapper.entity.MaterialTemporaryEntity;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongMaterialTemporaryMapper extends BaseMapper<MaterialTemporaryEntity> {

    IPage<MaterialTemporaryEntity> selectMaterialTemporaryPage(Page<MaterialTemporaryEntity> page,
                                                               @Param("select") LoongMaterialTemporarySelectPageRequest request);
}
