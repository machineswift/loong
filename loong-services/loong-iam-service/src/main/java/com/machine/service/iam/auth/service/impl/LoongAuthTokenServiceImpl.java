package com.machine.service.iam.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenUpdateTokenDto;
import com.machine.service.iam.auth.dao.ILoongAuthTokenDao;
import com.machine.service.iam.auth.dao.mapper.entity.LoongAuthTokenEntity;
import com.machine.service.iam.auth.service.ILoongAuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongAuthTokenServiceImpl implements ILoongAuthTokenService {

    @Autowired
    private ILoongAuthTokenDao authTokenDao;

    @Override
    public int add(LoongAuthTokenAddDto dto) {
        return authTokenDao.add(dto);
    }

    @Override
    public int deleteByUserName(String userName) {
        return authTokenDao.deleteByUserName(userName);
    }

    @Override
    public int updateToken(LoongAuthTokenUpdateTokenDto dto) {
        return authTokenDao.updateToken(dto.getSeries(), dto.getToken());
    }

    @Override
    public LoongAuthTokenDto getBySeries(String series) {
        LoongAuthTokenEntity entity = authTokenDao.getBySeries(series);
        if (entity == null) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongAuthTokenDto.class);
    }
}
