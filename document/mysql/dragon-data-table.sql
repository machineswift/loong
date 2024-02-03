DROP TABLE IF EXISTS `t_dragon_area`;
CREATE TABLE `t_dragon_area` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'id',
	`level` TINYINT NOT NULL COMMENT '层级',
	`parent_code` VARCHAR ( 12 ) NOT NULL COMMENT '父编码',
	`code` VARCHAR ( 12 ) NOT NULL COMMENT '编码',
	`category_code` VARCHAR ( 3 ) COMMENT '城乡分类代码',
	`name` VARCHAR ( 64 ) NOT NULL COMMENT '名称',
	`has_child` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否有子数据',
	`create_user` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_user` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `code` ) USING BTREE,
	KEY `idx_01` ( `parent_code` ) USING BTREE
) COMMENT = '地区表';

DROP TABLE IF EXISTS `t_dragon_material_permanent`;
CREATE TABLE `t_dragon_material_permanent` (
     `id` VARCHAR ( 32 ) NOT NULL COMMENT 'id',
     `type` VARCHAR ( 8 ) NOT NULL COMMENT '类型',
     `length` BIGINT UNSIGNED NOT NULL COMMENT '大小（字节）',
     `name` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '名称',
     `url` VARCHAR ( 256 ) NOT NULL DEFAULT '' COMMENT '地址',
     `create_user` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
     `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
     `update_user` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
     `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
     `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
     PRIMARY KEY ( `id` ) USING BTREE,
     KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '永久素材表';

DROP TABLE IF EXISTS `t_dragon_material_temporary`;
CREATE TABLE `t_dragon_material_temporary` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'id',
   `type` VARCHAR ( 8 ) NOT NULL COMMENT '类型',
   `length` BIGINT UNSIGNED NOT NULL COMMENT '大小（字节）',
   `name` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '名称',
   `url` VARCHAR ( 256 ) NOT NULL DEFAULT '' COMMENT '地址',
   `create_user` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_user` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
   PRIMARY KEY ( `id` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '临时素材表';