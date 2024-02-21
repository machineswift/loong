package com.machine.service.data.tag.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_tag")
public class TagEntity extends BaseEntity {

    /**
     * 分类ID
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 排序，sort值大的排序靠前
     */
    @TableField("sort")
    private Long sort;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

}