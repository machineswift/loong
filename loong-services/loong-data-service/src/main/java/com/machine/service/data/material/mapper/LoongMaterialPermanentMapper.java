package com.machine.service.data.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.mapper.entity.MaterialPermanentEntity;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongMaterialPermanentMapper extends BaseMapper<MaterialPermanentEntity> {

    IPage<MaterialPermanentEntity> selectMaterialPermanentPage(Page<MaterialPermanentEntity> page,
                                                               @Param("select") LoongMaterialPermanentSelectPageRequest request);
}
