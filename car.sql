/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : car

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 10/01/2021 01:43:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carinfo
-- ----------------------------
DROP TABLE IF EXISTS `carinfo`;
CREATE TABLE `carinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` double(10, 2) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of carinfo
-- ----------------------------
INSERT INTO `carinfo` VALUES (1, '大众', 100.00, '轿车', '在库');
INSERT INTO `carinfo` VALUES (4, '兰博基尼', 1000.00, '跑车', '在库');
INSERT INTO `carinfo` VALUES (5, '宝马x5', 500.00, '轿车', '在库');
INSERT INTO `carinfo` VALUES (6, '奥迪A8', 500.00, '轿车', '在库');

-- ----------------------------
-- Table structure for carstatus
-- ----------------------------
DROP TABLE IF EXISTS `carstatus`;
CREATE TABLE `carstatus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of carstatus
-- ----------------------------
INSERT INTO `carstatus` VALUES (1, '在库');
INSERT INTO `carstatus` VALUES (2, '借出');
INSERT INTO `carstatus` VALUES (3, '报修');

-- ----------------------------
-- Table structure for cartype
-- ----------------------------
DROP TABLE IF EXISTS `cartype`;
CREATE TABLE `cartype`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of cartype
-- ----------------------------
INSERT INTO `cartype` VALUES (1, '轿车');
INSERT INTO `cartype` VALUES (2, '跑车');
INSERT INTO `cartype` VALUES (3, 'SUV');
INSERT INTO `cartype` VALUES (4, '客车');
INSERT INTO `cartype` VALUES (5, '货车');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('插入成功');
INSERT INTO `log` VALUES ('删除成功');
INSERT INTO `log` VALUES ('更新成功');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `btime` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `rtime` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (3, 7, 6, '2020-12-16 20:56:44', '2020-12-17 12:35:13', 1);
INSERT INTO `orders` VALUES (4, 7, 1, '2020-12-17 12:42:30', '2020-12-17 12:45:12', 1);
INSERT INTO `orders` VALUES (5, 7, 6, '2020-12-17 13:20:31', '2020-12-17 13:20:49', 1);
INSERT INTO `orders` VALUES (6, 7, 1, '2020-12-17 13:54:42', '2020-12-17 13:55:07', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `type` int(1) NOT NULL,
  `money` double(255, 0) NOT NULL,
  `psd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 1, 10000, '123456');
INSERT INTO `user` VALUES (2, 'zhangsan', 0, 3600, '123456');
INSERT INTO `user` VALUES (7, 'wangwu', 0, 9000, '1');

-- ----------------------------
-- View structure for 用户订单
-- ----------------------------
DROP VIEW IF EXISTS `用户订单`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `用户订单` AS select `user`.`name` AS `用户名`,`carinfo`.`name` AS `车名`,`orders`.`id` AS `id`,`orders`.`uid` AS `uid`,`orders`.`cid` AS `cid`,`orders`.`btime` AS `btime`,`orders`.`rtime` AS `rtime`,`orders`.`status` AS `STATUS` from ((`user` join `orders`) join `carinfo`) where ((`orders`.`uid` = `user`.`id`) and (`orders`.`cid` = `carinfo`.`id`));

-- ----------------------------
-- View structure for 用户订单排行
-- ----------------------------
DROP VIEW IF EXISTS `用户订单排行`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `用户订单排行` AS select `user`.`name` AS `name`,count(`orders`.`uid`) AS `订单量` from (`user` join `orders`) where ((`orders`.`status` = 1) and (`orders`.`uid` = `user`.`id`)) group by `orders`.`uid` desc;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `user_insert`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `user_insert` AFTER INSERT ON `user` FOR EACH ROW insert into log(info) VALUES ('插入成功')
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `user_update`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `user_update` AFTER UPDATE ON `user` FOR EACH ROW insert into log(info) VALUES ('更新成功')
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `user_delete`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` TRIGGER `user_delete` AFTER DELETE ON `user` FOR EACH ROW insert into log(info) VALUES ('删除成功')
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
