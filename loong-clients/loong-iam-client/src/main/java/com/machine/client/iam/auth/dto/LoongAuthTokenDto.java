package com.machine.client.iam.auth.dto;

import lombok.Data;

@Data
public class LoongAuthTokenDto {

    private String userName;

    private String series;

    private String token;

    private Long updateTime;
}
