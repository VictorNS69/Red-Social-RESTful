-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: upmsocial
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `Mensajes_muro`
--

DROP TABLE IF EXISTS `Mensajes_muro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mensajes_muro` (
  `ID` int(4) DEFAULT NULL,
  `ID_USUARIO` int(4) DEFAULT NULL,
  `CUERPO_MENSAJE` varchar(140) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mensajes_muro`
--

LOCK TABLES `Mensajes_muro` WRITE;
/*!40000 ALTER TABLE `Mensajes_muro` DISABLE KEYS */;
/*!40000 ALTER TABLE `Mensajes_muro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mensajes_privados`
--

DROP TABLE IF EXISTS `Mensajes_privados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mensajes_privados` (
  `ID` int(4) DEFAULT NULL,
  `ID_ORIGEN` int(4) DEFAULT NULL,
  `ID_DESTINO` int(4) DEFAULT NULL,
  `CUERPO_MENSAJE` varchar(140) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mensajes_privados`
--

LOCK TABLES `Mensajes_privados` WRITE;
/*!40000 ALTER TABLE `Mensajes_privados` DISABLE KEYS */;
/*!40000 ALTER TABLE `Mensajes_privados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relaciones_amistad`
--

DROP TABLE IF EXISTS `Relaciones_amistad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Relaciones_amistad` (
  `ID_AMIGO1` int(4) DEFAULT NULL,
  `ID_AMIGO2` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relaciones_amistad`
--

LOCK TABLES `Relaciones_amistad` WRITE;
/*!40000 ALTER TABLE `Relaciones_amistad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Relaciones_amistad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuarios` (
  `ID` int(4) DEFAULT NULL,
  `NOMBRE` varchar(15) DEFAULT NULL,
  `APELLIDO1` varchar(15) DEFAULT NULL,
  `APELLIDO2` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `PAIS` varchar(15) DEFAULT NULL,
  `TELEFONO` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
INSERT INTO `Usuarios` VALUES (1234,'Danilo','Morgera','Perez','d.morgera@alumnos.upm.es','España',612345678);
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-27 12:10:12