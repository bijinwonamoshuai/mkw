CREATE TABLE `account` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `enable` int(11) DEFAULT '1' COMMENT '是否可用（1是、0否）',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `dtime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleter` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人',
  `remark` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `login_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '登录账号',
  `login_pass` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `city` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '所属城市',
  `type` int(11) DEFAULT NULL COMMENT '用户类型（0超级管理员、1普通用户）',
  `role_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `index_0` (`enable`,`status`),
  KEY `index_1` (`city`),
  KEY `index_2` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='管理员表';

CREATE TABLE `permission` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `enable` int(11) DEFAULT '1' COMMENT '是否可用（1是、0否）',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `dtime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleter` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人',
  `remark` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `menu_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `menu_level` int(11) DEFAULT NULL COMMENT '级别',
  `menu_code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `menu_parent` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父菜单Id',
  `menu_url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `menu_icon` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`id`),
  KEY `index_0` (`enable`,`status`),
  KEY `index_1` (`menu_parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

CREATE TABLE `role` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `enable` int(11) DEFAULT '1' COMMENT '是否可用（1是、0否）',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `dtime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleter` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人',
  `remark` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  KEY `index_0` (`enable`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

CREATE TABLE `role_permission` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `enable` int(11) DEFAULT '1' COMMENT '是否可用（1是、0否）',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `dtime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleter` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人',
  `remark` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `role_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '角色编号',
  `permission_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编号',
  PRIMARY KEY (`id`),
  KEY `index_0` (`enable`,`status`),
  KEY `index_1` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限表';

CREATE TABLE `log` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `enable` int(11) DEFAULT '1' COMMENT '是否可用（1是、0否）',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `dtime` datetime DEFAULT NULL COMMENT '删除时间',
  `deleter` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '删除人',
  `remark` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `module` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '模块',
  `operate` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '操作方式',
  `ip` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'ip地址',
  `url` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `params` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '参数',
  PRIMARY KEY (`id`),
  KEY `index_0` (`enable`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志表';

INSERT INTO `account` (`id`, `enable`, `status`, `ctime`, `creater`, `mtime`, `modifier`, `dtime`, `deleter`, `remark`, `login_name`, `login_pass`, `login_time`, `login_count`, `name`, `url`, `city`, `type`, `role_id`) VALUES ('21c70c60c46348f89ef52dcf896b603d', '1', '0', '2018-04-12 13:13:11', '', NULL, NULL, NULL, NULL, NULL, 'admin', '202cb962ac59075b964b07152d234b70', NULL, NULL, '管理员', NULL, NULL, '0', NULL);
