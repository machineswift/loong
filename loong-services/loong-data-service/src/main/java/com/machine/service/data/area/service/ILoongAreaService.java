package com.machine.service.data.area.service;

import com.machine.service.data.area.service.bo.inbo.LoongAreaInsertInBO;
import com.machine.service.data.area.service.bo.outbo.LoongAreaListOutBO;

import java.util.List;

public interface ILoongAreaService {
    void init();

    Boolean insertBatch(List<LoongAreaInsertInBO> inBOList);

    List<LoongAreaListOutBO> selectByLevel(Integer level);

}
