package com.machine.service.data.label.dao.impl;

import com.machine.service.data.label.dao.ILoongLabelDao;
import com.machine.service.data.label.dao.mapper.LoongLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongLabelDaoImpl implements ILoongLabelDao {

    @Autowired
    private LoongLabelMapper loongLabelMapper;

}
