package com.machine.service.data.area.service;

import com.machine.service.data.area.service.bo.inbo.DragonAreaInsertInBO;
import com.machine.service.data.area.service.bo.outbo.DragonAreaListOutBO;

import java.util.List;

public interface IDragonAreaService {
    void init();

    Boolean insertBatch(List<DragonAreaInsertInBO> inBOList);

    List<DragonAreaListOutBO> selectByLevel(Integer level);

}
