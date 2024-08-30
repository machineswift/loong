package com.machine.common.envm;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 婚姻状况
 */
@Getter
@AllArgsConstructor
public enum MarraigeEnum {

    SECRECY("SECRECY", "保密"),
    MARRIED("MARRIED", "已婚"),
    UNMARRIED("UNMARRIED", "未婚");

    private final String code;
    private final String msg;
}
