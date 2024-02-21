package com.machine.service.data.material.rest.request;

import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.PageRequest;
import lombok.Data;

@Data
public class LoongMaterialPermanentSelectPageRequest extends PageRequest {
    private DataMaterIalTypeEnum materIalType;
}
