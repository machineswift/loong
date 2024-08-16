package com.machine.client.iam.user.dto;

import com.machine.common.envm.crm.customer.GenderEnum;
import lombok.Data;

@Data
public class LoongUserDetailDto {
    private String userId;

    private String userName;

    private String fullName;

    private String phone;

    private GenderEnum gender;

    private Boolean enabled;
}
