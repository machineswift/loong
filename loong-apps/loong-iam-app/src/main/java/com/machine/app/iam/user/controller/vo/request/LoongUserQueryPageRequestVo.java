package com.machine.app.iam.user.controller.vo.request;

import com.machine.common.model.LoongPageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户分页查询请求参数")
public class LoongUserQueryPageRequestVo extends LoongPageRequest {

    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phone;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fullName;
}
