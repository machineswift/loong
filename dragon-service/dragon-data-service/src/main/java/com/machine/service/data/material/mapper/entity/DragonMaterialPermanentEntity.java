package com.machine.service.data.material.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_dragon_material_permanent")
public class DragonMaterialPermanentEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

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