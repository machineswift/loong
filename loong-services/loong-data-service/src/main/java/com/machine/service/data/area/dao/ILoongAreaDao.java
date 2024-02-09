package com.machine.service.data.area.dao;

import com.machine.service.data.area.dao.dto.indto.LoongAreaInsertInDTO;
import com.machine.service.data.area.dao.dto.outdto.LoongAreaListOutDTO;

import java.util.List;

public interface ILoongAreaDao {

    Boolean insertBatch(List<LoongAreaInsertInDTO> inDTOList);

    List<LoongAreaListOutDTO> selectByLevel(Integer level);

    List<LoongAreaListOutDTO> selectByParentCode(String number);
}
