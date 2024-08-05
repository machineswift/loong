package com.machine.client.iam.auth.dto;

import lombok.Data;

@Data
public class LoongAuthTokenAddDto {

    private String username;

    private String series;

    private String token;
}
