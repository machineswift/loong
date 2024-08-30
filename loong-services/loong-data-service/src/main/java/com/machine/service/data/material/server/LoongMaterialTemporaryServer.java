package com.machine.service.data.material.server;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.data.material.ILoongMaterialTemporaryClient;
import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryListOutputDto;
import com.machine.common.model.LoongPageResponse;
import com.machine.service.data.material.service.ILoongMaterialTemporaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("material/temporary")
public class LoongMaterialTemporaryServer implements ILoongMaterialTemporaryClient {

    @Autowired
    private ILoongMaterialTemporaryService materialTemporaryService;

    @Override
    public String create(LoongMaterialTemporaryCreateInputDto inputDto) {
        return materialTemporaryService.create(inputDto);
    }

    @Override
    public LoongMaterialTemporaryDetailOutputDto detail(String materIalId) {
        return materialTemporaryService.detail(materIalId);
    }

    @Override
    @PostMapping("page")
    public LoongPageResponse<LoongMaterialTemporaryListOutputDto> selectPage(@RequestBody LoongMaterialTemporaryQueryPageInputVo inputVo) {
        Page<LoongMaterialTemporaryListOutputDto> pageResult = materialTemporaryService.selectPage(inputVo);

        return new LoongPageResponse<>(
                pageResult.getCurrent(),
                pageResult.getSize(),
                pageResult.getTotal(),
                pageResult.getRecords());
    }
}
