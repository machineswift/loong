package com.machine.service.data.config.service.impl;

import com.machine.client.data.config.dto.LoongConfigDto;
import com.machine.service.data.config.dao.ISystemConfigDao;
import com.machine.service.data.config.dao.mapper.entity.SystemConfigEntity;
import com.machine.service.data.config.service.ISystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

    @Autowired
    private ISystemConfigDao configDao;

    @Override
    public void create(LoongConfigDto dto) {
        SystemConfigEntity entity = new SystemConfigEntity();
        entity.setCategory(dto.getCategory());
        entity.setCode(dto.getCode());
        entity.setContent(dto.getContent());
        configDao.insert(entity);
    }

    @Override
    public void delete(LoongConfigDto dto) {
        SystemConfigEntity entity = configDao.getByUk(dto.getCategory(), dto.getCode());
        if (null == entity) {
            return;
        }
        configDao.delete(entity.getId());
    }

    @Override
    public void update(LoongConfigDto dto) {
        SystemConfigEntity entity = configDao.getByUk(dto.getCategory(), dto.getCode());
        if (null == entity) {
            return;
        }

        SystemConfigEntity updateEntity = new SystemConfigEntity();
        updateEntity.setId(entity.getId());
        updateEntity.setContent(dto.getContent());
        configDao.update(updateEntity);
    }

    @Override
    public LoongConfigDto getByUk(LoongConfigDto dto) {
        SystemConfigEntity entity = configDao.getByUk(dto.getCategory(), dto.getCode());
        if (null == entity) {
            return null;
        }
        return new LoongConfigDto(entity.getCategory(), entity.getCode(), entity.getContent());
    }
}
