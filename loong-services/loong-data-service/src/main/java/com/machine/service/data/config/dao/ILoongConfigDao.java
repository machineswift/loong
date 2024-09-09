package com.machine.service.data.config.dao;

import com.machine.service.data.config.dao.mapper.entity.ConfigEntity;

public interface ILoongConfigDao {
    ConfigEntity getByUk(String category, String code);
}
