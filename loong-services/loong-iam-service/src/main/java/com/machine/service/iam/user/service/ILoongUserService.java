package com.machine.service.iam.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.input.LoongUserCreateInputDto;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.client.iam.user.dto.input.LoongUserUpdatePasswordInputDto;
import com.machine.client.iam.user.dto.output.LoongUserListOutputDto;

import java.util.List;

public interface ILoongUserService {

    String create(LoongUserCreateInputDto inputDto);

    int updatePassword(LoongUserUpdatePasswordInputDto dto);

    LoongUserDetailDto detail(String userId);

    LoongUserAuthDetailDto authDetail(String userId);

    LoongUserDto getByUserName(String userName);

    Page<LoongUserListOutputDto> selectPage(LoongUserQueryPageInputVo inputVo);

}
