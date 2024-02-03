package com.machine.service.iam.area.service;

import com.machine.service.iam.area.service.bo.inbo.DragonAreaInsertInBO;
import com.machine.service.iam.area.service.bo.outbo.DragonAreaListOutBO;

import java.util.List;

public interface IDragonAreaService {
    void init();

    Boolean insertBatch(List<DragonAreaInsertInBO> inBOList);

    List<DragonAreaListOutBO> selectByLevel(Integer level);

}
