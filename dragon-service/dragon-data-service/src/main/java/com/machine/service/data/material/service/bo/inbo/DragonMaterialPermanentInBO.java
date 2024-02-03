package com.machine.service.data.material.service.bo.inbo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DragonMaterialPermanentInBO {

    /**
     * 类型
     */
    private String type;

    /**
     * 大小（字节）
     */
    private Long length;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;
}