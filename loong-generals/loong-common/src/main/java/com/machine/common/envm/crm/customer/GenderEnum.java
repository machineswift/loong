package com.machine.common.envm.crm.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    UNDEFINED("UNDEFINED", "未知"),
    MALE("MALE", "男"),
    FEMALE("FEMALE", "女");

    private final String code;
    private final String msg;
}
