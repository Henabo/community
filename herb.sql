/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : herb

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 10/04/2020 11:00:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_id` int(11) NOT NULL COMMENT '管理员账号',
  `admin_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员名',
  `admin_password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员密码',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删除，1删除，默认值：0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `type` tinyint(1) NOT NULL COMMENT '评论类型，一级回答，二级回答',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案描述',
  `shielded` tinyint(1) NOT NULL DEFAULT 0 COMMENT '屏蔽，0未屏蔽，1屏蔽，默认值：0',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删除，1删除，默认值：0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, 1, 0, '这是一个回复内容json测试接口', 0, 1585131354384, NULL, 0);
INSERT INTO `comment` VALUES (3, 1, 0, '这是一个回复内容json测试接口', 0, 1585131607052, NULL, 0);
INSERT INTO `comment` VALUES (4, 1, 0, '这是一个回复内容json测试接口', 0, 1585131654069, NULL, 0);
INSERT INTO `comment` VALUES (5, 1, 0, '这是一个回复内容json测试接口', 0, 1585131843310, NULL, 0);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题内容',
  `answer_time` bigint(20) NOT NULL COMMENT '最新回答时间，如没人回答，即添加问题时间',
  `ended` tinyint(1) DEFAULT 0 COMMENT '终结，0未终结，1已终结，默认值：0',
  `shielded` tinyint(1) NOT NULL DEFAULT 0 COMMENT '屏蔽，0未屏蔽，1屏蔽，默认值：0',
  `comment_count` int(11) DEFAULT 0 COMMENT '评论数',
  `view_count` int(11) DEFAULT 0 COMMENT '浏览数',
  `like_count` int(11) DEFAULT 0 COMMENT '点赞数',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除，0未删除，1删除，默认值：0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 1, '三', '是', 1584364908039, 0, 0, 0, 8, 0, '', 1584364908039, 1585291579379, 0);
INSERT INTO `question` VALUES (2, 1, '我想提问题', '这个问题十分的深奥，你们都不会的，别想了', 1584428927877, 0, 0, 0, 0, 0, '测试标签', 1584428927877, NULL, 0);
INSERT INTO `question` VALUES (3, 1, '大家好', '大家好', 1584434920372, 0, 0, 0, 0, 0, '测试标签', 1584434920372, NULL, 0);
INSERT INTO `question` VALUES (4, 1, '我提问题', '看看啊看看看看看', 1584436210860, 0, 0, 0, 0, 0, '测试标签', 1584436210860, NULL, 0);
INSERT INTO `question` VALUES (5, 1, '1', '1', 1584436401141, 0, 0, 0, 0, 0, '1', 1584436401141, NULL, 0);
INSERT INTO `question` VALUES (6, 1, '2', '2', 1584436407421, 1, 0, 0, 0, 0, '2', 1584436407421, NULL, 0);
INSERT INTO `question` VALUES (7, 1, '3', '3', 1584436416736, 0, 0, 0, 0, 0, '3', 1584436416736, NULL, 0);
INSERT INTO `question` VALUES (8, 1, '4', '4', 1584436422949, 0, 0, 0, 0, 0, '4', 1584436422949, NULL, 0);
INSERT INTO `question` VALUES (9, 1, '9', '9', 1584436460065, 0, 0, 0, 0, 0, '9', 1584436460065, NULL, 0);
INSERT INTO `question` VALUES (10, 1, '10', '10', 1584436467500, 0, 0, 0, 0, 0, '10', 1584436467500, NULL, 0);
INSERT INTO `question` VALUES (11, 1, '11', '11', 1584443505426, 0, 0, 0, 0, 0, '11', 1584443505426, NULL, 0);
INSERT INTO `question` VALUES (12, 2, '大哥好', '觉得浪费时间深度', 1584443505426, 0, 0, 0, 0, 0, '去', 1584443505426, NULL, 0);
INSERT INTO `question` VALUES (13, 1, '再一次测试一下', '我是大帅哥', 1584541441790, 0, 0, 0, 0, 0, '逗B集锦', 1584541441790, NULL, 0);

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `register_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '0未处理，1同意，2拒绝',
  `add_time` bigint(20) NOT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `avator_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像地址',
  `gender` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '未知' COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `blacklisted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '拉黑，0未拉黑，1拉黑，默认值：0',
  `add_time` bigint(20) NOT NULL COMMENT '添加时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除：0未删除，1删除，默认值：0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '何纳博', '123456', 'https://i1.hdslb.com/bfs/face/c47dd5e63e179360daf0673a865c947cdc30a142.jpg_64x64.jpg', '未知', NULL, 0, 12345667, NULL, 0);
INSERT INTO `user` VALUES (2, '章翎茜', '123456', 'https://avatar.csdnimg.cn/A/B/A/1_qq_42670493_1565317327.jpg', '未知', NULL, 0, 12345667, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
