package com.machine.client.data.material.indto;

import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoongMaterialPermanentQueryPageInputVo extends LoongPageRequest {

    private DataMaterIalTypeEnum type;

}
