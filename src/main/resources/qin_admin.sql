/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 127.0.0.1:3306
 Source Schema         : qin_admin

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 05/03/2021 14:50:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `dic_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典所属模块名称',
  `module_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '模块描述',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典所属组名称',
  `group_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '字典所属组描述',
  `dic_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典标识',
  `dic_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典值',
  `value_type` int(255) NOT NULL COMMENT '字典值类型 0：字符串；1：布尔值；2：数字；3：时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`dic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, 'system', '系统模块', 'flag', '标识组', 'ip_record', 'true', 2, '是否开始登录ip地址记录', '2020-12-23 09:40:04', '2020-12-23 09:40:08');
INSERT INTO `dictionary` VALUES (2, 'system', '系统模块', 'flag', '标识组', 'single_login', 'false', 2, '是否开始单点登录', '2020-12-23 16:10:19', '2020-12-23 16:10:23');

-- ----------------------------
-- Table structure for ip_address
-- ----------------------------
DROP TABLE IF EXISTS `ip_address`;
CREATE TABLE `ip_address`  (
  `id` bigint(20) NOT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pro_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `city_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `region_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `manager_id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `manager_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `manager_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `manager_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `status` int(255) NULL DEFAULT NULL COMMENT '账号状态',
  `over_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`manager_id`) USING BTREE,
  UNIQUE INDEX `manager_account`(`manager_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'admin', '123456', 'admin', NULL, NULL, '2020-06-11 00:05:27', '2020-06-11 00:05:30');
INSERT INTO `manager` VALUES (3, 'manager', '123456', '管理', NULL, NULL, NULL, NULL);
INSERT INTO `manager` VALUES (4, 'finance', '123456', '财务', NULL, NULL, NULL, NULL);
INSERT INTO `manager` VALUES (5, 'admin2', '123456', '加密账号', NULL, NULL, NULL, NULL);
INSERT INTO `manager` VALUES (6, 'admin3', '123456', '测试测试', NULL, NULL, NULL, NULL);
INSERT INTO `manager` VALUES (7, 'ceshi1', '123456', '测试加密', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for manager_role
-- ----------------------------
DROP TABLE IF EXISTS `manager_role`;
CREATE TABLE `manager_role`  (
  `mr_id` int(64) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`mr_id`, `role_id`, `manager_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manager_role
-- ----------------------------
INSERT INTO `manager_role` VALUES (1, '1', '1', NULL, NULL);
INSERT INTO `manager_role` VALUES (2, '2', '2', NULL, NULL);
INSERT INTO `manager_role` VALUES (3, '32', '3', '2020-10-30 10:34:56', '2020-10-30 10:34:56');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名字',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问url',
  `parent_menu` int(11) NULL DEFAULT NULL COMMENT '父类id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标路径',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态,显示或隐藏',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', '', -1, 'fa-tachometer', NULL, 0);
INSERT INTO `menu` VALUES (2, '计划任务', '/task', 1, 'fa-clock-o', NULL, 3);
INSERT INTO `menu` VALUES (3, '角色管理', '/role', 1, 'fa-id-card', NULL, 1);
INSERT INTO `menu` VALUES (4, '菜单列表', '/menu', 1, 'fa-align-justify', NULL, 2);
INSERT INTO `menu` VALUES (5, '管理人员', '/manager', 1, 'fa-user-circle-o', NULL, 0);
INSERT INTO `menu` VALUES (6, '字典管理', '/dic', 1, 'fa-book', NULL, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理账号id',
  `role_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '1', 'admin', '2019-11-11 17:48:41', '2019-11-11 17:48:43');
INSERT INTO `role` VALUES (32, NULL, '人事部', NULL, NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `rm_id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`rm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (68, '1', '1', NULL, NULL);
INSERT INTO `role_menu` VALUES (69, '1', '2', NULL, NULL);
INSERT INTO `role_menu` VALUES (70, '1', '3', NULL, NULL);
INSERT INTO `role_menu` VALUES (71, '1', '4', NULL, NULL);
INSERT INTO `role_menu` VALUES (72, '1', '5', NULL, NULL);
INSERT INTO `role_menu` VALUES (73, '1', '6', NULL, NULL);
INSERT INTO `role_menu` VALUES (132, '32', '1', NULL, NULL);
INSERT INTO `role_menu` VALUES (133, '32', '5', NULL, NULL);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `bean_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `job_status` int(255) NULL DEFAULT NULL COMMENT '任务状态 0：暂停；1：运行',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (1, 'demo2', '这是用于测试的定时任务', '0/2 * * * * ? ', 'com.tttiger.admin.common.task.TestTask', 0, 'test', '2020-11-11 10:34:36', '2020-11-12 18:57:33');

SET FOREIGN_KEY_CHECKS = 1;
