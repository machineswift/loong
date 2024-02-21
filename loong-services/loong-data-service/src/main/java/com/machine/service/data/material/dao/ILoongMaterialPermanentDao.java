package com.machine.service.data.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialPermanentInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialPermanentDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialPermanentPageOutDTO;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectPageRequest;

public interface ILoongMaterialPermanentDao {

    String insert(LoongMaterialPermanentInsertInDTO inDTO);

    LoongMaterialPermanentDetailOutDTO selectById(String id);

    Page<LoongMaterialPermanentPageOutDTO> selectPage(LoongMaterialPermanentSelectPageRequest request);
}
