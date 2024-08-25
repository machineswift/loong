package com.machine.app.iam.user.controller.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "修改密码请求参数")
public class LoongUserChangePasswordRequestVo {

    @NotBlank(message = "旧密码不能为空")
    @Schema(description = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String newPassword;
}
