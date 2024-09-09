package com.machine.service.data.config.server;

import com.machine.client.data.config.ILoongConfigClient;
import com.machine.client.data.config.dto.LoongConfigDto;
import com.machine.service.data.config.service.ILoongConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/config")
public class LoongConfigServer implements ILoongConfigClient {

    @Autowired
    private ILoongConfigService configService;

    @Override
    @PostMapping("save")
    public void save(LoongConfigDto dto) {

    }

    @Override
    @DeleteMapping("remove")
    public void remove(LoongConfigDto dto) {

    }

    @Override
    @PutMapping("update")
    public void update(LoongConfigDto dto) {

    }

    @Override
    @PostMapping("exist")
    public boolean exist(LoongConfigDto dto) {

        return false;
    }

    @Override
    @PostMapping("boolValue")
    public Boolean getBool(LoongConfigDto dto) {
        return null;
    }

    @Override
    @PostMapping("boolValue/default")
    public Boolean getBoolOrElse(LoongConfigDto dto, Boolean defaultValue) {
        return null;
    }

    @Override
    @PostMapping("intValue")
    public Integer getInt(LoongConfigDto dto) {
        return 0;
    }

    @Override
    @PostMapping("intValue/default")
    public Integer getIntOrElse(LoongConfigDto dto, Integer defaultValue) {
        return 0;
    }

    @Override
    @PostMapping("longValue")
    public Long getLong(LoongConfigDto dto) {
        return 0L;
    }

    @Override
    @PostMapping("longValue/default")
    public Long getLongOrElse(LoongConfigDto dto, Long defaultValue) {
        return 0L;
    }

    @Override
    @PostMapping("stringValue")
    public String getString(LoongConfigDto dto) {
        return "";
    }

    @Override
    @PostMapping("stringValue/default")
    public String getStringOrElse(LoongConfigDto dto, String defaultValue) {
        return "";
    }


    private LoongConfigDto getByUk(LoongConfigDto dto){
return null;
    }
}
