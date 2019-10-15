/*
 Navicat Premium Data Transfer

 Source Server         : FirstMysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : green_farm

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 09/10/2019 08:31:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER ,
  `password` varchar(255) CHARACTER ,
  `salt` varchar(255) CHARACTER SET,
  `isDelete` int(255) ,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 ;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'jianhzhong', 'AD4DAF5903C73531AFD96637347B0B17', 'defhtvcd', 0);

SET FOREIGN_KEY_CHECKS = 1;
