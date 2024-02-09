package com.machine.common.envm.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemEnvironmentEnum {
    DEV("DEV", "开发环境"),
    TEST("TEST", "测试环境"),
    UAT("UAT", "预发布环境"),
    PROD("PROD", "生产环境");

    private final String code;
    private final String msg;
}