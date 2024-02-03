package com.machine.service.data.area.dao;

import com.machine.service.data.area.dao.dto.indto.DragonAreaInsertInDTO;
import com.machine.service.data.area.dao.dto.outdto.DragonAreaListOutDTO;

import java.util.List;

public interface IDragonAreaDao {

    Boolean insertBatch(List<DragonAreaInsertInDTO> inDTOList);

    List<DragonAreaListOutDTO> selectByLevel(Integer level);

    List<DragonAreaListOutDTO> selectByParentCode(String number);
}
