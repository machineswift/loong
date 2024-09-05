package com.machine.client.iam.department;

import com.machine.client.iam.department.dto.input.LoongDepartmentCreateInputDto;
import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputDto;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;
import com.machine.common.config.LoongOpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "loong-iam-service/loong-iam-service/server/department", configuration = LoongOpenFeignConfig.class)
public interface ILoongDepartmentClient {

    @PostMapping("create")
    String create(@RequestBody LoongDepartmentCreateInputDto inputVo);

    @PostMapping("list")
    List<LoongDepartmentListOutputDto> list(@RequestBody LoongDepartmentQueryListInputDto inputVo);

}
