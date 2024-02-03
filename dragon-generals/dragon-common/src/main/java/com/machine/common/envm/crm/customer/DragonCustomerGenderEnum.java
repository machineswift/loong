package com.machine.common.envm.crm.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DragonCustomerGenderEnum {
    UNDEFINED("UNDEFINED", "未知"),
    MALE("MALE", "男"),
    FEMALE("FEMALE", "女");

    private final String code;
    private final String msg;
}
