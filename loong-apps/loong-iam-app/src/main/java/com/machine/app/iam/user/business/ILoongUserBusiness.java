package com.machine.app.iam.user.business;

import com.machine.app.iam.user.controller.vo.request.LoongUserChangePasswordRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserCreateRequestVo;
import com.machine.app.iam.user.controller.vo.request.LoongUserQueryPageRequestVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;
import com.machine.app.iam.user.controller.vo.response.LoongUserListResponseVo;
import com.machine.common.model.LoongPageResponse;

import java.util.List;

public interface ILoongUserBusiness {

    String create(LoongUserCreateRequestVo requestVo);

    void changePassword(LoongUserChangePasswordRequestVo requestVo);

    LoongUserDetailResponseVo detail(String userId);

    LoongPageResponse<LoongUserListResponseVo> selectPage(LoongUserQueryPageRequestVo requestVo);

}
