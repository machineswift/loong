package com.machine.service.iam.user.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.service.iam.user.dao.ILoongUserDao;
import com.machine.service.iam.user.dao.mapper.ILoongUserMapper;
import com.machine.service.iam.user.dao.mapper.entity.LoongUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongUserDaoImpl implements ILoongUserDao {

    @Autowired
    private ILoongUserMapper userMapper;

    @Override
    public String insert(LoongUserEntity insertEntity) {
        userMapper.insert(insertEntity);
        return insertEntity.getId();
    }

    @Override
    public int updatePassword(String userId,
                              String password) {
        LoongUserEntity updateEntity = new LoongUserEntity();
        updateEntity.setId(userId);
        updateEntity.setPassword(password);

        return userMapper.updateById(updateEntity);
    }

    @Override
    public LoongUserEntity detail(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public LoongUserEntity getByUserName(String userName) {
        Wrapper<LoongUserEntity> queryWrapper = new LambdaQueryWrapper<LoongUserEntity>()
                .eq(LoongUserEntity::getUserName, userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<LoongUserEntity> selectPage(LoongUserQueryPageInputVo inputVo) {
        IPage<LoongUserEntity> page = new Page<>(inputVo.getCurrent(), inputVo.getSize());
        return userMapper.selectUserPage(inputVo, page);
    }

}
