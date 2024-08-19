package com.machine.app.iam.department.business;

import com.machine.app.iam.department.controller.vo.request.LoongDepartmentCreateRequestVo;
import com.machine.app.iam.department.controller.vo.request.LoongDepartmentQueryListRequestVo;
import com.machine.app.iam.department.controller.vo.response.LoongDepartmentListResponseVo;

import java.util.List;

public interface ILoongDepartmentBusiness {

    String create(LoongDepartmentCreateRequestVo requestVo);

    List<LoongDepartmentListResponseVo> list(LoongDepartmentQueryListRequestVo requestVo);

}
