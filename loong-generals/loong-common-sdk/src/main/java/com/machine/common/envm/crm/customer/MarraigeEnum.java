package com.machine.common.envm.crm.customer;

import com.machine.common.envm.LoongBaseEnum;
import com.machine.common.envm.LoongStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 婚姻状况
 */
@Getter
@AllArgsConstructor
public enum MarraigeEnum implements LoongBaseEnum<LoongStatusEnum, String> {

    SECRECY("SECRECY", "保密"),
    MARRIED("MARRIED", "已婚"),
    UNMARRIED("UNMARRIED", "未婚");

    private final String code;
    private final String msg;
}
