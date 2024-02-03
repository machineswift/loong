package com.machine.service.iam.area.dao;

import com.machine.service.iam.area.dao.dto.indto.DragonAreaInsertInDTO;
import com.machine.service.iam.area.dao.dto.outdto.DragonAreaListOutDTO;

import java.util.List;

public interface IDragonAreaDao {

    Boolean insertBatch(List<DragonAreaInsertInDTO> inDTOList);

    List<DragonAreaListOutDTO> selectByLevel(Integer level);

    List<DragonAreaListOutDTO> selectByParentCode(String number);
}
