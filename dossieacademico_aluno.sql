-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: dossieacademico
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
  `ID` smallint unsigned NOT NULL,
  `NOME` varchar(30) NOT NULL,
  `SOBRENOME` varchar(30) NOT NULL,
  `codigo` bigint unsigned NOT NULL,
  `teste_1` float(3,1) DEFAULT NULL,
  `teste_2` float(3,1) DEFAULT NULL,
  `teste_3` float(3,1) DEFAULT NULL,
  `media` float(3,1) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `aluno_ibfk_1` (`ID`),
  CONSTRAINT `aluno_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `turma` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (32,'Jose','Manuel',202456,20.0,20.0,0.0,14.0),(28,'EDSON','PAULO',2020111,12.3,12.0,12.5,12.3),(28,'MANUEL ','TOME',2020134,14.0,12.0,18.0,14.8),(29,'MARILIA','MANUELA',2020135,18.5,14.0,17.0,16.2),(29,'AMAD','ISSA',2020192,16.0,13.5,16.0,15.0),(28,'JOAO','PEDRO',2020356,12.0,13.0,16.0,13.4),(28,'BRUNO','RODRIGUES',2020432,14.0,15.0,16.0,14.8),(29,'MANUEL','ROCHA',2020555,15.5,19.5,20.0,18.4);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-10 18:45:52
