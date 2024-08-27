package com.machine.app.iam.permission.controller.vo.response;

import com.machine.common.envm.iam.PermissionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "明细返回数据")
public class LoongPermissionDetailResponseVo {

    @Schema(description = "Id")
    private String permissionId;

    @Schema(description = "父Id")
    private String parentId;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "名称")
    private String name;

    /**
     * {@link PermissionTypeEnum}
     */

    @Schema(description = "类型，MENU、BUTTON")
    private PermissionTypeEnum type;


    @Schema(description = "描述")
    private String description;
}
