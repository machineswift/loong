package com.machine.app.manage.data.material.controller.request;

import com.machine.common.envm.data.material.DataMaterIalTypeEnum;
import com.machine.common.model.LoongPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoongMaterialPermanentQueryPageRequest extends LoongPageRequest {

    private DataMaterIalTypeEnum materIalType;

}
