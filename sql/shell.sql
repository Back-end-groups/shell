/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.28 : Database - shell
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shell` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `shell`;

/*Table structure for table `chat_record` */

DROP TABLE IF EXISTS `chat_record`;

CREATE TABLE `chat_record` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户id+时间+对象id',
  `user01_id` bigint NOT NULL COMMENT '发送者id',
  `user02_id` bigint NOT NULL COMMENT '接收者id',
  `message_type` enum('文字','emoji','图片','视频') CHARACTER SET utf8 DEFAULT NULL COMMENT '消息类型',
  `content` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '消息内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chat_record` */

/*Table structure for table `house` */

DROP TABLE IF EXISTS `house`;

CREATE TABLE `house` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上架房源时间+用户id',
  `title` varchar(255) DEFAULT NULL COMMENT '房源信息标题',
  `introduction` text COMMENT '房屋简介',
  `price` bigint DEFAULT NULL COMMENT '单价（元/平方米）',
  `down_payment` bigint DEFAULT NULL COMMENT '首付',
  `total_price` varchar(20) DEFAULT NULL COMMENT '总售价',
  `house_type` varchar(50) DEFAULT NULL COMMENT '房型（-室-厅）',
  `first_picture` varchar(200) DEFAULT NULL COMMENT '房源首图',
  `area` int DEFAULT NULL COMMENT '房源面积（平方米）',
  `years` date DEFAULT NULL COMMENT '房源建成年份',
  `listing` date DEFAULT NULL COMMENT '挂牌时间',
  `toward` varchar(20) DEFAULT NULL COMMENT '朝向',
  `property_number` varchar(50) DEFAULT NULL COMMENT '房源编号',
  `address` varchar(255) DEFAULT NULL COMMENT '房源地址',
  `build_type` varchar(20) DEFAULT NULL COMMENT '楼型',
  `floor` int DEFAULT NULL COMMENT '楼层',
  `is_elevator` tinyint(1) DEFAULT NULL COMMENT '是否有电梯',
  `is_air_condition` tinyint(1) DEFAULT NULL COMMENT '是否有空调',
  `is_decoration` tinyint(1) DEFAULT NULL COMMENT '是否装修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `house` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` enum('男','女') DEFAULT NULL COMMENT '性别',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `id_card` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `role` enum('管理员','用户') DEFAULT NULL COMMENT '角色身份',
  `status` enum('正常','禁用') DEFAULT NULL COMMENT '账号状态',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `description` varchar(255) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`sex`,`nickname`,`avatar`,`phone`,`id_card`,`create_time`,`role`,`status`,`address`,`birthday`,`description`) values 
(1,'XV1dR','941b31e6daac21ec5cd97279e9f21dbe',NULL,NULL,NULL,NULL,NULL,'2023-04-20 09:07:28',NULL,NULL,NULL,NULL,NULL),
(2,'SYb','fb3734ac8d6a32a3fe318bff5744bf5f',NULL,NULL,NULL,NULL,NULL,'2023-04-20 09:26:20',NULL,NULL,NULL,NULL,NULL),
(3,'Z#B7Ae','zEH',NULL,NULL,NULL,NULL,NULL,'2023-04-20 10:16:20',NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
