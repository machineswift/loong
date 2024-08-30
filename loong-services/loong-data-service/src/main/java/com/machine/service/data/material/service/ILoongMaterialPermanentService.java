package com.machine.service.data.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialPermanentDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialPermanentListOutputDto;

public interface ILoongMaterialPermanentService {

    String create(LoongMaterialPermanentCreateInputDto inputDto);

    LoongMaterialPermanentDetailOutputDto selectById(String id);

    Page<LoongMaterialPermanentListOutputDto> selectPage(LoongMaterialPermanentQueryPageInputVo inputVo);
;
}
