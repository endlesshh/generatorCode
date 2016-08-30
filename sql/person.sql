/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : jeecg

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2016-08-27 17:23:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `ID` varchar(32) NOT NULL DEFAULT '' COMMENT '主键',
  `NAME` varchar(32) DEFAULT NULL COMMENT '用户名',
  `AGE` int(11) DEFAULT NULL COMMENT '年龄',
  `SALARY` decimal(10,2) DEFAULT NULL COMMENT '工资',
  `createDt` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('402881465551e02d015551f2012d0015', 'aaaaaa', '111111', '111.00', '2016-06-15 10:44:20');
