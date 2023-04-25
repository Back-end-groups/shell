-- MySQL dump 10.13  Distrib 8.0.29, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: shell
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat_record`
--

DROP TABLE IF EXISTS `chat_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_record` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '用户id+时间+对象id',
  `user01_id` bigint NOT NULL COMMENT '发送者id',
  `user02_id` bigint NOT NULL COMMENT '接收者id',
  `message_type` enum('文字','emoji','图片','视频') CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息类型',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_record`
--

LOCK TABLES `chat_record` WRITE;
/*!40000 ALTER TABLE `chat_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '上架房源时间+用户id',
  `title` varchar(255) DEFAULT NULL COMMENT '房源信息标题',
  `introduction` text COMMENT '房屋简介',
  `price` bigint DEFAULT NULL COMMENT '单价（元/平方米）',
  `down_payment` bigint DEFAULT NULL COMMENT '首付',
  `total_price` varchar(20) DEFAULT NULL COMMENT '总售价',
  `house_type` varchar(100) DEFAULT NULL COMMENT '房型（-室-厅）',
  `first_picture` varchar(200) DEFAULT NULL COMMENT '房源首图',
  `area` int DEFAULT NULL COMMENT '房源面积（平方米）',
  `years` int DEFAULT NULL COMMENT '房源建成年份',
  `listing` date DEFAULT NULL COMMENT '挂牌时间',
  `toward` varchar(100) DEFAULT NULL COMMENT '朝向',
  `property_number` varchar(50) DEFAULT NULL COMMENT '房源编号',
  `address` varchar(255) DEFAULT NULL COMMENT '房源地址',
  `build_type` varchar(100) DEFAULT NULL COMMENT '楼型',
  `floor` int DEFAULT NULL COMMENT '楼层',
  `is_elevator` tinyint(1) DEFAULT NULL COMMENT '是否有电梯',
  `is_air_condition` tinyint(1) DEFAULT NULL COMMENT '是否有空调',
  `is_decoration` tinyint(1) DEFAULT NULL COMMENT '是否装修',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES ('2023-04-24T21:50:01.0861','传思文部','esse laborum do aliqua nulla',75,NULL,'80','tempor sit proident do',NULL,30,0,'2023-04-24','minim Duis cillum','17','广东省苗栗县泾源县','anim exercitation labore amet id',5,0,0,0),('2023-04-24T22:05:48.9061','传思文部','esse laborum do aliqua nulla',75,NULL,'80','tempor sit proident do',NULL,30,0,'2023-04-24','minim Duis cillum','17','广东省苗栗县泾源县','anim exercitation labore amet id',5,0,0,0),('2023-04-24T22:07:16.9841','传思文部','esse laborum do aliqua nulla',75,NULL,'80','tempor sit proident do',NULL,30,0,'2023-04-24','minim Duis cillum','17','广东省苗栗县泾源县','anim exercitation labore amet id',5,0,0,0),('2023-04-24T22:08:41.4371','支调听江局什','dolor amet',30,NULL,'57','Ut',NULL,60,0,'2023-04-24','dolor','38','广西壮族自治区太原市其它区','ad',4,0,0,0),('2023-04-24T22:10:05.2331','支调听江局什','dolor amet',30,NULL,'57','Ut',NULL,60,0,'2023-04-24','dolor','38','广西壮族自治区太原市其它区','ad',4,0,0,0),('2023-04-24T22:18:59.7111','统资酸','adipisicing',84,NULL,'12','aliquip eiusmod','1234',27,0,'2023-04-24','nostrud','74','海外上海市黄山区','magna culpa cupidatat',5,0,0,0),('2023-04-25T09:23:32.1341','生期义志','Duis culpa sunt consectetur ullamco',34,NULL,'36','est dolor commodo dolor','http://dummyimage.com/400x400',85,0,'2023-04-25','et qui deserunt aliquip','25','浙江省汉中市六枝特区','esse eu in et magna',2,0,0,0);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_images`
--

DROP TABLE IF EXISTS `house_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house_images` (
  `house_id` varchar(100) NOT NULL,
  `image_url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_images`
--

LOCK TABLES `house_images` WRITE;
/*!40000 ALTER TABLE `house_images` DISABLE KEYS */;
INSERT INTO `house_images` VALUES ('2023-04-25T09:23:32.1341','http://dummyimage.com/400x400'),('2023-04-25T09:23:32.1341','http://dummyimage.com/400x400'),('2023-04-25T09:23:32.1341','http://dummyimage.com/400x400');
/*!40000 ALTER TABLE `house_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `description` varchar(255) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'XV1dR','941b31e6daac21ec5cd97279e9f21dbe',NULL,NULL,NULL,NULL,NULL,'2023-04-20 09:07:28',NULL,NULL,NULL,NULL,NULL),(2,'SYb','fb3734ac8d6a32a3fe318bff5744bf5f',NULL,NULL,NULL,NULL,NULL,'2023-04-20 09:26:20',NULL,NULL,NULL,NULL,NULL),(3,'Z#B7Ae','zEH',NULL,NULL,NULL,NULL,NULL,'2023-04-20 10:16:20',NULL,NULL,NULL,NULL,NULL),(4,'tbm','34644d2d07617ee5af38a5b78261a4f1',NULL,NULL,NULL,NULL,NULL,'2023-04-24 21:27:25',NULL,NULL,NULL,NULL,NULL),(5,'12345','2fe4142b67c6b836f8c5504e22d9d752',NULL,NULL,NULL,NULL,NULL,'2023-04-24 21:29:29',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-25 19:55:49
