package com.machine.service.data.area.rest;

import com.machine.service.data.area.service.IDragonAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("area")
public class DragonAreaRest {


    @Autowired
    private IDragonAreaService dragonAreaService;

    @GetMapping("init")
    public void init() {
        log.info("初始化区域信息");
        dragonAreaService.init();
    }

}
