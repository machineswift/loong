package com.machine.common.envm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoongStatusEnum implements LoongBaseEnum<LoongStatusEnum, String> {
    DISABLE("DISABLE", "禁用"),

    ENABLE("ENABLE", "启用");

    private final String code;
    private final String msg;
}
