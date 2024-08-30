package com.machine.service.iam.user.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_permission_relation")
public class LoongUserPermissionRelationEntity extends BaseEntity {

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 权限id
     */
    @TableField("permission_id")
    private String permissionId;

    /**
     * 权限类型,（0:可访问，1:可授权）
     */
    @TableField("permission_id")
    private Integer type;

}
