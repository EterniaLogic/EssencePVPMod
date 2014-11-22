-- MySQL dump 10.13  Distrib 5.6.20, for osx10.7 (x86_64)
--
-- Host: localhost    Database: essencepvp
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `Ability`
--

DROP TABLE IF EXISTS `Ability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ability` (
  `ID` int(11) DEFAULT NULL,
  `Icon` varchar(250) DEFAULT NULL,
  `Profession` int(11) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `Name` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ability`
--

LOCK TABLES `Ability` WRITE;
/*!40000 ALTER TABLE `Ability` DISABLE KEYS */;
INSERT INTO `Ability` VALUES (1,'s',NULL,'s2','S');
/*!40000 ALTER TABLE `Ability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ability_Property`
--

DROP TABLE IF EXISTS `Ability_Property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ability_Property` (
  `ID` int(11) DEFAULT NULL,
  `Value` int(11) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `Name` varchar(250) DEFAULT NULL,
  `Ability` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ability_Property`
--

LOCK TABLES `Ability_Property` WRITE;
/*!40000 ALTER TABLE `Ability_Property` DISABLE KEYS */;
/*!40000 ALTER TABLE `Ability_Property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Characters`
--

DROP TABLE IF EXISTS `Characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Characters` (
  `ID` int(11) DEFAULT NULL,
  `PlayerUID` varchar(250) DEFAULT NULL,
  `ClassAbilities` int(11) DEFAULT NULL,
  `PlayerName` varchar(250) DEFAULT NULL,
  `FactionID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Characters`
--

LOCK TABLES `Characters` WRITE;
/*!40000 ALTER TABLE `Characters` DISABLE KEYS */;
/*!40000 ALTER TABLE `Characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Factions`
--

DROP TABLE IF EXISTS `Factions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Factions` (
  `Leader` varchar(250) DEFAULT NULL,
  `ID` int(11) DEFAULT NULL,
  `Grade` int(11) DEFAULT NULL,
  `RegionsID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Factions`
--

LOCK TABLES `Factions` WRITE;
/*!40000 ALTER TABLE `Factions` DISABLE KEYS */;
/*!40000 ALTER TABLE `Factions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Profession`
--

DROP TABLE IF EXISTS `Profession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Profession` (
  `ID` int(11) DEFAULT NULL,
  `Icon` varchar(250) DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  `Name` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Profession`
--

LOCK TABLES `Profession` WRITE;
/*!40000 ALTER TABLE `Profession` DISABLE KEYS */;
/*!40000 ALTER TABLE `Profession` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-21 21:34:06
