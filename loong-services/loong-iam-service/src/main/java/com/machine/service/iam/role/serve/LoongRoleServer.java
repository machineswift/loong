package com.machine.service.iam.role.serve;

import com.machine.client.iam.role.ILoongRoleClient;
import com.machine.client.iam.role.dto.LoongRoleDetailDto;
import com.machine.service.iam.role.service.ILoongRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/role")
public class LoongRoleServer implements ILoongRoleClient {

    @Autowired
    private ILoongRoleService roleService;

    @Override
    @GetMapping("detail")
    public LoongRoleDetailDto detail(@RequestParam("roleId") String roleId) {
        return roleService.detail(roleId);
    }
}
