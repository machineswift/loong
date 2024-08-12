package com.machine.client.iam.user.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoongUserAuthDetailDto {
    private String userId;

    private String userName;

    private String password;

    private String fullName;

    private String phone;

    private Boolean enabled;

    private List<String> roleCodeList;

    private List<String> permissionCodeList;

    public void addPermissionCode(String permissionCode) {
        if (permissionCodeList == null) {
            permissionCodeList = new ArrayList<String>();
        }
        permissionCodeList.add(permissionCode);
    }
}
