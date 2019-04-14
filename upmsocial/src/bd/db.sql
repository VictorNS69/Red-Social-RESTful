-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: upmsocial
-- ------------------------------------------------------
-- Server version	5.7.25-1

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
-- Table structure for table `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuarios` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(20) NOT NULL,
  `APELLIDO1` varchar(20) NOT NULL,
  `APELLIDO2` varchar(20) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `PAIS` varchar(15),
  `TELEFONO` int(10),
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuarios`
--

LOCK TABLES `Usuarios` WRITE;
/*!40000 ALTER TABLE `Usuarios` DISABLE KEYS */;
INSERT INTO `Usuarios` VALUES (1,'Danilo','Morgera','Pérez','d.morgera@alumnos.upm.es','España',612345678),
(2,'Víctor','Nieves','Sánchez','victor.nieves.sanchez@alumnos.upm.es','España',612456358),
(3,'Raul','Prieto','Acedo','raulete@alumnos.upm.es','España',611232678),
(4,'Alejandro','Carmo','Hombre','carmoman@alumnos.upm.es','España',654345678),
(5,'Jorge','Dos','Gondor','inmortal@alumnos.upm.es','España',612123478),
(6,'Jorge','Uno','Diaz-Cano','habersi@alumnos.upm.es','España',616789678),
(7,'Bruno','Morgera','Pérez','brungie@alumnos.upm.es','España',618574978),
(8,'Chico','Morgera','Pérez','perrete@alumnos.upm.es','Perronia',617895678),
(9,'Adrian','Villar','Lopez','jajaj@alumnos.upm.es','España',617123458),
(10,'Juan','Sanchez','De La Cruz','taquito@alumnos.upm.es','Mexico',623455678),
(11,'Oscar','Mezar','Brisket','guacaguaca@alumnos.upm.es','Mexico',617856788),
(12,'Chris','Evans','Yeehaw','capiusa@alumnos.upm.es','Estados Unidos',617895678);
/*!40000 ALTER TABLE `Usuarios` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
--
-- Table structure for table `Mensajes_muro`
--

DROP TABLE IF EXISTS `Mensajes_muro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mensajes_muro` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` int NOT NULL,
  `CUERPO_MENSAJE` varchar(300) NOT NULL,
  `FECHA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID_USUARIO) REFERENCES Usuarios (ID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mensajes_muro`
--

LOCK TABLES `Mensajes_muro` WRITE;
/*!40000 ALTER TABLE `Mensajes_muro` DISABLE KEYS */;
INSERT INTO `Mensajes_muro` VALUES
(1,2,'Este será el primer mensaje de Victor',CURRENT_TIMESTAMP),
(2,2,'Este será el segundo mensaje de Victor',CURRENT_TIMESTAMP),
(3,1,'Este será el primer mensaje de Danilo',CURRENT_TIMESTAMP),
(4,1,'Este será el segundo mensaje de Danilo',CURRENT_TIMESTAMP),
(5,3,'Este será el primer mensaje de Raul',CURRENT_TIMESTAMP),
(6,3,'Este será el segundo mensaje de Raul',CURRENT_TIMESTAMP),
(7,4,'Este será el primer mensaje de Alejandro',CURRENT_TIMESTAMP),
(8,4,'Este será el segundo mensaje de Alejandro',CURRENT_TIMESTAMP),
(9,5,'Este será el primer mensaje de Jorge2',CURRENT_TIMESTAMP),
(10,5,'Este será el segundo mensaje de Jorge2',CURRENT_TIMESTAMP),
(11,6,'Este será el primer mensaje de Jorge1',CURRENT_TIMESTAMP),
(12,6,'Este será el segundo mensaje de Jorge1',CURRENT_TIMESTAMP),
(13,7,'Este será el primer mensaje de Bruno',CURRENT_TIMESTAMP),
(14,7,'Este será el segundo mensaje de Bruno',CURRENT_TIMESTAMP),
(15,8,'Este será el primer guau de Chico',CURRENT_TIMESTAMP),
(16,8,'Este será el segundo guau de Chico',CURRENT_TIMESTAMP),
(17,9,'Este será el primer mensaje de Adrian',CURRENT_TIMESTAMP),
(18,9,'Este será el segundo mensaje de Adrian',CURRENT_TIMESTAMP),
(19,10,'Este será el primer mensaje de Juan',CURRENT_TIMESTAMP),
(20,10,'Este será el segundo mensaje de Juan',CURRENT_TIMESTAMP),
(21,11,'Este será el primer mensaje de Oscar',CURRENT_TIMESTAMP),
(22,11,'Este será el segundo mensaje de Oscar',CURRENT_TIMESTAMP),
(23,12,'Este será el primer mensaje de Chris',CURRENT_TIMESTAMP),
(24,12,'Este será el segundo mensaje de Chris',CURRENT_TIMESTAMP);
/*!40000 ALTER TABLE `Mensajes_muro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Mensajes_privados`
--

DROP TABLE IF EXISTS `Mensajes_privados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Mensajes_privados` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_ORIGEN` int NOT NULL,
  `ID_DESTINO` int NOT NULL,
  `CUERPO_MENSAJE` varchar(300) NOT NULL,
  `FECHA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (ID_ORIGEN) REFERENCES Usuarios (ID) ON DELETE CASCADE,
  FOREIGN KEY (ID_DESTINO) REFERENCES Usuarios (ID) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Mensajes_privados`
--

LOCK TABLES `Mensajes_privados` WRITE;
/*!40000 ALTER TABLE `Mensajes_privados` DISABLE KEYS */;
INSERT INTO `Mensajes_privados` VALUES
(1,2,1,'Este será el primer mensaje privado de Victor a Danilo',CURRENT_TIMESTAMP),
(2,1,2,'Este será el primer mensaje privado de Danilo a Victor',CURRENT_TIMESTAMP),
(3,2,3,'Este será el primer mensaje privado de Victor a Raul',CURRENT_TIMESTAMP),
(4,4,7,'Este será el primer mensaje privado de Alejandro a Bruno',CURRENT_TIMESTAMP),
(5,1,5,'Este será el primer mensaje privado de Danilo a Jorge2',CURRENT_TIMESTAMP),
(6,8,2,'Este será el primer guau privado de Chico a Victor',CURRENT_TIMESTAMP),
(7,5,1,'Este será el primer mensaje privado de Jorge2 a Danilo',CURRENT_TIMESTAMP),
(8,3,2,'Este será el primer mensaje privado de Raul a Victor',CURRENT_TIMESTAMP);
/*!40000 ALTER TABLE `Mensajes_privados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relaciones_amistad`
--

DROP TABLE IF EXISTS `Relaciones_amistad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Relaciones_amistad` (
  `ID_AMIGO1` int NOT NULL,
  `ID_AMIGO2` int NOT NULL,
  PRIMARY KEY (ID_AMIGO1, ID_AMIGO2),
  CONSTRAINT FOREIGN KEY (ID_AMIGO1) REFERENCES Usuarios (ID) ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (ID_AMIGO2) REFERENCES Usuarios (ID) ON DELETE CASCADE,
  UNIQUE KEY (ID_AMIGO1, ID_AMIGO2),
  UNIQUE KEY (ID_AMIGO2, ID_AMIGO1)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relaciones_amistad`
--

LOCK TABLES `Relaciones_amistad` WRITE;
/*!40000 ALTER TABLE `Relaciones_amistad` DISABLE KEYS */;
INSERT INTO `Relaciones_amistad` VALUES
(1,2),
(4,2),
(1,3),
(6,8),
(7,5),
(4,1),
(3,4),
(5,9),
(10,1),
(12,3),
(12,11),
(7,8),
(7,9),
(3,4),
(4,9),
(1,12),
(5,6),
(6,8);
/*!40000 ALTER TABLE `Relaciones_amistad` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-02 14:52:56
