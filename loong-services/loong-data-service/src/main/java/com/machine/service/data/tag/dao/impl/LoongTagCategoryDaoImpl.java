package com.machine.service.data.tag.dao.impl;

import com.machine.service.data.tag.dao.ILoongTagCategoryDao;
import com.machine.service.data.tag.dao.mapper.LoongTagCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongTagCategoryDaoImpl implements ILoongTagCategoryDao {

    @Autowired
    private LoongTagCategoryMapper loongTagCategoryMapper;

}
