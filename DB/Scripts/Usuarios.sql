USE bd_Store;

CREATE USER Andres;
CREATE USER Alberto;
CREATE USER Julia;
CREATE USER Dalia;

SET PASSWORD FOR 'Andres'  = PASSWORD('And123.');
SET PASSWORD FOR 'Alberto' = PASSWORD('Alberto');
SET PASSWORD FOR 'Julia'   = PASSWORD('Juila');
SET PASSWORD FOR 'Dalia'   = PASSWORD('Dalia');

GRANT ALL PRIVILEGES  ON *.* TO 'Andres' WITH GRANT OPTION;

GRANT SELECT, INSERT, UPDATE, DELETE ON bd_Store.Clientes		TO 'Alberto';
GRANT SELECT, INSERT, UPDATE, DELETE ON bd_Store.Proveedores	TO 'Alberto';
GRANT SELECT, INSERT, UPDATE, DELETE ON bd_Store.Empleados		TO 'Alberto';

SELECT * FROM mysql.user;

SHOW TABLES;

UPDATE empleados SET Nombre = "Andres"	WHERE EmpleadoID = 1;
UPDATE empleados SET Nombre = "Alberto" WHERE EmpleadoID = 2;
UPDATE empleados SET Nombre = "Julia"	WHERE EmpleadoID = 3;
UPDATE empleados SET Nombre = "Dalia"	WHERE EmpleadoID = 4;

SELECT * FROM empleados;

DELIMITER $$
CREATE PROCEDURE changePass(id int, pass varchar(10))
BEGIN
		UPDATE empleados SET Contrasena = pass WHERE EmpleadoID = id;
END $$
DELIMITER ;