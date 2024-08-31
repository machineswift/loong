package com.machine.common.envm.iam;

import com.machine.common.envm.LoongBaseEnum;
import com.machine.common.envm.LoongStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionTypeEnum implements LoongBaseEnum<LoongStatusEnum, String> {
    MENU("MENU", "菜单"),
    BUTTON("BUTTON", "按钮");

    private final String code;
    private final String msg;
}
