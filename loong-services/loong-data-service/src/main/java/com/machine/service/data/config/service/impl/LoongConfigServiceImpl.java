package com.machine.service.data.config.service.impl;

import com.machine.client.data.config.dto.LoongConfigDto;
import com.machine.service.data.config.dao.ILoongConfigDao;
import com.machine.service.data.config.dao.mapper.entity.ConfigEntity;
import com.machine.service.data.config.service.ILoongConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongConfigServiceImpl implements ILoongConfigService {

    @Autowired
    private ILoongConfigDao configDao;

    @Override
    public LoongConfigDto getByUk(LoongConfigDto dto) {
        ConfigEntity entity = configDao.getByUk(dto.getCategory(), dto.getCode());
        if (null == entity) {
            return null;
        }
        return new LoongConfigDto(entity.getCategory(), entity.getCode(), entity.getContent());
    }
}
