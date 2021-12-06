/*
 Navicat Premium Data Transfer

 Source Server         : 自己_阿里云（119.23.25.159）
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 119.23.25.159:3306
 Source Schema         : p6e

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 06/12/2021 23:42:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p6e_auth_log
-- ----------------------------
DROP TABLE IF EXISTS `p6e_auth_log`;
CREATE TABLE `p6e_auth_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'oauth2-日志主键自增',
  `uid` int(11) NOT NULL COMMENT 'oauth2-日志用户ID',
  `cid` int(11) NOT NULL COMMENT 'oauth2-日志客户端ID',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2-日志类型',
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'oauth2-日志时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_auth_user_copy1
-- ----------------------------
DROP TABLE IF EXISTS `p6e_auth_user_copy1`;
CREATE TABLE `p6e_auth_user_copy1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'oauth2-用户认证主键自增',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证电话号码',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2-用户认证密码',
  `qq` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证QQ认证',
  `wechat` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证微信认证',
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction2
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction2`;
CREATE TABLE `p6e_jurisdiction2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '保护措施_权限_ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保护措施_权限_CODE',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保护措施_权限_名称',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '保护措施_权限_描述',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保护措施_权限_内容',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保护措施_权限_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保护措施_权限_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '保护措施_权限_操作人',
  PRIMARY KEY (`id`,`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100018 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_group2
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_group2`;
CREATE TABLE `p6e_jurisdiction_group2` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '保护措施_安全组_ID\nalter TABLE superuser auto_increment =1\n',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保护措施_安全组_名称',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '保护措施_安全组_描述',
  `weight` int(5) NOT NULL DEFAULT '0' COMMENT '保护措施_安全组_权重',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保护措施_安全组_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保护措施_安全组_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '保护措施_安全组_操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100006 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_group_relation_user2
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_group_relation_user2`;
CREATE TABLE `p6e_jurisdiction_group_relation_user2` (
  `uid` int(11) NOT NULL COMMENT '保护措施_安全组关联用户_UID',
  `gid` int(11) NOT NULL COMMENT '保护措施_安全组关联用户_GID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保护措施_安全组关联用户_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保护措施_安全组关联用户_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '保护措施_安全组关联用户_操作人',
  PRIMARY KEY (`uid`,`gid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_relation_group2
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_relation_group2`;
CREATE TABLE `p6e_jurisdiction_relation_group2` (
  `gid` int(11) NOT NULL COMMENT '保护措施_权限关联安全组_UID',
  `jurisdiction_id` int(11) NOT NULL COMMENT '保护措施_权限关联安全组_权限_ID',
  `jurisdiction_param` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '保护措施_权限关联安全组_权限_参数',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '保护措施_权限关联安全组_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保护措施_权限关联安全组_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '保护措施_权限关联安全组_操作人',
  PRIMARY KEY (`gid`,`jurisdiction_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_role
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_role`;
CREATE TABLE `p6e_jurisdiction_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限_角色_ID',
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '权限_角色_权重',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限_角色_名称',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限_角色_描述',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '权限_角色_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限_角色_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '权限_角色_操作人',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '权限_角色_是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_role_relation_url
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_role_relation_url`;
CREATE TABLE `p6e_jurisdiction_role_relation_url` (
  `rid` int(11) NOT NULL COMMENT '权限_角色路径关联_是否删除',
  `uid` int(11) NOT NULL COMMENT '权限_角色路径关联_是否删除',
  `param` json NOT NULL COMMENT '权限_角色路径关联_是否删除',
  PRIMARY KEY (`rid`,`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_url
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_url`;
CREATE TABLE `p6e_jurisdiction_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限_路径_ID',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限_路径_URL',
  `base_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限_路径_BASE_URL',
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限_路径_方法',
  `config` json NOT NULL COMMENT '权限_路径_参数配置',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限_路径_名称',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限_路径_描述',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '权限_路径_创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权限_路径_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'sys' COMMENT '权限_路径_操作人',
  `is_delete` int(2) NOT NULL DEFAULT '0' COMMENT '权限_路径_是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_jurisdiction_user_relation_role
-- ----------------------------
DROP TABLE IF EXISTS `p6e_jurisdiction_user_relation_role`;
CREATE TABLE `p6e_jurisdiction_user_relation_role` (
  `uid` int(11) NOT NULL COMMENT '权限_用户角色关联_用户ID',
  `rid` int(11) NOT NULL COMMENT '权限_用户角色关联_角色ID',
  PRIMARY KEY (`uid`,`rid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_oauth2_ relevance
-- ----------------------------
DROP TABLE IF EXISTS `p6e_oauth2_ relevance`;
CREATE TABLE `p6e_oauth2_ relevance` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `p6e_oauth2_client`;
CREATE TABLE `p6e_oauth2_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'oauth2_id',
  `status` tinyint(2) NOT NULL DEFAULT '-1' COMMENT 'oauth2_客户端状态 -1 未激活 0 冻结 1 启动',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2_客户端名称',
  `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2_客户端KEY',
  `secret` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2_客户端密钥',
  `scope` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ALL' COMMENT 'oauth2_客户端权限',
  `redirect_uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2_客户端重定向url',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2_客户端描述',
  `limiting_rule` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0,0,0,0' COMMENT 'oauth2_客户端限流规则',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'ico.png' COMMENT 'oauth2_客户端图标',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CLIENT_KEY_UNIQUE` (`key`) USING BTREE COMMENT 'CLIENT_KEY 唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_oauth2_log
-- ----------------------------
DROP TABLE IF EXISTS `p6e_oauth2_log`;
CREATE TABLE `p6e_oauth2_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'oauth2-日志主键自增',
  `uid` int(11) NOT NULL COMMENT 'oauth2-日志用户ID',
  `cid` int(11) NOT NULL COMMENT 'oauth2-日志客户端ID',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2-日志类型',
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'oauth2-日志时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_oauth2_user
-- ----------------------------
DROP TABLE IF EXISTS `p6e_oauth2_user`;
CREATE TABLE `p6e_oauth2_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'oauth2-用户认证主键自增',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证电话号码',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'oauth2-用户认证密码',
  `qq` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证QQ认证',
  `wechat` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证微信认证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_user
-- ----------------------------
DROP TABLE IF EXISTS `p6e_user`;
CREATE TABLE `p6e_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'oauth2-用户认证主键自增',
  `status` int(11) DEFAULT '1',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'oauth2-用户认证邮箱',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '保护措施_安全组_创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保护措施_安全组_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'sys' COMMENT '保护措施_安全组_操作人',
  `is_delete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for p6e_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `p6e_user_auth`;
CREATE TABLE `p6e_user_auth` (
  `id` int(11) NOT NULL COMMENT 'oauth2-用户认证主键自增',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证电话号码',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证密码',
  `qq` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证QQ认证',
  `wechat` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'oauth2-用户认证微信认证',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '保护措施_安全组_创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '保护措施_安全组_更新时间',
  `operate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'sys' COMMENT '保护措施_安全组_操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
