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
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `carSeries` varchar(255) NOT NULL DEFAULT '',
  `model` varchar(255) NOT NULL DEFAULT '',
  `color` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `VehicleStyle` (`carSeries`,`model`,`color`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VehicleStyle`
--

LOCK TABLES `VehicleStyle` WRITE;
/*!40000 ALTER TABLE `VehicleStyle` DISABLE KEYS */;
INSERT INTO `VehicleStyle` VALUES (3,'傲虎','2015款 2.5i 经典版','古铜香槟金'),(2,'傲虎','2015款 2.5i 经典版','淡紫金属灰'),(1,'傲虎','2015款 2.5i 经典版','绸缎珍珠白');
/*!40000 ALTER TABLE `VehicleStyle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
   `id` int(10) NOT NULL AUTO_INCREMENT,
  `tel` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT '',
  `occupation` varchar(255) NOT NULL DEFAULT '',
  `identityCard` varchar(255) NOT NULL DEFAULT '',
  `birthday` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `introducerType` varchar(255) NOT NULL DEFAULT '',
  `introducer` varchar(255) NOT NULL DEFAULT '',
  `note` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tel` (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'5201213','张三','1','1','2013-01-04','laixiaohang@sina.com','1','1','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `tel` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `identificationCard` varchar(255) NOT NULL DEFAULT '',
  `birthday` varchar(255) NOT NULL DEFAULT '',
  `status` varchar(255) NOT NULL DEFAULT '',
  `add` varchar(255) NOT NULL DEFAULT '',
  `position` varchar(255) NOT NULL DEFAULT '',
  `store` varchar(255) NOT NULL DEFAULT '',
  `totalLose` varchar(255) NOT NULL DEFAULT '',
  `thisMonthLose` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tel` (`tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'lala','18695690001','aaa','333','111','1','1','1','2','0','0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
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
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customerTel`varchar(255) NOT NULL,
  `intentionVehicleStyleId` int(10) NOT NULL,
  `employeeTel`varchar(255) NOT NULL,
  `price` float NOT NULL,
  `discount` varchar(255) NOT NULL,
  `expectedDisCount` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL DEFAULT '',
  `visitTime` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
INSERT INTO `visit` VALUES (1,"5201314",1,"18695690001",20.5,"1-0.5","1-0.7","hello",'2015-09-17 23:48'),(2,"5201314",1,"18695690001",19.5,"1-0.5","1-0.7","hello",'2015-09-18 23:48');
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-17 23:05:40

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderDate` varchar(255) NOT NULL,
  `customerTel` varchar(255) NOT NULL,
  `vehicleStyleId` int(10) NOT NULL,
  `vehicleIdentificationNumber` varchar(255) NOT NULL,
  `price` float(10) NOT NULL,
  `invoicePrice` float(10) NOT NULL,
  `payment` varchar(255) NOT NULL, /* 付款情况  */
  `discount` varchar(255) NOT NULL DEFAULT '',
  `purchaseQuantity` int(10) NOT NULL,
  `employeeTel` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `order` (`orderDate`, `customerTel`,`vehicleStyleId`,`vehicleIdentificationNumber`,`price`,`invoicePrice`,`payment`,
`discount`,`purchaseQuantity`,`employeeTel`) values ("2015-10-05", "5201314",1,"abcdefg99999",20.3,19.3,"1-建设银行-10.0","1-0.5",2,"18695690001");

INSERT INTO `order` (`orderDate`, `customerTel`,`vehicleStyleId`,`vehicleIdentificationNumber`,`price`,`invoicePrice`,`payment`,
`discount`,`purchaseQuantity`,`employeeTel`) values ("2015-10-05", "5201314",1,"dasdasd",20.3,19.3,"1-建设银行-10.0","1-0.7",2,"18695690001");

