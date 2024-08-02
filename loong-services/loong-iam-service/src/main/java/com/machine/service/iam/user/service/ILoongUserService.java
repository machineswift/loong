package com.machine.service.iam.user.service;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;

public interface ILoongUserService {
    LoongUserDetailDto detail(String userId);

    LoongUserDto getByUserName(String userName);
}
