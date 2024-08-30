package com.machine.service.data.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.service.data.material.dao.mapper.entity.MaterialTemporaryEntity;

public interface ILoongMaterialTemporaryDao {

    String insert(LoongMaterialTemporaryCreateInputDto inDTO);

    LoongMaterialTemporaryDetailOutputDto selectById(String id);

    Page<MaterialTemporaryEntity> selectPage(LoongMaterialTemporaryQueryPageInputVo request);


}
