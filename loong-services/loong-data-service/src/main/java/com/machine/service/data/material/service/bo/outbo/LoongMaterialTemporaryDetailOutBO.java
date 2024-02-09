package com.machine.service.data.material.service.bo.outbo;

import com.machine.common.envm.crm.customer.CustomerGenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoongMaterialTemporaryDetailOutBO {
    private String id;
    /**
     * 类型
     */
    private CustomerGenderEnum type;

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