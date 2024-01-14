package com.machine.service.iam.area.mapper.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_dragon_data_area")
public class DragonAreaEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

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

    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Long createTime;

    /**
     * 修改人
     */
    @TableField("update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Long updateTime;

    /**
     * 是否已删除
     */
    @TableField(value = "is_deleted")
    private Boolean deleted;
}