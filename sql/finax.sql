/*
Navicat MySQL Data Transfer

Source Server         : Ubuntu
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : finax

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2020-08-04 11:20:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `param_key` varchar(20) DEFAULT NULL,
  `param_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('3', 'test1', '1', '', '2020-04-20 14:58:50');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `is_default` int DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `orderby` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('6', '0', 'sex', '性别', null, null, '', '1', '2020-04-08 10:45:54');
INSERT INTO `sys_dict` VALUES ('7', '6', 'man', '男', '1', null, '', '1', '2020-04-08 10:46:21');
INSERT INTO `sys_dict` VALUES ('8', '6', 'woman', '女', '0', null, '', '2', '2020-04-08 10:46:33');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `os` varchar(50) DEFAULT NULL,
  `broswer` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3106 DEFAULT CHARSET=utf8 COMMENT='用户登陆日志表';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  `orderby` int DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `menu_type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '-', 'fa-cog', '1', '2020-04-02 16:10:54', null, '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '#system/user/list', 'fa-user', '1', '2020-04-02 16:14:53', null, '2');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '#system/role/list', 'fa-user-plus', '2', '2020-04-02 16:16:52', null, '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '#system/menu/list', 'fa-list-ul', '3', '2020-04-02 16:17:53', null, '2');
INSERT INTO `sys_menu` VALUES ('5', '1', '参数设置', '#system/config/list', 'fa-cogs', '4', '2020-04-03 10:31:36', null, '2');
INSERT INTO `sys_menu` VALUES ('6', '1', '字典管理', '#system/dict/list', 'fa-book', '5', '2020-04-07 14:01:55', null, '2');
INSERT INTO `sys_menu` VALUES ('7', '1', '系统日志', '#system/log/operationLogList', 'fa-file', '6', '2020-04-16 15:21:49', null, '2');
INSERT INTO `sys_menu` VALUES ('8', '5', '添加/修改', null, null, '1', '2020-04-17 15:35:44', 'sys:config:add', '3');
INSERT INTO `sys_menu` VALUES ('9', '5', '列表', null, null, '4', '2020-04-17 15:36:17', 'sys:config:list', '3');
INSERT INTO `sys_menu` VALUES ('10', '5', '删除', null, null, '3', '2020-04-17 15:36:32', 'sys:config:delete', '3');
INSERT INTO `sys_menu` VALUES ('11', '2', '新增/修改', null, null, '5', '2020-04-17 15:38:10', 'sys:user:add', '3');
INSERT INTO `sys_menu` VALUES ('12', '2', '列表', null, null, '6', '2020-04-17 15:38:28', 'sys:user:list', '3');
INSERT INTO `sys_menu` VALUES ('13', '2', '删除', null, null, '7', '2020-04-17 15:38:47', 'sys:user:delete', '3');
INSERT INTO `sys_menu` VALUES ('14', '3', '新增/修改', null, null, '1', '2020-04-17 15:39:23', 'sys:role:add', '3');
INSERT INTO `sys_menu` VALUES ('15', '3', '列表', null, null, '2', '2020-04-17 15:39:38', 'sys:role:list', '3');
INSERT INTO `sys_menu` VALUES ('16', '3', '删除', null, null, '3', '2020-04-17 15:39:53', 'sys:role:delete', '3');
INSERT INTO `sys_menu` VALUES ('17', '4', '新增/修改', null, null, '1', '2020-04-17 15:40:28', 'sys:menu:add', '3');
INSERT INTO `sys_menu` VALUES ('18', '4', '列表', null, null, '2', '2020-04-17 15:40:46', 'sys:menu:list', '3');
INSERT INTO `sys_menu` VALUES ('19', '4', '删除', null, null, '3', '2020-04-17 15:41:01', 'sys:menu:delete', '3');
INSERT INTO `sys_menu` VALUES ('20', '6', '新增/修改', null, null, '1', '2020-04-17 15:41:50', 'sys:dict:add', '3');
INSERT INTO `sys_menu` VALUES ('21', '6', '列表', null, null, '2', '2020-04-17 15:42:12', 'sys:dict:list', '3');
INSERT INTO `sys_menu` VALUES ('22', '6', '删除', null, null, '3', '2020-04-17 15:42:25', 'sys:dict:delete', '3');

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `action` varchar(50) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `params` varchar(2000) DEFAULT NULL,
  `result` varchar(2000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3111 DEFAULT CHARSET=utf8 COMMENT='用户操作日志表';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'test1', '日志管理员', '2020-05-07 11:39:26');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `menu_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('40', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('41', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('44', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('45', '1', '7');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `errorcount` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', 'admin@live.cn', '', '2020-01-07 15:57:55', '0');
INSERT INTO `sys_user` VALUES ('7', 'test1', '4a3252a5edf8fcaa8bde0bfcce79560d', '', '', '2020-05-08 11:01:14', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('9', '8', '1');
INSERT INTO `sys_user_role` VALUES ('13', '5', '1');
INSERT INTO `sys_user_role` VALUES ('14', '6', '2');
INSERT INTO `sys_user_role` VALUES ('15', '7', '1');