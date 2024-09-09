package com.machine.client.data.config;

import com.machine.client.data.config.dto.LoongConfigDto;
import com.machine.common.config.LoongOpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "loong-data-service/loong-data-service/server/config", configuration = LoongOpenFeignConfig.class)
public interface ILoongConfigClient {

    @PostMapping("save")
    void save(@Validated @RequestBody LoongConfigDto dto);

    @DeleteMapping("remove")
    void remove(@Validated @RequestBody LoongConfigDto dto);

    @PutMapping("update")
    void update(@Validated @RequestBody LoongConfigDto inputDto);

    @PostMapping("exist")
    boolean exist(@Validated @RequestBody LoongConfigDto inputDto);

    @PostMapping("boolValue")
    Boolean getBool(@Validated @RequestBody LoongConfigDto inputDto);

    @PostMapping("boolValue/default")
    Boolean getBoolOrElse(@Validated @RequestBody LoongConfigDto inputDto,
                          @RequestParam("defaultValue") Boolean defaultValue);

    @PostMapping("intValue")
    Integer getInt(@Validated @RequestBody LoongConfigDto inputDto);

    @PostMapping("intValue/default")
    Integer getIntOrElse(@Validated @RequestBody LoongConfigDto inputDto,
                         @RequestParam(value = "defaultValue") Integer defaultValue);

    @PostMapping("longValue")
    Long getLong(@Validated @RequestBody LoongConfigDto inputDto);

    @PostMapping("longValue/default")
    Long getLongOrElse(@Validated @RequestBody LoongConfigDto inputDto,
                       @RequestParam("defaultValue") Long defaultValue);

    @PostMapping("stringValue")
    String getString(@Validated @RequestBody LoongConfigDto inputDto);

    @PostMapping("stringValue/default")
    String getStringOrElse(@Validated @RequestBody LoongConfigDto inputDto,
                           @RequestParam("defaultValue") String defaultValue);
}
