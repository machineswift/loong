DROP TABLE IF EXISTS `t_area`;
CREATE TABLE `t_area` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`level` TINYINT NOT NULL COMMENT '层级',
	`parent_code` VARCHAR ( 12 ) NOT NULL COMMENT '父编码',
	`code` VARCHAR ( 12 ) NOT NULL COMMENT '编码',
	`category_code` VARCHAR ( 3 ) COMMENT '城乡分类代码',
	`name` VARCHAR ( 64 ) NOT NULL COMMENT '名称',
	`has_child` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否有子数据',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `code` ) USING BTREE,
	KEY `idx_01` ( `parent_code` ) USING BTREE
) COMMENT = '地区表';

DROP TABLE IF EXISTS `t_material_permanent`;
CREATE TABLE `t_material_permanent` (
     `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
     `type` VARCHAR ( 8 ) NOT NULL COMMENT '类型',
     `length` BIGINT UNSIGNED NOT NULL COMMENT '大小（字节）',
     `name` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '名称',
     `url` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '地址',
     `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
     `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
     `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
     `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
     PRIMARY KEY ( `id` ) USING BTREE,
     KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '永久素材表';

DROP TABLE IF EXISTS `t_material_temporary`;
CREATE TABLE `t_material_temporary` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `type` VARCHAR ( 8 ) NOT NULL COMMENT '类型',
   `length` BIGINT UNSIGNED NOT NULL COMMENT '大小（字节）',
   `name` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '名称',
   `url` VARCHAR ( 128 ) NOT NULL DEFAULT '' COMMENT '地址',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY ( `id` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '临时素材表';


DROP TABLE IF EXISTS `t_label_category`;
CREATE TABLE `t_label_category` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
   `sort` BIGINT UNSIGNED NOT NULL COMMENT '排序，sort值大的排序靠前',
   `name` VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '名称',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY ( `id` ) USING BTREE,
   UNIQUE KEY `uk_01` ( `name` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '人工标签分类表';

DROP TABLE IF EXISTS `t_label`;
CREATE TABLE `t_label` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `category_id` VARCHAR ( 32 ) NOT NULL COMMENT '分类ID',
   `sort` BIGINT UNSIGNED NOT NULL COMMENT '排序，sort值大的排序靠前',
   `name` VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '名称',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY ( `id` ) USING BTREE,
   UNIQUE KEY `uk_01` ( `name` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '人工标签表';


DROP TABLE IF EXISTS `t_label_option`;
CREATE TABLE `t_label_option` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `label_id` VARCHAR ( 32 ) NOT NULL COMMENT '人工标签ID',
   `sort` INT UNSIGNED NOT NULL COMMENT '排序，sort值大的排序靠前',
   `enabled` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '状态，禁用:0 启用:1',
   `name` VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '名称',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY ( `id` ) USING BTREE,
   UNIQUE KEY `uk_01` (`label_id`, `name` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '人工标签选项表';


DROP TABLE IF EXISTS `t_tag_category`;
CREATE TABLE `t_tag_category` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
   `sort` INT UNSIGNED NOT NULL COMMENT '排序，sort值大的排序靠前',
   `name` VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '名称',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY ( `id` ) USING BTREE,
   UNIQUE KEY `uk_01` ( `name` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '智能标签分类表';


DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `category_id` VARCHAR ( 32 ) NOT NULL COMMENT '分类ID',
   `sort` BIGINT UNSIGNED NOT NULL COMMENT '排序，sort值大的排序靠前',
   `name` VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '名称',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY ( `id` ) USING BTREE,
   UNIQUE KEY `uk_01` ( `name` ) USING BTREE,
   KEY `idx_01` ( `update_time` ) USING BTREE
) COMMENT = '智能标签表';


DROP TABLE IF EXISTS `t_leaf_alloc`;
CREATE TABLE IF NOT EXISTS `t_leaf_alloc` (
    `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
    `biz_tag` VARCHAR ( 128 ) NOT NULL COMMENT '业务标签',
    `max_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '当前已分配的最大ID',
    `step` INT UNSIGNED NOT NULL DEFAULT '100' COMMENT '每次分配的步长，默认为100',
    `expire_time` BIGINT UNSIGNED NOT NULL COMMENT '过期时间',
    `remark` VARCHAR ( 2048 ) NOT NULL COMMENT '备注',
    `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
    `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
    `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
    `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
    PRIMARY KEY ( `id` ),
    UNIQUE KEY `uk_01` ( `biz_tag` ),
	KEY `idx_01` ( `expire_time` )
) COMMENT = 'leaf 表';


DROP TABLE IF EXISTS `t_system_config`;
CREATE TABLE `t_system_config` (
    `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
    `category` varchar(32) NOT NULL DEFAULT '' COMMENT '分类',
    `code` varchar(64) NOT NULL COMMENT '编码',
    `content` text COMMENT '内容',
    `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
    `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
    `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
    `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_01` (`category`,`code`)
) COMMENT='配置信息表';

DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
    `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
    `code` varchar(32)  NOT NULL COMMENT '编码',
    `name` varchar(64)  NOT NULL COMMENT '名称',
    `status` varchar(16)  NOT NULL COMMENT '状态',
    `department_id` VARCHAR ( 32 ) NOT NULL COMMENT '所属部门',
    `province` varchar(16)  DEFAULT NULL COMMENT '省',
    `city` varchar(16)  DEFAULT NULL COMMENT '市',
    `area` varchar(16)  DEFAULT NULL COMMENT '区',
    `town` varchar(32)  DEFAULT NULL COMMENT '镇/街道',
    `address` varchar(256)  DEFAULT NULL COMMENT '详细地址',
    `longitude` decimal(13,10) DEFAULT NULL COMMENT '门店GPS定位经度',
    `latitude` decimal(12,10) DEFAULT NULL COMMENT '门店GPS定位纬度',
    `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
    `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
    `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
    `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_01` (`code`)
)  COMMENT='门店表';
