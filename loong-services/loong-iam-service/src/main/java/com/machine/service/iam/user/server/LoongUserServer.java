package com.machine.service.iam.user.server;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.input.LoongUserCreateInputDto;
import com.machine.client.iam.user.dto.input.LoongUserQueryPageInputVo;
import com.machine.client.iam.user.dto.input.LoongUserUpdatePasswordInputDto;
import com.machine.client.iam.user.dto.output.LoongUserListOutputDto;
import com.machine.common.model.LoongPageResponse;
import com.machine.service.iam.user.service.ILoongUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("server/user")
public class LoongUserServer implements ILoongUserClient {

    @Autowired
    private ILoongUserService userService;

    @Override
    @PostMapping("create")
    public String create(@RequestBody LoongUserCreateInputDto inputDto) {
        return userService.create(inputDto);
    }

    @Override
    @PutMapping("updatePassword")
    public int updatePassword(@RequestBody LoongUserUpdatePasswordInputDto dto) {
        return userService.updatePassword(dto);
    }

    @Override
    @GetMapping("detail")
    public LoongUserDetailDto detail(@RequestParam("userId") String userId) {
        return userService.detail(userId);
    }

    @Override
    @GetMapping("auth_detail")
    public LoongUserAuthDetailDto authDetail(@RequestParam("userId") String userId) {
        return userService.authDetail(userId);
    }

    @Override
    @GetMapping("getByUserName")
    public LoongUserDto getByUserName(@RequestParam("userName") String userName) {
        return userService.getByUserName(userName);
    }

    @Override
    @PostMapping("page")
    public LoongPageResponse<LoongUserListOutputDto> selectPage(LoongUserQueryPageInputVo inputVo) {
        Page<LoongUserListOutputDto> pageResult = userService.selectPage(inputVo);

        return new LoongPageResponse<>(
                pageResult.getCurrent(),
                pageResult.getSize(),
                pageResult.getTotal(),
                pageResult.getRecords());
    }
}
