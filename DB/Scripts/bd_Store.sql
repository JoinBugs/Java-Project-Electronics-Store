DROP DATABASE IF EXISTS bd_Store;
CREATE DATABASE bd_Store;

USE bd_Store;

CREATE TABLE Proveedores(ProveedorID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, NombreEmpresa VARCHAR(30) NOT NULL,
NombreContacto VARCHAR(40) NOT NULL, Direccion VARCHAR(20) NOT NULL, Ciudad VARCHAR(20) NOT NULL,
Facebook VARCHAR(20) NOT NULL, Telefono VARCHAR(20) NULL);

CREATE TABLE Empleados(EmpleadoID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(20) NOT NULL,
Apellido VARCHAR(20) NOT NULL, Contrasena VARCHAR(50) NOT NULL, Edad INT NOT NULL, Puesto VARCHAR(40) NOT NULL, 
Direccion VARCHAR(40) NOT NULL, Telefono VARCHAR(20) NULL, Facebook VARCHAR(40) NULL,
Casado TINYINT(1) NOT NULL);

CREATE TABLE Clientes(ClienteID INT NOT NULL PRIMARY KEY, NombreCliente VARCHAR(20) NOT NULL,
Direccion VARCHAR(30) NOT NULL, Telefono VARCHAR(20) NOT NULL, Facebook VARCHAR(30) NULL);

CREATE TABLE Categorias(CategoriaID INT NOT NULL PRIMARY KEY, NombreCategoria VARCHAR(30) NOT NULL,
Descripcion LONGTEXT NULL); 

CREATE TABLE Productos(ProductoID INT NOT NULL PRIMARY KEY, CategoriaID INT NOT NULL, FOREIGN KEY(CategoriaID) REFERENCES Categorias(CategoriaID), 
ProveedorID INT NOT NULL, FOREIGN KEY(ProveedorID) REFERENCES Proveedores(ProveedorID), NombreProducto VARCHAR(30) NOT NULL,
Precio DOUBLE NOT NULL, UnidadesEnAlmacen INT NOT NULL, Descontinuado TINYINT(1) NOT NULL);

CREATE TABLE Ordenes(OrdenId INT NOT NULL PRIMARY KEY, EmpleadoID INT NOT NULL, FOREIGN KEY(EmpleadoID) REFERENCES Empleados(EmpleadoID),
ClienteID INT NOT NULL, FOREIGN KEY(ClienteID) REFERENCES Clientes(ClienteID), FechaOrden DATETIME NOT NULL, DireccionPedido VARCHAR(40) NOT NULL, 
CiudadPedido VARCHAR(40));

CREATE TABLE DetalleOrden(OrdenID INT NOT NULL, FOREIGN KEY(OrdenID) REFERENCES Ordenes(OrdenId), ProductoID INT NOT NULL,
FOREIGN KEY(ProductoID) REFERENCES Productos(ProductoID), Precio DOUBLE NOT NULL, Cantidad INT NOT NULL, Descontinuado TINYINT(1));


INSERT INTO proveedores VALUES(1, 'Intel',	'Jorge Esperanza',	  '05  De Mayo',	'Moroleon',  'Jorge@hotmail.com',	'445 671 89 43 21'),
							  (2, 'Intel',	'Daniel Sanchez',	  '12 De Febrero',	'Uriangato', 'Daniel@hotmail.com',	'445 665 77 43 22'),
							  (3, 'AMD',	'Gabriela Torres',	  '8 De Octubre',	'Uriangato', 'Gabriela@hotmail.com','445 665 77 43 23'),
                              (4, 'HP',     'Andres Cornejo',	  '1 De Enero',		'Moroleaon', 'Andres@hotmail.com',	'445 665 77 43 24'),
							  (5, 'Toshiba','Lusia Cortez',		  '24 De Diciembre','Uriangato', 'Luisa@hotmail.com',	'445 665 77 43 25'),
							  (6, 'GateWay','Francisco Fernandez','01 De Febrero',	'Uriangato', 'Franciso@hotmail.com','445 665 77 43 26'),
                              (7, 'Apple',  'Tomas Tinoco',		  '5 De Junio',		'Moroleaon', 'Tomas@hotmail.com',	'445 665 77 43 27'),
                              (8, 'San Disk','Alberto Sierra',	  '12 De Febrero',	'Uriangato', 'Alberto@hotmail.com', '445 665 77 43 28');
							 

INSERT INTO empleados VALUES(1, 'Ramiro',	'Alvarez',	PASSWORD('123'), 18, 'Cajero',	'20 De Agosto',	'443 123 45 76 31', 'Alvarez@hotmail.com',	0),
							(2, 'Antonio',	'Casares',	PASSWORD('123'), 23, 'Gerente',	'21 De Febrero','443 123 45 76 32', 'Antonio@hotmail.com',	0),
							(3, 'Felipa',	'Torres',	PASSWORD('123'), 44, 'Gerente',	'22 De Febrero','443 123 45 76 33', 'Felipa@hotmail.com',	1),
							(4, 'Jasmin',	'Zabala',	PASSWORD('123'), 50, 'Cajero',	'23 De Abril',	'443 123 45 76 34', 'Jasmin@hotmail.com',	0),
							(5, 'Alexis',	'Derbez',	PASSWORD('123'), 18, 'Cajero',	'24 De Enero',	'443 123 45 76 35', 'Alexis@hotmail.com',	1),
							(6, 'Joe',		'Catalan',	PASSWORD('123'), 25, 'Cajero',	'25 De Febrero','443 123 45 76 36', 'Joe@hotmail.com',		0),
							(7, 'Alex',		'Mercado',	PASSWORD('123'), 30, 'Cajero',	'26 De Mayo',	'443 123 45 76 37', 'Alex@hotmail.com',		1),
							(8, 'Melani',	'Zamudio',	PASSWORD('123'), 22, 'Cajero',	'27 De Febrero','443 123 45 76 38', 'Melani@hotmail.com',	0);


INSERT INTO clientes VALUES(1, 'Daniel Esperanza',	'01 De Enero',		'442 345 12 32 80', 'Daniel@hotmail.com'),
						   (2, 'Jorge Ezequiel',	'23 De Marzo',		'442 345 12 32 81', 'Jorge@hotmail.com'),
						   (3, 'Maria Gonzales',	'1 De Enero',		'442 345 12 32 82', 'Maria@hotmail.com'),
                           (4, 'Ortencia Jimenez',	'21 De Diciembre',	'442 345 12 32 83', 'Ortencia@hotmail.com'),  
						   (5, 'Jimena Cortez',		'17 De Enero',		'442 345 12 32 84', 'Jimena@hotmail.com'),
                           (6, 'Ingrid Azuares',	'19 De Enero',		'442 345 12 32 85', 'Ingrid@hotmail.com'),
						   (7, 'Adriana Fonceca',	'07 De Enero',		'442 345 12 32 86', 'Adriana@hotmail.com'),
						   (8, 'Hilda Perez', 		'01 De Octubre',	'442 345 12 32 87', 'Hilda@hotmail.com');


INSERT INTO categorias VALUES(1, 'Discos Duros', null),
							 (2, 'Procesadores', null),
							 (3, 'Laptos', null),
							 (4, 'Targetas Graficas', null),
							 (5, 'Ram', null),
							 (6, 'Software', null),
							 (7, 'Inalambrico', null),
							 (8, 'Redes', null);



INSERT INTO productos VALUES(1, 1, 4, 'HDD-Kingston', 1300, 78,	0),
							(2, 1, 6, 'SSD-Kingston', 2300, 100,0),
							(3, 1, 7, 'HDD-San Disk', 3300, 34,	0),				
							(4, 1, 3, 'SSD-San Disk', 4000, 12,	0),							
							(5, 2, 1, 'I7-Intel',	  1800, 78,	0),
							(6, 2, 4, 'CoreDuo-AMD',  1450, 65,	0),
							(7, 4, 8, 'Pantalla Led', 1399, 42,	0),
							(8, 3, 4, 'Bocinas', 	  270,  107,0);


INSERT INTO ordenes VALUES(1, 5, 7, now(), '1 De Marzo',		'Morelia'),
						  (2, 1, 3, now(), '1 De Octubre',		'Moroleon'),
						  (3, 7, 7, now(), 'Lazaro Cardenas',	'Yuriria'),
						  (4, 3, 8, now(), '1 De Marzo',		'Uriangato'),
						  (5, 8, 3, now(), '1 De Marzo',		'Morelia'),
						  (6, 2, 6, now(), 'Francisco I Madero','Yuriria'),
						  (7, 8, 7, now(), '1 De Marzo',		'Morelia'),
					      (8, 1, 1, now(), '12 De Octubre', '	Uriangato');


INSERT INTO detalleOrden VALUES(1, 3, 89.99, 12,0),
							   (2, 2, 45, 	33, 0),
							   (3, 5, 77,	78, 0), 
							   (4, 8, 105,	1,  0),
							   (5, 2, 200,	6,  0),
                               (6, 4, 1000, 78, 0),
                               (7, 2, 200,	100,0),
                               (8, 1, 570,	250,0);
















