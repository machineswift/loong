package com.machine.service.data.material.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.service.data.material.dao.mapper.entity.MaterialTemporaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoongMaterialTemporaryMapper extends BaseMapper<MaterialTemporaryEntity> {

    Page<MaterialTemporaryEntity> selectMaterialPage(@Param("inputVo") LoongMaterialTemporaryQueryPageInputVo inputVo,
                                         IPage<MaterialTemporaryEntity> page);
}
