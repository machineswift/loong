DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`user_name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`password` VARCHAR ( 256 ) NOT NULL COMMENT '密码，加密存储',
	`phone` VARCHAR ( 16 ) NOT NULL default '' COMMENT '手机号',
	`full_name` VARCHAR ( 64 ) NOT NULL default '' COMMENT '用户姓名',
	`gender` VARCHAR ( 16 ) NOT NULL default 'UNDEFINED'  COMMENT '性别',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `user_name` ) USING BTREE,
	KEY `idx_01` ( `phone` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '用户表';


DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '角色表';


DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '权限表';


DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '分组表';


DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `role_id` VARCHAR ( 32 ) NOT NULL COMMENT '角色ID',
	`permission_id` VARCHAR ( 32 ) NOT NULL COMMENT '权限id',
	`type` INT NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `role_id`,`permission_id` ) USING BTREE
) COMMENT = '角色权限表';
DROP TABLE IF EXISTS `t_role_permission`;

DROP TABLE IF EXISTS `t_group_permission`;
CREATE TABLE `t_group_permission` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `group_id` VARCHAR ( 32 ) NOT NULL COMMENT '组ID',
	`permission_id` VARCHAR ( 32 ) NOT NULL COMMENT '权限id',
	`type` INT NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `group_id`,`permission_id` ) USING BTREE
) COMMENT = '角色权限表';