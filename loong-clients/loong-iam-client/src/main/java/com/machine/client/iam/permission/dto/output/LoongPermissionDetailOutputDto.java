package com.machine.client.iam.permission.dto.output;

import com.machine.common.envm.iam.PermissionTypeEnum;
import lombok.Data;

@Data
public class LoongPermissionDetailOutputDto {

    private String permissionId;

    private String parentId;

    private String code;

    private String name;

    private PermissionTypeEnum type;

    private String description;

}
