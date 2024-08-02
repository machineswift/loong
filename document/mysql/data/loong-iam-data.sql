DELETE FROM t_role
INSERT INTO `t_role`
 (`id`,`parent_id`,`NAME`,`description`,`create_by`,`create_time`,`update_by`,`update_time`)
VALUES
	('rolebbbbccccddddeeeeffffgggghhhh','','admin','系统管理员','userbbbbccccddddeeeeffffgggghhhh',1722499004775,'userbbbbccccddddeeeeffffgggghhhh',1722499004775),
	('rolebbbbccccddddeeeeffffggggiiii','','user','用户','userbbbbccccddddeeeeffffgggghhhh',1722499004775,'userbbbbccccddddeeeeffffgggghhhh',1722499004775);


DELETE FROM t_user;
INSERT INTO t_user
	(id,user_name,PASSWORD,full_name, `create_by`,`create_time`,`update_by`,`update_time`)
VALUES
		('userbbbbccccddddeeeeffffgggghhhh','admin','{noop}123','系统管理员','userbbbbccccddddeeeeffffgggghhhh',1722499004775,'userbbbbccccddddeeeeffffgggghhhh',1722499004775),
		('userbbbbccccddddeeeeffffggggiiii','machine','{noop}123','机械','userbbbbccccddddeeeeffffgggghhhh',1722499004775,'userbbbbccccddddeeeeffffgggghhhh',1722499004775);


DELETE FROM t_user_role_relation;
INSERT INTO t_user_role_relation
	(id,user_id,role_id, `create_by`,`create_time`,`update_by`,`update_time`)
VALUES
		('userroleccccddddeeeeffffgggghhhh','userbbbbccccddddeeeeffffgggghhhh','rolebbbbccccddddeeeeffffgggghhhh','uuuubbbbccccddddeeeeffffgggghhhh',1722499004775,'uuuubbbbccccddddeeeeffffgggghhhh',1722499004775),
		('userroleccccddddeeeeffffggggiiii','userbbbbccccddddeeeeffffggggiiii','rolebbbbccccddddeeeeffffggggiiii','uuuubbbbccccddddeeeeffffgggghhhh',1722499004775,'uuuubbbbccccddddeeeeffffgggghhhh',1722499004775);
