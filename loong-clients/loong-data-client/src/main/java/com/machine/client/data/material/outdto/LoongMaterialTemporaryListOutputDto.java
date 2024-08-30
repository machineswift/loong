package com.machine.client.data.material.outdto;

import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongMaterialTemporaryListOutputDto {
    private String materialId;
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