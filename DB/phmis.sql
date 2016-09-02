/*
SQLyog Community v8.82 
MySQL - 5.6.21 : Database - phmis_new
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`phmis_new` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `phmis_new`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  `street` varchar(20) NOT NULL,
  `suite` varchar(10) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `stateId` int(11) NOT NULL,
  `zipCode` char(5) NOT NULL,
  `creationDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `address` */

/*Table structure for table `addresstype` */

DROP TABLE IF EXISTS `addresstype`;

CREATE TABLE `addresstype` (
  `typeId` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `addresstype` */

insert  into `addresstype`(`typeId`,`type`) values (1,'Current'),(2,'Permanent'),(3,'Work');

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`username`,`password`) values ('admin','admin');

/*Table structure for table `cheque` */

DROP TABLE IF EXISTS `cheque`;

CREATE TABLE `cheque` (
  `id` int(10) unsigned NOT NULL,
  `bankName` varchar(30) NOT NULL,
  `number` varchar(30) NOT NULL,
  `dueDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cheque` */

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `creationDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contact` */

/*Table structure for table `contacttype` */

DROP TABLE IF EXISTS `contacttype`;

CREATE TABLE `contacttype` (
  `typeId` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `contacttype` */

insert  into `contacttype`(`typeId`,`type`) values (1,'Personal'),(2,'Work');

/*Table structure for table `county` */

DROP TABLE IF EXISTS `county`;

CREATE TABLE `county` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fipsCode` char(5) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `county` */

insert  into `county`(`id`,`fipsCode`,`name`) values (1,'25001','Barnstable County'),(2,'25003','Berkshire County'),(3,'25005','Bristol County'),(4,'25007','Dukes County'),(5,'25009','Essex County'),(6,'25011','Franklin County'),(7,'25013','Hampden County'),(8,'25015','Hampshire County'),(9,'25017','Middlesex County'),(10,'25019','Nantucket County'),(11,'25021','Norfolk County'),(12,'25023','Plymouth County'),(13,'25025','Suffolk County'),(14,'25027','Worcester County');

/*Table structure for table `devicerequest` */

DROP TABLE IF EXISTS `devicerequest`;

CREATE TABLE `devicerequest` (
  `id` int(10) unsigned NOT NULL,
  `enterpriseId` int(10) unsigned NOT NULL,
  `specialInstruction` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `devicerequest` */

insert  into `devicerequest`(`id`,`enterpriseId`,`specialInstruction`) values (1,3,'skhbs sdbkhsbv'),(2,3,''),(3,3,'');

/*Table structure for table `enterprise` */

DROP TABLE IF EXISTS `enterprise`;

CREATE TABLE `enterprise` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `typeId` int(10) unsigned NOT NULL,
  `countyId` int(11) NOT NULL,
  `street` varchar(50) NOT NULL,
  `suite` varchar(15) DEFAULT NULL,
  `city` varchar(30) NOT NULL,
  `state` char(2) NOT NULL,
  `zipcode` char(5) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  `creationDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `enterprise` */

insert  into `enterprise`(`id`,`number`,`name`,`typeId`,`countyId`,`street`,`suite`,`city`,`state`,`zipcode`,`contact`,`url`,`creationDate`,`modifiedDate`) values (1,'E1428638719487','Massachusetts General Hospital',1,13,'Huntington Avenue','','Boston','MA','02121','6173730109','http://www.massgeneral.org/','2015-04-10 00:00:00','2015-04-10 00:00:00'),(2,'E1428638795836','Brigham and Women Hospital',1,13,'Brigham Circle','','Boston','MA','02123','6173730109','www.svbuwv.com','2015-04-10 00:06:35',NULL),(3,'E1428659077310','Boston Warehouse',2,13,'Huntington Avenue','','Boston','MA','02156','6173730109','http://www.massgeneral.org/','2015-04-10 00:00:00','2015-04-10 00:00:00'),(4,'E1428717211693','ABC Devices',3,13,'Huntington Avenue','','Boston','MA','02156','6173730109','www.svbuwv.com','2015-04-10 00:00:00','2015-04-10 00:00:00');

/*Table structure for table `enterprisetype` */

DROP TABLE IF EXISTS `enterprisetype`;

CREATE TABLE `enterprisetype` (
  `typeId` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `enterprisetype` */

insert  into `enterprisetype`(`typeId`,`type`) values (1,'Hospital'),(2,'Warehouse'),(3,'Vendor');

/*Table structure for table `etransfer` */

DROP TABLE IF EXISTS `etransfer`;

CREATE TABLE `etransfer` (
  `id` int(10) unsigned NOT NULL,
  `bankName` varchar(30) NOT NULL,
  `transactionId` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `etransfer` */

/*Table structure for table `inventoryitem` */

DROP TABLE IF EXISTS `inventoryitem`;

CREATE TABLE `inventoryitem` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `itemCode` varchar(30) NOT NULL,
  `productId` int(11) NOT NULL,
  `enterpriseId` int(11) NOT NULL,
  `additionDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `inventoryitem` */

insert  into `inventoryitem`(`id`,`itemCode`,`productId`,`enterpriseId`,`additionDate`) values (1,'14298792528030',1,3,'2015-04-24 08:40:52'),(2,'14298792528031',1,3,'2015-04-24 08:40:52'),(3,'14298792528032',1,3,'2015-04-24 08:40:52'),(4,'14298792528033',2,3,'2015-04-24 08:40:52'),(5,'14298792528034',2,3,'2015-04-24 08:40:52');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `orderId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `invoice` */

/*Table structure for table `logindetails` */

DROP TABLE IF EXISTS `logindetails`;

CREATE TABLE `logindetails` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `loginDate` datetime NOT NULL,
  `ipAddress` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

/*Data for the table `logindetails` */

insert  into `logindetails`(`id`,`userId`,`loginDate`,`ipAddress`) values (1,1,'2015-04-16 04:42:43','0:0:0:0:0:0:0:1'),(2,1,'2015-04-16 04:54:17','0:0:0:0:0:0:0:1'),(3,1,'2015-04-16 05:12:56','0:0:0:0:0:0:0:1'),(4,1,'2015-04-16 05:14:54','0:0:0:0:0:0:0:1'),(5,1,'2015-04-16 05:16:26','0:0:0:0:0:0:0:1'),(6,1,'2015-04-18 03:47:46','0:0:0:0:0:0:0:1'),(7,1,'2015-04-18 03:48:15','0:0:0:0:0:0:0:1'),(8,1,'2015-04-18 03:51:06','0:0:0:0:0:0:0:1'),(9,1,'2015-04-18 03:52:10','0:0:0:0:0:0:0:1'),(10,1,'2015-04-18 04:04:55','0:0:0:0:0:0:0:1'),(11,1,'2015-04-18 04:08:27','0:0:0:0:0:0:0:1'),(12,1,'2015-04-18 04:23:02','0:0:0:0:0:0:0:1'),(13,1,'2015-04-18 04:28:03','0:0:0:0:0:0:0:1'),(14,1,'2015-04-18 04:28:56','0:0:0:0:0:0:0:1'),(15,1,'2015-04-18 04:40:37','0:0:0:0:0:0:0:1'),(16,1,'2015-04-18 04:47:09','0:0:0:0:0:0:0:1'),(17,1,'2015-04-18 04:52:03','0:0:0:0:0:0:0:1'),(18,1,'2015-04-18 04:53:18','0:0:0:0:0:0:0:1'),(19,1,'2015-04-18 04:54:10','0:0:0:0:0:0:0:1'),(20,1,'2015-04-18 04:55:13','0:0:0:0:0:0:0:1'),(21,1,'2015-04-18 05:12:19','0:0:0:0:0:0:0:1'),(22,1,'2015-04-18 05:15:12','0:0:0:0:0:0:0:1'),(23,1,'2015-04-18 07:37:59','0:0:0:0:0:0:0:1'),(24,3,'2015-04-18 07:46:09','0:0:0:0:0:0:0:1'),(25,4,'2015-04-18 07:57:05','0:0:0:0:0:0:0:1'),(26,1,'2015-04-18 08:05:17','0:0:0:0:0:0:0:1'),(27,2,'2015-04-18 08:06:32','0:0:0:0:0:0:0:1'),(28,1,'2015-04-18 08:07:00','0:0:0:0:0:0:0:1'),(29,2,'2015-04-18 08:07:38','0:0:0:0:0:0:0:1'),(30,3,'2015-04-18 08:09:05','0:0:0:0:0:0:0:1'),(31,1,'2015-04-18 08:09:22','0:0:0:0:0:0:0:1'),(32,3,'2015-04-18 08:09:39','0:0:0:0:0:0:0:1'),(33,3,'2015-04-18 08:15:06','0:0:0:0:0:0:0:1'),(34,4,'2015-04-18 08:15:48','0:0:0:0:0:0:0:1'),(35,1,'2015-04-18 08:19:12','0:0:0:0:0:0:0:1'),(36,1,'2015-04-18 08:20:42','0:0:0:0:0:0:0:1'),(37,1,'2015-04-18 08:25:15','0:0:0:0:0:0:0:1'),(38,1,'2015-04-18 08:27:12','0:0:0:0:0:0:0:1'),(39,1,'2015-04-18 08:28:38','0:0:0:0:0:0:0:1'),(40,5,'2015-04-18 08:30:55','0:0:0:0:0:0:0:1'),(41,6,'2015-04-18 08:32:07','0:0:0:0:0:0:0:1'),(42,7,'2015-04-18 08:32:22','0:0:0:0:0:0:0:1'),(43,1,'2015-04-18 08:33:16','0:0:0:0:0:0:0:1'),(44,8,'2015-04-18 08:33:53','0:0:0:0:0:0:0:1'),(45,5,'2015-04-18 08:35:42','0:0:0:0:0:0:0:1'),(46,1,'2015-04-18 09:24:49','0:0:0:0:0:0:0:1'),(47,1,'2015-04-18 09:26:01','10.0.0.10'),(48,1,'2015-04-18 09:53:28','0:0:0:0:0:0:0:1'),(49,7,'2015-04-18 10:08:24','0:0:0:0:0:0:0:1'),(50,6,'2015-04-18 10:08:43','0:0:0:0:0:0:0:1'),(51,6,'2015-04-20 08:04:48','0:0:0:0:0:0:0:1'),(52,6,'2015-04-20 09:34:40','0:0:0:0:0:0:0:1'),(53,6,'2015-04-20 10:02:21','0:0:0:0:0:0:0:1'),(54,6,'2015-04-22 05:17:31','0:0:0:0:0:0:0:1'),(55,6,'2015-04-22 05:23:36','0:0:0:0:0:0:0:1'),(56,6,'2015-04-22 05:31:29','0:0:0:0:0:0:0:1'),(57,6,'2015-04-22 06:00:50','0:0:0:0:0:0:0:1'),(58,8,'2015-04-22 06:02:11','0:0:0:0:0:0:0:1'),(59,1,'2015-04-22 06:02:51','0:0:0:0:0:0:0:1'),(60,2,'2015-04-22 06:03:03','0:0:0:0:0:0:0:1'),(61,8,'2015-04-22 06:04:55','0:0:0:0:0:0:0:1'),(62,6,'2015-04-22 06:05:12','0:0:0:0:0:0:0:1'),(63,8,'2015-04-22 06:05:20','0:0:0:0:0:0:0:1'),(64,2,'2015-04-22 06:05:54','0:0:0:0:0:0:0:1'),(65,6,'2015-04-22 06:06:11','0:0:0:0:0:0:0:1'),(66,2,'2015-04-22 06:06:27','0:0:0:0:0:0:0:1'),(67,6,'2015-04-22 06:06:36','0:0:0:0:0:0:0:1'),(68,8,'2015-04-22 06:06:50','0:0:0:0:0:0:0:1'),(69,6,'2015-04-22 06:08:31','0:0:0:0:0:0:0:1'),(70,8,'2015-04-22 06:10:01','0:0:0:0:0:0:0:1'),(71,6,'2015-04-22 06:13:29','0:0:0:0:0:0:0:1'),(72,8,'2015-04-22 06:13:57','0:0:0:0:0:0:0:1'),(73,6,'2015-04-22 06:15:40','0:0:0:0:0:0:0:1'),(74,8,'2015-04-22 06:16:47','0:0:0:0:0:0:0:1'),(75,8,'2015-04-22 06:19:37','0:0:0:0:0:0:0:1'),(76,8,'2015-04-22 06:26:46','0:0:0:0:0:0:0:1'),(77,6,'2015-04-22 06:27:53','0:0:0:0:0:0:0:1'),(78,8,'2015-04-22 06:28:12','0:0:0:0:0:0:0:1'),(79,8,'2015-04-22 06:33:43','0:0:0:0:0:0:0:1'),(80,8,'2015-04-22 07:52:50','0:0:0:0:0:0:0:1'),(81,8,'2015-04-22 07:58:00','0:0:0:0:0:0:0:1'),(82,8,'2015-04-22 08:54:27','0:0:0:0:0:0:0:1'),(83,8,'2015-04-22 08:59:00','0:0:0:0:0:0:0:1'),(84,8,'2015-04-22 09:01:00','0:0:0:0:0:0:0:1'),(85,8,'2015-04-22 09:02:48','0:0:0:0:0:0:0:1'),(86,8,'2015-04-22 09:04:28','0:0:0:0:0:0:0:1'),(87,2,'2015-04-22 09:09:21','0:0:0:0:0:0:0:1'),(88,2,'2015-04-22 16:24:58','0:0:0:0:0:0:0:1'),(89,2,'2015-04-22 16:59:41','0:0:0:0:0:0:0:1'),(90,2,'2015-04-22 16:59:53','0:0:0:0:0:0:0:1'),(91,1,'2015-04-22 17:08:55','0:0:0:0:0:0:0:1'),(92,2,'2015-04-22 17:09:05','0:0:0:0:0:0:0:1'),(93,2,'2015-04-22 17:54:21','0:0:0:0:0:0:0:1'),(94,2,'2015-04-22 18:14:55','0:0:0:0:0:0:0:1'),(95,2,'2015-04-22 18:17:27','0:0:0:0:0:0:0:1'),(96,2,'2015-04-22 18:42:16','0:0:0:0:0:0:0:1'),(97,2,'2015-04-22 19:09:50','0:0:0:0:0:0:0:1'),(98,2,'2015-04-22 19:21:42','0:0:0:0:0:0:0:1'),(99,2,'2015-04-22 19:38:42','0:0:0:0:0:0:0:1'),(100,2,'2015-04-22 19:46:43','0:0:0:0:0:0:0:1'),(101,2,'2015-04-23 05:27:22','0:0:0:0:0:0:0:1'),(102,2,'2015-04-23 05:57:29','0:0:0:0:0:0:0:1'),(103,2,'2015-04-23 06:02:20','0:0:0:0:0:0:0:1'),(104,2,'2015-04-23 06:04:42','0:0:0:0:0:0:0:1'),(105,2,'2015-04-23 06:08:39','0:0:0:0:0:0:0:1'),(106,2,'2015-04-23 06:23:49','0:0:0:0:0:0:0:1'),(107,2,'2015-04-23 06:25:31','0:0:0:0:0:0:0:1'),(108,2,'2015-04-23 06:36:27','0:0:0:0:0:0:0:1'),(109,2,'2015-04-23 06:53:54','0:0:0:0:0:0:0:1'),(110,2,'2015-04-23 07:45:33','0:0:0:0:0:0:0:1'),(111,2,'2015-04-23 07:51:51','0:0:0:0:0:0:0:1'),(112,2,'2015-04-23 07:54:58','0:0:0:0:0:0:0:1'),(113,2,'2015-04-23 08:20:38','0:0:0:0:0:0:0:1'),(114,8,'2015-04-23 08:25:05','0:0:0:0:0:0:0:1'),(115,2,'2015-04-23 08:25:28','0:0:0:0:0:0:0:1'),(116,8,'2015-04-23 08:37:04','0:0:0:0:0:0:0:1'),(117,8,'2015-04-23 08:51:26','0:0:0:0:0:0:0:1'),(118,8,'2015-04-23 08:53:08','0:0:0:0:0:0:0:1'),(119,8,'2015-04-23 08:54:51','0:0:0:0:0:0:0:1'),(120,8,'2015-04-23 08:56:49','0:0:0:0:0:0:0:1'),(121,8,'2015-04-23 09:55:59','0:0:0:0:0:0:0:1'),(122,8,'2015-04-23 09:58:32','0:0:0:0:0:0:0:1'),(123,8,'2015-04-23 09:59:54','0:0:0:0:0:0:0:1'),(124,8,'2015-04-23 10:02:25','0:0:0:0:0:0:0:1'),(125,8,'2015-04-23 10:24:16','0:0:0:0:0:0:0:1'),(126,8,'2015-04-23 10:25:39','0:0:0:0:0:0:0:1'),(127,8,'2015-04-23 10:37:37','0:0:0:0:0:0:0:1'),(128,8,'2015-04-23 10:39:56','0:0:0:0:0:0:0:1'),(129,8,'2015-04-23 10:41:53','0:0:0:0:0:0:0:1'),(130,8,'2015-04-23 11:14:45','0:0:0:0:0:0:0:1'),(131,8,'2015-04-23 11:17:04','0:0:0:0:0:0:0:1'),(132,8,'2015-04-23 11:18:05','0:0:0:0:0:0:0:1'),(133,8,'2015-04-23 11:23:43','0:0:0:0:0:0:0:1'),(134,8,'2015-04-23 11:50:21','0:0:0:0:0:0:0:1'),(135,8,'2015-04-23 12:31:08','0:0:0:0:0:0:0:1'),(136,8,'2015-04-23 12:35:04','0:0:0:0:0:0:0:1'),(137,4,'2015-04-24 01:06:22','0:0:0:0:0:0:0:1'),(138,4,'2015-04-24 01:13:27','0:0:0:0:0:0:0:1'),(139,4,'2015-04-24 01:24:15','0:0:0:0:0:0:0:1'),(140,8,'2015-04-24 01:30:05','0:0:0:0:0:0:0:1'),(141,8,'2015-04-24 01:31:12','0:0:0:0:0:0:0:1'),(142,4,'2015-04-24 01:36:30','0:0:0:0:0:0:0:1'),(143,4,'2015-04-24 01:43:36','0:0:0:0:0:0:0:1'),(144,4,'2015-04-24 03:24:24','0:0:0:0:0:0:0:1'),(145,4,'2015-04-24 03:25:57','0:0:0:0:0:0:0:1'),(146,4,'2015-04-24 05:05:49','0:0:0:0:0:0:0:1'),(147,4,'2015-04-24 05:09:00','0:0:0:0:0:0:0:1'),(148,4,'2015-04-24 05:18:18','0:0:0:0:0:0:0:1'),(149,4,'2015-04-24 05:25:15','0:0:0:0:0:0:0:1'),(150,4,'2015-04-24 05:32:30','0:0:0:0:0:0:0:1'),(151,4,'2015-04-24 05:35:38','0:0:0:0:0:0:0:1'),(152,4,'2015-04-24 05:50:10','0:0:0:0:0:0:0:1'),(153,4,'2015-04-24 05:59:18','0:0:0:0:0:0:0:1'),(154,4,'2015-04-24 06:04:10','0:0:0:0:0:0:0:1'),(155,4,'2015-04-24 06:05:46','0:0:0:0:0:0:0:1'),(156,4,'2015-04-24 06:09:29','0:0:0:0:0:0:0:1'),(157,4,'2015-04-24 06:14:43','0:0:0:0:0:0:0:1'),(158,4,'2015-04-24 06:22:38','0:0:0:0:0:0:0:1'),(159,4,'2015-04-24 06:26:58','0:0:0:0:0:0:0:1'),(160,4,'2015-04-24 06:27:54','0:0:0:0:0:0:0:1'),(161,4,'2015-04-24 06:53:20','0:0:0:0:0:0:0:1'),(162,4,'2015-04-24 07:19:25','0:0:0:0:0:0:0:1'),(163,4,'2015-04-24 07:20:40','0:0:0:0:0:0:0:1'),(164,4,'2015-04-24 08:28:35','0:0:0:0:0:0:0:1'),(165,4,'2015-04-24 08:37:32','0:0:0:0:0:0:0:1'),(166,4,'2015-04-24 08:38:54','0:0:0:0:0:0:0:1'),(167,4,'2015-04-24 08:49:11','0:0:0:0:0:0:0:1'),(168,4,'2015-04-24 08:50:37','0:0:0:0:0:0:0:1'),(169,4,'2015-04-24 08:56:37','0:0:0:0:0:0:0:1'),(170,2,'2015-04-24 09:01:40','0:0:0:0:0:0:0:1'),(171,8,'2015-04-24 09:02:48','0:0:0:0:0:0:0:1'),(172,2,'2015-04-24 09:04:45','0:0:0:0:0:0:0:1'),(173,8,'2015-04-24 09:05:43','0:0:0:0:0:0:0:1'),(174,8,'2015-04-24 11:21:38','0:0:0:0:0:0:0:1'),(175,8,'2015-04-24 11:27:12','0:0:0:0:0:0:0:1'),(176,8,'2015-04-24 11:35:00','0:0:0:0:0:0:0:1'),(177,8,'2015-04-24 11:36:02','0:0:0:0:0:0:0:1'),(178,8,'2015-04-24 11:53:52','0:0:0:0:0:0:0:1'),(179,8,'2015-04-24 12:06:49','0:0:0:0:0:0:0:1'),(180,8,'2015-04-24 12:09:05','0:0:0:0:0:0:0:1'),(181,8,'2015-04-24 12:17:09','0:0:0:0:0:0:0:1'),(182,8,'2015-04-24 12:21:41','0:0:0:0:0:0:0:1'),(183,2,'2015-04-24 12:23:44','0:0:0:0:0:0:0:1'),(184,1,'2015-04-24 17:08:25','0:0:0:0:0:0:0:1'),(185,1,'2015-04-24 17:09:41','0:0:0:0:0:0:0:1'),(186,1,'2015-04-24 17:11:27','0:0:0:0:0:0:0:1');

/*Table structure for table `maintenance` */

DROP TABLE IF EXISTS `maintenance`;

CREATE TABLE `maintenance` (
  `reportId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `inventoryItemId` int(11) NOT NULL,
  `maintenanceDate` date NOT NULL,
  `reportCreationDate` datetime NOT NULL,
  `report` text NOT NULL,
  `maintenanceCost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`reportId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `maintenance` */

/*Table structure for table `medicalstaff` */

DROP TABLE IF EXISTS `medicalstaff`;

CREATE TABLE `medicalstaff` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `enterpriseId` int(11) NOT NULL,
  `additionDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `medicalstaff` */

insert  into `medicalstaff`(`id`,`number`,`name`,`enterpriseId`,`additionDate`,`modifiedDate`) values (1,'R1429707922697','Piyush Chak',1,'2015-04-22 00:00:00',NULL),(2,'R1429880591239','Pawan Mata',1,'2015-04-24 00:00:00',NULL),(3,'R1429880598560','Prateek Mane',1,'2015-04-24 00:00:00',NULL);

/*Table structure for table `medicalteam` */

DROP TABLE IF EXISTS `medicalteam`;

CREATE TABLE `medicalteam` (
  `surgeryId` int(11) NOT NULL,
  `staffId` int(11) NOT NULL,
  PRIMARY KEY (`surgeryId`,`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `medicalteam` */

/*Table structure for table `operatingroom` */

DROP TABLE IF EXISTS `operatingroom`;

CREATE TABLE `operatingroom` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enterpriseId` int(11) NOT NULL,
  `number` varchar(20) NOT NULL,
  `location` varchar(20) NOT NULL,
  `creationDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `operatingroom` */

insert  into `operatingroom`(`id`,`enterpriseId`,`number`,`location`,`creationDate`,`modifiedDate`) values (1,1,'R1429707874010','102 EL','2015-04-22 00:00:00',NULL),(2,1,'R1429880640667','256 GH','2015-04-24 00:00:00',NULL);

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

insert  into `orderitem`(`id`,`orderId`,`productId`,`quantity`) values (1,1,1,3),(2,1,2,2);

/*Table structure for table `orderrequest` */

DROP TABLE IF EXISTS `orderrequest`;

CREATE TABLE `orderrequest` (
  `id` int(10) unsigned NOT NULL,
  `orderId` int(11) NOT NULL,
  `addedToInventory` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderrequest` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `orderedBy` int(11) NOT NULL,
  `orderedFrom` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `addedToInventory` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`number`,`orderedBy`,`orderedFrom`,`creationDate`,`addedToInventory`) values (1,'O1429871262818',3,4,'2015-04-24 06:28:35',1);

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `typeId` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `paymentDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `payment` */

/*Table structure for table `paymenttype` */

DROP TABLE IF EXISTS `paymenttype`;

CREATE TABLE `paymenttype` (
  `typeId` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `mode` varchar(10) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `paymenttype` */

insert  into `paymenttype`(`typeId`,`mode`) values (1,'Cash'),(2,'Cheque'),(3,'E-Tranfer');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `enterpriseId` int(11) NOT NULL,
  `additionDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`number`,`name`,`description`,`cost`,`enterpriseId`,`additionDate`,`modifiedDate`) values (1,'P1429694787602','product1','wefw  wf wf ','125.00',4,'2015-04-22 00:00:00',NULL),(2,'P1429695123646','product2','bscs a iabab bk','153.00',4,'2015-04-22 00:00:00',NULL);

/*Table structure for table `productrequested` */

DROP TABLE IF EXISTS `productrequested`;

CREATE TABLE `productrequested` (
  `requestId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`requestId`,`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `productrequested` */

insert  into `productrequested`(`requestId`,`productId`) values (1,1),(1,2),(2,1),(3,2),(4,1),(4,2);

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `createdBy` int(11) NOT NULL,
  `processedBy` int(11) DEFAULT NULL,
  `typeId` int(11) NOT NULL,
  `statusId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL,
  `processedDate` datetime DEFAULT NULL,
  `processComment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `request` */

insert  into `request`(`id`,`number`,`createdBy`,`processedBy`,`typeId`,`statusId`,`creationDate`,`processedDate`,`processComment`) values (1,'R1429853421263',8,NULL,2,2,'2015-04-24 01:30:21','2015-04-24 06:28:08',NULL),(2,'R1429853578311',8,NULL,2,4,'2015-04-24 01:32:58','2015-04-24 08:58:41',''),(3,'R1429853764031',8,NULL,2,1,'2015-04-24 01:36:04',NULL,NULL),(4,'R1429880709115',2,NULL,1,2,'2015-04-24 09:05:09',NULL,NULL);

/*Table structure for table `requeststatus` */

DROP TABLE IF EXISTS `requeststatus`;

CREATE TABLE `requeststatus` (
  `statusId` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`statusId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `requeststatus` */

insert  into `requeststatus`(`statusId`,`status`) values (1,'Pending'),(2,'Approved'),(3,'On Hold'),(4,'Cancelled'),(5,'Delivered'),(6,'Available');

/*Table structure for table `requesttype` */

DROP TABLE IF EXISTS `requesttype`;

CREATE TABLE `requesttype` (
  `typeId` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `requesttype` */

insert  into `requesttype`(`typeId`,`type`) values (1,'Surgery Request'),(2,'Device Request'),(3,'Order Request');

/*Table structure for table `reserveddevices` */

DROP TABLE IF EXISTS `reserveddevices`;

CREATE TABLE `reserveddevices` (
  `surgeryId` int(11) NOT NULL,
  `inventoryItemId` int(11) NOT NULL,
  PRIMARY KEY (`surgeryId`,`inventoryItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reserveddevices` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `supportedEnterpriseTypeId` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_role` (`supportedEnterpriseTypeId`),
  CONSTRAINT `FK_role` FOREIGN KEY (`supportedEnterpriseTypeId`) REFERENCES `enterprisetype` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`supportedEnterpriseTypeId`) values (1,'Hospital Administrator',1),(2,'Doctor',1),(3,'Resource Manager',1),(4,'Warehouse Administrator',2),(5,'Inventory Manager',2),(6,'Store Administrator',3),(7,'Product Manager',3),(8,'Sales Manager',3);

/*Table structure for table `surgery` */

DROP TABLE IF EXISTS `surgery`;

CREATE TABLE `surgery` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `operatingRoomId` int(11) DEFAULT NULL,
  `scheduleDate` date DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `patientName` varchar(50) NOT NULL,
  `creationDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `surgery` */

insert  into `surgery`(`id`,`userId`,`operatingRoomId`,`scheduleDate`,`type`,`patientName`,`creationDate`) values (1,2,1,'2015-04-27','Heart Bypass','Pawan Mata','2015-04-24 09:05:09');

/*Table structure for table `surgeryrequest` */

DROP TABLE IF EXISTS `surgeryrequest`;

CREATE TABLE `surgeryrequest` (
  `id` int(10) unsigned NOT NULL,
  `surgeryId` int(11) NOT NULL,
  `dateFrom` date NOT NULL,
  `dateTo` date NOT NULL,
  `specialInstructions` text,
  `staffCount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `surgeryrequest` */

insert  into `surgeryrequest`(`id`,`surgeryId`,`dateFrom`,`dateTo`,`specialInstructions`,`staffCount`) values (4,1,'2015-04-26','2015-04-30','',2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `roleId` int(11) NOT NULL,
  `enterpriseId` int(11) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `creationDate` datetime NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `lastAccessedFrom` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`number`,`username`,`password`,`emailId`,`roleId`,`enterpriseId`,`firstName`,`lastName`,`dateOfBirth`,`status`,`creationDate`,`modifiedDate`,`lastLoginDate`,`lastAccessedFrom`) values (1,'U1429358699231','vineet','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',1,1,'Vineet','Dandekar',NULL,1,'2015-04-18 08:04:59','2015-04-18 08:05:17','2015-04-24 17:11:27','0:0:0:0:0:0:0:1'),(2,'U1429358756329','madhav','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',2,1,'Madhav','Sahu',NULL,1,'2015-04-18 08:05:56','2015-04-18 08:06:32','2015-04-24 12:23:44','0:0:0:0:0:0:0:1'),(3,'U1429358924889','sumit','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',4,3,'Sumit','Nair',NULL,1,'2015-04-18 08:08:44','2015-04-18 08:09:05','2015-04-18 08:15:06','0:0:0:0:0:0:0:1'),(4,'U1429359328051','nitin','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',5,3,'Nitin','Shetty',NULL,1,'2015-04-18 08:15:28','2015-04-18 08:15:48','2015-04-24 08:56:37','0:0:0:0:0:0:0:1'),(5,'U1429360234418','rugved','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',6,4,'Rugved','Jahagirdar',NULL,1,'2015-04-18 08:30:34','2015-04-18 08:30:55','2015-04-18 08:35:42','0:0:0:0:0:0:0:1'),(6,'U1429360278991','tushar','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',7,4,'Tushar','Kale',NULL,1,'2015-04-18 08:31:18','2015-04-18 08:32:07','2015-04-22 06:27:53','0:0:0:0:0:0:0:1'),(7,'U1429360298835','deepak','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',8,4,'Deepak','Sharma',NULL,1,'2015-04-18 08:31:38','2015-04-18 08:32:22','2015-04-18 10:08:24','0:0:0:0:0:0:0:1'),(8,'U1429360420056','suhas','827ccb0eea8a706c4c34a16891f84e7b','dandekar.v@husky.neu.edu',3,1,'Suhas','Pirankar',NULL,1,'2015-04-18 08:33:40','2015-04-18 08:33:53','2015-04-24 12:21:41','0:0:0:0:0:0:0:1');

/* Trigger structure for table `user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `insertLoginDetails` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `insertLoginDetails` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
	INSERT INTO `logindetails` (`userId`, `loginDate`, `ipAddress`)
	VALUES (new.`id`, NOW(), new.`lastAccessedFrom`);
    END */$$


DELIMITER ;

/* Procedure structure for procedure `activateAccount` */

/*!50003 DROP PROCEDURE IF EXISTS  `activateAccount` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `activateAccount`(
	iUserId int,
	iPassword varchar(50),
	iKey varchar(50),
	iIpAddr varchar(20)
    )
BEGIN
	declare oCount int;
	
	set oCount = (select count(`id`) from `user` where `id` = iUserId and `password` = iKey);
	
	if(oCount = 1) then
		update `user` set `password` = MD5(iPassword), `status` = 1, `modifiedDate` = now(), `lastLoginDate` = now(), `lastAccessedFrom` = iIpAddr
		where `id` = iUserId;
		select * from `user` where `id` = iUserId;
	else
		select * from `user` where `id` = 0;
	end if;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `addEnterprise` */

/*!50003 DROP PROCEDURE IF EXISTS  `addEnterprise` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addEnterprise`(
	iNumber varchar(15),
	iName varchar(50),
	iTypeId int,
	iCountyId int,
	iStreet varchar(50),
	iSuite varchar(15),
	iCity varchar(30),
	iState char(2),
	iZipCode char(5),
	iContact varchar(15),
	iUrl varchar(50)
    )
BEGIN
	declare id int;
	
	set id = (select count(`id`) from `enterprise` where `name` = iName and `typeId` = iTypeId);
	
	if(id = 0) then
		insert into `enterprise`(`number`, `name`, `typeId`, `countyId`, `street`, `suite`, `city`, `state`, `zipcode`, `contact`, `url`, `creationDate`) 
		values(iNumber, iName, iTypeId, iCountyId, iStreet, iSuite, iCity, iState, iZipCode, iContact, iUrl, now());
		set id = last_insert_id();		
	end if;
		
	select id;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `addOrder` */

/*!50003 DROP PROCEDURE IF EXISTS  `addOrder` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addOrder`(
	iNumber varchar(30),
	iOrderedBy int,
	iOrderedFrom int
    )
BEGIN
	declare orderId int;
	
	insert into `orders` (`number`, `orderedBy`, `orderedFrom`, `creationDate`)
	values (iNumber, iOrderedBy, iOrderedFrom, now());
	
	set orderId = last_insert_id();
	
	select * from `orders` where `id` =  orderId;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `addSurgery` */

/*!50003 DROP PROCEDURE IF EXISTS  `addSurgery` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addSurgery`(
	iSurgeryId int,
	scheduleDate date,
	roomId int
	
    )
BEGIN
	declare requestId int;
	update `surgery` set `operatingRoomId` = roomId, `scheduleDate` = scheduleDate where `id` = iSurgeryId;
	set requestId = (select `id` from `surgeryrequest` where `surgeryId` = iSurgeryId);
	update `request` set `statusId` = 2 where `id` = requestId;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `addUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `addUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `addUser`(
	iNumber varchar(15),
	iUserName varchar(30),
	iEmailId varchar(50),
	iRoleId int,
	iEnterpriseId int,
	iFirstName varchar(30),
	iLastName varchar(30)
    )
BEGIN
	declare idCount int;
	declare ikey VARCHAR(50);
	
	set idCount = (select count(id) from `user` where `username` = iUserName);
	
	if(idCount = 0) then
		insert into `user` (`number`, `username`, `password`, `emailId`, `roleId`, `enterpriseId`, `firstName`, `lastName`, `creationDate`)
		values (iNumber, iUserName, md5(iUserName), iEmailId, iRoleId, iEnterpriseId, iFirstName, iLastName, now());
		set idCount = last_insert_id();
		set ikey = MD5(iUserName);
	end if;
	
	select idCount, ikey;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `authenticateUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `authenticateUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `authenticateUser`(
	iUserName varchar(30),
	iPassword varchar(30),
	iIpAddr varchar(30)
    )
BEGIN
	declare idCount int;
	
	set idCount = (select count(`id`) from `user` where `username` = iUserName and `password` = md5(iPassword) and `status` = 1);
	
	if(idCount = 1) then
		update `user` set `lastLoginDate` = now(), `lastAccessedFrom` = iIpAddr
		where `username` = iUserName;
		select * from `user` WHERE `username` = iUserName;
	else
		select * from `user` where `username` = '';
	end if;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `createDeviceRequest` */

/*!50003 DROP PROCEDURE IF EXISTS  `createDeviceRequest` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createDeviceRequest`(
	iUserId int,
	iRequestNumber varchar(30),
	iRequestTypeId int,
	iRequestStatusId int,
	iEnterpriseId int,
	iSpecialInstruction text
    )
BEGIN
	declare oRequestId int;
	
	insert into `request`(`number`, `createdBy`, `typeId`, `statusId`, `creationDate`)
	values (iRequestNumber, iUserId, iRequestTypeId, iRequestStatusId, now());
	set oRequestId = last_insert_id();
	
	insert into `devicerequest` values (oRequestId, iEnterpriseId,iSpecialInstruction);
	
	select oRequestId;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `createSurgeryRequest` */

/*!50003 DROP PROCEDURE IF EXISTS  `createSurgeryRequest` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createSurgeryRequest`(
	iUserId int,
	iSurgeryType varchar(30),
	iPatientName varchar(50),
	iRequestNumber varchar(30),
	iRequestTypeId int,
	iRequestStatusId int,
	iDateFrom datetime,
	iDateTo datetime,
	iSpecialInstruction text,
	iStaffCount int
	
    )
BEGIN
	declare oSurgeryId int;
	declare oRequestId int;
	
	insert into `surgery` (`userId`, `type`, `patientName`, `creationDate`)
	values (iUserId, iSurgeryType, iPatientName, now());
	set oSurgeryId = last_Insert_id();
	
	insert into `request` (`number`, `createdBy`, `typeId`, `statusId`, `creationDate`)
	values (iRequestNumber, iUserId, iRequestTypeId, iRequestStatusId, now());
	set oRequestId = last_insert_id();
	
	insert into `surgeryrequest`(`id`, `surgeryId`, `dateFrom`, `dateTo`, `specialInstructions`, `staffCount`)
	values (oRequestId, oSurgeryId, iDateFrom, iDateTo, iSpecialInstruction, iStaffCount);
	
	select oRequestId;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `isDateAvailable` */

/*!50003 DROP PROCEDURE IF EXISTS  `isDateAvailable` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `isDateAvailable`(
	iEnterpriseId int,
	iDate date,
	iStaffCount int
    )
BEGIN
	declare countRooms int;
	declare totalRooms int;
	DECLARE countStaff INT;
	DECLARE totalStaff INT;
	declare result int;
	
	set totalRooms = (select count(*) from `operatingroom` where `enterpriseId` = iEnterpriseId);
	
	set countRooms = (select count(*) from `surgery` where `scheduleDate` = date(iDate) and `userId` in 
			 (select `id` from `user` where `enterpriseId` = iEnterpriseId));
	
	if(totalRooms = countRooms) then
		set result = 0;
	else
		set countStaff = (select count(*) from `surgery` s join `medicalteam` mt on s.`id` = mt.`surgeryId`
				  WHERE `scheduleDate` = DATE(iDate) AND `userId` IN 
				 (SELECT `id` FROM `user` WHERE `enterpriseId` = iEnterpriseId));
		if(countStaff <= iStaffCount) then
			set result = 0;
		else
			set result = 1;
		end if;
	end if;
	
	select result;
	
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
