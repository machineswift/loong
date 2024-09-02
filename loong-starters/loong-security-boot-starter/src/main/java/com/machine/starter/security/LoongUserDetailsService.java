package com.machine.starter.security;

import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import com.machine.client.iam.user.dto.input.LoongUserUpdatePasswordInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoongUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    private ILoongUserClient loongUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoongUserDto userDto = loongUserClient.getByUserName(username);
        if (userDto == null) {
            throw new UsernameNotFoundException(username);
        }
        LoongUserAuthDetailDto userDetailDto = loongUserClient.authDetail(userDto.getUserId());
        return new LoongUserDetails(userDetailDto);
    }

    @Override
    public UserDetails updatePassword(UserDetails user,
                                      String newPassword) {
        LoongUserDto userDto = loongUserClient.getByUserName(user.getUsername());
        if (userDto == null) {
            throw new UsernameNotFoundException(user.getUsername());
        }

        LoongUserUpdatePasswordInputDto updatePasswordDto = new LoongUserUpdatePasswordInputDto();
        updatePasswordDto.setUserId(userDto.getUserId());
        updatePasswordDto.setPassword(newPassword);
        loongUserClient.updatePassword(updatePasswordDto);
        return user;
    }
}
