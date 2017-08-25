/*
Navicat MySQL Data Transfer

Source Server         : CentOS01
Source Server Version : 50173
Source Host           : 192.168.164.129:3306
Source Database       : huazi

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-08-22 14:25:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for acc_record
-- ----------------------------
DROP TABLE IF EXISTS `acc_record`;
CREATE TABLE `acc_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `location` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `person` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `pro_dec` varchar(3000) CHARACTER SET utf8 NOT NULL,
  `reason_dec` varchar(3000) CHARACTER SET utf8 DEFAULT NULL,
  `so_method` varchar(3000) CHARACTER SET utf8 DEFAULT NULL,
  `document` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13729 DEFAULT CHARSET=latin1;
