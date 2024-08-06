package com.machine.service.iam.auth.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.service.iam.auth.dao.ILoongAuthTokenDao;
import com.machine.service.iam.auth.dao.mapper.ILoongAuthTokenMapper;
import com.machine.service.iam.auth.dao.mapper.entity.LoongAuthTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongAuthTokenDaoImpl implements ILoongAuthTokenDao {

    @Autowired
    private ILoongAuthTokenMapper authTokenMapper;

    @Override
    public int add(LoongAuthTokenAddDto dto) {
        LoongAuthTokenEntity insertEntity = new LoongAuthTokenEntity();
        insertEntity.setUserName(dto.getUsername());
        insertEntity.setSeries(dto.getSeries());
        insertEntity.setToken(dto.getToken());
        return authTokenMapper.insert(insertEntity);
    }

    @Override
    public int deleteByUserName(String userName) {
        Wrapper<LoongAuthTokenEntity> queryWrapper = new LambdaQueryWrapper<LoongAuthTokenEntity>()
                .eq(LoongAuthTokenEntity::getUserName, userName);
        LoongAuthTokenEntity entity = authTokenMapper.selectOne(queryWrapper);
        if (null == entity) {
            return 0;
        }
        return authTokenMapper.deleteById(entity.getId());
    }

    @Override
    public int updateToken(String series,
                           String token) {
        Wrapper<LoongAuthTokenEntity> queryWrapper = new LambdaQueryWrapper<LoongAuthTokenEntity>()
                .eq(LoongAuthTokenEntity::getSeries, series);
        LoongAuthTokenEntity entity = authTokenMapper.selectOne(queryWrapper);
        if (null == entity) {
            return 0;
        }

        LoongAuthTokenEntity updateEntity = new LoongAuthTokenEntity();
        updateEntity.setId(entity.getId());
        updateEntity.setToken(token);

        return authTokenMapper.updateById(updateEntity);
    }

    @Override
    public LoongAuthTokenEntity getBySeries(String series) {
        Wrapper<LoongAuthTokenEntity> queryWrapper = new LambdaQueryWrapper<LoongAuthTokenEntity>()
                .eq(LoongAuthTokenEntity::getSeries, series);
        return authTokenMapper.selectOne(queryWrapper);
    }
}
