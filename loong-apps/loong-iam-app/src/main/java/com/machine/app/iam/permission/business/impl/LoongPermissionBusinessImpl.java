package com.machine.app.iam.permission.business.impl;


import cn.hutool.json.JSONUtil;
import com.machine.app.iam.permission.business.ILoongPermissionBusiness;
import com.machine.app.iam.permission.controller.vo.request.LoongPermissionCreateRequestVo;
import com.machine.app.iam.permission.controller.vo.request.LoongPermissionQueryListRequestVo;
import com.machine.app.iam.permission.controller.vo.response.LoongPermissionDetailResponseVo;
import com.machine.app.iam.permission.controller.vo.response.LoongPermissionListResponseVo;
import com.machine.client.iam.permission.ILoongPermissionClient;
import com.machine.client.iam.permission.dto.input.LoongPermissionCreateInputDto;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionDetailOutputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionListOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoongPermissionBusinessImpl implements ILoongPermissionBusiness {

    @Autowired
    private ILoongPermissionClient permissionClient;

    @Override
    public String create(LoongPermissionCreateRequestVo requestVo) {
        LoongPermissionCreateInputDto inputDto = JSONUtil.toBean(JSONUtil.toJsonStr(requestVo), LoongPermissionCreateInputDto.class);
        return permissionClient.create(inputDto);
    }

    @Override
    public void delete(String permissionId) {
        permissionClient.delete(permissionId);
    }

    @Override
    public LoongPermissionDetailResponseVo detail(String permissionId) {
        LoongPermissionDetailOutputDto outputDto = permissionClient.detail(permissionId);
        return JSONUtil.toBean(JSONUtil.toJsonStr(outputDto), LoongPermissionDetailResponseVo.class);
    }

    @Override
    public List<LoongPermissionListResponseVo> list(LoongPermissionQueryListRequestVo requestVo) {
        LoongPermissionQueryListInputDto inputDto = JSONUtil.toBean(JSONUtil.toJsonStr(requestVo), LoongPermissionQueryListInputDto.class);
        List<LoongPermissionListOutputDto> outputDtoList = permissionClient.list(inputDto);
        return JSONUtil.toList(JSONUtil.toJsonStr(outputDtoList), LoongPermissionListResponseVo.class);
    }
}
