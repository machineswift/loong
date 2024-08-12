package com.machine.app.iam.user.business.impl;

import cn.hutool.json.JSONUtil;
import com.machine.app.iam.user.business.ILoongUserBusiness;
import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoongUserBusinessImpl implements ILoongUserBusiness {

    @Autowired
    private ILoongUserClient loongUserClient;

    @Override
    public String create(LoongUserCreateRequestVo requestVo) {
        return "";
    }

    @Override
    public LoongUserDetailResponseVo detail(String userId) {
        LoongUserDetailDto detailDto = loongUserClient.detail(userId);
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailDto), LoongUserDetailResponseVo.class);
    }
}
