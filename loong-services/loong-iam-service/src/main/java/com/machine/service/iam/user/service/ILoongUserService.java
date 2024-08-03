package com.machine.service.iam.user.service;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.LoongUserUpdatePasswordDto;

public interface ILoongUserService {

    int updatePassword(LoongUserUpdatePasswordDto dto);

    LoongUserDetailDto detail(String userId);

    LoongUserDto getByUserName(String userName);

}
