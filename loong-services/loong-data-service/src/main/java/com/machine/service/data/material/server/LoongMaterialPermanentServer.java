package com.machine.service.data.material.server;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.ILoongMaterialPermanentClient;
import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialPermanentDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialPermanentListOutputDto;
import com.machine.common.model.LoongPageResponse;
import com.machine.service.data.material.service.ILoongMaterialPermanentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("server/material-permanent")
public class LoongMaterialPermanentServer implements ILoongMaterialPermanentClient {

    @Autowired
    private ILoongMaterialPermanentService materialPermanentService;

    @Override
    @PostMapping("create")
    public String create(LoongMaterialPermanentCreateInputDto inputDto) {
        return materialPermanentService.create(inputDto);
    }

    @Override
    @GetMapping("detail")
    public LoongMaterialPermanentDetailOutputDto detail(@RequestParam("materIalId")String materIalId) {
        return materialPermanentService.selectById(materIalId);
    }

    @Override
    @PostMapping("page")
    public LoongPageResponse<LoongMaterialPermanentListOutputDto> selectPage(@RequestBody LoongMaterialPermanentQueryPageInputVo inputVo) {
        Page<LoongMaterialPermanentListOutputDto> pageResult = materialPermanentService.selectPage(inputVo);

        return new LoongPageResponse<>(
                pageResult.getCurrent(),
                pageResult.getSize(),
                pageResult.getTotal(),
                pageResult.getRecords());
    }
}
