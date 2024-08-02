package com.machine.app.iam.auth.controller.vo.request;

import lombok.Data;

@Data
public class LoginCredentialsRequestVo {
    private String username;
    private String password;
    private String captcha;
}
