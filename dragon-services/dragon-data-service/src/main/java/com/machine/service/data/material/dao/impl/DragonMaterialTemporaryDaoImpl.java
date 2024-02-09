package com.machine.service.data.material.dao.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.tool.json.DragonPageUtil;
import com.machine.service.data.material.dao.IDragonMaterialTemporaryDao;
import com.machine.service.data.material.mapper.DragonMaterialTemporaryMapper;
import com.machine.service.data.material.mapper.entity.DragonMaterialTemporaryEntity;
import com.machine.service.data.material.rest.request.DragonMaterialTemporarySelectPageRequest;
import com.machine.service.data.material.service.bo.outbo.DragonMaterialTemporaryPageOutBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DragonMaterialTemporaryDaoImpl implements IDragonMaterialTemporaryDao {

    @Autowired
    private DragonMaterialTemporaryMapper dragonMaterialTemporaryMapper;


    @Override
    public Page<DragonMaterialTemporaryPageOutBO> selectPage(DragonMaterialTemporarySelectPageRequest request) {
        Page<DragonMaterialTemporaryEntity> page = new Page(request.getCurrent(), request.getSize());
        IPage<DragonMaterialTemporaryEntity> entityIPage = dragonMaterialTemporaryMapper.selectMaterialTemporaryPage(page, request);
        return DragonPageUtil.convertT1ToT2(entityIPage, DragonMaterialTemporaryPageOutBO.class);
    }
}
