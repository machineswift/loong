package com.machine.service.data.material.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.tool.json.DragonPageUtil;
import com.machine.service.data.material.dao.IDragonMaterialTemporaryDao;
import com.machine.service.data.material.rest.request.DragonMaterialTemporarySelectPageRequest;
import com.machine.service.data.material.service.IDragonMaterialTemporaryService;
import com.machine.service.data.material.service.bo.outbo.DragonMaterialPermanentOutBO;
import com.machine.service.data.material.service.bo.outbo.DragonMaterialTemporaryPageOutBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DragonMaterialTemporaryServiceImpl implements IDragonMaterialTemporaryService {

    @Autowired
    private IDragonMaterialTemporaryDao dragonMaterialTemporaryDao;

    @Override
    public Page<DragonMaterialTemporaryPageOutBO> selectPage(DragonMaterialTemporarySelectPageRequest request) {
        Page<DragonMaterialTemporaryPageOutBO> outBOPage = dragonMaterialTemporaryDao.selectPage(request);
        return DragonPageUtil.convertT1ToT2(outBOPage, DragonMaterialTemporaryPageOutBO.class);
    }
}
