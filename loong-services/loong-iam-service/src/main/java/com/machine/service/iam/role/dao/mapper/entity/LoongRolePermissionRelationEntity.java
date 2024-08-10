package com.machine.service.iam.role.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_role_permission_relation")
public class LoongRolePermissionRelationEntity extends BaseEntity {
    /**
     * '角色ID'
     */
    @TableField("role_id")
    private String roleId;

    /**
     * '权限id'
     */
    @TableField("permission_id")
    private String permissionId;

    /**
     * 权限类型,（0:可访问，1:可授权）
     */
    @TableField("type")
    private String type;
}
