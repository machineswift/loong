package com.machine.service.data.leaf.dao.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.machine.service.data.leaf.dao.ILoongLeafDao;
import com.machine.service.data.leaf.dao.mapper.LoongLeafMapper;
import com.machine.service.data.leaf.dao.mapper.entity.LoongLeafEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongLeafDaoImpl implements ILoongLeafDao {

    @Autowired
    private LoongLeafMapper leafMapper;

    @Autowired
    private LeafTransactional leafTransactional;

    @Override
    public LoongLeafEntity updateMaxId(LoongLeafEntity entity) {
        if (StrUtil.isEmpty(entity.getBizTag())) {
            return null;
        }

        Wrapper<LoongLeafEntity> queryWrapper = new LambdaQueryWrapper<LoongLeafEntity>()
                .eq(LoongLeafEntity::getBizTag, entity.getBizTag());

        LoongLeafEntity leafAlloc = leafMapper.selectOne(queryWrapper);
        if (null == leafAlloc) {
            if (null == entity.getStep() || entity.getStep() < 1) {
                return null;
            }
            leafAlloc = JSONUtil.toBean(JSONUtil.toJsonStr(entity), LoongLeafEntity.class);
            leafTransactional.insert(leafAlloc);
            if (null != leafAlloc.getId()) {
                leafAlloc = leafMapper.selectOne(queryWrapper);
            }
        }

        Integer result = leafTransactional.updateMaxId(leafAlloc);
        while (null == result || result < 1) {
            leafAlloc = leafMapper.selectOne(queryWrapper);
            result = leafTransactional.updateMaxId(leafAlloc);
        }
        return leafAlloc;
    }
}
