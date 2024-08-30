package com.machine.service.data.label.dao.impl;

import com.machine.service.data.label.dao.ILoongLabelCategoryDao;
import com.machine.service.data.label.dao.mapper.LoongLabelCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongLabelCategoryDaoImpl implements ILoongLabelCategoryDao {

    @Autowired
    private LoongLabelCategoryMapper loongLabelCategoryMapper;

}
