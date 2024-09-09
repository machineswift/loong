package com.machine.service.data.area.dao.mapper.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@TableName("t_area")
@EqualsAndHashCode(callSuper = true)
public class LoongAreaEntity extends BaseEntity {

    /**
     * 层级
     */
    @TableField("level")
    private Integer level;


    /**
     * 父编码
     */
    @TableField("parent_code")
    private String parentCode;


    /**
     * 编码
     */
    @TableField("code")
    private String code;


    /**
     * 城乡分类代码
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 名称
     */
    @TableField("name")
    private String name;


    /**
     * 是否有子节点
     */
    @TableField("has_child")
    private Boolean hasChild;
}