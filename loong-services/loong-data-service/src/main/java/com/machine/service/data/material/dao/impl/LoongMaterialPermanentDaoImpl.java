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
import com.machine.service.data.material.mapper.entity.LoongMaterialPermanentEntity;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectLoongPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class LoongMaterialPermanentDaoImpl implements ILoongMaterialPermanentDao {

    @Autowired
    private LoongMaterialPermanentMapper loongMaterialPermanentMapper;

    @Override
    public String insert(LoongMaterialPermanentInsertInDTO inDTO) {
        LoongMaterialPermanentEntity entity = JSONUtil.toBean(JSONUtil.toJsonStr(inDTO), LoongMaterialPermanentEntity.class);
        entity.setCreateUser("system");
        entity.setCreateTime(new Date().getTime());
        entity.setUpdateUser("system");
        entity.setUpdateTime(new Date().getTime());
        loongMaterialPermanentMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public LoongMaterialPermanentDetailOutDTO selectById(String id) {
        LoongMaterialPermanentEntity entity = loongMaterialPermanentMapper.selectById(id);
        if (null == entity) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialPermanentDetailOutDTO.class);
    }

    @Override
    public Page<LoongMaterialPermanentPageOutDTO> selectPage(LoongMaterialPermanentSelectLoongPageRequest request) {
        Page<LoongMaterialPermanentEntity> page = new Page<>(request.getCurrent(), request.getSize());
        IPage<LoongMaterialPermanentEntity> entityIPage = loongMaterialPermanentMapper.selectMaterialPermanentPage(page, request);
        return LoongPageUtil.convertT1ToT2(entityIPage, LoongMaterialPermanentPageOutDTO.class);
    }
}
