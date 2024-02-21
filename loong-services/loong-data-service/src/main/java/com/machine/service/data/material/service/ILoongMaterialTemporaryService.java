package com.machine.service.data.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectPageRequest;
import com.machine.service.data.material.service.bo.inbo.LoongMaterialTemporaryInsertInBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialTemporaryDetailOutBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialTemporaryPageOutBO;

public interface ILoongMaterialTemporaryService {

    String insert(LoongMaterialTemporaryInsertInBO inBO);

    LoongMaterialTemporaryDetailOutBO selectById(String id);

    Page<LoongMaterialTemporaryPageOutBO> selectPage(LoongMaterialTemporarySelectPageRequest request);

}
