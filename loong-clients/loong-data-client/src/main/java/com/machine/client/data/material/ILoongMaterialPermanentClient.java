package com.machine.client.data.material;

import com.machine.client.data.material.indto.LoongMaterialPermanentCreateInputDto;
import com.machine.client.data.material.indto.LoongMaterialPermanentQueryPageInputVo;
import com.machine.client.data.material.outdto.LoongMaterialPermanentDetailOutputDto;
import com.machine.client.data.material.outdto.LoongMaterialPermanentListOutputDto;
import com.machine.common.config.LoongOpenFeignConfig;
import com.machine.common.model.LoongPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loong-data-service/loong-data-service/server/material-permanent", configuration = LoongOpenFeignConfig.class)
public interface ILoongMaterialPermanentClient {

    @PostMapping("create")
    String create(@RequestBody LoongMaterialPermanentCreateInputDto inputDto);

    @GetMapping("detail")
    LoongMaterialPermanentDetailOutputDto detail(@RequestParam("materIalId") String materIalId);

    @PostMapping("page")
    LoongPageResponse<LoongMaterialPermanentListOutputDto> selectPage(@RequestBody LoongMaterialPermanentQueryPageInputVo inputVo);

}
