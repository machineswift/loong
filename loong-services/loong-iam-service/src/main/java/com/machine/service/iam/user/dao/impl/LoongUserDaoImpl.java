package com.machine.service.iam.user.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    public int updatePassword(String userId,
                              String password) {
        LoongUserEntity updateEntity =new LoongUserEntity();
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

}
