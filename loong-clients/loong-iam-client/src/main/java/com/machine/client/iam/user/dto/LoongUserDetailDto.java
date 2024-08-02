package com.machine.client.iam.user.dto;

import lombok.Data;

@Data
public class LoongUserDetailDto {
    private String userId;

    private String userName;

    private String password;

    private String fullName;

    private String phone;

    private Boolean enabled;
}
