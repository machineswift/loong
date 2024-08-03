package com.machine.client.iam.user.dto;

import lombok.Data;

@Data
public class LoongUserUpdatePasswordDto {

    private String userId;

    private String password;
}
