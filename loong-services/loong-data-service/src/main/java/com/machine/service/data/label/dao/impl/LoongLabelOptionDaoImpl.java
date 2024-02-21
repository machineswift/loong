package com.machine.service.data.label.dao.impl;

import com.machine.service.data.label.dao.ILoongLabelOptionDao;
import com.machine.service.data.label.mapper.LoongLabelOptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongLabelOptionDaoImpl implements ILoongLabelOptionDao {

    @Autowired
    private LoongLabelOptionMapper loongLabelOptionMapper;

}
