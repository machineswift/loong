package com.machine.service.data.material.service.impl;

import com.machine.service.data.material.dao.IDragonMaterialTemporaryDao;
import com.machine.service.data.material.service.IDragonMaterialTemporaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DragonMaterialTemporaryServiceImpl implements IDragonMaterialTemporaryService {

    @Autowired
    private IDragonMaterialTemporaryDao dragonMaterialTemporaryDao;

}
