package com.machine.service.data.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialTemporaryInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryPageOutDTO;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectLoongPageRequest;

public interface ILoongMaterialTemporaryDao {

    String insert(LoongMaterialTemporaryInsertInDTO insertInDTO);
    LoongMaterialTemporaryDetailOutDTO selectById(String id);

    Page<LoongMaterialTemporaryPageOutDTO> selectPage(LoongMaterialTemporarySelectLoongPageRequest request);


}
