/*
Navicat MySQL Data Transfer

Source Server         : ubuntu
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : finax

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-04-07 15:49:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(20) DEFAULT NULL,
  `param_value` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  `orderby` int(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '-', 'fa-cog', '1', '2020-04-02 16:10:54');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '#system/user/list', 'fa-user', '1', '2020-04-02 16:14:53');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '#system/role/list', 'fa-user-plus', '2', '2020-04-02 16:16:52');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '#system/menu/list', 'fa-list-ul', '3', '2020-04-02 16:17:53');
INSERT INTO `sys_menu` VALUES ('5', '1', '参数设置', '#system/config/list', 'fa-cogs', '4', '2020-04-03 10:31:36');
INSERT INTO `sys_menu` VALUES ('6', '1', '字典管理', '#system/dict/list', 'fa-book', '5', '2020-04-07 14:01:55');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `errorcount` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', '', '', '2020-01-07 15:57:55', '0');
