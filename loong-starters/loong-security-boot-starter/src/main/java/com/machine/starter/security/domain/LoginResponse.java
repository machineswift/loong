package com.machine.starter.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;

    private String refreshToken;
}
