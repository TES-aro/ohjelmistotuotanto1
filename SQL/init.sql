CREATE DATABASE  IF NOT EXISTS `ot1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ot1`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ot1
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asiakkaat`
--

DROP TABLE IF EXISTS `asiakkaat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asiakkaat` (
  `asiakas_id` int NOT NULL AUTO_INCREMENT,
  `etunimi` varchar(255) NOT NULL,
  `sukunimi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `puhelin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`asiakas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asiakkaat`
--

LOCK TABLES `asiakkaat` WRITE;
/*!40000 ALTER TABLE `asiakkaat` DISABLE KEYS */;
INSERT INTO `asiakkaat` VALUES (1,'test','testinen','test@test.com','+358123456'),(2,'test2','testinen2','test2@test.com','+3581233456');
/*!40000 ALTER TABLE `asiakkaat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laskut`
--

DROP TABLE IF EXISTS `laskut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laskut` (
  `lasku_id` int NOT NULL AUTO_INCREMENT,
  `luonti_pvm` date NOT NULL,
  `erapaiva` date NOT NULL,
  `summa` decimal(10,2) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `varaus_id` int DEFAULT NULL,
  PRIMARY KEY (`lasku_id`),
  KEY `varaus_id` (`varaus_id`),
  CONSTRAINT `laskut_ibfk_1` FOREIGN KEY (`varaus_id`) REFERENCES `varaukset` (`varaus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laskut`
--

LOCK TABLES `laskut` WRITE;
/*!40000 ALTER TABLE `laskut` DISABLE KEYS */;
/*!40000 ALTER TABLE `laskut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mokit`
--

DROP TABLE IF EXISTS `mokit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mokit` (
  `mokki_id` int NOT NULL AUTO_INCREMENT,
  `kapasiteetti` int NOT NULL,
  `hinta_per_yo` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`mokki_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mokit`
--

LOCK TABLES `mokit` WRITE;
/*!40000 ALTER TABLE `mokit` DISABLE KEYS */;
INSERT INTO `mokit` VALUES (1,3,100.00);
/*!40000 ALTER TABLE `mokit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varaukset`
--

DROP TABLE IF EXISTS `varaukset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `varaukset` (
  `varaus_id` int NOT NULL AUTO_INCREMENT,
  `alku_pvm` date NOT NULL DEFAULT (curdate()),
  `loppu_pvm` date NOT NULL,
  `hinta_per_yo` decimal(8,2) NOT NULL,
  `asiakas_id` int DEFAULT NULL,
  `mokki_id` int DEFAULT NULL,
  PRIMARY KEY (`varaus_id`),
  KEY `asiakas_id` (`asiakas_id`),
  KEY `mokki_id` (`mokki_id`),
  CONSTRAINT `varaukset_ibfk_1` FOREIGN KEY (`asiakas_id`) REFERENCES `asiakkaat` (`asiakas_id`),
  CONSTRAINT `varaukset_ibfk_2` FOREIGN KEY (`mokki_id`) REFERENCES `mokit` (`mokki_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varaukset`
--

LOCK TABLES `varaukset` WRITE;
/*!40000 ALTER TABLE `varaukset` DISABLE KEYS */;
INSERT INTO `varaukset` VALUES (1,'2026-05-11','2026-05-14',300.00,1,1);
/*!40000 ALTER TABLE `varaukset` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-13 12:06:44
