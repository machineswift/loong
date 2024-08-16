package com.machine.client.iam.user.dto.input;

import com.machine.common.model.LoongPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoongUserQueryPageInputVo extends LoongPageRequest {

    private String userName;


    private String phone;

    /**
     * 用户姓名
     */
    private String fullName;

    private Boolean enabled;

}
