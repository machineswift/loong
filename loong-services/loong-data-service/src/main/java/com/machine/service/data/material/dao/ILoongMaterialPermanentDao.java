package com.machine.service.data.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.service.data.material.dao.mapper.entity.MaterialPermanentEntity;

public interface ILoongMaterialPermanentDao {

    String insert(LoongMaterialPermanentCreateInputDto inDTO);

    MaterialPermanentEntity selectById(String id);

    Page<MaterialPermanentEntity> selectPage(LoongMaterialPermanentQueryPageInputVo inputVo);
}
