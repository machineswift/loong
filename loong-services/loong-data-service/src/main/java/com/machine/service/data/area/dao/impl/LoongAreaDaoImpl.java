package com.machine.service.data.area.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.machine.service.data.area.dao.ILoongAreaDao;
import com.machine.service.data.area.dao.dto.indto.LoongAreaInsertInDTO;
import com.machine.service.data.area.dao.dto.outdto.LoongAreaListOutDTO;
import com.machine.service.data.area.dao.mapper.LoongAreaMapper;
import com.machine.service.data.area.dao.mapper.entity.AreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class LoongAreaDaoImpl implements ILoongAreaDao {

    @Autowired
    private LoongAreaMapper loongAreaMapper;

    @Override
    public Boolean insertBatch(List<LoongAreaInsertInDTO> inDTOList) {
        if (CollectionUtil.isEmpty(inDTOList)) {
            return Boolean.TRUE;
        }

        List<AreaEntity> entityList = JSONUtil.toList(JSONUtil.toJsonStr(inDTOList), AreaEntity.class);

//        for (LoongAreaEntity entity : entityList) {
//            entity.setCreateBy("system");
//            entity.setCreateTime(new Date().getTime());
//
//            entity.setUpdateBy("system");
//            entity.setUpdateTime(new Date().getTime());
//        }
        loongAreaMapper.insertBatch(entityList);
        return Boolean.TRUE;
    }

    @Override
    public List<LoongAreaListOutDTO> selectByLevel(Integer level) {
        if (null == level) {
            return Collections.emptyList();
        }
        List<AreaEntity> entityList = loongAreaMapper.selectByLevel(level);
        return JSONUtil.toList(JSONUtil.toJsonStr(entityList), LoongAreaListOutDTO.class);
    }

    @Override
    public List<LoongAreaListOutDTO> selectByParentCode(String parentCode) {
        if (null == parentCode) {
            return Collections.emptyList();
        }
        List<AreaEntity> entityList = loongAreaMapper.selectByParentCode(parentCode);
        return JSONUtil.toList(JSONUtil.toJsonStr(entityList), LoongAreaListOutDTO.class);
    }
}
