/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Schema         : p6e

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 06/09/2021 20:10:01

 初始化需要创建的表
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p6e_message_group
-- ----------------------------
DROP TABLE IF EXISTS `p6e_message_group`;
CREATE TABLE `p6e_message_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息中心_平台组_ID',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台组_类型 SMS/MAIL',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '消息中心_平台组_状态',
  `limit` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台组_限流规则',
  `route` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台组_路由规则',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台组_名称',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息中心_平台组_描述',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息中心_平台组_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '消息中心_平台组_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '消息中心_平台组_操作人',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '消息中心_平台组_是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_message_group_relation_platform
-- ----------------------------
DROP TABLE IF EXISTS `p6e_message_group_relation_platform`;
CREATE TABLE `p6e_message_group_relation_platform` (
  `gid` int(11) NOT NULL COMMENT '消息中心_平台组_关联_平台_组ID',
  `pid` int(11) NOT NULL COMMENT '消息中心_平台组_关联_平台_平台ID',
  PRIMARY KEY (`gid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_message_log
-- ----------------------------
DROP TABLE IF EXISTS `p6e_message_log`;
CREATE TABLE `p6e_message_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息中心_日志_ID',
  `pid` int(11) NOT NULL COMMENT '消息中心_日志_平台ID',
  `tid` int(11) NOT NULL COMMENT '消息中心_日志_模版ID',
  `mark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_日志_标记',
  `source` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_日志_来源',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '消息中心_日志_状态',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '消息中心_日志_描述',
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息中心_日志_时间',
  PRIMARY KEY (`id`),
  KEY `mark_normal` (`mark`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_message_platform
-- ----------------------------
DROP TABLE IF EXISTS `p6e_message_platform`;
CREATE TABLE `p6e_message_platform` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息中心_平台_ID',
  `weight` int(11) NOT NULL DEFAULT '1' COMMENT '消息中心_平台_权重',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '消息中心_平台_状态',
  `limit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台_限流',
  `config` json NOT NULL COMMENT '消息中心_平台_配置',
  `actuator` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台_处理器标记',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_平台_名称',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息中心_平台_描述',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息中心_平台_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '消息中心_平台_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '消息中心_平台_操作人',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '消息中心_平台_是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_message_template
-- ----------------------------
DROP TABLE IF EXISTS `p6e_message_template`;
CREATE TABLE `p6e_message_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息中心_模版_ID',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_模版_类型 SMS/MAIL',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息中心_模版_标题',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息中心_模版_名称',
  `parser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '消息中心_模版_解析器',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '消息中心_模版_内容',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息中心_模版_描述',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息中心_模版_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '消息中心_模版_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '消息中心_模版_操作人',
  `is_delete` bigint(2) NOT NULL DEFAULT '0' COMMENT '消息中心_模版_是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
