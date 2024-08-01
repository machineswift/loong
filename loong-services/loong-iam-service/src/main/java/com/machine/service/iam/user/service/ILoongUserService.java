package com.machine.service.iam.user.service;

import com.machine.client.iam.user.dto.LoongUserDetailDto;

public interface ILoongUserService {
    LoongUserDetailDto detail(String userId);
}
