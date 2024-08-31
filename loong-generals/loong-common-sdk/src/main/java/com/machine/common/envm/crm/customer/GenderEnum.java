package com.machine.common.envm.crm.customer;

import com.machine.common.envm.LoongBaseEnum;
import com.machine.common.envm.LoongStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements LoongBaseEnum<LoongStatusEnum, String> {
    MALE("MALE", "男"),
    FEMALE("FEMALE", "女"),
    UNDEFINED("UNDEFINED", "未知");

    private final String code;
    private final String msg;
}
