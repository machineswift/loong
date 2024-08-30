package com.machine.service.data.material.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.service.data.material.dao.ILoongMaterialTemporaryDao;
import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryListOutputDto;
import com.machine.service.data.material.dao.mapper.entity.MaterialTemporaryEntity;
import com.machine.service.data.material.service.ILoongMaterialTemporaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoongMaterialTemporaryServiceImpl implements ILoongMaterialTemporaryService {

    @Autowired
    private ILoongMaterialTemporaryDao materialTemporaryDao;

    @Override
    public String create(LoongMaterialTemporaryCreateInputDto inputDto) {
        return materialTemporaryDao.insert(inputDto);
    }

    @Override
    public LoongMaterialTemporaryDetailOutputDto detail(String id) {
        LoongMaterialTemporaryDetailOutputDto detailOutDTO = materialTemporaryDao.selectById(id);
        if(null == detailOutDTO){
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(detailOutDTO), LoongMaterialTemporaryDetailOutputDto.class);
    }

    @Override
    public Page<LoongMaterialTemporaryListOutputDto> selectPage(LoongMaterialTemporaryQueryPageInputVo request) {
        Page<MaterialTemporaryEntity> page = materialTemporaryDao.selectPage(request);

        Page<LoongMaterialTemporaryListOutputDto> pageResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<LoongMaterialTemporaryListOutputDto> outputDtoList = new ArrayList<>();
        for (MaterialTemporaryEntity entity : page.getRecords()) {
            LoongMaterialTemporaryListOutputDto outputDto = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongMaterialTemporaryListOutputDto.class);
            outputDto.setMaterialId(entity.getId());
            outputDtoList.add(outputDto);
        }
        pageResult.setRecords(outputDtoList);
        return pageResult;
    }
}
