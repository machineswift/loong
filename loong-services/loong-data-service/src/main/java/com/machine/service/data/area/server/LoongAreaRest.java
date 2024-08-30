package com.machine.service.data.area.server;

import com.machine.client.data.area.ILoongAreaClient;
import com.machine.service.data.area.service.ILoongAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/area")
public class LoongAreaRest implements ILoongAreaClient {


    @Autowired
    private ILoongAreaService areaService;

    @GetMapping("init")
    public void init() {
        log.info("初始化区域信息");
        areaService.init();
    }

}
