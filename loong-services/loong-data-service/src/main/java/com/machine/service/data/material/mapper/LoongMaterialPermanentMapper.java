package com.machine.service.data.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.mapper.entity.LoongMaterialPermanentEntity;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectLoongPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongMaterialPermanentMapper extends BaseMapper<LoongMaterialPermanentEntity> {

    IPage<LoongMaterialPermanentEntity> selectMaterialPermanentPage(Page<LoongMaterialPermanentEntity> page,
                                                                    @Param("select") LoongMaterialPermanentSelectLoongPageRequest request);
}
