package com.machine.client.iam.permission.dto.input;

import com.machine.common.envm.iam.PermissionTypeEnum;
import lombok.Data;

@Data
public class LoongPermissionCreateInputDto {

    private String parentId;

    private String code;

    private String name;

    private PermissionTypeEnum type;

    private String description;
}
