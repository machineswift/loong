package com.machine.service.data.material.rest;

import com.machine.service.data.material.service.IDragonMaterialTemporaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("material/temporary")
public class DragonMaterialTemporaryRest {


    @Autowired
    private IDragonMaterialTemporaryService dragonMaterialTemporaryService;



}