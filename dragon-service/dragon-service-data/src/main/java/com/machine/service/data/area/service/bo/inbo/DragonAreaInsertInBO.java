package com.machine.service.data.area.service.bo.inbo;

import lombok.Data;

@Data
public class DragonAreaInsertInBO {

    /**
     * 层级
     */
    private Integer level;


    /**
     * 父编码
     */
    private String parentCode;


    /**
     * 编码
     */
    private String code;


    /**
     * 城乡分类代码
     */
    private String categoryCode;

    /**
     * 名称
     */
    private String name;


    /**
     * 是否有子节点
     */
    private Boolean hasChild;
}
