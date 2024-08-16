package com.machine.client.iam.user.dto.input;

import com.machine.common.envm.crm.customer.GenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongUserCreateInputDto {

    private String userName;
    private String phone;
    private String fullName;
    private String password;
    private GenderEnum gender;
}
