package com.machine.app.iam.department.controller.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoongDepartmentCreateRequestVo {

    @NotBlank(message = "父id不能为空")
    private String parentId;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String description;

}
