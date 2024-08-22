package com.machine.app.iam.department.controller.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "创建部门请求参数")
public class LoongDepartmentCreateRequestVo {

    @NotBlank(message = "父id不能为空")
    @Schema(description = "父id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String parentId;

    @NotBlank(message = "名称不能为空")
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

}
