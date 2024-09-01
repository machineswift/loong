package com.machine.client.data.material;

import com.machine.client.data.material.indto.LoongMaterialTemporaryCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialTemporaryQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialTemporaryListOutputDto;
import com.machine.common.context.LoongFeignConfig;
import com.machine.common.model.LoongPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loong-data-service/loong-data-service/server/material-temporary",
        configuration = LoongFeignConfig.class)
public interface ILoongMaterialTemporaryClient {

    @PostMapping("create")
    String create(@RequestBody LoongMaterialTemporaryCreateInputDto inputDto);

    @GetMapping("detail")
    LoongMaterialTemporaryDetailOutputDto detail(@RequestParam("materIalId") String materIalId);

    @PostMapping("page")
    LoongPageResponse<LoongMaterialTemporaryListOutputDto> selectPage(@RequestBody LoongMaterialTemporaryQueryPageInputVo inputVo);

}
