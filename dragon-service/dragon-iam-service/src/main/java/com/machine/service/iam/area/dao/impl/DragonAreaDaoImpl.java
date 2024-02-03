package com.machine.service.iam.area.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.machine.service.iam.area.dao.IDragonAreaDao;
import com.machine.service.iam.area.dao.dto.indto.DragonAreaInsertInDTO;
import com.machine.service.iam.area.dao.dto.outdto.DragonAreaListOutDTO;
import com.machine.service.iam.area.mapper.DragonAreaMapper;
import com.machine.service.iam.area.mapper.entity.DragonAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class DragonAreaDaoImpl implements IDragonAreaDao {

    @Autowired
    private DragonAreaMapper dragonAreaMapper;

    @Override
    public Boolean insertBatch(List<DragonAreaInsertInDTO> inDTOList) {
        if (CollectionUtil.isEmpty(inDTOList)) {
            return Boolean.TRUE;
        }

        List<DragonAreaEntity> entityList = JSONUtil.toList(JSONUtil.toJsonStr(inDTOList), DragonAreaEntity.class);
        //entityList.forEach(a->a.setId(UUID.randomUUID().toString().replaceAll("-","")));

        for (DragonAreaEntity entity : entityList) {
            entity.setCreateUser("system");
            entity.setCreateTime(new Date().getTime());

            entity.setUpdateUser("system");
            entity.setUpdateTime(new Date().getTime());
        }
        dragonAreaMapper.insertBatch(entityList);
        return Boolean.TRUE;
    }

    @Override
    public List<DragonAreaListOutDTO> selectByLevel(Integer level) {
        if (null == level) {
            return Collections.emptyList();
        }
        List<DragonAreaEntity> entityList = dragonAreaMapper.selectByLevel(level);
        return JSONUtil.toList(JSONUtil.toJsonStr(entityList), DragonAreaListOutDTO.class);
    }

    @Override
    public List<DragonAreaListOutDTO> selectByParentCode(String parentCode) {
        if (null == parentCode) {
            return Collections.emptyList();
        }
        List<DragonAreaEntity> entityList = dragonAreaMapper.selectByParentCode(parentCode);
        return JSONUtil.toList(JSONUtil.toJsonStr(entityList), DragonAreaListOutDTO.class);
    }
}
