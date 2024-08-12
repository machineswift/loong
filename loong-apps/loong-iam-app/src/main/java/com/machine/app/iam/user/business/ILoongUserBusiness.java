package com.machine.app.iam.user.business;

import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;

public interface ILoongUserBusiness {

    String create(LoongUserCreateRequestVo requestVo);

    LoongUserDetailResponseVo detail(String userId);

}
