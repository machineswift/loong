package com.machine.client.iam.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongUserDto {
    private String userId;
    private String userName;
    private String password;
}
