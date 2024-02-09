package com.machine.service.data.material.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.service.data.material.rest.request.DragonMaterialTemporarySelectPageRequest;
import com.machine.service.data.material.service.bo.outbo.DragonMaterialTemporaryPageOutBO;

public interface IDragonMaterialTemporaryDao {

    Page<DragonMaterialTemporaryPageOutBO> selectPage(DragonMaterialTemporarySelectPageRequest request);
}
