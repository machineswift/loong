package com.machine.app.iam.config;

import com.machine.client.iam.user.dto.LoongUserDetailDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoongUserDetails implements UserDetails {

    private LoongUserDetailDto userDetailDto;

    public LoongUserDetails(LoongUserDetailDto userDetailDto) {
        this.userDetailDto = userDetailDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userDetailDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailDto.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userDetailDto.getEnabled();
    }
}
