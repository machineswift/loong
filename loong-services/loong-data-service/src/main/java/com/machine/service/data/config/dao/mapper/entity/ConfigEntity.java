package com.machine.service.data.config.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_system_config")
public class ConfigEntity extends BaseEntity {

    /**
     * 分类
     */
    @TableField("category")
    private String category;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 内容
     */
    @TableField("content")
    private String content;
}