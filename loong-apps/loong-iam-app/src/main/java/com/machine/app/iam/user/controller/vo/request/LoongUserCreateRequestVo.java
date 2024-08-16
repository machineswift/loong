package com.machine.app.iam.user.controller.vo.request;

import com.machine.common.envm.crm.customer.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.machine.common.constant.LoongPatternConstant.*;

@Data
public class LoongUserCreateRequestVo {

    @Pattern(regexp = USER_NAME_PATTERN, message = "用户名格式错误")
    private String userName;

    @Pattern(regexp = PHONE_PATTERN, message = "手机号格式错误")
    private String phone;

    @NotBlank(message = "姓名不能为空")
    private String fullName;

    @Pattern(regexp = PASSWORD_PATTERN, message = "密码格式错误")
    private String password;

    /**
     * {@link GenderEnum}
     */
    @NotNull(message = "性别不能为空")
    private GenderEnum gender;

}
