/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-02-24 22:16:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attendance`
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendance_id` int(8) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `attendance_type` varchar(32) NOT NULL COMMENT '考勤类型，作为外键',
  `course_name` varchar(32) NOT NULL,
  `attendance_time` date DEFAULT NULL,
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '13034480218', '旷课', '大型数据库', '2017-02-17');
INSERT INTO `attendance` VALUES ('2', '13034480219', '早退', '大型数据库', '2017-02-15');
INSERT INTO `attendance` VALUES ('3', '13034480265', '请假', '程序设计基础', '2017-02-01');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `classname` varchar(32) NOT NULL,
  `major` varchar(32) NOT NULL,
  `native_place` varchar(32) NOT NULL,
  `absenteeism` int(8) DEFAULT '0',
  `late` int(8) DEFAULT '0',
  `leave_early` int(8) DEFAULT '0',
  `leave` int(8) DEFAULT '0',
  `unpaid_work` int(8) DEFAULT '0',
  `fail` int(8) DEFAULT '0',
  `home_late` int(8) DEFAULT '0',
  `error` int(8) DEFAULT '0',
  `scholarship` int(8) DEFAULT '0',
  `cadre` int(8) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('13034480221', '黄志壕', '计算机科学与技术', '计算机13-2', '广东', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `student` VALUES ('13034480242', '测试02', '计算机13-2', '计算机', '贵州', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `student` VALUES ('13034480243', '测试03', '计算机13-2', '计算机', '贵州', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `student` VALUES ('13034480244', '测试04', '计算机13-2', '计算机', '贵州', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `student` VALUES ('13034480245', '测试05', '计算机13-2', '计算机', '贵州', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `student` VALUES ('13034480246', '测试06', '计算机13-2', '计算机', '贵州', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `student` VALUES ('13034480260', '哈哈', '计算机2', '计算机', '上海', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `synthesize`
-- ----------------------------
DROP TABLE IF EXISTS `synthesize`;
CREATE TABLE `synthesize` (
  `synthesize_id` int(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(32) NOT NULL,
  `synthesize_type` varchar(32) NOT NULL COMMENT '加减分类型',
  `description` varchar(50) NOT NULL DEFAULT '',
  `synthesize_time` date DEFAULT NULL,
  PRIMARY KEY (`synthesize_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of synthesize
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=511125888970 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zlj', '123');
INSERT INTO `user` VALUES ('7', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('8', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('9', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('10', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('11', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('12', '719459589@qq.com', '123456');
INSERT INTO `user` VALUES ('13', 'admin', 'admin');
INSERT INTO `user` VALUES ('14', 'admin', 'ss');
INSERT INTO `user` VALUES ('15', 'aaa', 'aaa');
INSERT INTO `user` VALUES ('16', 'ABC', 'ADE');
INSERT INTO `user` VALUES ('17', 'admin', 'ASDS');
INSERT INTO `user` VALUES ('18', 'xixi', 'xixixi');
INSERT INTO `user` VALUES ('19', 'sss', 'ddd');
INSERT INTO `user` VALUES ('20', 'ces', 'ces');
INSERT INTO `user` VALUES ('21', 'ldh', 'ldh');
INSERT INTO `user` VALUES ('511125888969', 'ss', 'aaa');
