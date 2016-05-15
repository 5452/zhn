/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : zhn

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-05-14 21:49:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_resource
-- ----------------------------
DROP TABLE IF EXISTS `tbl_resource`;
CREATE TABLE `tbl_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `signature` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `parent_id` bigint(20) DEFAULT '0',
  `parent_name` varchar(255) DEFAULT '',
  `type` int(11) DEFAULT '0',
  `url` varchar(255) DEFAULT '',
  `level` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT '',
  `deleted` tinyint(1) DEFAULT '0',
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_resource
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `signature` varchar(255) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `description` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_resource`;
CREATE TABLE `tbl_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `display_name` varchar(255) DEFAULT '',
  `email` varchar(255) DEFAULT '',
  `mobile` varchar(15) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `area_code` varchar(10) DEFAULT '',
  `status` tinyint(4) DEFAULT '0',
  `user_type` tinyint(4) DEFAULT '0',
  `admin` tinyint(1) DEFAULT '0' COMMENT '是否后台用户',
  `deleted` tinyint(1) DEFAULT '0',
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', 'Wang', '王', '', '', '$2a$10$LnAkao26owbYYc/sgYjP4Ownkx23xbzamwbXv2iIi8mT7Flkk/1Va', '', '0', '0', '0', '0', '2016-05-13 17:54:50', '2016-05-13 17:54:50');
INSERT INTO `tbl_user` VALUES ('2', 'Zhang San', '张三', 'zhangsan@zhn.com', '', '$2a$10$LnAkao26owbYYc/sgYjP4Ownkx23xbzamwbXv2iIi8mT7Flkk/1Va', '', '0', '0', '0', '0', '2016-05-13 17:54:53', '2016-05-13 17:54:53');
INSERT INTO `tbl_user` VALUES ('18', 'Li Si', '李四', 'lisi@test1.com', '', '$2a$10$LnAkao26owbYYc/sgYjP4Ownkx23xbzamwbXv2iIi8mT7Flkk/1Va', '', '0', '0', '0', '0', '2016-05-13 17:54:55', '2016-05-13 17:54:55');
INSERT INTO `tbl_user` VALUES ('32', 'Wang Jun', '王俊', 'test1@test1.com', '', '$2a$10$LnAkao26owbYYc/sgYjP4Ownkx23xbzamwbXv2iIi8mT7Flkk/1Va', '', '0', '0', '0', '0', '2016-05-13 17:54:59', '2016-05-13 17:54:59');

-- ----------------------------
-- Table structure for tbl_user_login
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_login`;
CREATE TABLE `tbl_user_login` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `ip` varchar(255) NOT NULL DEFAULT '',
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_login
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `create_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
