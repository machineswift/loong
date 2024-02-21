package com.machine.service.data.label.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.machine.starter.mybatis.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_label_option")
public class LabelOptionEntity extends BaseEntity {

    /**
     * 人工标签ID
     */
    @TableField("label_id")
    private String labelId;

    /**
     * 排序，sort值大的排序靠前
     */
    @TableField("sort")
    private Long sort;

    /**
     * 状态，禁用:0 启用:1
     */
    @TableField("status")
    private Integer status;

    /**
     * 名称
     */
    @TableField("name")
    private String name;
}