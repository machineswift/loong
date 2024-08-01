package com.machine.app.iam.user.business;

import com.machine.app.iam.user.controller.vo.response.LoongUserDetailResponseVo;

public interface ILoongUserBusiness {
    LoongUserDetailResponseVo detail(String userId);
}
