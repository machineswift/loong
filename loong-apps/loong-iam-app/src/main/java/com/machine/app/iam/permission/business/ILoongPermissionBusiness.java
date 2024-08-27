package com.machine.app.iam.permission.business;

import com.machine.app.iam.permission.controller.vo.request.LoongPermissionCreateRequestVo;
import com.machine.app.iam.permission.controller.vo.request.LoongPermissionQueryListRequestVo;
import com.machine.app.iam.permission.controller.vo.response.LoongPermissionDetailResponseVo;
import com.machine.app.iam.permission.controller.vo.response.LoongPermissionListResponseVo;

import java.util.List;

public interface ILoongPermissionBusiness {

    String create(LoongPermissionCreateRequestVo requestVo);

    void delete(String permissionId);

    LoongPermissionDetailResponseVo detail(String permissionId);

    List<LoongPermissionListResponseVo> list(LoongPermissionQueryListRequestVo requestVo);

}
