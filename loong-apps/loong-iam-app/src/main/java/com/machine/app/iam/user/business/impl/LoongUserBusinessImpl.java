package com.machine.app.iam.user.business.impl;

import cn.hutool.json.JSONUtil;
import com.machine.app.iam.user.business.ILoongUserBusiness;
import com.machine.app.iam.user.controller.vo.request.LoongUserChangePasswordRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserQueryPageRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserListResponseVo;
import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.input.LoongUserCreateInputDto;
import com.machine.client.iam.user.dto.input.LoongUserUpdatePasswordInputDto;
import com.machine.client.iam.user.dto.output.LoongUserListOutputDto;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.common.model.LoongPageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

import static cn.hutool.http.ContentType.JSON;

@Slf4j
@Component
public class LoongUserBusinessImpl implements ILoongUserBusiness {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ILoongUserClient loongUserClient;

    @Override
    public String create(LoongUserCreateRequestVo requestVo) {
        //加密密码
        requestVo.setPassword(passwordEncoder.encode(requestVo.getPassword()));

        LoongUserCreateInputDto inputDto = JSONUtil.toBean(JSONUtil.toJsonStr(requestVo), LoongUserCreateInputDto.class);
        return loongUserClient.create(inputDto);
    }

    @Override
    public void changePassword(LoongUserChangePasswordRequestVo requestVo) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        LoongUserDto userDto = loongUserClient.getByUserName(username);
        // 验证旧密码
        if (!passwordEncoder.matches(requestVo.getOldPassword(), userDto.getPassword())) {
            throw new InvalidParameterException("旧密码不正确");
        }
        loongUserClient.updatePassword(new LoongUserUpdatePasswordInputDto(userDto.getUserId(), requestVo.getNewPassword()));
    }

    @Override
    public LoongUserDetailResponseVo detail(String userId) {
        LoongUserDetailDto detailDto = loongUserClient.detail(userId);
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailDto), LoongUserDetailResponseVo.class);
    }

    @Override
    public LoongPageResponse<LoongUserListResponseVo> selectPage(LoongUserQueryPageRequestVo requestVo) {
        LoongUserQueryPageInputVo inputVo = JSONUtil.toBean(JSONUtil.toJsonStr(requestVo), LoongUserQueryPageInputVo.class);
        LoongPageResponse<LoongUserListOutputDto> page = loongUserClient.selectPage(inputVo);

        return new LoongPageResponse<>(
                page.getCurrent(),
                page.getSize(),
                page.getTotal(),
                JSONUtil.toList(JSONUtil.toJsonStr(page.getRecords()), LoongUserListResponseVo.class));
    }
}
