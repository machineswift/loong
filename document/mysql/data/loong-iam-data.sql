DELETE FROM t_role;
INSERT INTO `t_role`
 (`id`,`parent_id`,`code`,`NAME`,`description`,`create_by`,`create_time`,`update_by`,`update_time`)
VALUES
	('root_role_id','','root','系统管理员','系统管理员','root_user_id',1722499004775,'root_user_id',1722499004775),
	('admin_role_id','','admin','系统管理员','系统管理员','root_user_id',1722499004775,'root_user_id',1722499004775),
	('rolebbbbccccddddeeeeffffggggiiii','','user','用户','用户','root_user_id',1722499004775,'root_user_id',1722499004775);



DELETE FROM t_user;
INSERT INTO t_user
	(id,user_name,enabled,PASSWORD,full_name, `create_by`,`create_time`,`update_by`,`update_time`)
VALUES
		('root_user_id','root',1,'{noop}123456','系统管理员','root_user_id',1722499004775,'root_user_id',1722499004775),
		('admin_user_id','admin',1,'{noop}123456','管理员','root_user_id',1722499004775,'root_user_id',1722499004775),
		('userbbbbccccddddeeeeffffggggiiii','machine',1,'{noop}123456','机械','root_user_id',1722499004775,'root_user_id',1722499004775);


DELETE FROM t_user_role_relation;
INSERT INTO t_user_role_relation
	(id,user_id,role_id, `create_by`,`create_time`,`update_by`,`update_time`)
VALUES
		('root_user_role_id','root_user_id','root_role_id','root_user_id',1722499004775,'root_user_id',1722499004775),
		('admin_user_role_id','admin_user_id','admin_role_id','root_user_id',1722499004775,'root_user_id',1722499004775),
		('userroleccccddddeeeeffffggggiiii','userbbbbccccddddeeeeffffggggiiii','rolebbbbccccddddeeeeffffggggiiii','root_user_id',1722499004775,'root_user_id',1722499004775);




DELETE FROM t_department;
INSERT INTO `t_department` (id,parent_id,name,description,`create_by`,`create_time`,`update_by`,`update_time`)
VALUES
 ('root_department_id', '',  'machine', '','root_user_id',1722499004775,'root_user_id',1722499004775),
 ('departmentccddddeeeeffffggggxxbx', 'root_department_id',  '信息部', '','root_user_id',1722499004775,'root_user_id',1722499004775),
 ('departmentccddddeeeeffffggggxscb', 'root_department_id',  '市场部', '','root_user_id',1722499004775,'root_user_id',1722499004775),
 ('departmentccddddeeeeffffggggxcwb', 'root_department_id',  '财务部', '','root_user_id',1722499004775,'root_user_id',1722499004775);