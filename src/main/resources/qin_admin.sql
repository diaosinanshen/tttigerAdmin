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

 Date: 19/11/2021 11:37:04
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
  `module_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '模块键',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典所属组名称',
  `group_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '组键',
  `dic_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典标识',
  `dic_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典值',
  `value_type` int(255) NOT NULL COMMENT '字典值类型 0：字符串；1：布尔值；2：数字；3：日期；4：日期时间；5：时间；',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '字典描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`dic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, '系统管理', 'system', '标志', 'flag', 'single_login', 'false', 1, '是否开始单点登录2', '2020-12-23 16:10:19', '2021-06-24 14:22:46');

-- ----------------------------
-- Table structure for ip_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `ip_blacklist`;
CREATE TABLE `ip_blacklist`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `province_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `city_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `region_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for login_record
-- ----------------------------
DROP TABLE IF EXISTS `login_record`;
CREATE TABLE `login_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `login_success` tinyint(1) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `login_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_record
-- ----------------------------
INSERT INTO `login_record` VALUES (12, 'admin23', 0, '127.0.0.1', '2021-04-06 15:33:19');
INSERT INTO `login_record` VALUES (13, 'admin', 0, '127.0.0.1', '2021-04-06 15:34:43');
INSERT INTO `login_record` VALUES (14, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-11 14:19:51');
INSERT INTO `login_record` VALUES (15, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-11 23:04:17');
INSERT INTO `login_record` VALUES (16, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 09:35:06');
INSERT INTO `login_record` VALUES (17, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 09:37:46');
INSERT INTO `login_record` VALUES (18, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 10:20:57');
INSERT INTO `login_record` VALUES (19, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 10:44:07');
INSERT INTO `login_record` VALUES (20, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 10:59:35');
INSERT INTO `login_record` VALUES (21, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 11:10:18');
INSERT INTO `login_record` VALUES (22, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-12 11:28:31');
INSERT INTO `login_record` VALUES (23, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-15 09:03:50');
INSERT INTO `login_record` VALUES (24, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-15 10:39:57');
INSERT INTO `login_record` VALUES (25, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-15 11:13:22');
INSERT INTO `login_record` VALUES (26, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-15 11:32:13');
INSERT INTO `login_record` VALUES (27, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-15 14:20:35');
INSERT INTO `login_record` VALUES (28, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-15 15:49:49');
INSERT INTO `login_record` VALUES (29, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:06:42');
INSERT INTO `login_record` VALUES (30, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:07:17');
INSERT INTO `login_record` VALUES (31, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:07:28');
INSERT INTO `login_record` VALUES (32, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:10:59');
INSERT INTO `login_record` VALUES (33, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:14:34');
INSERT INTO `login_record` VALUES (34, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:22:17');
INSERT INTO `login_record` VALUES (35, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:30:49');
INSERT INTO `login_record` VALUES (36, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:31:55');
INSERT INTO `login_record` VALUES (37, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:34:12');
INSERT INTO `login_record` VALUES (38, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:37:21');
INSERT INTO `login_record` VALUES (39, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:40:08');
INSERT INTO `login_record` VALUES (40, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:43:05');
INSERT INTO `login_record` VALUES (41, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:53:48');
INSERT INTO `login_record` VALUES (42, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:54:04');
INSERT INTO `login_record` VALUES (43, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:55:00');
INSERT INTO `login_record` VALUES (44, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:56:25');
INSERT INTO `login_record` VALUES (45, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 11:59:05');
INSERT INTO `login_record` VALUES (46, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 14:06:24');
INSERT INTO `login_record` VALUES (47, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 14:35:30');
INSERT INTO `login_record` VALUES (48, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 15:05:24');
INSERT INTO `login_record` VALUES (49, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-16 17:21:43');
INSERT INTO `login_record` VALUES (50, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-18 09:09:46');
INSERT INTO `login_record` VALUES (51, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 09:58:05');
INSERT INTO `login_record` VALUES (52, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 12:38:22');
INSERT INTO `login_record` VALUES (53, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 12:51:45');
INSERT INTO `login_record` VALUES (54, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 12:55:12');
INSERT INTO `login_record` VALUES (55, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 13:42:03');
INSERT INTO `login_record` VALUES (56, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:26:38');
INSERT INTO `login_record` VALUES (57, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:34:41');
INSERT INTO `login_record` VALUES (58, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:38:21');
INSERT INTO `login_record` VALUES (59, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:39:13');
INSERT INTO `login_record` VALUES (60, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:41:46');
INSERT INTO `login_record` VALUES (61, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:43:50');
INSERT INTO `login_record` VALUES (62, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:44:12');
INSERT INTO `login_record` VALUES (63, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:44:56');
INSERT INTO `login_record` VALUES (64, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 14:47:28');
INSERT INTO `login_record` VALUES (65, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:23:33');
INSERT INTO `login_record` VALUES (66, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:23:42');
INSERT INTO `login_record` VALUES (67, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:25:49');
INSERT INTO `login_record` VALUES (68, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:26:45');
INSERT INTO `login_record` VALUES (69, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:28:04');
INSERT INTO `login_record` VALUES (70, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:28:17');
INSERT INTO `login_record` VALUES (71, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:33:28');
INSERT INTO `login_record` VALUES (72, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:33:56');
INSERT INTO `login_record` VALUES (73, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:35:17');
INSERT INTO `login_record` VALUES (74, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:35:34');
INSERT INTO `login_record` VALUES (75, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:35:53');
INSERT INTO `login_record` VALUES (76, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:46:11');
INSERT INTO `login_record` VALUES (77, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:46:23');
INSERT INTO `login_record` VALUES (78, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:46:58');
INSERT INTO `login_record` VALUES (79, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:47:45');
INSERT INTO `login_record` VALUES (80, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:49:10');
INSERT INTO `login_record` VALUES (81, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:49:34');
INSERT INTO `login_record` VALUES (82, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:50:31');
INSERT INTO `login_record` VALUES (83, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:50:58');
INSERT INTO `login_record` VALUES (84, 'admin', 1, '0:0:0:0:0:0:0:1', '2021-06-24 15:52:15');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'admin', '123456', 'admin', 0, '2100-04-17 15:22:58', '2020-06-11 00:05:27', '2021-03-10 19:27:56');
INSERT INTO `manager` VALUES (3, 'manager', '123456', '管理', 0, NULL, NULL, NULL);
INSERT INTO `manager` VALUES (4, 'finance', '123456', '财务', 0, NULL, NULL, NULL);
INSERT INTO `manager` VALUES (5, 'admin2', '123456', '加密账号', 0, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '1', 'admin', '2019-11-11 17:48:41', '2019-11-11 17:48:43');
INSERT INTO `role` VALUES (32, NULL, '人事部2', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (68, '1', '1', NULL, NULL);
INSERT INTO `role_menu` VALUES (69, '1', '2', NULL, NULL);
INSERT INTO `role_menu` VALUES (70, '1', '3', NULL, NULL);
INSERT INTO `role_menu` VALUES (71, '1', '4', NULL, NULL);
INSERT INTO `role_menu` VALUES (72, '1', '5', NULL, NULL);
INSERT INTO `role_menu` VALUES (73, '1', '6', NULL, NULL);
INSERT INTO `role_menu` VALUES (134, '32', '1', NULL, NULL);
INSERT INTO `role_menu` VALUES (135, '32', '5', NULL, NULL);

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
