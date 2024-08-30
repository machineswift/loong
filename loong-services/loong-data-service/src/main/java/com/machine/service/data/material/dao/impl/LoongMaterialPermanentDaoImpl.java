package com.machine.service.data.material.dao.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.service.data.material.dao.ILoongMaterialPermanentDao;
import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.service.data.material.dao.mapper.LoongMaterialPermanentMapper;
import com.machine.service.data.material.dao.mapper.entity.MaterialPermanentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongMaterialPermanentDaoImpl implements ILoongMaterialPermanentDao {

    @Autowired
    private LoongMaterialPermanentMapper loongMaterialPermanentMapper;

    @Override
    public String insert(LoongMaterialPermanentCreateInputDto inDTO) {
        MaterialPermanentEntity entity = JSONUtil.toBean(JSONUtil.toJsonStr(inDTO), MaterialPermanentEntity.class);
        loongMaterialPermanentMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public MaterialPermanentEntity selectById(String id) {
        return loongMaterialPermanentMapper.selectById(id);
    }

    @Override
    public Page<MaterialPermanentEntity> selectPage(LoongMaterialPermanentQueryPageInputVo inputVo) {
        IPage<MaterialPermanentEntity> page = new Page<>(inputVo.getCurrent(), inputVo.getSize());
        return loongMaterialPermanentMapper.selectMaterialPage(inputVo, page);

    }
}
