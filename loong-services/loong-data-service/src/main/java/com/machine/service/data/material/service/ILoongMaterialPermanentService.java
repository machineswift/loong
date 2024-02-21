package com.machine.service.data.material.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectPageRequest;
import com.machine.service.data.material.service.bo.inbo.LoongMaterialPermanentInsertInBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialPermanentDetailOutBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialPermanentPageOutBO;

public interface ILoongMaterialPermanentService {

    String insert(LoongMaterialPermanentInsertInBO inBO);

    LoongMaterialPermanentDetailOutBO selectById(String id);

    Page<LoongMaterialPermanentPageOutBO> selectPage(LoongMaterialPermanentSelectPageRequest request);
}
