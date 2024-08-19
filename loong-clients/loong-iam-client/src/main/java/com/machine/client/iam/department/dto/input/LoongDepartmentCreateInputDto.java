package com.machine.client.iam.department.dto.input;

import lombok.Data;

@Data
public class LoongDepartmentCreateInputDto {

    private String parentId;

    private String name;

    private String description;
}
