DELETE FROM t_role;
INSERT INTO `t_role`
 (`id`,`parent_id`,`NAME`,`description`,`create_by`,`create_time`,`update_by`,`update_time`)
VALUES
	('system_role_id','','system','系统管理员','system_user_id',1722499004775,'system_user_id',1722499004775),
	('admin_role_id','','admin','系统管理员','system_user_id',1722499004775,'system_user_id',1722499004775),
	('rolebbbbccccddddeeeeffffggggiiii','','user','用户','system_user_id',1722499004775,'system_user_id',1722499004775);


DELETE FROM t_user;
INSERT INTO t_user
	(id,user_name,enabled,PASSWORD,full_name, `create_by`,`create_time`,`update_by`,`update_time`)
VALUES
		('system_user_id','system',1,'{noop}123456','系统管理员','system_user_id',1722499004775,'system_user_id',1722499004775),
		('admin_user_id','admin',1,'{noop}123456','管理员','system_user_id',1722499004775,'system_user_id',1722499004775),
		('userbbbbccccddddeeeeffffggggiiii','machine',1,'{noop}123456','机械','system_user_id',1722499004775,'system_user_id',1722499004775);


DELETE FROM t_user_role_relation;
INSERT INTO t_user_role_relation
	(id,user_id,role_id, `create_by`,`create_time`,`update_by`,`update_time`)
VALUES
		('system_user_role_id','system_user_id','system_role_id','system_user_id',1722499004775,'system_user_id',1722499004775),
		('admin_user_role_id','admin_user_id','admin_role_id','system_user_id',1722499004775,'system_user_id',1722499004775),
		('userroleccccddddeeeeffffggggiiii','userbbbbccccddddeeeeffffggggiiii','rolebbbbccccddddeeeeffffggggiiii','system_user_id',1722499004775,'system_user_id',1722499004775);

