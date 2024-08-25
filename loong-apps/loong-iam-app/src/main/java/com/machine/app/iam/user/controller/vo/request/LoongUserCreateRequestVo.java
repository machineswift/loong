package com.machine.app.iam.user.controller.vo.request;

import com.machine.common.envm.crm.customer.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.machine.common.constant.LoongPatternConstant.*;

@Data
@Schema(description = "创建用户请求参数")
public class LoongUserCreateRequestVo {

    @Pattern(regexp = USER_NAME_PATTERN, message = "用户名格式错误")
    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @Pattern(regexp = PHONE_PATTERN, message = "手机号格式错误")
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fullName;

    @Pattern(regexp = PASSWORD_PATTERN, message = "密码格式错误")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    /**
     * {@link GenderEnum}
     */
    @NotNull(message = "性别不能为空")
    @Schema(description = "性别，MALE:男 FEMALE:女 UNDEFINED:未知", requiredMode = Schema.RequiredMode.REQUIRED)
    private GenderEnum gender;

}
