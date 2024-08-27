package com.machine.client.iam.permission;

import com.machine.client.iam.permission.dto.input.LoongPermissionCreateInputDto;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionDetailOutputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionListOutputDto;
import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "loong-iam-service/loong-iam-service/server/permission", configuration = LoongFeignConfig.class)
public interface ILoongPermissionClient {

    @PostMapping("create")
    String create(@RequestBody LoongPermissionCreateInputDto inputDto);

    @DeleteMapping("delete")
    void delete(@RequestParam("permissionId") String permissionId);

    @GetMapping("detail")
    LoongPermissionDetailOutputDto detail(@RequestParam("permissionId") String permissionId);

    @PostMapping("list")
    List<LoongPermissionListOutputDto> list(@RequestBody LoongPermissionQueryListInputDto inputDto);
}
