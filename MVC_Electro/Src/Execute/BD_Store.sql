-- MySQL dump 10.11
--
-- Host: localhost    Database: bd_store
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `categorias` (
  `CategoriaID` int(11) NOT NULL,
  `NombreCategoria` varchar(30) NOT NULL,
  `Descripcion` longtext,
  PRIMARY KEY  (`CategoriaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Discos Duros',NULL),(2,'Procesadores',NULL),(3,'Laptos',NULL),(4,'Targetas Graficas',NULL),(5,'Ram',NULL),(6,'Software',NULL),(7,'Inalambrico',NULL),(8,'Redes',NULL);
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `ClienteID` int(11) NOT NULL,
  `NombreCliente` varchar(20) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Facebook` varchar(30) default NULL,
  PRIMARY KEY  (`ClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Daniel Esperanza','01 De Enero','442 345 12 32 80','Daniel@hotmail.com'),(2,'Jorge Ezequiel','23 De Marzo','442 345 12 32 81','Jorge@hotmail.com'),(3,'Maria Gonzales','1 De Enero','442 345 12 32 82','Maria@hotmail.com'),(6,'Ingrid Azuares Rios','19 De Enero','442 345 12 32 85','Ingrid@hotmail.com'),(7,'Adriana Fonceca','07 De Enero','442 345 12 32 86','Adriana@hotmail.com'),(8,'Hilda Perez','01 De Octubre','442 345 12 32 87','Hilda@hotmail.com'),(9,'Jorge Mendez Campos','Francisco I Madero','444 78 90 43 21','Francisco@Gmail.com'),(10,'Maria Perez Lopez','12 De Octubre','778 90 21 34 21','Maria@hotmail.com'),(11,'Rocio Duran Alvarez','Calle Puebla Num #70','445 67 89 43 21 ','RocioDuran@hotmail.com'),(12,'Matias','5 de Mayo','778 34 65 44','Matias@hotmail.com'),(14,'Daniel Cervantez','12 De Octubre','556 78 90 43 21 11','Daniel@hotmail.com');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleorden`
--

DROP TABLE IF EXISTS `detalleorden`;
CREATE TABLE `detalleorden` (
  `OrdenID` int(11) NOT NULL,
  `ProductoID` int(11) NOT NULL,
  `Precio` double NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Descontinuado` tinyint(1) default NULL,
  KEY `OrdenID` (`OrdenID`),
  KEY `ProductoID` (`ProductoID`),
  CONSTRAINT `detalleorden_ibfk_1` FOREIGN KEY (`OrdenID`) REFERENCES `ordenes` (`OrdenId`),
  CONSTRAINT `detalleorden_ibfk_2` FOREIGN KEY (`ProductoID`) REFERENCES `productos` (`ProductoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalleorden`
--

LOCK TABLES `detalleorden` WRITE;
/*!40000 ALTER TABLE `detalleorden` DISABLE KEYS */;
INSERT INTO `detalleorden` VALUES (1,3,89.99,12,0),(2,2,45,33,0),(3,5,77,78,0),(4,8,105,1,0),(5,2,200,6,0),(6,4,1000,78,0),(7,2,200,100,0),(8,1,570,250,0);
/*!40000 ALTER TABLE `detalleorden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `EmpleadoID` int(11) NOT NULL auto_increment,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `Edad` int(11) NOT NULL,
  `Puesto` varchar(40) NOT NULL,
  `Direccion` varchar(40) NOT NULL,
  `Telefono` varchar(20) default NULL,
  `Facebook` varchar(40) default NULL,
  `Casado` tinyint(1) NOT NULL,
  PRIMARY KEY  (`EmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'Andres','Alvarez',18,'Cajero','20 De Agosto','443 123 45 76 31','Alvarez@hotmail.com',0),(2,'Alberto','Casares',23,'Gerente','21 De Febrero','443 123 45 76 32','Antonio@hotmail.com',0),(3,'Julia','Torres',44,'Gerente','22 De Febrero','443 123 45 76 33','Felipa@hotmail.com',1),(4,'Dalia','Zabala',50,'Cajero','23 De Abril','443 123 45 76 34','Jasmin@hotmail.com',0),(5,'Alexis','Derbez',18,'Cajero','24 De Enero','443 123 45 76 35','Alexis@hotmail.com',1),(6,'Joe','Catalan',25,'Cajero','25 De Febrero','443 123 45 76 36','Joe@hotmail.com',0),(7,'Alex','Mercado',30,'Cajero','26 De Mayo','443 123 45 76 37','Alex@hotmail.com',1),(8,'Melani','Zamudio',22,'Cajero','27 De Febrero','443 123 45 76 38','Melani@hotmail.com',0);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes`
--

DROP TABLE IF EXISTS `ordenes`;
CREATE TABLE `ordenes` (
  `OrdenId` int(11) NOT NULL,
  `EmpleadoID` int(11) NOT NULL,
  `ClienteID` int(11) NOT NULL,
  `FechaOrden` datetime NOT NULL,
  `DireccionPedido` varchar(40) NOT NULL,
  `CiudadPedido` varchar(40) default NULL,
  PRIMARY KEY  (`OrdenId`),
  KEY `EmpleadoID` (`EmpleadoID`),
  KEY `ClienteID` (`ClienteID`),
  CONSTRAINT `ordenes_ibfk_1` FOREIGN KEY (`EmpleadoID`) REFERENCES `empleados` (`EmpleadoID`),
  CONSTRAINT `ordenes_ibfk_2` FOREIGN KEY (`ClienteID`) REFERENCES `clientes` (`ClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordenes`
--

LOCK TABLES `ordenes` WRITE;
/*!40000 ALTER TABLE `ordenes` DISABLE KEYS */;
INSERT INTO `ordenes` VALUES (1,5,7,'2013-12-03 21:06:52','1 De Marzo','Morelia'),(2,1,3,'2013-12-03 21:06:52','1 De Octubre','Moroleon'),(3,7,7,'2013-12-03 21:06:52','Lazaro Cardenas','Yuriria'),(4,3,8,'2013-12-03 21:06:52','1 De Marzo','Uriangato'),(5,8,3,'2013-12-03 21:06:52','1 De Marzo','Morelia'),(6,2,6,'2013-12-03 21:06:52','Francisco I Madero','Yuriria'),(7,8,7,'2013-12-03 21:06:52','1 De Marzo','Morelia'),(8,1,1,'2013-12-03 21:06:52','12 De Octubre','	Uriangato');
/*!40000 ALTER TABLE `ordenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `ProductoID` int(11) NOT NULL,
  `CategoriaID` int(11) NOT NULL,
  `ProveedorID` int(11) NOT NULL,
  `NombreProducto` varchar(30) NOT NULL,
  `Precio` double NOT NULL,
  `UnidadesEnAlmacen` int(11) NOT NULL,
  `Descontinuado` tinyint(1) NOT NULL,
  PRIMARY KEY  (`ProductoID`),
  KEY `CategoriaID` (`CategoriaID`),
  KEY `ProveedorID` (`ProveedorID`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`CategoriaID`) REFERENCES `categorias` (`CategoriaID`),
  CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`ProveedorID`) REFERENCES `proveedores` (`ProveedorID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,4,'HDD-Kingston',1300,78,0),(2,1,6,'SSD-Kingston',2300,100,0),(3,1,7,'HDD-San Disk',3300,34,0),(4,1,3,'SSD-San Disk',4000,12,0),(5,2,1,'I7-Intel',1800,78,0),(6,2,4,'CoreDuo-AMD',1450,65,0),(7,4,8,'Pantalla Led',1399,42,0),(8,3,4,'Bocinas',270,107,0);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `ProveedorID` int(11) NOT NULL auto_increment,
  `NombreEmpresa` varchar(30) NOT NULL,
  `NombreContacto` varchar(40) NOT NULL,
  `Direccion` varchar(20) NOT NULL,
  `Ciudad` varchar(20) NOT NULL,
  `Facebook` varchar(20) NOT NULL,
  `Telefono` varchar(20) default NULL,
  PRIMARY KEY  (`ProveedorID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'Intel','Jorge Esperanza','05  De Mayo','Moroleon','Jorge@hotmail.com','445 671 89 43 21'),(2,'Intel','Daniel Sanchez','12 De Febrero','Uriangato','Daniel@hotmail.com','445 665 77 43 22'),(3,'AMD','Gabriela Torres','8 De Octubre','Uriangato','Gabriela@hotmail.com','445 665 77 43 23'),(4,'HP','Andres Cornejo','1 De Enero','Moroleon','Andres@hotmail.com','445 665 77 43 24'),(5,'Toshiba','Lusia Cortez','24 De Diciembre','Uriangato','Luisa@hotmail.com','445 665 77 43 25'),(6,'GateWay','Francisco Fernandez','01 De Febrero','Uriangato','Franciso@hotmail.com','445 665 77 43 26'),(7,'Apple','Tomas Tinoco','5 De Junio','Moroleon','Tomas@hotmail.com','445 665 77 43 27'),(8,'San Disk','Alberto Sierra','12 De Febrero','Uriangato','Alberto@hotmail.com','445 665 77 43 28'),(9,'Acer','Alfredo Ruiz','5 De Mayo','Moroleon','Alfredo@hotmail.com','445 67 89 43 66');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-11 19:10:38
