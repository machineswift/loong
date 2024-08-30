package com.machine.service.data.material.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_material_temporary")
public class MaterialTemporaryEntity extends BaseEntity {
    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 大小（字节）
     */
    @TableField("length")
    private Long length;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 地址
     */
    @TableField("url")
    private String url;
}