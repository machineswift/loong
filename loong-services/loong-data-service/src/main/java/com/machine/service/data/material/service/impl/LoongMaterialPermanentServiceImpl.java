package com.machine.service.data.material.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.service.data.material.dao.ILoongMaterialPermanentDao;
import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.client.data.material.outdto.LoongMaterialPermanentDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialPermanentListOutputDto;
import com.machine.service.data.material.dao.mapper.entity.MaterialPermanentEntity;
import com.machine.service.data.material.service.ILoongMaterialPermanentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoongMaterialPermanentServiceImpl implements ILoongMaterialPermanentService {

    @Autowired
    private ILoongMaterialPermanentDao materialPermanentDao;

    @Override
    public String create(LoongMaterialPermanentCreateInputDto inputDto) {
        return materialPermanentDao.insert(inputDto);
    }

    @Override
    public LoongMaterialPermanentDetailOutputDto selectById(String id) {
        MaterialPermanentEntity entity = materialPermanentDao.selectById(id);
        if (null == entity) {
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialPermanentDetailOutputDto.class);
    }

    @Override
    public Page<LoongMaterialPermanentListOutputDto> selectPage(LoongMaterialPermanentQueryPageInputVo request) {
        Page<MaterialPermanentEntity> page = materialPermanentDao.selectPage(request);

        Page<LoongMaterialPermanentListOutputDto> pageResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<LoongMaterialPermanentListOutputDto> outputDtoList = new ArrayList<>();
        for (MaterialPermanentEntity entity : page.getRecords()) {
            LoongMaterialPermanentListOutputDto outputDto = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialPermanentListOutputDto.class);
            outputDto.setMaterialId(entity.getId());
            outputDtoList.add(outputDto);
        }
        pageResult.setRecords(outputDtoList);
        return pageResult;
    }
}
