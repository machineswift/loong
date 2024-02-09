package com.machine.service.data.material.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.tool.json.LoongPageUtil;
import com.machine.service.data.material.dao.ILoongMaterialTemporaryDao;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialTemporaryInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialTemporaryPageOutDTO;
import com.machine.service.data.material.rest.request.LoongMaterialTemporarySelectLoongPageRequest;
import com.machine.service.data.material.service.ILoongMaterialTemporaryService;
import com.machine.service.data.material.service.bo.inbo.LoongMaterialTemporaryInsertInBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialTemporaryDetailOutBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialTemporaryPageOutBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongMaterialTemporaryServiceImpl implements ILoongMaterialTemporaryService {

    @Autowired
    private ILoongMaterialTemporaryDao materialTemporaryDao;

    @Override
    public String insert(LoongMaterialTemporaryInsertInBO insertInBO) {
        LoongMaterialTemporaryInsertInDTO insertInDTO = JSONUtil.toBean(JSONUtil.toJsonStr(insertInBO), LoongMaterialTemporaryInsertInDTO.class);
        return materialTemporaryDao.insert(insertInDTO);
    }

    @Override
    public LoongMaterialTemporaryDetailOutBO selectById(String id) {
        LoongMaterialTemporaryDetailOutDTO detailOutDTO = materialTemporaryDao.selectById(id);
        if(null == detailOutDTO){
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutDTO), LoongMaterialTemporaryDetailOutBO.class);
    }

    @Override
    public Page<LoongMaterialTemporaryPageOutBO> selectPage(LoongMaterialTemporarySelectLoongPageRequest request) {
        Page<LoongMaterialTemporaryPageOutDTO> outBOPage = materialTemporaryDao.selectPage(request);
        return LoongPageUtil.convertT1ToT2(outBOPage, LoongMaterialTemporaryPageOutBO.class);
    }
}
