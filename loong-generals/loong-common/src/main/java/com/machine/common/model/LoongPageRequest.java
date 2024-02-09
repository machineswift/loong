package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongPageRequest {

    //当前页
    private long current;

    //每页的数量
    private int size;
}
