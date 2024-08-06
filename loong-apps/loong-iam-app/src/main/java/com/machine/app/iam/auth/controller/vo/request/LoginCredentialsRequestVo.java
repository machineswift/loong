package com.machine.app.iam.auth.controller.vo.request;

import lombok.Data;

@Data
public class LoginCredentialsRequestVo {
    private String userName;
    private String password;
    private String captcha;
    private String rememberMe;
}
