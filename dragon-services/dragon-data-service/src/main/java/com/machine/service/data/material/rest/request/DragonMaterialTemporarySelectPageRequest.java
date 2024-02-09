package com.machine.service.data.material.rest.request;

import com.machine.common.envm.data.material.DragonDataMaterIalTypeEnum;
import com.machine.common.model.PageRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DragonMaterialTemporarySelectPageRequest extends PageRequest {

    private DragonDataMaterIalTypeEnum materIalType;

}
