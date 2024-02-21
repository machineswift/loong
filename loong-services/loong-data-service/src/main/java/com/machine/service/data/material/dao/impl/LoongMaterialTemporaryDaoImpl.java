package com.machine.service.data.material.dao.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.tool.json.LoongPageUtil;
import com.machine.service.data.material.dao.ILoongMaterialTemporaryDao;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialTemporaryInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryPageOutDTO;
import com.machine.service.data.material.mapper.LoongMaterialTemporaryMapper;
import com.machine.service.data.material.mapper.entity.MaterialTemporaryEntity;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongMaterialTemporaryDaoImpl implements ILoongMaterialTemporaryDao {

    @Autowired
    private LoongMaterialTemporaryMapper loongMaterialTemporaryMapper;

    @Override
    public String insert(LoongMaterialTemporaryInsertInDTO inDTO) {
        MaterialTemporaryEntity entity = JSONUtil.toBean(JSONUtil.toJsonStr(inDTO), MaterialTemporaryEntity.class);
        loongMaterialTemporaryMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public LoongMaterialTemporaryDetailOutDTO selectById(String id) {
        MaterialTemporaryEntity entity = loongMaterialTemporaryMapper.selectById(id);
        if (null == entity) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialTemporaryDetailOutDTO.class);
    }


    @Override
    public Page<LoongMaterialTemporaryPageOutDTO> selectPage(LoongMaterialTemporarySelectPageRequest request) {
        Page<MaterialTemporaryEntity> page = new Page<>(request.getCurrent(), request.getSize());
        IPage<MaterialTemporaryEntity> entityIPage = loongMaterialTemporaryMapper.selectMaterialTemporaryPage(page, request);
        return LoongPageUtil.convertT1ToT2(entityIPage, LoongMaterialTemporaryPageOutDTO.class);
    }
}
