package com.machine.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongPageRequest {

    //当前页
    private Long current = 1L;

    //每页的数量
    private Long size = 20L;
}
