package com.machine.service.iam.auth.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_auth_token")
public class LoongAuthTokenEntity extends BaseEntity {
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    @TableField("series")
    private String series;

    @TableField("token")
    private String token;
}
