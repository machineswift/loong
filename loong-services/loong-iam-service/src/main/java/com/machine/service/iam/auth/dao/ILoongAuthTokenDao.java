package com.machine.service.iam.auth.dao;

import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.service.iam.auth.dao.mapper.entity.LoongAuthTokenEntity;

public interface ILoongAuthTokenDao {

    int add(LoongAuthTokenAddDto dto);

    int deleteByUserName(String userName);

    int updateToken(String series, String token);

    LoongAuthTokenEntity getBySeries(String series);

}
