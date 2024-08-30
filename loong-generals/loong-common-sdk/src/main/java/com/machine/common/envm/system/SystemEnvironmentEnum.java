package com.machine.common.envm.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemEnvironmentEnum {
    DEV("DEV", "开发环境"),
    SIT("SIT", "系统整合测试环境（内测）"),
    TEST("TEST", "测试环境"),
    UAT("UAT", "用户验收测试环境（预发布）"),
    PET("PET", "性能评估测试环境（压测）"),
    SIM("SIM", "仿真环境（压测）"),
    PROD("PROD", "生产环境");

    private final String code;
    private final String msg;
}