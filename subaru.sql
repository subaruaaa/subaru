-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: subaru
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `VehicleStyle`
--

DROP TABLE IF EXISTS `VehicleStyle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VehicleStyle` (
  `vehicleStyleId` int(10) NOT NULL AUTO_INCREMENT,
  `carSeries` varchar(255) NOT NULL DEFAULT '',
  `model` varchar(255) NOT NULL DEFAULT '',
  `color` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`vehicleStyleId`),
  UNIQUE KEY `VehicleStyle` (`carSeries`,`model`,`color`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VehicleStyle`
--

LOCK TABLES `VehicleStyle` WRITE;
/*!40000 ALTER TABLE `VehicleStyle` DISABLE KEYS */;
INSERT INTO `VehicleStyle` VALUES (3,'傲虎','2015款 2.5i 经典版','古铜香槟金'),(2,'傲虎','2015款 2.5i 经典版','淡紫金属灰'),(1,'傲虎','2015款 2.5i 经典版','绸缎珍珠白'),(4,'未知','未知','未知');
/*!40000 ALTER TABLE `VehicleStyle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` int(10) NOT NULL AUTO_INCREMENT,
  `customerTel` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `occupation` varchar(255) NOT NULL DEFAULT '',
  `identityCard` varchar(255) NOT NULL DEFAULT '',
  `birthday` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `introducerType` varchar(255) NOT NULL DEFAULT '',
  `introducer` varchar(255) NOT NULL DEFAULT '',
  `note` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `tel` (`customerTel`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'5221318','张三','1','1','2013-01-04','laixiaohang@sina.com','1','1',''),(4,'5201316','张四','1','1','2013-01-04','laixiaohang@sina.com','1','1',''),(6,'5201317','张四','1','1','2013-01-04','laixiaohang@sina.com','1','1',''),(7,'5201318','张四','1','1','2013-01-04','laixiaohang@sina.com','1','1',''),(8,'1580602333','晨曦中','1','2','2015-10-13','萨芬的','4','',''),(9,'5201313','张四','1','1','2013-01-04','laixiaohang@sina.com','1','王大锤',''),(10,'5201310','张四','1','1','2013-01-04','laixiaohang@sina.com','1','1',''),(11,'5201410','张四','1','1','2013-01-04','laixiaohang@sina.com','1','1','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeId` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `employeeTel` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL DEFAULT '',
  `identificationCard` varchar(255) NOT NULL DEFAULT '',
  `birthday` varchar(255) NOT NULL DEFAULT '',
  `statusId` int(10) NOT NULL DEFAULT '-1',
  `add` varchar(255) NOT NULL DEFAULT '',
  `positionId` varchar(255) NOT NULL DEFAULT '0',
  `storeId` varchar(255) NOT NULL DEFAULT '0',
  `totalLose` varchar(255) NOT NULL DEFAULT '0',
  `thisMonthLose` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`employeeId`),
  UNIQUE KEY `tel` (`employeeTel`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (7,'la','18695690001','laixiaohang@sina.com','333','2014-10-30',1,'1','-1','-1','0','0'),(12,'la','18695690007','aaa','333','111',1,'1','1','2','0','0'),(13,'la','18695690003','aaa','333','111',1,'1','1','2','0','0'),(15,'la','18695690004','aaa','333','111',1,'1','1','2','0','0'),(17,'la','18695690005','aaa','333','111',1,'1','1','2','0','0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `introducerType`
--

DROP TABLE IF EXISTS `introducerType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `introducerType` (
  `introducerTypeId` int(10) NOT NULL AUTO_INCREMENT,
  `introducerType` varchar(255) NOT NULL,
  PRIMARY KEY (`introducerTypeId`),
  UNIQUE KEY `introducerType` (`introducerType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `introducerType`
--

LOCK TABLES `introducerType` WRITE;
/*!40000 ALTER TABLE `introducerType` DISABLE KEYS */;
INSERT INTO `introducerType` VALUES (4,'企业高管'),(5,'员工亲戚'),(3,'政府高管'),(6,'无');
/*!40000 ALTER TABLE `introducerType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderId` int(10) NOT NULL AUTO_INCREMENT,
  `orderDate` varchar(255) NOT NULL,
  `customerTel` varchar(255) NOT NULL,
  `vehicleStyleId` int(10) NOT NULL,
  `vehicleIdentificationNumber` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `invoicePrice` float NOT NULL,
  `payment` varchar(255) NOT NULL,
  `discount` varchar(255) NOT NULL DEFAULT '',
  `purchaseQuantity` int(10) NOT NULL,
  `employeeTel` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (3,'2015-10-05','5201314',1,'abcdefg99999',20.3,19.3,'1-建设银行-10.0','1-0.5',2,'18695690001'),(4,'2015-10-06','5201314',1,'1222111',20.5,18,'1-建設銀行-10.0','1-0.5',1,'18695690001'),(5,'2015-10-06','5201314',1,'111111',20.5,18,'1-建設銀行-10.0','1-0.5',1,'18695690001'),(6,'2015-10-06','5201314',1,'111111',20.5,18,'1-建設銀行-10.0','1-0.5',1,'18695690001');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwd`
--

DROP TABLE IF EXISTS `passwd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passwd` (
  `employeeTel` varchar(255) NOT NULL DEFAULT '',
  `md5Passwd` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`employeeTel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwd`
--

LOCK TABLES `passwd` WRITE;
/*!40000 ALTER TABLE `passwd` DISABLE KEYS */;
INSERT INTO `passwd` VALUES ('18695690001','e510a9aa29d2204f53bfe7e1ec96ccb4');
/*!40000 ALTER TABLE `passwd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `positionId` int(10) NOT NULL AUTO_INCREMENT,
  `position` varchar(255) NOT NULL,
  PRIMARY KEY (`positionId`),
  UNIQUE KEY `position` (`position`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (6,'后勤保障'),(9,'未知'),(7,'财务'),(8,'财务经理'),(4,'销售主管'),(3,'销售经理');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `storeId` int(10) NOT NULL AUTO_INCREMENT,
  `store` varchar(255) NOT NULL,
  PRIMARY KEY (`storeId`),
  UNIQUE KEY `store` (`store`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (3,'厦门店'),(9,'未知'),(6,'泉州店'),(5,'海沧总部'),(7,'漳州店'),(8,'福州店'),(4,'集美店');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit` (
  `visitId` int(10) NOT NULL AUTO_INCREMENT,
  `customerId` varchar(255) NOT NULL,
  `intentionVehicleStyleId` int(10) NOT NULL,
  `employeeId` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `discountId` varchar(255) NOT NULL,
  `expectedDisCountId` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL DEFAULT '',
  `visitTime` varchar(255) NOT NULL DEFAULT '',
  `quota` varchar(255) NOT NULL,
  `expectedQuota` varchar(255) NOT NULL,
  PRIMARY KEY (`visitId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
INSERT INTO `visit` VALUES (22,'1',1,'7',20.6,'1','1','111','2015-10-16 06:57:44','0.3','0.5'),(23,'1',1,'7',20.5,'1','1','bbb','2015-10-16 06:55:58','0.3','0.5'),(25,'1',1,'7',20.5,'1','1','bbb','2015-10-16 07:06:49','0.3','0.5');
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-18  9:20:40
