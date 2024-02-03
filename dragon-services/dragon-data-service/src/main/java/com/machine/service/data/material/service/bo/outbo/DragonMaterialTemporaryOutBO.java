package com.machine.service.data.material.service.bo.outbo;

import com.machine.common.envm.crm.customer.DragonCustomerGenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DragonMaterialTemporaryOutBO {
    /**
     * 类型
     */
    private DragonCustomerGenderEnum type;

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