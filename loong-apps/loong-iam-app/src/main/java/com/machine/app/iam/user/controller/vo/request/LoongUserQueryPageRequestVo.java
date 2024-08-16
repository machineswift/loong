package com.machine.app.iam.user.controller.vo.request;

import com.machine.common.model.LoongPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoongUserQueryPageRequestVo extends LoongPageRequest {

    private String userName;


    private String phone;

    /**
     * 用户姓名
     */
    private String fullName;

}
