package com.machine.service.data.material.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.service.data.material.dao.mapper.entity.MaterialPermanentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongMaterialPermanentMapper extends BaseMapper<MaterialPermanentEntity> {

    Page<MaterialPermanentEntity> selectMaterialPage(@Param("inputVo") LoongMaterialPermanentQueryPageInputVo inputVo,
                                                 IPage<MaterialPermanentEntity> page);
}
