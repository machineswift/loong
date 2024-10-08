package com.machine.service.iam.role.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_role")
public class LoongRoleEntity extends BaseEntity {
    /**
     * 父ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

}
