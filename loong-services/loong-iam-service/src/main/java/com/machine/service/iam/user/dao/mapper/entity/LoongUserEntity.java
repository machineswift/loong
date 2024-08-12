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
@TableName("t_user")
public class LoongUserEntity extends BaseEntity {
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 状态，禁用:0 启用:1
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 密码，加密存储
     */
    @TableField("password")
    private String password;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户姓名
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;
}
