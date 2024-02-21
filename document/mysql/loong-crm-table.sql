DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`identity_card` VARCHAR ( 18 ) NOT NULL default '' COMMENT '身份证',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '姓名',
	`gender` VARCHAR ( 16 ) NOT NULL default 'UNDEFINED'  COMMENT '性别',
	`birth_year` INT NOT NULL DEFAULT '0' COMMENT '生日-年',
	`birth_month` INT NOT NULL DEFAULT '0' COMMENT '生日-月',
	`birth_day` INT NOT NULL DEFAULT '0' COMMENT '生日-日',
    `valid_start_time` date NOT NULL COMMENT '有效期开始时间',
    `valid_end_time` date NOT NULL COMMENT '有效期结束时间',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `identity_card` ) USING BTREE,
	KEY `idx_01` ( `create_time` ) USING BTREE
) COMMENT = '客户表';

DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`customer_id` VARCHAR ( 32 ) NOT NULL DEFAULT '' COMMENT '客户ID',
	`phone` VARCHAR ( 16 ) NOT NULL COMMENT '手机号',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '姓名',
	`gender` VARCHAR ( 16 ) NOT NULL DEFAULT 'UNDEFINED'  COMMENT '性别',
	`birth_year` INT NOT NULL DEFAULT '0' COMMENT '生日-年',
	`birth_month` INT NOT NULL DEFAULT '0' COMMENT '生日-月',
	`birth_day` INT NOT NULL DEFAULT '0' COMMENT '生日-日',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	`deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否已删除',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `phone` ) USING BTREE,
	KEY `idx_01` ( `create_time` ) USING BTREE
) COMMENT = '会员表';
