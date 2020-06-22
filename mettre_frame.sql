/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : mettre_frame

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 21/06/2020 19:01:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_account
-- ----------------------------
DROP TABLE IF EXISTS `f_account`;
CREATE TABLE `f_account`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '账目标题',
  `amount` decimal(65, 2) NOT NULL COMMENT '账目金额',
  `classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '账目类型（餐饮、购物...）',
  `type` int(2) NOT NULL COMMENT '消费：0 进账：1',
  `record_day` date NOT NULL COMMENT '日期',
  `crate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录插入日期',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` int(22) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000031 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of f_account
-- ----------------------------
INSERT INTO `f_account` VALUES (1000001, '测试消费', 0.01, '10001', 0, '2020-06-21', '2020-06-21 17:29:11', NULL, 10000002);
INSERT INTO `f_account` VALUES (1000016, '测试消费', 0.01, '10001', 0, '2020-06-21', '2020-06-21 17:49:44', NULL, 10000002);

-- ----------------------------
-- Table structure for f_account_classification
-- ----------------------------
DROP TABLE IF EXISTS `f_account_classification`;
CREATE TABLE `f_account_classification`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '账目分类id',
  `classification_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名称',
  `classification_type` int(10) NOT NULL COMMENT '账目分类类别 0：消费 1：入账',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `title`(`classification_title`) USING BTREE COMMENT '唯一索引（账单分类不能重复）'
) ENGINE = InnoDB AUTO_INCREMENT = 10005 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of f_account_classification
-- ----------------------------
INSERT INTO `f_account_classification` VALUES (10001, '餐饮', 0);
INSERT INTO `f_account_classification` VALUES (10004, '购物', 0);

-- ----------------------------
-- Table structure for f_user
-- ----------------------------
DROP TABLE IF EXISTS `f_user`;
CREATE TABLE `f_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE COMMENT '模糊查询手机号'
) ENGINE = InnoDB AUTO_INCREMENT = 10000003 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of f_user
-- ----------------------------
INSERT INTO `f_user` VALUES (10000002, '18844157372', '$2a$10$jy8D.2wlV5rbxPq6MaNisu6WxxU8ob13EdzCiRtBdzfYMOtA4RcVq', '2019-12-29 13:21:18', '2019-12-29 13:21:18', '阿拉丁');

SET FOREIGN_KEY_CHECKS = 1;
