package com.machine.service.data.tag.dao.impl;

import com.machine.service.data.tag.dao.ILoongTagDao;
import com.machine.service.data.tag.mapper.LoongTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoongTagDaoImpl implements ILoongTagDao {

    @Autowired
    private LoongTagMapper loongTagMapper;

}
