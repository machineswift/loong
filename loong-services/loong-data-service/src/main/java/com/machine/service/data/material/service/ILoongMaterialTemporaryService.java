package com.machine.service.data.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryListOutputDto;

public interface ILoongMaterialTemporaryService {

    String create(LoongMaterialTemporaryCreateInputDto inputDto);

    LoongMaterialTemporaryDetailOutputDto detail(String id);

    Page<LoongMaterialTemporaryListOutputDto> selectPage(LoongMaterialTemporaryQueryPageInputVo inputVo);

}
