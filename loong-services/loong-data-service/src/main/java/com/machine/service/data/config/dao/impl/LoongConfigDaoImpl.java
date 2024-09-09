package com.machine.service.data.config.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.machine.service.data.config.dao.ILoongConfigDao;
import com.machine.service.data.config.dao.mapper.LoongConfigMapper;
import com.machine.service.data.config.dao.mapper.entity.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongConfigDaoImpl implements ILoongConfigDao {

    @Autowired
    private LoongConfigMapper configMapper;

    @Override
    public ConfigEntity getByUk(String category, String code) {
        Wrapper<ConfigEntity> queryWrapper = new LambdaQueryWrapper<ConfigEntity>()
                .eq(ConfigEntity::getCategory, category)
                .eq(ConfigEntity::getCode, code);
        return configMapper.selectOne(queryWrapper);
    }
}
