package com.machine.app.iam.permission.controller.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class LoongPermissionQueryListRequestVo {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
