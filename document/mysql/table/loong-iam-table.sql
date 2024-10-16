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
 	`code` VARCHAR ( 32 ) NOT NULL COMMENT '编码',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '用户名',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `code` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '角色表';


DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
 	`code` VARCHAR ( 64 ) NOT NULL COMMENT '编码',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '名称',
	`type` VARCHAR ( 8 ) NOT NULL COMMENT '类型，MENU、BUTTON',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
	UNIQUE KEY `uk_01` ( `code` ) USING BTREE,
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
	`type` INT NOT NULL DEFAULT '0' COMMENT '权限类型,（0:可访问，1:可授权）',
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
	`type` INT NOT NULL DEFAULT '0' COMMENT '权限类型,（0:可访问，1:可授权）',
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
	`type` INT NOT NULL DEFAULT '0' COMMENT '权限类型,（0:可访问，1:可授权）',
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

DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `parent_id` VARCHAR ( 32 ) NOT NULL COMMENT '父ID',
	`name` VARCHAR ( 32 ) NOT NULL COMMENT '名称',
	`description` VARCHAR ( 2048 ) NOT NULL COMMENT '描述',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    KEY `idx_01` ( `parent_id` ) USING BTREE,
	KEY `idx_02` ( `create_time` ) USING BTREE
) COMMENT = '部门表';

DROP TABLE IF EXISTS `t_user_department_relation`;
CREATE TABLE `t_user_department_relation` (
   `id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
   `user_id` VARCHAR ( 32 ) NOT NULL COMMENT '用户ID',
	`department_id` VARCHAR ( 32 ) NOT NULL COMMENT '组织id',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
	PRIMARY KEY ( `id` ) USING BTREE,
    UNIQUE KEY `uk_01` ( `user_id`,`department_id` ) USING BTREE
) COMMENT = '用户部门关系表';


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


DROP TABLE IF EXISTS `t_oauth2_registered_client`;
CREATE TABLE `t_oauth2_registered_client` (
	`id` VARCHAR ( 32 ) NOT NULL COMMENT 'ID',
	`client_id` VARCHAR ( 64 ) NOT NULL COMMENT '客户端ID',
	`client_id_issued_at` BIGINT UNSIGNED NOT NULL COMMENT '客户端ID发放时间',
	`client_secret` VARCHAR ( 256 ) DEFAULT NULL COMMENT '客户端密钥',
	`client_secret_expires_at` BIGINT UNSIGNED COMMENT '客户端密钥过期时间',
	`client_name` VARCHAR ( 256 ) NOT NULL COMMENT '客户端名称',
	`client_authentication_methods` VARCHAR ( 1024 ) NOT NULL COMMENT '客户端认证方法',
	`authorization_grant_types` VARCHAR ( 1024 ) NOT NULL COMMENT '授权授予类型',
	`redirect_uris` VARCHAR ( 1024 ) DEFAULT NULL COMMENT '重定向URI',
	`post_logout_redirect_uris` VARCHAR ( 1024 ) DEFAULT NULL COMMENT '注销后重定向URI',
	`scopes` VARCHAR ( 1024 ) NOT NULL COMMENT '作用域',
	`client_settings` VARCHAR ( 4096 ) NOT NULL COMMENT '客户端设置',
	`token_settings` VARCHAR ( 4096 ) NOT NULL COMMENT '令牌设置',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
PRIMARY KEY ( `id` ) USING BTREE
) COMMENT = 'OAuth2 客户端注册信息表';


DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent` (
	`registered_client_id` VARCHAR ( 64 ) NOT NULL COMMENT '注册客户端ID',
	`principal_name` VARCHAR ( 256 ) NOT NULL COMMENT '主体名称',
	`authorities` VARCHAR ( 1000 ) NOT NULL COMMENT '授权权限',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
PRIMARY KEY ( registered_client_id, principal_name )
) COMMENT = 'OAuth2 授权同意信息表';


DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization` (
	id VARCHAR ( 32 ) NOT NULL COMMENT '授权记录ID',
	registered_client_id VARCHAR ( 128 ) NOT NULL COMMENT '注册客户端ID',
	principal_name VARCHAR ( 256 ) NOT NULL COMMENT '主体名称',
	authorization_grant_type VARCHAR ( 128 ) NOT NULL COMMENT '授权授予类型',
	authorized_scopes VARCHAR ( 1024 ) DEFAULT NULL COMMENT '授权范围',
	attributes BLOB DEFAULT NULL COMMENT '属性',
	state VARCHAR ( 512 ) DEFAULT NULL COMMENT '状态',
	authorization_code_value BLOB DEFAULT NULL COMMENT '授权码值',
	authorization_code_issued_at BIGINT UNSIGNED DEFAULT NULL COMMENT '授权码发放时间',
	authorization_code_expires_at BIGINT UNSIGNED DEFAULT NULL COMMENT '授权码过期时间',
	authorization_code_metadata BLOB DEFAULT NULL COMMENT '授权码元数据',
	access_token_value BLOB DEFAULT NULL COMMENT '访问令牌值',
	access_token_issued_at BIGINT UNSIGNED DEFAULT NULL COMMENT '访问令牌发放时间',
	access_token_expires_at BIGINT UNSIGNED DEFAULT NULL COMMENT '访问令牌过期时间',
	access_token_metadata BLOB DEFAULT NULL COMMENT '访问令牌元数据',
	access_token_type VARCHAR ( 128 ) DEFAULT NULL COMMENT '访问令牌类型',
	access_token_scopes VARCHAR ( 1024 ) DEFAULT NULL COMMENT '访问令牌范围',
	oidc_id_token_value BLOB DEFAULT NULL COMMENT 'OpenID Connect ID 令牌值',
	oidc_id_token_issued_at BIGINT UNSIGNED DEFAULT NULL COMMENT 'OpenID Connect ID 令牌发放时间',
	oidc_id_token_expires_at BIGINT UNSIGNED DEFAULT NULL COMMENT 'OpenID Connect ID 令牌过期时间',
	oidc_id_token_metadata BLOB DEFAULT NULL COMMENT 'OpenID Connect ID 令牌元数据',
	refresh_token_value BLOB DEFAULT NULL COMMENT '刷新令牌值',
	refresh_token_issued_at BIGINT UNSIGNED DEFAULT NULL COMMENT '刷新令牌发放时间',
	refresh_token_expires_at BIGINT UNSIGNED DEFAULT NULL COMMENT '刷新令牌过期时间',
	refresh_token_metadata BLOB DEFAULT NULL COMMENT '刷新令牌元数据',
	user_code_value BLOB DEFAULT NULL COMMENT '用户码值',
	user_code_issued_at BIGINT UNSIGNED DEFAULT NULL COMMENT '用户码发放时间',
	user_code_expires_at BIGINT UNSIGNED DEFAULT NULL COMMENT '用户码过期时间',
	user_code_metadata BLOB DEFAULT NULL COMMENT '用户码元数据',
	device_code_value BLOB DEFAULT NULL COMMENT '设备码值',
	device_code_issued_at BIGINT UNSIGNED DEFAULT NULL COMMENT '设备码发放时间',
	device_code_expires_at BIGINT UNSIGNED DEFAULT NULL COMMENT '设备码过期时间',
	device_code_metadata BLOB DEFAULT NULL COMMENT '设备码元数据',
	`create_by` VARCHAR ( 32 ) NOT NULL COMMENT '创建人',
	`create_time` BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
	`update_by` VARCHAR ( 32 ) NOT NULL COMMENT '修改人',
	`update_time` BIGINT UNSIGNED NOT NULL COMMENT '更新时间',
PRIMARY KEY ( id )
) COMMENT = 'OAuth2 授权信息表';