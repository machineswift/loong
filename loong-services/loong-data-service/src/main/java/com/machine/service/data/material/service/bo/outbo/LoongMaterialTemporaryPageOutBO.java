package com.machine.service.data.material.service.bo.outbo;

import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongMaterialTemporaryPageOutBO {
    private String id;
    /**
     * 类型
     */
    private DataMaterIalTypeEnum type;

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