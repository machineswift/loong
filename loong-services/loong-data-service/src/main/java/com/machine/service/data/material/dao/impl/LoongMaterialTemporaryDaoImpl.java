package com.machine.service.data.material.dao.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.service.data.material.dao.ILoongMaterialTemporaryDao;
import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.service.data.material.dao.mapper.LoongMaterialTemporaryMapper;
import com.machine.service.data.material.dao.mapper.entity.MaterialTemporaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongMaterialTemporaryDaoImpl implements ILoongMaterialTemporaryDao {

    @Autowired
    private LoongMaterialTemporaryMapper loongMaterialTemporaryMapper;

    @Override
    public String insert(LoongMaterialTemporaryCreateInputDto inDTO) {
        MaterialTemporaryEntity entity = JSONUtil.toBean(JSONUtil.toJsonStr(inDTO), MaterialTemporaryEntity.class);
        loongMaterialTemporaryMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public LoongMaterialTemporaryDetailOutputDto selectById(String id) {
        MaterialTemporaryEntity entity = loongMaterialTemporaryMapper.selectById(id);
        if (null == entity) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialTemporaryDetailOutputDto.class);
    }

    @Override
    public Page<MaterialTemporaryEntity> selectPage(LoongMaterialTemporaryQueryPageInputVo inputVo) {
        IPage<MaterialTemporaryEntity> page = new Page<>(inputVo.getCurrent(), inputVo.getSize());
        return loongMaterialTemporaryMapper.selectMaterialPage(inputVo, page);

    }
}
