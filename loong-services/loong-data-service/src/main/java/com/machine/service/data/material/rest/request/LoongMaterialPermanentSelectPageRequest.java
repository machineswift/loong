package com.machine.service.data.material.rest.request;

import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageRequest;
import lombok.Data;

@Data
public class LoongMaterialPermanentSelectPageRequest extends LoongPageRequest {
    private DataMaterIalTypeEnum materIalType;
}
