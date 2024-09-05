package com.machine.starter.security;

import cn.hutool.core.collection.CollectionUtil;
import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoongUserDetails implements UserDetails {

    private final LoongUserAuthDetailDto userDetailDto;

    public LoongUserDetails(LoongUserAuthDetailDto userDetailDto) {
        this.userDetailDto = userDetailDto;
    }

    public String getUserId() {
        return userDetailDto.getUserId();
    }

    @Override
    public String getUsername() {
        return userDetailDto.getUserName();
    }

    @Override
    public String getPassword() {
        return userDetailDto.getPassword();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> resultList = new ArrayList<>();

        List<String> roleCodeList = userDetailDto.getRoleCodeList();
        if (CollectionUtil.isNotEmpty(roleCodeList)) {
            for (String roleCode : roleCodeList) {
                resultList.add(new SimpleGrantedAuthority("ROLE_" + roleCode));
            }
        }

        List<String> permissionCodeList = userDetailDto.getPermissionCodeList();
        if (CollectionUtil.isNotEmpty(permissionCodeList)) {
            for (String permissionCode : permissionCodeList) {
                resultList.add(new SimpleGrantedAuthority(permissionCode));
            }
        }
        return resultList;
    }
}