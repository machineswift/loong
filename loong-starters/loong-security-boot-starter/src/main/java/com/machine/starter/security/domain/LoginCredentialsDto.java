package com.machine.starter.security.domain;

import lombok.Data;

@Data
public class LoginCredentialsDto {
    private String userName;
    private String password;
    private String captcha;
    private String userKey;
}
