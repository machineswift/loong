package com.machine.app.iam.user.controller.vo.response;

import com.machine.common.envm.crm.customer.GenderEnum;
import lombok.Data;

@Data
public class LoongUserDetailResponseVo {
    private String userId;
    private String userName;
    private String phone;
    private String fullName;
    private GenderEnum gender;
}
