package com.machine.app.iam.user.controller.vo.response;

import lombok.Data;

@Data
public class LoongUserListResponseVo {

    private String userId;

    private String userName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String fullName;
}
