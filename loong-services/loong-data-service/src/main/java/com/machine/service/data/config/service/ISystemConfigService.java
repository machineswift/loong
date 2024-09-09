package com.machine.service.data.config.service;

import com.machine.client.data.config.dto.LoongConfigDto;

public interface ISystemConfigService {

    void create(LoongConfigDto dto);

    void delete(LoongConfigDto dto);

    void update(LoongConfigDto dto);

    LoongConfigDto getByUk(LoongConfigDto dto);

}
