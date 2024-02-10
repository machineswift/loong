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
import com.machine.service.data.material.mapper.entity.LoongMaterialTemporaryEntity;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectLoongPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class LoongMaterialTemporaryDaoImpl implements ILoongMaterialTemporaryDao {

    @Autowired
    private LoongMaterialTemporaryMapper loongMaterialTemporaryMapper;

    @Override
    public String insert(LoongMaterialTemporaryInsertInDTO inDTO) {
        LoongMaterialTemporaryEntity entity = JSONUtil.toBean(JSONUtil.toJsonStr(inDTO), LoongMaterialTemporaryEntity.class);
        entity.setCreateUser("system");
        entity.setCreateTime(new Date().getTime());
        entity.setUpdateUser("system");
        entity.setUpdateTime(new Date().getTime());
        loongMaterialTemporaryMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public LoongMaterialTemporaryDetailOutDTO selectById(String id) {
        LoongMaterialTemporaryEntity entity = loongMaterialTemporaryMapper.selectById(id);
        if (null == entity) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialTemporaryDetailOutDTO.class);
    }


    @Override
    public Page<LoongMaterialTemporaryPageOutDTO> selectPage(LoongMaterialTemporarySelectLoongPageRequest request) {
        Page<LoongMaterialTemporaryEntity> page = new Page<>(request.getCurrent(), request.getSize());
        IPage<LoongMaterialTemporaryEntity> entityIPage = loongMaterialTemporaryMapper.selectMaterialTemporaryPage(page, request);
        return LoongPageUtil.convertT1ToT2(entityIPage, LoongMaterialTemporaryPageOutDTO.class);
    }
}
