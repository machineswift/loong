package com.machine.service.data.material.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.common.tool.json.LoongPageUtil;
import com.machine.service.data.material.dao.ILoongMaterialPermanentDao;
import com.machine.service.data.material.dao.dto.indto.LoongMaterialPermanentInsertInDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialPermanentDetailOutDTO;
import com.machine.service.data.material.dao.dto.outdto.LoongMaterialPermanentPageOutDTO;
import com.machine.service.data.material.rest.request.LoongMaterialPermanentSelectLoongPageRequest;
import com.machine.service.data.material.service.ILoongMaterialPermanentService;
import com.machine.service.data.material.service.bo.inbo.LoongMaterialPermanentInsertInBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialPermanentDetailOutBO;
import com.machine.service.data.material.service.bo.outbo.LoongMaterialPermanentPageOutBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoongMaterialPermanentServiceImpl implements ILoongMaterialPermanentService {

    @Autowired
    private ILoongMaterialPermanentDao materialPermanentDao;

    @Override
    public String insert(LoongMaterialPermanentInsertInBO inBO) {
        LoongMaterialPermanentInsertInDTO insertInDTO = JSONUtil.toBean(JSONUtil.toJsonStr(inBO), LoongMaterialPermanentInsertInDTO.class);
        return materialPermanentDao.insert(insertInDTO);
    }

    @Override
    public LoongMaterialPermanentDetailOutBO selectById(String id) {
        LoongMaterialPermanentDetailOutDTO detailOutDTO = materialPermanentDao.selectById(id);
        if(null == detailOutDTO){
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutDTO), LoongMaterialPermanentDetailOutBO.class);
    }

    @Override
    public Page<LoongMaterialPermanentPageOutBO> selectPage(LoongMaterialPermanentSelectLoongPageRequest request) {
        Page<LoongMaterialPermanentPageOutDTO> outBOPage = materialPermanentDao.selectPage(request);
        return LoongPageUtil.convertT1ToT2(outBOPage, LoongMaterialPermanentPageOutBO.class);
    }
}
