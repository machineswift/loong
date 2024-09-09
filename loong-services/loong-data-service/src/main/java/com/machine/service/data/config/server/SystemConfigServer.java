package com.machine.service.data.config.server;

import com.machine.client.data.config.ILoongConfigClient;
import com.machine.client.data.config.dto.LoongConfigDto;
import com.machine.service.data.config.service.ISystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/config")
public class SystemConfigServer implements ILoongConfigClient {

    @Autowired
    private ISystemConfigService configService;

    @Override
    @PostMapping("create")
    public void create(@Validated @RequestBody LoongConfigDto dto) {
        log.info("新增配置: {}", dto);
        configService.create(dto);
    }

    @Override
    @DeleteMapping("delete")
    public void delete(@Validated @RequestBody LoongConfigDto dto) {
        log.info("删除配置: {}", dto);
        configService.delete(dto);
    }

    @Override
    @PutMapping("update")
    public void update(@Validated @RequestBody LoongConfigDto dto) {
        log.info("修改配置: {}", dto);
        configService.update(dto);
    }

    @Override
    @PostMapping("exist")
    public boolean exist(@Validated @RequestBody LoongConfigDto dto) {
        LoongConfigDto conf = getByUk(dto);
        return conf != null;
    }

    @Override
    @PostMapping("boolValue")
    public Boolean getBool(@Validated @RequestBody LoongConfigDto dto) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return null;
        }
        return Boolean.valueOf(conf.getContent());
    }

    @Override
    @PostMapping("boolValue/default")
    public Boolean getBoolOrElse(@Validated @RequestBody LoongConfigDto dto,
                                 Boolean defaultValue) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return defaultValue;
        }
        return Boolean.valueOf(conf.getContent());
    }

    @Override
    @PostMapping("intValue")
    public Integer getInt(@Validated @RequestBody LoongConfigDto dto) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return null;
        }
        return Integer.valueOf(conf.getContent());
    }

    @Override
    @PostMapping("intValue/default")
    public Integer getIntOrElse(@Validated @RequestBody LoongConfigDto dto,
                                Integer defaultValue) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return defaultValue;
        }
        return Integer.valueOf(conf.getContent());
    }

    @Override
    @PostMapping("longValue")
    public Long getLong(@Validated @RequestBody LoongConfigDto dto) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return null;
        }
        return Long.valueOf(conf.getContent());
    }

    @Override
    @PostMapping("longValue/default")
    public Long getLongOrElse(@Validated @RequestBody LoongConfigDto dto,
                              Long defaultValue) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return defaultValue;
        }
        return Long.valueOf(conf.getContent());
    }

    @Override
    @PostMapping("stringValue")
    public String getString(@Validated @RequestBody LoongConfigDto dto) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return null;
        }
        return conf.getContent();
    }

    @Override
    @PostMapping("stringValue/default")
    public String getStringOrElse(@Validated @RequestBody LoongConfigDto dto,
                                  String defaultValue) {
        LoongConfigDto conf = getByUk(dto);
        if (conf == null) {
            return defaultValue;
        }
        return conf.getContent();
    }


    private LoongConfigDto getByUk(LoongConfigDto dto) {
        return configService.getByUk(dto);
    }
}
