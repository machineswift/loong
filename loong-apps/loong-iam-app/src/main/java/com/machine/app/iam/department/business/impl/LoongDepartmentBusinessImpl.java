package com.machine.app.iam.department.business.impl;

import cn.hutool.json.JSONUtil;
import com.machine.app.iam.department.business.ILoongDepartmentBusiness;
import com.machine.app.iam.department.controller.vo.request.LoongDepartmentQueryListRequestVo;
import com.machine.app.iam.department.controller.vo.response.LoongDepartmentListResponseVo;
import com.machine.client.iam.department.ILoongDepartmentClient;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoongDepartmentBusinessImpl implements ILoongDepartmentBusiness {

    @Autowired
    private ILoongDepartmentClient departmentClient;

    @Override
    public List<LoongDepartmentListResponseVo> list(LoongDepartmentQueryListRequestVo requestVo) {
        LoongDepartmentQueryListInputVo inputVo = JSONUtil.toBean(JSONUtil.toJsonStr(requestVo),
                LoongDepartmentQueryListInputVo.class);
        List<LoongDepartmentListOutputDto> outputDtoList = departmentClient.list(inputVo);
        return JSONUtil.toList(JSONUtil.toJsonStr(outputDtoList), LoongDepartmentListResponseVo.class);
    }
}
