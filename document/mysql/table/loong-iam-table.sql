DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`user_name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
    `enabled` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '状态，禁用:0 启用:1',
	`password` VARCHAR ( 256 ) NOT NULL COMMENT '密码，加密存储',
	`phone` VARCHAR ( 16 ) NOT NULL default '' COMMENT '手机号',
	`full_name` VARCHAR ( 64 ) NOT NULL default '' COMMENT '用户姓名',
	`gender` VARCHAR ( 16 ) NOT NULL default 'UNDEFINED'  COMMENT '性别',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
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
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '分组表';


DROP TABLE IF EXISTS `t_role_permission_relation`;
CREATE TABLE `t_role_permission_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `role_id` VARCHAR ( 32 ) NOT NULL COMMENT '角色ID',
	`permission_id` VARCHAR ( 32 ) NOT NULL COMMENT '权限id',
	`type` INT NOT NULL COMMENT '权限类型,（0:可访问，1:可授权）',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `role_id`,`permission_id` ) USING BTREE
) COMMENT = '角色权限关系表';


DROP TABLE IF EXISTS `t_group_permission_relation`;
CREATE TABLE `t_group_permission_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `group_id` VARCHAR ( 32 ) NOT NULL COMMENT '分组ID',
	`permission_id` VARCHAR ( 32 ) NOT NULL COMMENT '权限id',
	`type` INT NOT NULL COMMENT '权限类型,（0:可访问，1:可授权）',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `group_id`,`permission_id` ) USING BTREE
) COMMENT = '分组权限关系表';


DROP TABLE IF EXISTS `t_group_role_relation`;
CREATE TABLE `t_group_role_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `group_id` VARCHAR ( 32 ) NOT NULL COMMENT '分组ID',
	`role_id` VARCHAR ( 32 ) NOT NULL COMMENT '角色id',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `group_id`,`role_id` ) USING BTREE
) COMMENT = '分组角色关系表';


DROP TABLE IF EXISTS `t_user_group_relation`;
CREATE TABLE `t_user_group_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `user_id` VARCHAR ( 32 ) NOT NULL COMMENT '角色id',
   `group_id` VARCHAR ( 32 ) NOT NULL COMMENT '组ID',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `user_id`,`group_id` ) USING BTREE
) COMMENT = '用户分组关系表';


DROP TABLE IF EXISTS `t_user_permission_relation`;
CREATE TABLE `t_user_permission_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `user_id` VARCHAR ( 32 ) NOT NULL COMMENT '用户ID',
	`permission_id` VARCHAR ( 32 ) NOT NULL COMMENT '权限id',
	`type` INT NOT NULL COMMENT '权限类型,（0:可访问，1:可授权）',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `user_id`,`permission_id` ) USING BTREE
) COMMENT = '用户权限关系表';


DROP TABLE IF EXISTS `t_user_role_relation`;
CREATE TABLE `t_user_role_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `user_id` VARCHAR ( 32 ) NOT NULL COMMENT '用户ID',
	`role_id` VARCHAR ( 32 ) NOT NULL COMMENT '角色id',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `user_id`,`role_id` ) USING BTREE
) COMMENT = '用户角色关系表';



DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '组织表';



DROP TABLE IF EXISTS `t_user_organization_relation`;
CREATE TABLE `t_user_organization_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `user_id` VARCHAR ( 32 ) NOT NULL COMMENT '用户ID',
	`organization_id` VARCHAR ( 32 ) NOT NULL COMMENT '组织id',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `user_id`,`organization_id` ) USING BTREE
) COMMENT = '用户组织关系表';


DROP TABLE IF EXISTS `t_user_operate_log`;
CREATE TABLE `t_user_operate_log` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `user_id` VARCHAR ( 32 ) NOT NULL COMMENT '用户ID',
   `ip_address` VARCHAR ( 32 ) NOT NULL COMMENT 'IP地址',
   `device_info` VARCHAR ( 128 )  COMMENT '设备信息',
   `action_result` INT NOT NULL DEFAULT '1' COMMENT '操作结果，0失败 1成功',
   `action_url` VARCHAR ( 128 ) NOT NULL COMMENT '操作地址',
   `action_param` TEXT COMMENT '操作参数',
   `action_result` TEXT COMMENT '操作结果',
   `remark` VARCHAR ( 2048 ) NOT NULL COMMENT '备注',
   `create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
   `create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
   `update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
   `update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `user_id` ) USING BTREE,
    KEY `idx_02` ( `action_url` ) USING BTREE,
	KEY `idx_03` ( `create_time` ) USING BTREE
) COMMENT = '用户操作日志表';


DROP TABLE IF EXISTS `t_auth_token`;
CREATE TABLE `t_auth_token` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`user_name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`series` VARCHAR ( 64 ) NOT NULL COMMENT 'series',
	`token` VARCHAR ( 64 ) NOT NULL COMMENT 'token',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `series` ) USING BTREE,
	KEY `idx_01` ( `user_name` ) USING BTREE
) COMMENT = '认证token表';