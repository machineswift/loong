package com.machine.app.iam.config;

import com.machine.client.iam.user.ILoongUserClient;
import com.machine.client.iam.user.dto.LoongUserDetailDto;
import com.machine.client.iam.user.dto.LoongUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoongUserDetailsService implements UserDetailsService {

    @Autowired
    private ILoongUserClient loongUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoongUserDto userDto = loongUserClient.getByUserName(username);
        if (userDto == null) {
            throw new UsernameNotFoundException(username);
        }
        LoongUserDetailDto userDetailDto = loongUserClient.detail(userDto.getUserId());
        return new LoongUserDetails(userDetailDto);
    }
}
