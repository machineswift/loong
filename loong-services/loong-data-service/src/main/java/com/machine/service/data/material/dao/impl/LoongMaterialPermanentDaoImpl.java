package com.machine.service.data.material.dao.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.tool.json.LoongPageUtil;
import com.machine.service.data.material.dao.ILoongMaterialPermanentDao;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialPermanentInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialPermanentDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialPermanentPageOutDTO;
import com.machine.service.data.material.mapper.LoongMaterialPermanentMapper;
import com.machine.service.data.material.mapper.entity.MaterialPermanentEntity;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongMaterialPermanentDaoImpl implements ILoongMaterialPermanentDao {

    @Autowired
    private LoongMaterialPermanentMapper loongMaterialPermanentMapper;

    @Override
    public String insert(LoongMaterialPermanentInsertInDTO inDTO) {
        MaterialPermanentEntity entity = JSONUtil.toBean(JSONUtil.toJsonStr(inDTO), MaterialPermanentEntity.class);
        loongMaterialPermanentMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public LoongMaterialPermanentDetailOutDTO selectById(String id) {
        MaterialPermanentEntity entity = loongMaterialPermanentMapper.selectById(id);
        if (null == entity) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialPermanentDetailOutDTO.class);
    }

    @Override
    public Page<LoongMaterialPermanentPageOutDTO> selectPage(LoongMaterialPermanentSelectPageRequest request) {
        Page<MaterialPermanentEntity> page = new Page<>(request.getCurrent(), request.getSize());
        IPage<MaterialPermanentEntity> entityIPage = loongMaterialPermanentMapper.selectMaterialPermanentPage(page, request);
        return LoongPageUtil.convertT1ToT2(entityIPage, LoongMaterialPermanentPageOutDTO.class);
    }
}
