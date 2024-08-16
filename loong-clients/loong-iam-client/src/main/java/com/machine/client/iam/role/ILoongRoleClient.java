package com.machine.client.iam.role;

import com.machine.client.iam.role.dto.LoongRoleDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loong-iam-service/loong-iam-service/server/role")
public interface ILoongRoleClient {

    @GetMapping("detail")
    LoongRoleDetailDto detail(@RequestParam("roleId") String roleId);

}
