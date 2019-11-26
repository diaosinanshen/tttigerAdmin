/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : qin_admin

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 26/11/2019 20:53:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `manager_id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `manager_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `manager_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `manager_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`manager_id`) USING BTREE,
  UNIQUE INDEX `manager_account`(`manager_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'admin', '123456', '管理员', '2019-11-11 16:04:55', '2019-11-11 16:04:58');
INSERT INTO `manager` VALUES (2, 'qin', '123456', '管理2', '2019-11-22 16:09:08', '2019-11-22 16:09:10');

-- ----------------------------
-- Table structure for manager_role
-- ----------------------------
DROP TABLE IF EXISTS `manager_role`;
CREATE TABLE `manager_role`  (
  `mr_id` int(64) NOT NULL,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`mr_id`, `role_id`, `manager_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manager_role
-- ----------------------------
INSERT INTO `manager_role` VALUES (1, '1', '1', NULL, NULL);
INSERT INTO `manager_role` VALUES (2, '2', '2', NULL, NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL COMMENT '主键id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名字',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问url',
  `parent_menu` int(11) NULL DEFAULT NULL COMMENT '父类id',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态,显示或隐藏',
  `menu_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', '', -1, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (2, '添加管理', '/manager', 1, NULL, NULL, NULL);
INSERT INTO `menu` VALUES (3, '添加角色', '/role', 1, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '1', 'admin', '2019-11-11 17:48:41', '2019-11-11 17:48:43');
INSERT INTO `role` VALUES (2, '2', 'manager', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, '1', '2', NULL, NULL);
INSERT INTO `role_menu` VALUES (2, '2', '3', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
