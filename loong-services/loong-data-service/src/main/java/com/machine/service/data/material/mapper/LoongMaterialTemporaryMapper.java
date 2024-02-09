package com.machine.service.data.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.mapper.entity.LoongMaterialTemporaryEntity;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectLoongPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongMaterialTemporaryMapper extends BaseMapper<LoongMaterialTemporaryEntity> {

    IPage<LoongMaterialTemporaryEntity> selectMaterialTemporaryPage(Page<LoongMaterialTemporaryEntity> page,
                                                                    @Param("select") LoongMaterialTemporarySelectLoongPageRequest request);
}
