package com.machine.app.iam.user.controller.vo.request;

import lombok.Data;

@Data
public class LoongUserChangePasswordRequestVo {
    private String oldPassword;
    private String newPassword;
}
