package com.machine.service.data.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialTemporaryInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryPageOutDTO;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectPageRequest;

public interface ILoongMaterialTemporaryDao {

    String insert(LoongMaterialTemporaryInsertInDTO inDTO);
    LoongMaterialTemporaryDetailOutDTO selectById(String id);

    Page<LoongMaterialTemporaryPageOutDTO> selectPage(LoongMaterialTemporarySelectPageRequest request);


}
