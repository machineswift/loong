package com.machine.app.iam.permission.controller.vo.request;

import com.machine.common.envm.iam.PermissionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "创建权限请求参数")
public class LoongPermissionCreateRequestVo {

    @NotBlank(message = "父Id不能为空")
    @Schema(description = "父Id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String parentId;

    @NotBlank(message = "编码不能为空")
    @Schema(description = "编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * {@link PermissionTypeEnum}
     */
    @NotBlank(message = "类型不能为空")
    @Schema(description = "类型，MENU、BUTTON", requiredMode = Schema.RequiredMode.REQUIRED)
    private PermissionTypeEnum type;

    @NotBlank(message = "描述不能为空")
    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
