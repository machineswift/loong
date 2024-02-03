package com.machine.service.data.material.service.impl;

import com.machine.service.data.material.service.IDragonMaterialPermanentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DragonMaterialPermanentServiceImpl implements IDragonMaterialPermanentService {

    @Autowired
    private IDragonMaterialPermanentService dragonMaterialPermanentService;

}
