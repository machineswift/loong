package com.machine.client.iam.user;

import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.input.LoongUserCreateInputDto;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.client.iam.user.dto.input.LoongUserUpdatePasswordInputDto;
import com.machine.client.iam.user.dto.output.LoongUserListOutputDto;
import com.machine.common.model.LoongPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "loong-iam-service/loong-iam-service/serve/user")
public interface ILoongUserClient {

    @PostMapping("create")
    String create(@RequestBody LoongUserCreateInputDto inputDto);

    @PutMapping("updatePassword")
    int updatePassword(@RequestBody LoongUserUpdatePasswordInputDto inputDto);

    @GetMapping("detail")
    LoongUserDetailDto detail(@RequestParam("userId") String userId);

    @GetMapping("auth_detail")
    LoongUserAuthDetailDto authDetail(@RequestParam("userId") String userId);

    @GetMapping("getByUserName")
    LoongUserDto getByUserName(@RequestParam("userName") String userName);

    @PostMapping("page")
    LoongPageResponse<LoongUserListOutputDto> selectPage(@RequestBody LoongUserQueryPageInputVo inputVo);

}
