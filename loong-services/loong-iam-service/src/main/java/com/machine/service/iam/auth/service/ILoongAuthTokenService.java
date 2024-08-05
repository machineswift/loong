package com.machine.service.iam.auth.service;

import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenUpdateTokenDto;

public interface ILoongAuthTokenService {

    int add(LoongAuthTokenAddDto dto);

    int deleteByUserName(String userName);

    int updateToken(LoongAuthTokenUpdateTokenDto dto);

    LoongAuthTokenDto getBySeries(String series);

}
