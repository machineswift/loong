package com.machine.client.iam.department;

import com.machine.client.iam.department.dto.input.LoongDepartmentQueryListInputVo;
import com.machine.client.iam.department.dto.output.LoongDepartmentListOutputDto;
import com.machine.common.context.LoongFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "loong-iam-service/loong-iam-service/server/department", configuration = LoongFeignConfig.class)
public interface ILoongDepartmentClient {

    @PostMapping("list")
    List<LoongDepartmentListOutputDto> list(@RequestBody LoongDepartmentQueryListInputVo inputVo);
}
