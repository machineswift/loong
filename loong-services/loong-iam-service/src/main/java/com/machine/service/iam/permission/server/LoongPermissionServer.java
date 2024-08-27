package com.machine.service.iam.permission.server;

import com.machine.client.iam.permission.ILoongPermissionClient;
import com.machine.client.iam.permission.dto.input.LoongPermissionCreateInputDto;
import com.machine.client.iam.permission.dto.input.LoongPermissionQueryListInputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionDetailOutputDto;
import com.machine.client.iam.permission.dto.output.LoongPermissionListOutputDto;
import com.machine.service.iam.permission.service.ILoongPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/permission")
public class LoongPermissionServer implements ILoongPermissionClient {

    @Autowired
    private ILoongPermissionService permissionService;

    @Override
    @PostMapping("create")
    public String create(@RequestBody LoongPermissionCreateInputDto inputDto) {
        return permissionService.create(inputDto);
    }

    @Override
    @DeleteMapping("delete")
    public void delete(@RequestParam("permissionId") String permissionId) {
        permissionService.delete(permissionId);
    }

    @Override
    @GetMapping("detail")
    public LoongPermissionDetailOutputDto detail(@RequestParam("permissionId") String permissionId) {
        return permissionService.detail(permissionId);
    }

    @Override
    @PostMapping("list")
    public List<LoongPermissionListOutputDto> list(@RequestBody LoongPermissionQueryListInputDto inputDto) {
        return permissionService.list(inputDto);
    }
}
