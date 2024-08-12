package com.machine.app.iam.user.controller.vo.request;

import lombok.Data;

@Data
public class LoongUserCreateRequestVo {

    private String userName;


    private String phone;

    /**
     * 用户姓名
     */
    private String fullName;

}
